package generics;

import java.util.Iterator;

import net.mindview.util.Generator;

public class Ex07_IterableFibonacci2 implements Iterable<Integer> {
	private int count;
    private class Fibonacci implements Generator<Integer> {
    	private int n = 0;
    	public Integer next() {
    		return fib(n++);
    	}
    	private Integer fib(int n) {
    		if(n < 2) return 1;
    		return fib(n-2) + fib(n-1);
    	}
    }
    public Ex07_IterableFibonacci2(int n) {
    	this.count = n;
    }
    public boolean hasNext() {
    	return count > 0;
    }
    public Integer next() {
    	count--;
    	return this.Fibonacci.next();
    }
    public Iterator<Integer> iterator() {
    	return new Ex07_IterableFibonacci2.this.Fibonacci();
    }
    public void remove() {  // Not implemented
    	throw new RuntimeException();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
