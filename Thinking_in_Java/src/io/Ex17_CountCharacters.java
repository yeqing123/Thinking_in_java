package io;
import java.util.*;

public class Ex17_CountCharacters {

	public static void main(String[] args) {
		// 获取文件中的所有单词
        TextFile words = new TextFile("./src/io/Ex17_CountCharacters.java", "\\W+");
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(String word : words) {
        	char[] chars = word.toCharArray();
        	for(char key :chars) {
        		Integer freq = map.get(key);
        		map.put(key, freq == null ? 1 : freq + 1);
        	}
        }
        List<Character> keys = Arrays.asList(map.keySet().toArray(new Character[0]));
        Collections.sort(keys);
        for(Character key : keys)
            System.out.println(key + " => " + map.get(key));
	}

}
