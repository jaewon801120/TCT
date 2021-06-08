package exercise;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.ArrayList;

public class ClassLoaderTest {
	public static void main(String args[]) {
		//String path = "EXTRACTOR";
		String path = "lib";

		// load class files in directory

		ArrayList<URL> urls = new ArrayList<URL>();

		URLStreamHandler streamHandler = null;

		File classPath = new File(path);

		try {
			System.out.println("file:" + classPath.getCanonicalPath() + File.separator);
			urls.add(new URL(null, "file:" + classPath.getCanonicalPath() + File.separator, streamHandler));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// load jar files

		File[] files = classPath.listFiles();

		for (File file : files)
		{

			if (file.isFile() && file.getName().endsWith(".jar"))
			{
				try {
					System.out.println("FILE="+file.getCanonicalPath());
					URL url = new URL(null, "file:" + file.getCanonicalPath(), streamHandler);
					System.out.println("url="+url);
					urls.add(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		URLClassLoader loader = new URLClassLoader((URL[]) urls.toArray(new URL[urls.size()]));
		
		String[] classNames = {"com.lgcns.extractor.ExtractorA", "com.lgcns.extractor.ExtractorB", "com.lgcns.extractor.ExtractorC"};

		
		try {
			for(String className: classNames) {
				Class<?> clazz = loader.loadClass(className);
				Object obj = clazz.newInstance();

				Method m = clazz.getMethod("getName");
				System.out.println("getName="+ m.invoke(obj));
				
				
				Method mm = clazz.getMethod("extract", int[].class);
				int[] ans = (int[]) mm.invoke(obj, new int[] {1,2,3,4,5,6,7,8,9,10,11,12});
				System.out.println("extract:"+ ans[0] +","+ ans[1] +","+ ans[2]);				
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			loader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
