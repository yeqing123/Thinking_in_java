// {Args: 100}
package concurrency;
import java.util.*;
import java.util.concurrent.*;

public class Ex14_ManyTimers {
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.err.println("Usage: java Ex14_ManyTimers <num of timers>");
			System.exit(0);
		}
		int numOfTimers = Integer.parseInt(args[0]);
		for(int i = 0; i < numOfTimers; i++) {
			new Timer().schedule(new TimerTask() {
				public void run() {
					System.out.println(System.currentTimeMillis());
				}
			}, numOfTimers - i);
		}
		// Wait for timers to expire
		TimeUnit.MILLISECONDS.sleep(2 * numOfTimers);
		System.exit(0);
	}
}