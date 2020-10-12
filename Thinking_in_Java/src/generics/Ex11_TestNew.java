package generics;
import net.mindview.util.*;
import java.util.*;
import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;


class MyMap<K, E>  {
	private Map<K, E> map;
	public MyMap() {
		map = New.map();
	}
	public void put(K key, E list) {
		this.map.put(key, list);
	}
	public String toString() {
		return this.map.toString();
	}
}


class MySet<T> {
	private Set<T> coffees;
	public MySet() {
		coffees = New.set();
	}
	public void add(T e) {
		coffees.add(e);
	}
	public Iterator<T> Iterator() {
		return coffees.iterator();
	}
	public String toString() {
		return coffees.toString();
	}
}

public class Ex11_TestNew {
	private static MyMap<StoryCharacters, List<? extends StoryCharacters>> map;
	private static MySet<Coffee> cset;
    public static void initMyMap(int keySize, int listSize) {
    	map = new MyMap<StoryCharacters, List<? extends StoryCharacters>>();
    	for(int i = 0; i < keySize; i++) {
    		StroyCharactersGenerator gen = new StroyCharactersGenerator();
    		List<StoryCharacters> list = New.list();
            for(int j = 0; j < listSize; j++)
            	list.add(gen.next());
            map.put(new StoryCharacters(), list);
    	}
    }
	
	public static void initCoffeeSet() {
		cset = new MySet<Coffee>();
		for(Coffee c : new CoffeeGenerator(7))
        	cset.add(c);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initMyMap(3, 7);
		initCoffeeSet();
		List<SixTuple<String, Double, Integer, Float, Boolean, Character>> list = New.list();
		list.add(new SixTuple<String, Double, Integer, Float, Boolean, Character>(
				"hello world", 11.2, 15, 10.5F, true, 'c'));
        System.out.println(map);
        System.out.println(list);
        Iterator<Coffee> iter = cset.Iterator();
        while(iter.hasNext())
        	System.out.print(iter.next() + " ");
	}

}
