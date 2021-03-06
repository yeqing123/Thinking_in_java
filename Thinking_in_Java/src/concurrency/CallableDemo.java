package concurrency;
import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
	private int id;
	public TaskWithResult(int id) { this.id = id; }
	public String call() {
		return "result of TaskWithReault " + id;
	}
}

public class CallableDemo {
	public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<Future<String>>();
        for(int i = 0; i < 10; i++)
        	result.add(exec.submit(new TaskWithResult(i)));
        for(Future<String> fs : result)
        	try {
        		// get() blocks until completion:
        		System.out.println(fs.get());
        	} catch(InterruptedException e) {
        		return ;
        	} catch(ExecutionException e) {
        		System.out.println(e);
        	} finally {
        		exec.shutdown();
        	}
	}
}
