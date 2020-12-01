package io;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import net.mindview.util.*;

public class Ex03_DirList5 {
    public static long calculateSize(File path, String[] files) {
    	long total = 0;
    	for(String s : files) {
    		String file = TextFile.read((new File(path, s).getAbsolutePath()));
            total += file.getBytes().length;
    	}
    	return total;
    }
	public static void main(String[] args) {
        File path = new File("./src/io");
        String[] list;
        if(args.length == 0)
        	list = path.list();
        else
	        list = path.list(new FilenameFilter() {
	        	Pattern pattern = Pattern.compile(args[0]);
	        	public boolean accept(File dir, String name) {
	        		return pattern.matcher(name).matches();
	        	}
	        });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        long total = 0;
    	for(String file : list) {
    		// File.length()方法将返回指定文件的字节数
            total += new File(path, file).length();
            System.out.println(file);
    	}
    	System.out.println("================================");
        System.out.println(list.length + " file(s): " + total + " bytes");
	}

}
