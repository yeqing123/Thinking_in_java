package concurrency;
import java.util.concurrent.*;
import java.util.*;

class Blocker {
	synchronized void waitingCall() {
		try {
			while(!Thread.interrupted()) {
				wait();
				System.out.print(Thread.currentThread() + " ");
			}
		} catch(InterruptedException e) {
			// OK to exit this way
		}
	}
	synchronized void prod() { notify(); }
	synchronized void prodAll() { notifyAll(); }
}

class TaskOne implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() { blocker.waitingCall(); }
}

class TaskTwo implements Runnable {
	// A separate Blocker object;
	static Blocker blocker = new Blocker();
	public void run() { blocker.waitingCall(); }
}

public class NotifyVsNotifyAll {

	public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        	exec.execute(new TaskOne());
        exec.execute(new TaskTwo());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	boolean prod = true;
        	public void run() {
        		if(prod) {
        			System.out.print("\nnotify() ");
        			TaskOne.blocker.prod();
        			prod = false;
        		} else {
        			System.out.print("\nnotifyAll() ");
        			TaskOne.blocker.prodAll();
        			prod = true;
        		}
        	}
        }, 400, 400);   // Run every 0.4 second
        TimeUnit.SECONDS.sleep(5);  // Run for a while...
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print("TaskTwo.blocker.prodAll() ");
        TaskTwo.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow();  // Interrupt all tasks
	}

}