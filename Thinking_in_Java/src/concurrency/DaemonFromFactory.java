// Using a Thread Factory to create daemons:
package concurrency;
import java.util.concurrent.*;

public class DaemonFromFactory implements Runnable {
    public void run() {
    	try {
    		while(true) {
    			TimeUnit.MILLISECONDS.sleep(100);
    			System.out.println(Thread.currentThread() + " " + this);
    		}
    	} catch(InterruptedException e) {
    		System.out.println("Interrupted");
    	}
    }
	public static void main(String[] args) throws Exception {
        ExecutorService exec = new DaemonThreadPoolExecutor();
        for(int i = 0; i < 10; i++)
        	exec.execute(new DaemonFromFactory());
        System.out.println("All daemons started:");
        TimeUnit.MILLISECONDS.sleep(500);   
	}
}
