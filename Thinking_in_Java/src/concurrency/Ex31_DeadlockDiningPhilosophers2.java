package concurrency;
import java.util.Random;
import java.util.concurrent.*;

class Chopstick2 {
	private final int id;
	private boolean taken;
	public Chopstick2(int iden) { id = iden; }
	public synchronized void take() throws InterruptedException {
		while(taken)
			wait();
		taken = true;
	}
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
	public String toString() { return "Chopstick " + id; }
}

class ChopstickQueue extends LinkedBlockingQueue<Chopstick2> {}

class ChopstickBin {
	private ChopstickQueue bin = new ChopstickQueue();
	public Chopstick2 get() throws InterruptedException {
		return bin.take();
	}
	public void put(Chopstick2 stick) throws InterruptedException {
		bin.put(stick);
	}
}

class Philosopher2 implements Runnable {
    private final int id;
    private final int ponderFactor;
    private ChopstickBin bin;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
    	if(ponderFactor == 0) return ;
    	TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }
    public Philosopher2(int iden, int ponder, ChopstickBin bin) {
    	id = iden;
    	ponderFactor = ponder;
    	this.bin = bin;
    }
    public void run() {
    	try {
    		while(!Thread.interrupted()) {
    			System.out.println(this + " thinking");
    			pause();
    			// Get one chopstick from the bin
    			Chopstick2 c1 = bin.get();
    			System.out.println(this + " has " + c1 + 
    					" wating for another chopstick from bin");
    			// Get another chopstick from bin
    			Chopstick2 c2 = bin.get();
    			System.out.println(this + " has " + c2);
    			System.out.println(this + " eating");
    			pause();
    			// Put the chopsticks back in bin.
    			bin.put(c1);
    			bin.put(c2);
    		}
    	} catch(InterruptedException e) {
    		System.out.println(this + " exiting via interrupt");
    	}
    }
    public String toString() { return "Philosopher " + id; }
}

public class Ex31_DeadlockDiningPhilosophers2 {
	public static void main(String[] args) throws Exception { 
	    if(args.length < 3) {
	    	System.err.println("usage:\n" + "java Ex31_DiningPhilosophers2 " +
	                "\"numberOfPhilosophers\" \"ponderFactor deadlock\" " +
	    			"\"timeout\"\n" + "A nonzero ponderFactor will " +
	                "generate a random sleep time during think().\n" +
	    			"If deadlock is not the string 'deadlock', " +
	                "the program will not deadlock.\n" +
	    			"A nonzero timeout will stop the program after " +
	                "that number of seconds.");
	        System.exit(1);
	    }
	    ChopstickBin bin = new ChopstickBin();
	    int size = Integer.parseInt(args[0]);
	    int ponder = Integer.parseInt(args[1]);
	    for(int i = 0; i < size; i++)
	    	bin.put(new Chopstick2(i));
	    // One additional chopstick guarantees that at least
	    // one philosopher can eat without blocking.
	    if(!args[2].equals("deadlock"))
	    	bin.put(new Chopstick2(size));
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < size; i++)
        	exec.execute(new Philosopher2(i, ponder, bin));
        if(args.length == 4)
        	TimeUnit.SECONDS.sleep(5);
        else {
        	System.out.println("Press 'Enter' to quit");
        	System.in.read();
        }
        exec.shutdownNow();
	}
}
