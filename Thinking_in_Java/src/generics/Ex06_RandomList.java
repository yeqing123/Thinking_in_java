package generics;

import net.mindview.util.*;

public class Ex06_RandomList<T> {
  
    public static void dump(RandomList<?> rl) {
    	for(int i = 0; i < rl.size(); i++)
    		System.out.print(rl.select() + " ");
    	System.out.println();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Generator<Integer> gi = new CountingGenerator.Integer();
		RandomList<Integer> intList = new RandomList<Integer>();
		for(int i = 0; i < 10; i++)
            intList.add(gi.next());
        dump(intList);
        RandomList<String> stringList = new RandomList<String>();
        for(String s : "The quick brown fox jumped over the lazy black dog".split(" "))
        	stringList.add(s);
        dump(stringList);
        Generator<Character> gc = new CountingGenerator.Character();
        RandomList<Character> charList = new RandomList<Character>();
        for(int i = 0; i < 10; i++)
        	charList.add(gc.next());
        dump(charList);
	}

}
