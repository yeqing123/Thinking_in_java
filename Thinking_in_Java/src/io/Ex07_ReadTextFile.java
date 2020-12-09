package io;
import java.io.*;
import java.util.*;

public class Ex07_ReadTextFile {
    public static LinkedList<String> read(String filename) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader(filename));
		LinkedList<String> list = new LinkedList<String>();
		String s;
		while((s = in.readLine()) != null)
			list.add(s);
		in.close();
		return list;
    }
	public static void main(String[] args) throws IOException {
		List<String> list = read("./src/io/Ex07_ReadTextFile.java");
	    for(ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious();)
	    	System.out.println(it.previous());
 	}

}
