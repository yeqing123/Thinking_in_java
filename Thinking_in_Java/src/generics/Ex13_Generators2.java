package generics;

import java.util.*;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.*;


public class Ex13_Generators2 {
    public static <T> LinkedList<T> fill(
    		LinkedList<T> list, Generator<T> gen, int n) {
    	for(int i = 0; i < n; i++)
    		list.add(gen.next());
    	return list;
    }
    public static <T> Queue<T> fill(
    		Queue<T> queue, Generator<T> gen, int n) {
    	for(int i = 0; i < n; i++)
    		queue.add(gen.next());
    	return queue;
    }
    public static <T> Set<T> fill(
    		Set<T> set, Generator<T> gen, int n) {
    	for(int i = 0; i < n; i++)
    		set.add(gen.next());
    	return set;
    }
    
    public static <B> void display(B coll) {
    	System.out.println(coll.getClass().getName());
    	System.out.println(coll);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        LinkedList<Integer> list = fill(new LinkedList<Integer>(), new CountingGenerator.Integer(), 6);
		Set<Coffee> set = fill(new HashSet<Coffee>(), new CoffeeGenerator(), 7);
		Queue<String> queue = fill((Queue<String>)new LinkedList<String>(), new CountingGenerator.String(), 7);
		display(list);
		display(set);
		display(queue);
	}

}
