package io;
import java.util.*;

public class Ex17_CountCharacters {

	public static void main(String[] args) {
        TextFile words = new TextFile("./src/io/Ex17_CountCharacters.java", "\\W+");
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(String word : words) {
        	char[] chars = word.toCharArray();
        	for(char c :chars)
        		if(map.containsKey(c)) {
        			int count = map.get(c);
        			map.put(c, count + 1);
        		} else {
        			map.put(c, 1);
        		}
        }
        System.out.println(map);
	}

}
