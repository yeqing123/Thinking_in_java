package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomList<T> {
	private static Random rand = new Random(47);
	private List<T> storage = new ArrayList<T>();
    public void add(T e) {
    	storage.add(e);
    }
    public void addAll(T... digits) {
    	for(T e : digits)
    		storage.add(e);
    }
    public T select() {
    	return storage.get(rand.nextInt(storage.size()));
    }
    public int size() {
    	return storage.size();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomList<String> list = new RandomList<String>();
        for(int i = 0; i < 10; i++) {
        	list.add(Integer.toString(i));
        }
        for(int i = 0; i < 10; i++)
        	System.out.println(list.select());
	}

}
