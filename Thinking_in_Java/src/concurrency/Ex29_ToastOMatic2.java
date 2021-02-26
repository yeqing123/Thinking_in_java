package concurrency;
import java.util.concurrent.*;
import java.util.*;

class Toast2 {
	public enum Status {
		DRY,
		BUTTERED,
		JAMMED,
		READY {
			public String toString() {
				return BUTTERED.toString() + " & " + JAMMED.toString();
			}
		}
	}
	private Status status = Status.DRY;
	private final int id;
	public Toast2(int idn) { id = idn; }
	public void butter() {
		status = (Status.DRY == status) ? Status.BUTTERED : Status.READY;
	}
	public void jam() {
		status = (Status.DRY == status) ? Status.JAMMED : Status.READY;
	}
	public Status getStatus() { return status; }
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue2 extends LinkedBlockingQueue<Toast2> {}

class Toaster2 implements Runnable {
	private ToastQueue2 toastQueue;
	private int count;
	private Random rand = new Random(47);
	public Toaster2(ToastQueue2 tq) { toastQueue = tq; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				// Make toast
				Toast2 t = new Toast2(count++);
				System.out.println(t);
				// Insert into queue
				toastQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

// Apply butter to toast:
class Butterer implements Runnable {
	private ToastQueue2 inQueue, butteredQueue;
	public Butterer(ToastQueue2 in, ToastQueue2 buttered) {
		inQueue = in;
		butteredQueue = buttered;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast2 t = inQueue.take();
			    t.butter();
			    butteredQueue.put(t);
		    }
		} catch(InterruptedException e) {
			System.out.println("Butterer interrupted");
		}
		System.out.println("Butterer off");
	}
}

// Apply jam to toast:
class Jammer2 implements Runnable {
	private ToastQueue2 inQueue, jammedQueue;
	public Jammer2(ToastQueue2 in, ToastQueue2 jammed) {
		inQueue = in;
		jammedQueue = jammed;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast2 t = inQueue.take();
				t.jam();
				System.out.println(t);
				jammedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer off");
	}
}

// Consume the toast:
class Eater2 implements Runnable {
	private ToastQueue2 finishedQueue;
	public Eater2(ToastQueue2 finished) {
		finishedQueue = finished;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast2 t = finishedQueue.take();
				// Verify that all pieces are ready for consumption:
				if(t.getStatus() != Toast2.Status.READY) {
					System.out.println(">>>>> Error: " + t);
					System.exit(1);
				} else
					System.out.println("Chomp! " + t);
			}
		} catch(InterruptedException e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable {
	private ToastQueue2 inQueue, out1Queue, out2Queue;
	private boolean outTo2;  // control alternation
	public Alternator(ToastQueue2 in, ToastQueue2 out1, ToastQueue2 out2) {
		inQueue = in;
		out1Queue = out1;
		out2Queue = out2;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast2 t = inQueue.take();
				if(!outTo2)
					out1Queue.put(t);
				else 
					out2Queue.put(t);
				outTo2 = !outTo2;  // change state of next time
			}
		} catch(InterruptedException e) {
			System.out.println("Alternator interrupted");
		}
		System.out.println("Alternator off");
	}
}

// Accepts toasts on either channel, and relays them on to a "single" successor
class Merger implements Runnable {
	private ToastQueue2 in1Queue, in2Queue, toBeButteredQueue, 
	        toBeJammedQueue, finishedQueue;
	public Merger(ToastQueue2 in1, ToastQueue2 in2, ToastQueue2 toBeButtered, 
			ToastQueue2 toBeJammed, ToastQueue2 finished) {
		in1Queue = in1;
		in2Queue = in2;
		toBeButteredQueue = toBeButtered;
		toBeJammedQueue = toBeJammed;
		finishedQueue = finished;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast2 t = null;
				while(t == null) {
					t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
					if(t != null)
						break;
					t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
				}
				// Relay toast onto the proper queue
				switch(t.getStatus()) {
				    case BUTTERED: toBeJammedQueue.put(t);
				    break;
				    case JAMMED: toBeButteredQueue.put(t);
				    break;
				    default: finishedQueue.put(t);
				}
			}
		} catch(InterruptedException e) {
			System.out.println("Merger interrupted");
		}
		System.out.println("Merger off");
	}
}

public class Ex29_ToastOMatic2 {

	public static void main(String[] args) throws Exception {
        ToastQueue2
            dryQueue = new ToastQueue2(),
            butteredQueue = new ToastQueue2(),
            toBeButteredQueue = new ToastQueue2(),
            jammedQueue = new ToastQueue2(),
            toBeJammedQueue = new ToastQueue2(),
            finishedQueue = new ToastQueue2();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster2(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
        exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer2(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, 
        		toBeJammedQueue, finishedQueue));
        exec.execute(new Eater2(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
            
	}

}
