package Chapter1;

public class DeadLockDemo {
	private static String A = "A";
	private static String B = "B";
	
	public static void main(String [] argv){
		new DeadLockDemo().deadLock();
	}
	
	private void deadLock(){
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(A) {
					try {
						System.out.println("Get A lock in a thread");
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
					synchronized(B) {
						System.out.println("1");
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(B){
					System.out.println("Get B lock in another thread");
					synchronized(A) {
						System.out.println("2");
					}
				}
			}			
		});
		t1.start();
		t2.start();
	}
}
