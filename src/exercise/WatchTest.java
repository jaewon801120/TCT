//파일 변경 감지
//apache commons configuration2 오픈 소스 라이브러리로 감지 가능
//File클래스의 lastModified(); 메소드로 수정된 날짜를 받아와서 변경 감지하는 기능
//Spring Cloud Config로 config server를 만들고 github에 있는 파일이 변경되면 client들에게 변경된 값을 가져갈 수 있게 하는 기능 
//Spring의 ReloadableResourceBundleMessageSource 클래스 사용해서 감지하는 기능
//Java API WatchService로 변경된 파일 감지하는 기능

package exercise;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchTest {

	public static void main(String[] args) {

		String strPath = "D:\\Download\\jar";

		try {
			runWatch(strPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(strPath);
	}

	public static void runWatch(String strPath) throws IOException {

		// watchService 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 경로 생성
		Path path = Paths.get(strPath);
		// 해당 디렉토리 경로에 와치서비스와 이벤트 등록
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);

		Thread thread = new Thread(() -> {
			while (true) {
				WatchKey watchKey = null;
				try {
					watchKey = watchService.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				List<WatchEvent<?>> events = watchKey.pollEvents();
				for (WatchEvent<?> event : events) {
					// 이벤트 종류
					Kind<?> kind = event.kind();
					// 경로
					Path paths = (Path) event.context();
					System.out.println(paths.toAbsolutePath());
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						System.out.println("created something in directory");
					} else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
						System.out.println("delete something in directory");
					} else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
						System.out.println("modified something in directory");
					} else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
						System.out.println("overflow");
					} else {
						System.out.println("hello world");
					}
				}

				if (!watchKey.reset()) {
					try {
						watchService.close();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();

	}

}
