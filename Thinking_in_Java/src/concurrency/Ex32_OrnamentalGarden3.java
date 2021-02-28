package concurrency;
import java.util.*;
import java.util.concurrent.*;

class Entrance3 implements Runnable {
	private static Count count = new Count();
	private static List<Entrance3> entrances = new ArrayList<Entrance3>();
	private int number = 0;
	private final int id;
	private final CountDownLatch latch;
	private static volatile boolean canceled = false;
	public static void cancel() { canceled = true; }
	public Entrance3(int id, CountDownLatch latch) {
		this.id = id;
		this.latch = latch;
		entrances.add(this);
	}
	public void run() {
		while(!canceled) {
			synchronized(this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		latch.countDown();  // Reduce counter in the latch when running is over 
		System.out.println("Stopping " + this);
	}
	public synchronized int getValue() { return number; }
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}
	public static int getTotalCount() {
		return count.value();
	}
	public static int sumEntrances() {
		int sum = 0;
		for(Entrance3 entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

class WaitingEntrance implements Runnable {
	private final CountDownLatch latch;
	public WaitingEntrance(CountDownLatch latch) {
		this.latch = latch;
	}
	public void run() {
		try {
			latch.await();   // Wait for all Entrance3 runs to over
			System.out.println("Total: " + Entrance3.getTotalCount());
			System.out.println("Sum of Entrances: " + Entrance3.sumEntrances());
		} catch(InterruptedException e) {
			System.out.println("WaitingEntrance interrupted");
		}
	}
}

public class Ex32_OrnamentalGarden3 {

	public static void main(String[] args) throws Exception {
		int SIZE = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for(int i = 0; i < SIZE; i++)
			exec.execute(new Entrance3(i, latch));
		exec.execute(new WaitingEntrance(latch));
		TimeUnit.SECONDS.sleep(3);
		Entrance3.cancel();
		exec.shutdown();
	}

}
