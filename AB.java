import java.util.concurrent.Semaphore;
public class AB {

	private static Semaphore semA = new Semaphore(1);
	private static Semaphore semB = new Semaphore(0);
	
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
				} catch (InterruptedException e) {e.printStackTrace();}
				System.out.print("A");
				semB.release();
		}
	   }
	
	
	private static class B extends Thread {
		public void run() {
			
		
		try {
			semB.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(" B ");
		
		semA.release();
	} // run
	} // b 
} // ab
