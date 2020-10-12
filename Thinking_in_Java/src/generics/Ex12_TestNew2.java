package generics;
import net.mindview.util.*;
import java.util.*;
import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;


public class Ex12_TestNew2 {
    public static void printMap(Map<StoryCharacters, List<? extends StoryCharacters>> map, int keySize, int listSize) {
    	for(int i = 0; i < keySize; i++) {
    		StroyCharactersGenerator gen = new StroyCharactersGenerator();
    		List<StoryCharacters> list = New.list();
            for(int j = 0; j < listSize; j++)
            	list.add(gen.next());
            map.put(new StoryCharacters(), list);
    	}
    	System.out.println(map);
    }
	public static void printList(List<SixTuple<String, Double, Integer, Float, Boolean, Character>> list) {
		list.add(new SixTuple<String, Double, Integer, Float, Boolean, Character>(
				"hello world", 11.2, 15, 10.5F, true, 'c'));
		System.out.println(list);
	}
	public static void printSet(Set<Coffee> set) {
		for(Coffee c : new CoffeeGenerator(7))
        	set.add(c);
		Iterator<Coffee> iter = set.iterator();
		while(iter.hasNext())
			System.out.print(iter.next() + " ");
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printMap(New.<StoryCharacters, List<? extends StoryCharacters>>map(), 3, 7);
		printSet(New.<Coffee>set());
		printList(New.<SixTuple<String, Double, Integer, Float, Boolean, Character>>list());
	}

}
