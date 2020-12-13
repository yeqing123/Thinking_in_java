package io;
// {Args: ./src/io/Ex12_LineNumber.java ./src/io/Ex12_LineNumber.out}
import java.io.*;
import java.util.List;
import java.util.ListIterator;

public class Ex12_LineNumber {
	
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.err.println("Usage:java Ex12_LineNumber infile outfile");
		    System.exit(1);
		}
		List<String> list =  Ex07_ReadTextFile.read(args[0]);
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(args[1])));
		int lineCount = list.size();
		for(ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious();)
	    	out.println(lineCount-- + ": " + it.previous());
		out.close();
		// Show the stored file:
		System.out.println(BufferedInputFile.read(args[1]));
	}

}
