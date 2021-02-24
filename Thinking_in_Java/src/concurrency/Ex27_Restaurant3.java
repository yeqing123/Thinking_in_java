package concurrency;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class WaitPerson3 implements Runnable {
	private Restaurant3 restaurant;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	public WaitPerson3(Restaurant3 r) { restaurant = r; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				lock.lock();
				try {
				    while(null == restaurant.meal)
					    condition.await();
				} finally {
					lock.unlock();
				}
				System.out.println("Waitpersn got " + restaurant.meal);
				restaurant.chef.lock.lock();
				try {
					restaurant.meal = null;
					restaurant.chef.condition.signalAll();
				} finally {
					restaurant.chef.lock.unlock();
				}
			}
		} catch(InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef3 implements Runnable {
	private Restaurant3 restaurant;
	private int count = 0;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	public Chef3(Restaurant3 r) { restaurant = r; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				lock.lock();
				try {
					while(null != restaurant.meal)
						condition.await();
				} finally {
					lock.unlock();
				}
				if(10 == ++count) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up! ");
				restaurant.waitPerson.lock.lock();
				try {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.condition.signalAll();
				} finally {
					restaurant.waitPerson.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch(InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}

class Restaurant3 {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson3 waitPerson = new WaitPerson3(this);
    Chef3 chef = new Chef3(this);
    public Restaurant3() {
    	exec.execute(chef);
    	exec.execute(waitPerson);
    }
}
public class Ex27_Restaurant3 {
 
	public static void main(String[] args) {
        new Restaurant3();
	}

}
