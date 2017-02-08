package Chapter1;

public class ConcurrentTest {
	private static final long count = 10000000000l;
	
	public static void main(String [] argv) throws InterruptedException{
		concurrency();
		serial();
	}
	
	private static void concurrency() throws InterruptedException{
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				int a = 0;
				for (long i=0; i<count;i++){
					a+=5;
				}
			}
		});
		thread.start();
		int b = 0;
		for (long i = 0; i < count ; i++){
			b--;
		}
		thread.join();
		long time = System.currentTimeMillis() - start;
		System.out.println("Currency:"+time+" ms,b="+b);
	}
	private static void serial(){
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i=0;i<count;i++){
			a += 5;
		}
		int b=0;
		for (long i = 0;i<count;i++){
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("serial:" + time + " ms,b=" + b);
	}
}
