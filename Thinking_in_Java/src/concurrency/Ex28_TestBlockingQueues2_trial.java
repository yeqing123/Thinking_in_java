package concurrency;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

class LiftOffRunner_trial implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner_trial(BlockingQueue<LiftOff> queue) {
		rockets = queue;
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

class AddingLiftOff implements Runnable {
	private BlockingQueue<LiftOff> queue;
	private String msg;
	private final int count;
	public AddingLiftOff(String msg, BlockingQueue<LiftOff> queue, int count) {
		this.queue = queue;
		this.count = count;
		this.msg = msg;
	}
	public void run() {
		for(int i = 0; i < count; i++) {
			try {
			    queue.put(new LiftOff(5));
			} catch(InterruptedException e) {
				System.out.println("Interrupted during put()");
			}
		}
		System.out.println("Finished add LiftOff object on " + msg);
	}
}

public class Ex28_TestBlockingQueues2_trial {
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
		LiftOffRunner_trial runner = new LiftOffRunner_trial(queue);
		Thread t = new Thread(runner);
		t.start();
		AddingLiftOff addingLiftOff = new AddingLiftOff(msg, queue, 5);
		new Thread(addingLiftOff).start();
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
