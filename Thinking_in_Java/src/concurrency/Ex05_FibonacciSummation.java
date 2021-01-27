package concurrency;
import java.util.concurrent.*;
import net.mindview.util.Generator;
import java.util.*;

class FibonacciSummation implements Callable<Integer>, Generator<Integer> {
	private Integer total = 0;
    private int count = 0;
	private final int n;
    public FibonacciSummation(int n) { this.n = n; }
    public Integer next() {
    	return fib(count++);
    }
    private Integer fib(int n) {
    	if(n < 2) return 1;
    	return fib(n-2) + fib(n-1);
    }
    public Integer call() {
    	ArrayList<Integer> fibs = new ArrayList<Integer>();
    	while(count <= n) {
    	    Integer fib = this.next();
    		fibs.add(fib);
    		total += fib;
    	}
    	System.out.println("Seq. of " + n + ":" + fibs);
    	
    	return total;
    }
}

public class Ex05_FibonacciSummation {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> result = new ArrayList<Future<Integer>>();
		for(int i = 0; i < 5; i++)
            result.add(exec.submit(new FibonacciSummation(i)));
		for(Future<Integer> fs : result)
			System.out.println(fs.get());
	}

}
