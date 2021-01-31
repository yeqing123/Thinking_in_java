package concurrency;
import java.util.concurrent.*;
import net.mindview.util.Generator;
import java.util.*;

class FibonacciSum implements Callable<Integer>, Generator<Integer> {
    private int count = 0;
	private final int n;
    public FibonacciSum(int n) { this.n = n; }
    public Integer next() {
    	return fib(count++);
    }
    private Integer fib(int n) {
    	if(n < 2) return 1;
    	return fib(n-2) + fib(n-1);
    }
    public Integer call() {
    	int sum = 0;
    	for(int i = 0; i < n; i++)
    		sum += this.next();
    	return sum;
    }
}

public class Ex05_FibonacciSum {
	public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> result = new ArrayList<Future<Integer>>();
		for(int i = 1; i <= 5; i++)
            result.add(exec.submit(new FibonacciSum(i)));
		Thread.yield();
		exec.shutdown();
		for(Future<Integer> fi : result)
			try {
			    System.out.println(fi.get());
			} catch(InterruptedException e) {
				System.out.println(e);
			} catch(ExecutionException e) {
				System.out.println(e);
			}
	}
}
