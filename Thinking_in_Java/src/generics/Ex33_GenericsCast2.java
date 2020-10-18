package generics;
import java.util.*;

class FullStackException extends RuntimeException {}

class FixedSizeStack2<T> {
	private int index;
	private final int size;
	private List<T> storage;
	public FixedSizeStack2(int size) {
		this.size = size;
		storage = new ArrayList<T>(size);
	}
	public void push(T item) {
		if(index < size) {
		    storage.add(item);
	        index++;
		} else {
			throw new FullStackException();
		}
	}
	public T pop() { 
		if(index > 0) {
		    return (T)storage.remove(--index); 
		} else {
			throw new EmptyStackException();
		}
	}
	public int size() {
		return index;
	}
}

public class Ex33_GenericsCast2 {
    public static final int SIZE = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        FixedSizeStack2<String> strings = new FixedSizeStack2<String>(SIZE);
        for(String s : "A B C D E F G H I J".split(" "))
        	strings.push(s);
        try {
        	strings.push("K");
        } catch(FullStackException e) {
        	System.out.println(e.toString());
        }
        for(int i = 0; i < SIZE; i++) {
        	String s = strings.pop();
        	System.out.print(s + " ");
        }
        System.out.println();
        try {
        	strings.pop();
        } catch(EmptyStackException e) {
        	System.out.println(e.toString());
        }
	}

}
