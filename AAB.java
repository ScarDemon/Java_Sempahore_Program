import java.util.concurrent.Semaphore;


public class AAB {

	private static Semaphore semA = new Semaphore(2);
	private static Semaphore semB = new Semaphore(0);
	private static Semaphore mutex = new Semaphore(1);
	private static int countA = 0;
	public static void main (String [] args) throws Exception {
		while(true) {
			Thread a = new A();
			Thread b = new B();
			
			a.start();
			b.start(); 
			Thread.sleep(1000);
		}
	}
	
	private static class A extends Thread {
		public void run() {
			try {
				semA.acquire();
				mutex.acquire();
				} catch (InterruptedException e) {e.printStackTrace();}
				System.out.print(" A ");
				countA++;
				if (countA == 2) {
					semB.release();
				}
				mutex.release();
		}
	   }
	
	
	private static class B extends Thread {
		public void run() {
			
		
		try {
			semB.acquire();
			mutex.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(" B ");
		countA = 0;
		mutex.release();
		semA.release(2);
	} // run
	} // b 
} // ab
