package io;
// {Args: ".*\.java"}
import java.io.*;

public class Ex04_CalculateFilesSize {
  
	public static void main(String[] args) {
		Directory.TreeInfo ti;
		long total = 0;
		if(args.length == 0)
			ti = Directory.walk("./src/generics");
		else 
			ti = Directory.walk("./src/generics", args[0]);
		for(File file : ti)
    		total += file.length();
		System.out.println(ti.files.size() + " file(s): " + total + " bytes");
	}

}
