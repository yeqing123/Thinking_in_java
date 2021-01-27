package concurrency;
import java.util.Arrays;
import net.mindview.util.Generator;

class Fibonacci implements Runnable, Generator<Integer> {
	private int count = 0;
	private final int n;
	public Fibonacci(int n) {
		this.n = n;
	}
    public Integer next() { return fib(count++); }
    private Integer fib(int n) {
    	if(n < 2)
    		return 1;
    	return fib(n-2) + fib(n-1);
    }
    public void run() {
    	Integer[] sequence = new Integer[n];
    	for(int i = 0; i < n; i++)
    		sequence[i] = this.next();
        System.out.println("Seq. of " + n + ": " + Arrays.toString(sequence));
    }
}

public class Ex02_Fibonacci {
	public static void main(String[] args) {
         for(int i = 1; i <= 5; i++)
        	 new Thread(new Fibonacci(i)).start();
	}
}
