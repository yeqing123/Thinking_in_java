package io;
// {Args: ./src/io/Ex10_FindWords.java 
// line words static}
import java.io.IOException;
import java.util.*;

public class Ex10_FindWords {

	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.err.println("Usage:enter a file path, then enter words want to find");
			System.exit(1);
		}
		Set<String> words = new HashSet<String>();
		for(int i = 1; i < args.length; i++)
			words.add(args[i]);
		List<String> list = Ex07_ReadTextFile.read(args[0]);
		for(ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious();) {
            String candidate = it.previous();
            for(String word : words)
            	if(candidate.indexOf(word) != -1) {
            		System.out.println(candidate);
            	    break;
            	}
		}
	}

}
