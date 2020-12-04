package io;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class Ex07_ReadTextFile {
    public static LinkedList<String> read(String filename) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader(filename));
		LinkedList<String> list = new LinkedList<String>();
		String s;
		while((s = in.readLine()) != null)
			list.add(s);
		in.close();
		Collections.reverse(list);
		return list;
    }
	public static void main(String[] args) throws IOException {
		for(String line : read("./src/io/Ex07_ReadTextFile.java"))
			System.out.println(line);
 	}

}
