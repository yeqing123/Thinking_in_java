package io;

import java.io.*;

public class Ex09_ReadTextFileUc {
    public static void printUc(String filename) throws IOException {
		for(String line : Ex07_ReadTextFile.read(filename))
			System.out.println(line.toUpperCase());
    }
	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.err.println("Usage:enter file path... to read");
		    System.exit(1);
		}
		for(int i = 0; i < args.length; i++)
			printUc(args[i]);
	}

}
