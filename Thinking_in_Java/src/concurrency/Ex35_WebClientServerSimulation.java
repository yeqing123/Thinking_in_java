package concurrency;
import java.util.concurrent.*;
import java.util.*;

class WebClient {
	private final int serviceTime;
	public WebClient(int serviceTime) { this.serviceTime = serviceTime; }
	public String toString() { return "[" + serviceTime + "]"; }
	public int getServiceTime() { return serviceTime; }
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {
	public WebClientLine(int maxLineSize) {
		super(maxLineSize);
	}
	public String toString() {
		if(this.size() == 0)
		    return "[Empty]";	
	    StringBuilder result = new StringBuilder();
	    for(WebClient c : this)
	    	result.append(c);
	    return result.toString();
	}
}

class WebClientGenerator implements Runnable {
	volatile int loadFactor = 1;  // Start with one client/sec
	private static Random rand = new Random(47);
	private WebClientLine clients;
	public WebClientGenerator(WebClientLine cq) {
		clients = cq;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				clients.add(new WebClient(rand.nextInt(100)));
				TimeUnit.MILLISECONDS.sleep(300 / loadFactor);
			}
		} catch(InterruptedException e) {
			System.out.println("WebClientGenerator interrupted");
		}
		System.out.println("WebClientGenerator terminating");
	}
}

class Server implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private WebClientLine clients;
	public Server(WebClientLine cq) { 
		clients = cq;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				WebClient client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
			}
		} catch(InterruptedException e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}
	public String toString() { return "Server " + id + " "; }
	public String shortString() { return "S" + id; }
}

class SimulationManager implements Runnable {
	private ExecutorService exec;
	private WebClientGenerator gen;
	private WebClientLine clients;
	private Queue<Server> servers = new LinkedList<Server>();
	private int adjustmentPeriod;
	// Indicates whether the queue is stable
	private boolean stable = true;
	private int prevSize;
	public SimulationManager(ExecutorService e, WebClientGenerator gen, 
			WebClientLine clients, int adjustmentPeriod, int n) {
		exec = e;
		this.gen = gen;
		this.clients = clients;
		this.adjustmentPeriod = adjustmentPeriod;
		// Start with 'n' servers:
		for(int i = 0; i < n; i++) {
			Server server = new Server(clients);
			exec.execute(server);
			servers.add(server);
		}
	}
	public void adjustLoadFactor() {
		// This is actually a control system, By adjusting
		// the numbers, you can reveal stability issues in
		// the control mechanism.
		// If line is stable, increase the 'load factor':
		if(clients.size() > prevSize) {
			if(stable)  // Was stable previous time
			    stable = false;
			else if(!stable) {  // Not stable for a second time
				System.out.println("Peak load factor: ~" + gen.loadFactor);
				exec.shutdownNow();
			}
		} else {
			System.out.println("New load factor: " + ++gen.loadFactor);
		}
		prevSize = clients.size();
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				System.out.print(clients + " { ");
				for(Server server : servers)
					System.out.print(server.shortString() + " ");
				System.out.println("}");
				adjustLoadFactor();
			}
		} catch(InterruptedException e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}
	public String toString() { return "SimulationManager "; }
}

public class Ex35_WebClientServerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int NUM_OF_SERVERS = 5;
    static final int ADJUSTMENT_PERIOD = 1000;
	public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        WebClientLine clients = new WebClientLine(MAX_LINE_SIZE);
        WebClientGenerator gen = new WebClientGenerator(clients);
        exec.execute(gen);
        exec.execute(new SimulationManager(exec, gen, clients, 
        		ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
        if(args.length > 0)
        	TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
        	System.out.println("Press 'Enter' to quit");
        	System.in.read();
        }
        exec.shutdownNow();
	}

}
