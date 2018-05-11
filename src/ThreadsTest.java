public class ThreadsTest implements Runnable{
	
	public Thread t;
	public String threadName;

	public ThreadsTest(String name){
		threadName = name;
	}

	public void run(){
		for(int i = 0; i < 100; i++){
			System.out.println(threadName + " Running " + i);
		}
	}

	public void start(){
		System.out.println("Starting: " + threadName);
		t = new Thread(this);
		t.start();
	}

	public static void main(String[] args){
		ThreadsTest t1 = new ThreadsTest("Thread 1");
		t1.start();
		ThreadsTest t2 = new ThreadsTest("Thread 2");
		t2.start();
	}
}