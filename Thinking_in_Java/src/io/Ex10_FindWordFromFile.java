package io;
// {Args: ./src/io/Ex10_FindWordFromFile.java 
// line words static}
import java.io.IOException;

public class Ex10_FindWordFromFile {

	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.err.println("Usage:enter a file path, then enter words want to find");
			System.exit(1);
		}
        for(String line : Ex07_ReadTextFile.read(args[0]))
        	for(int i = 1; i < args.length; i++)
	        	if(line.contains(args[i]))
	        		System.out.println(line);
	}

}
