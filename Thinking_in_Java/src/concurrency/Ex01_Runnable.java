package concurrency;

class Tasks implements Runnable {
	private int timeSlices = 0;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public Tasks() {
		System.out.println("Start task " + id);
	}
	public String toString() {
		return "#" + id + " get " + timeSlices + " time CPU";
	}
	public void run() {
		while(++timeSlices <= 3) {
			System.out.println(this);
			Thread.yield();
		}
		System.out.println("Stop task " + id);
	}
}

public class Ex01_Runnable {

	public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
        	new Thread(new Tasks()).start();
	}

}
