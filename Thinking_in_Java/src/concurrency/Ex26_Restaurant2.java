package concurrency;
import java.util.concurrent.*;

class WaitPerson2 implements Runnable {
	private Restaurant2 restaurant;
	boolean notified;
	public WaitPerson2(Restaurant2 r) { restaurant = r; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(null == restaurant.meal)
						wait();  // ... for the chef to produce a meal
				}
				System.out.println("Waitperson got " + restaurant.meal);
				synchronized(restaurant.busBoy) {
					restaurant.busBoy.notified = true;
					restaurant.busBoy.meal = restaurant.meal;
					restaurant.busBoy.notifyAll();  // Clean up
				}
				synchronized(restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();  // Ready for another
				}
				synchronized(this) {
					if(!notified)
						wait();  // ... for the bus boy to clean up
					notified = false;
				}
			}
		} catch(InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef2 implements Runnable {
	private Restaurant2 restaurant;
	private int count = 0;
	public Chef2(Restaurant2 r) { restaurant = r; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(null != restaurant.meal)
						wait();  // ... for the meal to be taken
				}
				if(10 == ++count) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up! ");
				synchronized(restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch(InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}

class BusBoy implements Runnable {
	private Restaurant2 restaurant;
	boolean notified;
	volatile Meal meal;
	public BusBoy(Restaurant2 r) { restaurant = r; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					if(!notified)
						wait();
					notified = false;
				}
				System.out.println("BusBoy Cleaned up " + meal);
				synchronized(restaurant.waitPerson) {
				    restaurant.waitPerson.notified = true;
				    restaurant.waitPerson.notifyAll();
				}
			}
		} catch(InterruptedException e) {
			System.out.println("BusBoy interrupted");
		}
	}
}

class Restaurant2 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson2 waitPerson = new WaitPerson2(this);
    Chef2 chef = new Chef2(this);
    BusBoy busBoy = new BusBoy(this);
    public Restaurant2() {
    	exec.execute(chef);
    	exec.execute(waitPerson);
    	exec.execute(busBoy);
    }
}

public class Ex26_Restaurant2 {
	
	public static void main(String[] args) {
        new Restaurant2();
	}

}
