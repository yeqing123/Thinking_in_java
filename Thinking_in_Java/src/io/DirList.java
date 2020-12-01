package io;
// Display a file directory using regular expressions.
// {Args: "D.*/.java"}
import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    
	public static void main(String[] args) {
        File path = new File("./src/io/");
		String[] list;
        if(args.length == 0)
            list = path.list();
        else
        	list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(list));
	}
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    public DirFilter(String regex) {
    	pattern = Pattern.compile(regex);
    }
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
	
}
