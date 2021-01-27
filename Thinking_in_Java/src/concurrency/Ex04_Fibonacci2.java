package concurrency;
import java.util.concurrent.*;

public class Ex04_Fibonacci2 {
	public static void execute(ExecutorService exec) {
    	for(int i = 1; i <= 5; i++)
        	exec.execute(new Fibonacci(i));
    	Thread.yield();
    	exec.shutdown();
    }
	public static void main(String[] args) {
        execute(Executors.newCachedThreadPool());
	    execute(Executors.newFixedThreadPool(2));
	    execute(Executors.newSingleThreadExecutor()); 
	}
}
