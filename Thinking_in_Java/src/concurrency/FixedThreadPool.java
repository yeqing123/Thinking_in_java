package concurrency;
import java.util.concurrent.*;

public class FixedThreadPool {

	public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 5; i++)
        	exec.execute(new LiftOff());
        exec.shutdown();
	}

}
