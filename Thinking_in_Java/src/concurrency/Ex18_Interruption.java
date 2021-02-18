package concurrency;
import java.util.concurrent.*;

class NonTask {
	public static void longMethod() throws InterruptedException {
		System.out.println("Start sleeping");
		TimeUnit.SECONDS.sleep(60);
		System.out.println("Wake up");  // This step will never be run.
	}
}

class Task implements Runnable {
	public void run() {
		try {
		    NonTask.longMethod();
		} catch(InterruptedException ie) {
			System.out.println(ie.toString());
		} finally {
			// Any cleanup code here...
		}
	}
}

public class Ex18_Interruption {
	public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Task());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
	}
}
