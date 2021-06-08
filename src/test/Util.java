package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static void main(String[] args) {

		int nSec = time2sec("2:18:51");
		String strSec = sec2time(nSec);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//Date date = new Date(0);	// 1970-01-01 09:00:00
		Date date = new Date();		// System.currentTimeMillis()

		System.out.println("now  (" + format.format(date) + ")");

		//final long daySeconds = 86400L;	// date->second
		String str = "1970-01-01 09:00:02";
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = null;
		try {
			date2 = format2.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long timeMillis = date2.getTime();
		System.out.println("time millis : " + timeMillis);
	}

	public static int time2sec(String time) {
		String[] t = time.split(":");
		int sec = Integer.parseInt(t[0]) * 60 * 60;
		sec += Integer.parseInt(t[1]) * 60;
		sec += Integer.parseInt(t[2]);
		return sec;
	}

	public static String sec2time(int sec) {
		int s = sec % 60;
		int m = (sec / 60) % 60;
		int h = sec / 60 / 60;

		return String.format("%02d:%02d:%02d", h, m, s);
	}

	public static String addSec(String time, int sec) {
		int s = time2sec(time);
		return sec2time(s + sec);
	}

	public static void fileWrite(String filepath, String[] contensts) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(filepath);
			for (String ll : contensts) {
				pw.println(ll);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
