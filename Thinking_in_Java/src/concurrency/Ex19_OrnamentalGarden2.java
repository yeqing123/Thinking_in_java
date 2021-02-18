package concurrency;
import java.util.*;
import java.util.concurrent.*;


class Entrance2 implements Runnable {
	private static Count count = new Count();
	private static List<Entrance2> entrances = new ArrayList<Entrance2>();
	private int number = 0;
	// Doesn't need synchronization to read:
	private final int id;
	public Entrance2(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents
		// garbage collection of dead tasks:
		entrances.add(this);
	}
	public void run() {
		while(true) {
			synchronized(this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
			    TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e) {
				System.out.println("Stopping " + this);
				return;
			}
		}
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
		for(Entrance2 entrance : entrances)
			sum += entrance.getValue();
		return sum;
	}
}

public class Ex19_OrnamentalGarden2 {
	public static void main(String[] args) throws Exception {
	    ExecutorService exec = Executors.newCachedThreadPool(); 
		for(int i = 0; i < 5; i++) 
		    exec.execute(new Entrance2(i)); 
		TimeUnit.SECONDS.sleep(3); 
		exec.shutdownNow();   // 终止Executor启动的所有线程，并且不再接收新的线程
		if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) 
		    System.out.println("Some tasks were not terminated!"); 
		System.out.println("Total: " + Entrance2.getTotalCount()); 
	    System.out.println("Sum of Entrances: " + Entrance2.sumEntrances());
	}
}