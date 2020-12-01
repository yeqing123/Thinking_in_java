package io;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

class SortedDirList {
	private File path;
	public SortedDirList() { path = new File("."); }
    public SortedDirList(File path) {
    	this.path = path;
    }
    public String[] list() {
        String[] list = this.path.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    	return list;
    }
    public String[] list(final String regex) {
    	return path.list(new FilenameFilter() {
    		Pattern pattern = Pattern.compile(regex);
    		@Override
    		public boolean accept(File dir, String name) {
    			return pattern.matcher(name).matches();
    		}
    	});
    }
}

public class Ex02_SortedDirListTest {
	
	public static void main(String[] args) {
		SortedDirList sdl = new SortedDirList(new File("./src/io"));
		System.out.println(Arrays.toString(sdl.list()));
		System.out.println(Arrays.toString(sdl.list("D.*\\.java")));
	}

}
