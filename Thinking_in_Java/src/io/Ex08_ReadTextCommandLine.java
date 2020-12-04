package io;
// {Args: ./src/io/Ex08_ReadTextCommandLine.java}
import java.io.*;

public class Ex08_ReadTextCommandLine {
	
	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.err.println("Usage:enter file path... to read");
		    System.exit(1);
		}
		for(int i = 0; i < args.length; i++)
			for(String line : Ex07_ReadTextFile.read(args[i]))
				System.out.println(line);
	}

}
