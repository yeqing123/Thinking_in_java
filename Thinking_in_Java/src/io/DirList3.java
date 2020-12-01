package io;
// Building the anonymous inner class "in-place"
// {Args: "D.*\.java"}
import java.io.*;
import java.util.regex.*;
import java.util.*;

public class DirList3 {

	public static void main(String[] args) {
        File path = new File("./src/io");
        String[] list;
        if(args.length == 0)
        	list = path.list();
        else
        	list = path.list(new FilenameFilter() {
        		private Pattern pattern = Pattern.compile(args[0]);
        		public boolean accept(File dir, String name) {
        			return pattern.matcher(name).matches();
        		}
        	});
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
        	System.out.println(dirItem);
	}
}
