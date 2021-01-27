package concurrency;
import java.util.concurrent.*;

public class Ex03_Runnable2 {
    public static void execute(ExecutorService exec) {
    	for(int i = 0; i < 5; i++)
        	exec.execute(new Tasks());
    	Thread.yield();
    	exec.shutdown();
    }
	public static void main(String[] args) {
        execute(Executors.newCachedThreadPool());
        execute(Executors.newFixedThreadPool(2));
        execute(Executors.newSingleThreadExecutor()); 
	}
}
