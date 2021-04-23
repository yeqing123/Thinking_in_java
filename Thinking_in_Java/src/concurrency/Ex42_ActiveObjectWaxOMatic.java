package concurrency;
import java.util.concurrent.*;

class ActiveCar {
	private ExecutorService ex = Executors.newSingleThreadExecutor();
	private enum Action { WAX, BUFF }
	private Action lastAction = Action.BUFF;
	private boolean waxOn;
	public void wax() {
		try {
			ex.execute(waxingTask);
		} catch(RejectedExecutionException e) {}
	}
	public void buff() {
		try {
			ex.execute(buffingTask);
		} catch(RejectedExecutionException e) {}
	}
	public void shutdown() { ex.shutdown(); }
	private static void pause(int sleep_time) {
		try {
			TimeUnit.MILLISECONDS.sleep(sleep_time);
		} catch(InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}
	private class WaxingTask implements Runnable {
		public void run() {
			if(lastAction != Action.WAX) {
				System.out.print("Wax On! ");
				pause(200);
				waxOn = true;
				lastAction = Action.WAX;
			}
		}
	}
	private final WaxingTask waxingTask = new WaxingTask();
	private class BuffingTask implements Runnable {
		public void run() {
			if(lastAction != Action.BUFF) {
				System.out.print("Wax Off! ");
				pause(200);
				waxOn = false;
				lastAction = Action.BUFF;
			}
		}
	}
	private final BuffingTask buffingTask = new BuffingTask();
}

class WaxCar implements Runnable {
	private final ActiveCar car;
	public WaxCar(ActiveCar c) { car = c; }
	public void run() { car.wax(); }
}

class BuffCar implements Runnable {
	private final ActiveCar car;
	public BuffCar(ActiveCar c) { car = c; }
	public void run() { car.buff(); }
}

public class Ex42_ActiveObjectWaxOMatic {
	public static void main(String[] args) throws Exception {
		ActiveCar car = new ActiveCar();
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
		exec.scheduleAtFixedRate(new BuffCar(car), 0, 200, TimeUnit.MILLISECONDS);
		exec.scheduleAtFixedRate(new WaxCar(car), 0, 200, TimeUnit.MILLISECONDS);
		TimeUnit.SECONDS.sleep(5);  // Run for a while...
		exec.shutdownNow(); // Interrupt all tasks
		car.shutdown();
	}
}