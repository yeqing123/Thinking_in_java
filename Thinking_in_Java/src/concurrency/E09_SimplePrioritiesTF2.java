package concurrency;

import java.util.concurrent.*;

class SimplePriorities2 implements Runnable {
	private int countDown = 5;
	private volatile double d; // No optimization

	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}

	public void run() {
		for (;;) {
			// An expensive, interruptable operation:
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}
}

class PriorityThreadFactory2 implements ThreadFactory {
	private final int priority;

	PriorityThreadFactory2(int priority) {
		this.priority = priority;
	}

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(priority);
		return t;
	}
}

public class E09_SimplePrioritiesTF2 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new PriorityThreadFactory2(Thread.MIN_PRIORITY));
		for (int i = 0; i < 5; i++)
			exec.execute(new SimplePriorities2());
		Thread.yield();
		exec.shutdown();
		exec = Executors.newCachedThreadPool(new PriorityThreadFactory2(Thread.MAX_PRIORITY));
		exec.execute(new SimplePriorities2());
		Thread.yield();
		exec.shutdown();
	}
}