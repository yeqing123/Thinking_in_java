package concurrency;
import java.io.*;
import java.util.concurrent.*;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch(InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
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

public class TestBlockingQueues {
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
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i = 0; i < 5; i++)
			runner.add(new LiftOff(5));
		getKey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");
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