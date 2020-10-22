package generics;

import java.util.*;
import generics.coffee.*;

interface Addable<T> { void add(T object) throws Exception; }

class Fill {
	public static <T> void fill(Addable<T> addable, Class<? extends T> type, int size) {
		for(int i = 0; i < size; i++)
			try {
		        addable.add(type.newInstance());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
	}
}

class Adapter {
	public static <T> Addable<T> collectionAdapter(Collection<T> c) {
		return new CollectionAdapter<T>(c);
	}
	public static <T> Addable<T> arrayAdapter(T[] tArray) {
		return new ArrayAdapter<T>(tArray);
	}	
}

class CollectionAdapter<T> implements Addable<T> {
	Collection<T> c;
	public CollectionAdapter(Collection<T> c) {
		this.c = c;
	}
	public void add(T a) {
		c.add(a);
	}
}


class ArrayAdapter<T> implements Addable<T> {
	T[] tArray;
	int index = 0;
	public ArrayAdapter(T[] tArray) {
		this.tArray = tArray;
	}
	public void add(T a) throws Exception {
		if(index < tArray.length)
			tArray[index++] = a;
		else {
			System.out.println("The array is full!");
			throw new Exception("The array is full!");
		}
	}
}


public class Ex41_AdapterTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Coffee> list = new ArrayList<Coffee>();
        Fill.fill(Adapter.collectionAdapter(list), Coffee.class, 3);
        Fill.fill(Adapter.collectionAdapter(list), Latte.class, 3);
        for(Coffee c : list)
        	System.out.println(c);
        Coffee[] array = new Coffee[5];
        Fill.fill(Adapter.arrayAdapter(array), Americano.class, array.length);
        for(Coffee c : array)
        	System.out.println(c);
	}

}
