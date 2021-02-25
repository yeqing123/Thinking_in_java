package concurrency;
import java.util.concurrent.*;
import java.io.*;

class LiftOffRunner2 implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner2(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	public void add(LiftOff lo) throws InterruptedException {
		rockets.put(lo);
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch(InterruptedException e) {
			System.out.println("Waiting from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}
}

class LiftOffProducer implements Runnable {
	private LiftOffRunner2 runner;
	public LiftOffProducer(LiftOffRunner2 runner) {
		this.runner = runner;
	}
	public void run() {
		try {
			for(int i = 0; i < 5; i++)
				runner.add(new LiftOff(5));
	    } catch(InterruptedException e) {
	    	System.out.println("Waiting from put()");
	    }
		System.out.println("Exiting LiftOffProducer");
	}
}

public class Ex28_TestBlockingQueue2 {
    static void getKey() {
    	try {
    		// Compensate for Windows/Linux difference in the
    		// length of the result produced by the Enter key:
    		new BufferedReader(new InputStreamReader(System.in)).readLine();
    	} catch(IOException e) {
    		throw new RuntimeException();
    	}
    }
    static void getKey(String message) {
    	System.out.println(message);
    	getKey();
    }
    static void test(String msg, BlockingQueue<LiftOff> queue) {
    	System.out.println(msg);
    	ExecutorService exec = Executors.newCachedThreadPool();
    	LiftOffRunner2 runner = new LiftOffRunner2(queue);
    	exec.execute(runner);
    	exec.execute(new LiftOffProducer(runner));
    	getKey("Press 'Enter' (" + msg + ")");
    	exec.shutdownNow();
    	System.out.println("Finished " + msg + " test!");
    }
	public static void main(String[] args) {
		test("LinkedBlockingQueue",  // Unlimited size
				new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue",   // Fixed size
				new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue",     // Size of 1
				new SynchronousQueue<LiftOff>());
	}

}
