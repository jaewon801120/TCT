package exercise;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileExample {

	// ���ϻ���
	// �����̵�,�̸��ٲٱ�
	// ���Ͻð� ��������

	public static void main(String[] args) throws Exception {
		
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