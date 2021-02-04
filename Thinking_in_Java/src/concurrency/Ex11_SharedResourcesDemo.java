package concurrency;
import java.util.concurrent.*;

class SharedResources {
	private char c = 'a';
	private static int n = 0;
	public synchronized void test() {
		n++;
		for(int i = 0; i < n; i++)
			c++;
		System.out.println("n = " + n + ", c: " + c);
	}
}

class SharedThread implements Runnable {
	private SharedResources sr;
	public SharedThread() { this.sr = new SharedResources(); }
	public void run() {
		sr.test();
	}
}

public class Ex11_SharedResourcesDemo {

	public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        	exec.execute(new SharedThread());
	}

}
