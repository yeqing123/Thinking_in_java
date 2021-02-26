package concurrency;
import java.util.*;
import java.util.concurrent.*;

class CharQueue extends LinkedBlockingQueue<Character> {}

class Sender2 implements Runnable {
	private CharQueue out = new CharQueue();
	private Random rand = new Random(47);
	public CharQueue getQueue() { return out; }
	public void run() {
		try {
			while(true) 
				for(char c = 'A'; c <= 'z'; c++) {
					out.put(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
		} catch(InterruptedException e) {
			System.out.println(e + " Sender sleep interrupted");
		}
	}
}

class Receiver2 implements Runnable {
	private CharQueue in;
	public Receiver2(CharQueue queue) {
		this.in = queue;
	}
	public void run() {
		try {
			while(true) {
			    // Blocks until characters are there:
				System.out.print("Read: " + in.take() + ", ");
			}
		} catch(InterruptedException e) {
			System.out.println(e + " Receiver is interrupted");
		}
	}
}

public class Ex30_PipedIO2 {
	public static void main(String[] args) throws Exception {
        Sender2 sender = new Sender2();
        Receiver2 receiver = new Receiver2(sender.getQueue());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
	}
}
