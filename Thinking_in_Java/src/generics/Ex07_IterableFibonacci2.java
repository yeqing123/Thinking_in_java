package generics;

import java.util.Iterator;

import net.mindview.util.Generator;

public class Ex07_IterableFibonacci2 implements Iterable<Integer> {
	private int count;
	private Fibonacci fibonacci = new Fibonacci();
    public Ex07_IterableFibonacci2(int n) {
    	this.count = n;
    }
    class Fibonacci implements Generator<Integer> {
    	private int n = 0;
    	public Integer next() {
    		return fib(n++);
    	}
    	private Integer fib(int n) {
    		if(n < 2) return 1;
    		return fib(n-2) + fib(n-1);
    	}
    }
    public Iterator<Integer> iterator() {
    	return new Iterator<Integer>() {
    		public boolean hasNext() {
    	    	return count > 0;
    	    }
    	    public Integer next() {
    	    	count--;
    	    	return fibonacci.next();
    	    }
    	};
    }
    public void remove() {  // Not implemented
    	throw new UnsupportedOperationException();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for(int i : new Ex07_IterableFibonacci2(18))
        	System.out.print(i + " ");
	}

}
