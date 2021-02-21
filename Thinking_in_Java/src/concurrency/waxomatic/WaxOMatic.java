// Basic task cooperation.
package concurrency.waxomatic;
import java.util.concurrent.*;

class Car {
	private boolean waxOn = false;
	public synchronized void waxed() {  
		waxOn = true;  // Ready to buff
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;  // Ready for another coat of wax
		notifyAll();
	}
	public synchronized void waitForWaxing() throws InterruptedException {
		while(false == waxOn)
			wait();
	}
	public synchronized void waitForBuffing() throws InterruptedException {
		while(true == waxOn)
			wait();
	}
	public synchronized boolean getState() { return waxOn; }
}

class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car c) { car = c; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.print("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();   
			}
		} catch(InterruptedException e) {
		    System.out.println("WaxOn exiting via interrupt");
		}	
		System.out.println("Ending Wax On task");
	}
}

class Buff implements Runnable {
	private Car car;
	public Buff(Car c) { car = c; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				car.waitForWaxing();   
				System.out.print("Buff! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch(InterruptedException e) {
			System.out.println("Buff exiting via interrupt");
		}
		System.out.println("Ending Buff task");
	}
}

public class WaxOMatic {

	public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOn(car));
        exec.execute(new Buff(car));
        TimeUnit.SECONDS.sleep(5);   // Run for a while...
        exec.shutdownNow();         // Interrupt all tasks
	}

}
