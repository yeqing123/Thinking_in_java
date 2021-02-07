// {Args: 4}
package concurrency;
import java.util.concurrent.*;

class SerialNumberGenerator2 {
	private static int serialNumber;
	public synchronized static int nextSerialNumber() {
		return serialNumber++;
	}
}

public class Ex13_SerialNumberChecker2 {
    private static final int SIZE = 10;
    private static CircularSet serials = 
    		new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
    	public void run() {
    		int serial = SerialNumberGenerator2.nextSerialNumber();
    		if(serials.contains(serial)) {
    			System.out.println("Duplicate: " + serial);
    			System.exit(0);
    		}
    		serials.add(serial);
    	}
    }
	public static void main(String[] args) throws Exception {
        for(int i = 0; i < SIZE; i++)
        	exec.execute(new SerialChecker());
        if(args.length > 0) {
        	TimeUnit.SECONDS.sleep(new Integer(args[0]));
        	System.out.println("No duplicates detected");
        	System.exit(0);
        } else {
        	System.err.println("Provide a sleep time in sec.");
        	System.exit(1);
        }
	}

}
