package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

	public static Socket mSocket;

	public static BufferedReader mIn;
	public static PrintWriter mOut;

	public static void main(String[] args) {
		try {
			String ip = "localhost";
			mSocket = new Socket(ip, 9876);
			System.out.println(ip + " �����");

			// ��� �ձ�
			mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			mOut = new PrintWriter(mSocket.getOutputStream());

			// �޼��� ����
			mOut.println("ABCDFILE.TXT");
			mOut.flush();

			// ���� ���
			System.out.println(mIn.readLine());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			// ���� �ݱ� (���� ����)
			try {
				mSocket.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
