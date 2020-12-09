package io;
// {Args: ./src/io/Ex08_ReadTextCommandLine.java}
import java.io.*;
import java.util.*;

public class Ex08_CommandLine {
	
	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.err.println("Usage:enter file path... to read");
		    System.exit(1);
		}
		List<String> list =  Ex07_ReadTextFile.read(args[0]);
		for(ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious();)
	    	System.out.println(it.previous());
	}

}
