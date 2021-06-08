package exercise;

import java.io.*;

public class FileIO {

	public static void main(String[] args) {
		
		try
		{
			readAll("");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	// �ؽ�Ʈ ���� �� ���� ��� �б� (Utility Ÿ��)
	public static String readAll(String filePath) throws IOException {
		 
	    StringBuilder  stringBuilder;
	    FileReader     fileReader     = null;
	    BufferedReader bufferedReader = null;
	    try {
	        stringBuilder  = new StringBuilder();
	        fileReader     = new FileReader(filePath);
	        bufferedReader = new BufferedReader(fileReader);
	        String line;
	        while ((line = bufferedReader.readLine()) != null)
	            stringBuilder.append(line).append('\n');
	         
	    } finally {
	        if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
	        if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
	    }
	     
	    return stringBuilder.toString();
	}
	
	// �ؽ�Ʈ ���� ���� ������ �о ó��
	public static void readLine(String filePath) throws IOException {
		 
		FileReader     fileReader     = null;
		BufferedReader bufferedReader = null;
		try {
		    fileReader     = new FileReader(filePath);
		    bufferedReader = new BufferedReader(fileReader);
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        //[[line �� ó��]]
		    }
		     
		} finally {
		    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
		    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
		}
	}
	
	// �ؽ�Ʈ ���� ���� (Utility Ÿ��)
	public static void write(String filePath, String content) throws IOException {
		 
	    FileWriter     fileWriter     = null;
	    BufferedWriter bufferedWriter = null;
	    try {
	        fileWriter     = new FileWriter(filePath);
	        bufferedWriter = new BufferedWriter(fileWriter);
	        bufferedWriter.write(content);
	 
	    } finally {
	        if (bufferedWriter != null) try { bufferedWriter.close(); } catch (Exception ex) { /* Do Nothing */ }
	        if (fileWriter     != null) try { fileWriter    .close(); } catch (Exception ex) { /* Do Nothing */ }
	    }
	}
	
	// ���� �� ���� ����Ʈ ó��
	public static void folderRead() {

		File folder = new File(".");
		for (File filex: folder.listFiles()) {
		    String fileName     = filex.getName();
		    String absolutePath = filex.getAbsolutePath();
		}
		
	}
	
	// ������ Ư�� ����Ʈ���� Ư�� ���̸�ŭ �б�
	public static byte[] readBytes(String filePath, int offset, int length) throws IOException {
		 
	    RandomAccessFile randomFile = null;
	    try {
	        randomFile = new RandomAccessFile(filePath, "r"); 
	        randomFile.seek(offset);
	         
	        byte[] dataBytes = new byte[length];
	        randomFile.readFully(dataBytes);
	         
	        return dataBytes;
	         
	    } finally {
	        if (randomFile != null) try { randomFile.close(); } catch (Exception ex) { /* Do Nothing */ }
	    }
	}
	
}
