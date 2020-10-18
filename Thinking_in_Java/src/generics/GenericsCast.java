package generics;

class FixedSizeStack<T> {
	private int index = 0; 
	private Object[] storage;
	public FixedSizeStack(int size) {
		storage = new Object[size];
	}
	public void push(T item) { storage[index++] = item; }
	@SuppressWarnings("unchecked")
	public T pop() { return (T)storage[--index]; }
}

public class GenericsCast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        FixedSizeStack<String> strings = 
        		new FixedSizeStack<String>(1);
        strings.push("A");
        System.out.println(strings.pop());
        try {
        	strings.pop();
        } catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println(e.toString());
        }
        strings = 
        		new FixedSizeStack<String>(1);
        strings.push("B");
        try {
        	strings.push("B");
        } catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println(e.toString());
        }
	}

}
