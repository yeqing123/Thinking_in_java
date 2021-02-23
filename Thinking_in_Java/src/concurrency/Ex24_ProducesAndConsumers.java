package concurrency;
import java.util.concurrent.*;
import java.util.*;

class Consumers implements Runnable {
	private Ex24_ProducesAndConsumers pc;
	public Consumers(Ex24_ProducesAndConsumers pc) { this.pc = pc; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(pc.buffer.isEmpty())
						wait();
				}
				while(!pc.buffer.isEmpty()) {
				    System.out.println("Eat " + pc.buffer.pop());
				    TimeUnit.MILLISECONDS.sleep(200);
				}
				synchronized(pc.produces) {
					pc.produces.notifyAll();
				}
			 }
		} catch(InterruptedException e) {
			System.out.println("Consumer interrupted");
		}
	}
}

class Produces implements Runnable {
	private Ex24_ProducesAndConsumers pc;
	private int count = 0;
	public Produces(Ex24_ProducesAndConsumers pc) { this.pc = pc; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(pc.buffer.isFull()) {
					    System.out.println("buffer is full!");
						wait();
					}
				}
				System.out.print("Produce a meal " + count + ", ");
				synchronized(pc.consumer) {
					pc.buffer.push(new Meal(count++));
					pc.consumer.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch(InterruptedException e) {
			System.out.println("Produces interrupted");
		}
	}
}

class Buffer {
	private final int len;
	private int count = 0;
	private Meal[] tray;
	public Buffer(int l) { 
		len = l; 
	    tray = new Meal[len];
	}
	public Meal pop() {
		if(count == 0)
			return null;
	    return tray[--count];
	}
	public boolean push(Meal meal) {
		if(count < len) {
			tray[count++] = meal;
		    return true;
		}
		return false;
	}
	public boolean isEmpty() {
		return count <= 0 ? true : false;
	}
	public boolean isFull() {
		return count >= len ? true : false;
	}
}

public class Ex24_ProducesAndConsumers {
	ExecutorService exec = Executors.newCachedThreadPool();
	Buffer buffer = new Buffer(10);
	Meal meal;
	Consumers consumer = new Consumers(this);
	Produces produces = new Produces(this);
	public Ex24_ProducesAndConsumers() throws Exception {
		exec.execute(consumer);
		exec.execute(produces);
		TimeUnit.MILLISECONDS.sleep(5000);
		exec.shutdownNow();
	}
	public static void main(String[] args) throws Exception {
        new Ex24_ProducesAndConsumers();
        
	}

}
