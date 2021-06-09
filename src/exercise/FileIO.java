package exercise;

import java.io.*;

public class FileIO {

	public static void main(String[] args) {
		
		try
		{
			write("D:/Download/jar/654.txt", "TestFile1234\r\nTestFile5678\r\nTestFile91011");
			
			String fileData = readAll("D:/Download/jar/654.txt");
			System.out.println(fileData);
			
			readLine("D:/Download/jar/654.txt");
			
			byte[] byteData = readBytes("D:/Download/jar/654.txt", 0, fileData.length());
			System.out.println(new String(byteData));
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

	public static String readAll(String filePath) throws IOException {
		 
	    StringBuilder  stringBuilder;
	    FileReader     fileReader     = null;
	    BufferedReader bufferedReader = null;
	    try {
	        stringBuilder  = new StringBuilder();
	        fileReader     = new FileReader(filePath);
	        bufferedReader = new BufferedReader(fileReader);
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            stringBuilder.append(line).append('\n');
	        }
	         
	    } finally {
	        if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
	        if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
	    }
	     
	    return stringBuilder.toString();
	}
	
	public static void readLine(String filePath) throws IOException {
		 
		FileReader     fileReader     = null;
		BufferedReader bufferedReader = null;
		try {
		    fileReader     = new FileReader(filePath);
		    bufferedReader = new BufferedReader(fileReader);
		    String line;
		    int nLine = 0;
		    while ((line = bufferedReader.readLine()) != null) {
		    	nLine++;
		    	System.out.println("line " + nLine + " : " + line);
		    }
		     
		} finally {
		    if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
		    if (fileReader     != null) try { fileReader    .close(); } catch (Exception ex) { /* Do Nothing */ }
		}
	}
	
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
	
	public static void folderRead() {

		File folder = new File(".");
		for (File filex: folder.listFiles()) {
		    String fileName     = filex.getName();
		    String absolutePath = filex.getAbsolutePath();
		}
		
	}
	
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
