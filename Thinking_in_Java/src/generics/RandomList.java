package generics;

import java.util.Random;

public class RandomList<T> {
    private class Node {
    	T item;
    	Node next;
    	Node() { item = null; next = null; }
    	Node(T item, Node next) {
    		this.item = item;
    		this.next = next;
    	}
    	public boolean isEnd() {
    		return item == null && next == null;
    	}
    }
    Node top = new Node();
    private Random rand = new Random();
    private int i = 0;
    public void push(T item) {
    	Node node = new Node(item, top);
    	top = node;
    	i++;
    }
    public T pop() {
    	T result = top.item;
    	if(!top.isEnd())
    		top = top.next;
    	return result;
    }
    public T select() {
    	int n = rand.;
    	T result;
    	for(int i = 1; (result = this.pop()) != null  && i != n; i++);
    	return result;
    		
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomList<String> list = new RandomList<String>();
        for(int i = 0; i < 20; i++) {
        	list.push(Integer.toString(i));
        }
        for(int i = 0; i < 20; i++)
        	System.out.println(list.select());
	}

}
