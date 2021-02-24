package concurrency.waxomatic2;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;

class Car {
	boolean waxOn = false;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	public void waxed() {
		lock.lock();
		try {
			waxOn = true;  // Ready to buff
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;  // Ready for another coat of wax
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
		    while(false == waxOn)
			    condition.await();
		} finally {
			lock.unlock();
		}
	}
	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while(true == waxOn) 
				condition.await();
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car c) { car = c; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.print("Wax on! ");
				car.waxed();
				TimeUnit.MILLISECONDS.sleep(200);
				car.waitForBuffing();
			}
		} catch(InterruptedException e) {
			System.out.println("WaxOn interrupted.");
		}
		System.out.println("Ending Wax on task.");
	}
}

class Buff implements Runnable {
	private Car car;
	public Buff(Car c) { car = c; }
	public void run( ) {
		try {
			while(!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.print("Buff! ");
				car.buffed();
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch(InterruptedException e) {
			System.out.println("Buff interrupted.");
		}
		System.out.println("Ending Buffing task.");
	}
}

public class WaxOMatic2 {
    public static void main(String[] args) throws Exception {
    	Car car = new Car();
    	ExecutorService exec = Executors.newCachedThreadPool();
    	exec.execute(new WaxOn(car));
    	exec.execute(new Buff(car));
    	TimeUnit.SECONDS.sleep(3);
    	exec.shutdownNow();
    }
}
