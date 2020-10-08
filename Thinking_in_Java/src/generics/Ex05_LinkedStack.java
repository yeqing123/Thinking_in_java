// A stack implemented with an internal structure.
package generics;

public class Ex05_LinkedStack<T> {
    private class Node {
    	T item;
    	Node next;
    	Node() {
    		this.item = null;
    		this.next = null;
    	}
    	Node(T item, Node next) {
    		this.item = item;
    		this.next = next;
    	}
    	@SuppressWarnings("unused")
		public boolean isEnd() {
    		return next == null && item == null;
    	}
    }
    private Node top = new Node(); // End sentinel
    public void push(T item) {
    	Node node = new Node(item, top);
    	top = node;
    }
    public T pop() {
    	T result = top.item;
    	if(!top.isEnd()) {
    	    top = top.next;
    	} 
    	return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Ex05_LinkedStack<String> stack = new Ex05_LinkedStack<String>();
        for(String s : "Phasers on stun!".split(" ")) {
        	stack.push(s);
        }
        String s;
        while((s = stack.pop()) != null) {
        	System.out.println(s);
        }
	}

}
