package concurrency;
import java.util.concurrent.*;
import java.util.*;

class Count2 {
	private int count = 0;
	private Random rand = new Random(47);
	// Remove the synchronized keyword to see counting fail:
	public synchronized int increment() {
		int temp = count;
		if(rand.nextBoolean())  // Yield half the time
			Thread.yield();
		return (count = ++temp);
	}
	public synchronized int value() { return count; }
}

class Sensor implements Runnable {
	private static Random rand = new Random(47);
	private static Count2 count = new Count2();
	private static List<Sensor> sensors = new ArrayList<Sensor>();
	private int number;
	private final int id;
	private static volatile boolean canceled = false;
	public static void cancel() { canceled = true; }
	public Sensor(int id) {
		this.id = id;
		sensors.add(this);
	}
	public void run() {
		while(!canceled) {
			// Simulate a random occurence of an ionizing event
			if(rand.nextInt(5) == 0) {
				synchronized(this) { ++number; }
				count.increment();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
	}
	public synchronized int getValue() { return number; }
	public String toString() {
		return "Sensor " + id + ": " + getValue();
	}
	public static int getTotalCount() {
		return count.value();
	}
	public static int sumSensors() {
		int sum = 0;
		for(Sensor sensor : sensors)
			sum += sensor.getValue();
		return sum;
	}
}

public class Ex17_RadiationCounter {

	public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        	exec.execute(new Sensor(i));
        TimeUnit.SECONDS.sleep(3);
        Sensor.cancel();
        exec.shutdown();
        if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
        	System.out.println("Some tasks were not terminated!");
        System.out.println("Total: " + Sensor.getTotalCount());
        System.out.println("Sum of Sensors: " + Sensor.sumSensors());
	}

}
