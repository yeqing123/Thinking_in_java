package concurrency;
import java.util.*;
import java.util.concurrent.*;

class RandomSleep implements Runnable {
	private static Random rand = new Random(47);
	private final int sleep_time = rand.nextInt(10) + 1;
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(sleep_time);
		} catch(InterruptedException e) {
			System.out.println("Interrupted: " + e);
		}
		System.out.println(sleep_time);
	}
}

public class Ex06_RandomSleep {
	public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        	exec.execute(new RandomSleep());
        Thread.yield();
        exec.shutdown();
	}
}
