package concurrency;
import java.util.concurrent.*;

public class SingleThreadExeuctor {

	public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++)
        	exec.execute(new LiftOff());
        exec.shutdown();
	}

}
