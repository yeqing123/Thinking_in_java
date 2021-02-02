package concurrency;
import java.util.concurrent.*;

public class NaiveExceptionHandling {
	public static void main(String[] args) {
        try {
        	ExecutorService exec = Executors.newCachedThreadPool();
        	exec.execute(new ExceptionThread());
        } catch(RuntimeException e) {
        	// This statement will Not execute!
        	System.out.println("Exception has been handled!");
        }
	}
}
