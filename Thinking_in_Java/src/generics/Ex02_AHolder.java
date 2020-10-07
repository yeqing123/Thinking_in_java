package generics;

import java.util.Arrays;
import java.util.List;

class A {
	private int counter;
	private static int number = 0;
	public A() {
		counter = ++number;
	}
	public String toString() {
		return "A class of instanced " + counter;
	}
}

public class Ex02_AHolder<T> {
    private T a, b, c;
    public Ex02_AHolder(T a, T b, T c) {
    	this.a = a;
    	this.b = b;
    	this.c = c;
    }
    public List<T> getAll() {
    	return Arrays.asList(a, b, c);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Ex02_AHolder<A> holder = new Ex02_AHolder<A>(new A(), new A(), new A());
        for(A a : holder.getAll()) 
        	System.out.println(a);
	}

}
