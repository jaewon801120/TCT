package exercise;

import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileExample {

	public static void main(String[] args) throws Exception {

        Path path2 = Paths.get("D:\\Download\\jar");
        // !File.exists()
        if(Files.notExists(path2)) {
            System.out.println("폴더 생성");
            Files.createDirectories(path2);
        }
        path2 = Paths.get("D:\\Download\\jar\\123.txt");
        if(Files.notExists(path2)) {
            System.out.println("파일 생성");
            Files.createFile(path2);
        }

		Path path = Paths.get("D:\\Download\\jar\\123.txt");
		System.out.println("디렉토리 여부 : " + Files.isDirectory(path));
		System.out.println("파일 여부 : " + Files.isRegularFile(path));
		System.out.println("마지막 수정 시간 : " + Files.getLastModifiedTime(path));
		System.out.println("파일 크기 : " + Files.size(path));
		System.out.println("소유자 : " + Files.getOwner(path));
		System.out.println("숨김 파일 여부 : " + Files.isHidden(path));
		System.out.println("읽기 가능 여부 : " + Files.isReadable(path));
		System.out.println("쓰기 가능 여부 : " + Files.isWritable(path));
		System.out.println("실행 여부 : " + Files.isExecutable(path));

		// 기본 파일 시스템 정보 추출하는 객체
		FileSystem fs = FileSystems.getDefault();
		Iterable<FileStore> infolist = fs.getFileStores();
		for (FileStore info : infolist) {
			System.out.println("드라이브 이름 : " + info.name());
			System.out.println("저장 타입 : " + info.type());
			System.out.println("용량 : " + info.getTotalSpace() / (1024 * 1024 * 1024) + "GB");
			System.out.println("사용 가능한 용량 : " + info.getUsableSpace() / (1024 * 1024 * 1024) + "GB");
		}

		System.out.println("====현재 사용중인 드라이버 루트 ====");
		for (Path path1 : fs.getRootDirectories()) {
			System.out.println(path1.toString());
		}
		
		// 특정 경로의 파일 정보를 가져옴(Uri | String)         
        // 파일명
        System.out.println(path.getFileName());
        // 부모 확인
        System.out.println(path.getParent().getFileName());
        // 루트 확인
        System.out.println(path.getRoot());
        // 위에 쓴 경로들의 단계 수
        System.out.println(path.getNameCount());
        // 개별 단계 확인
        for(int i = 0; i < path.getNameCount(); i++) {
            System.out.println(i + " 단계 : " + path.getName(i));
        }
        
        //////////////////////////////////////////////////
        
		String[] s = new String[] {};

		System.out.println(s.length);

		File[] files = FileExample.listDir(new File("D:\\Download\\jar\\"));
		for (File file : files) {
			System.out.println(file.getPath());
		}

		File from = new File("D:\\Download\\jar\\123.txt");
		try {
			PrintWriter pw = new PrintWriter(from);
			pw.println("TestFile1234");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File to = new File("D:\\Download\\jar\\321.txt");
		if (from.exists())
			from.renameTo(to);

		try {
			BufferedReader br = new BufferedReader(new FileReader(to));
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		copyFile(from, new File("D:\\Download\\jar\\456.txt"));

		for (File file : FileExample.listDir(new File("D:\\Download\\jar\\"))) {
			System.out.println(file.getPath());
		}

		System.out.println("bef:" + from.exists());
		try {
			Files.deleteIfExists(from.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aft:" + from.exists());
	}

	public static File[] listDir(File dir) {
		ArrayList<File> list = new ArrayList<>();

		File[] fileList = dir.listFiles();

		for (File file : fileList) {
			if (file.isDirectory()) {
				list.add(file);
				list.addAll(Arrays.asList(listDir(file)));
			} else {
				list.add(file);
			}
		}

		return list.toArray(new File[] {});
	}

	public static void copyFile(File fromFile, File toFile) {

		try {
			FileInputStream in = new FileInputStream(fromFile);
			FileOutputStream out = new FileOutputStream(toFile);

			byte[] b = new byte[4096];
			int cnt = 0;
			while ((cnt = in.read(b)) != -1) {
				out.write(b, 0, cnt);
			}

			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}