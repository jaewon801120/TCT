package exercise;

public class ThreadTest {
	public static void main(String args[]) {
		Runnable runnable = new ThreadAccount();
		new Thread(runnable).start();
		new Thread(runnable).start();
	}
}

class ThreadAccount implements Runnable {
	ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	Account account = new Account();

	public void run() {
		threadLocal.set((int) (Math.random() * 100));
		
		while (account.getBalance() > 0) {
			account.withdraw(100);
			System.out.println("(Thread" + threadLocal.get() + ", balance : " + account.getBalance());
		}
	}
}

class Account {
	private int balance = 1000;

	public int getBalance() {
		return balance;
	}

	public synchronized void withdraw(int money) {
		if (balance >= money) {
			try { // Thread 제어권을넘겨주기위해sleep() 사용
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			balance -= money;
		}
	}
}
