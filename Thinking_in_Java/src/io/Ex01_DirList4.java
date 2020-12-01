package io;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import net.mindview.util.*;

public class Ex01_DirList4 {
   
	public static void main(String[] args) {
        File path = new File("./src/io/");
        String[] list;
        if(args.length == 0)
        	list = path.list();
        else 
        	list = path.list(new FilenameFilter() {
        		// 命令行中的第一个参数仍然是限定文件名的正则表达式
        		Pattern pattern = Pattern.compile(args[0]);
        		public boolean accept(File dir, String name) {
        			if(pattern.matcher(name).matches()) {  // 首先判断文件名称是否符合正则规则
        				// 利用TextFile工具类，获得文件内容的Set集合，
        				// File类中的getAbsolutePath()，会返回该文件的绝对路径。
	                	Set<String> words = new HashSet<String>(
	                			new TextFile(new File(dir, name).getAbsolutePath(), "\\W+"));
	                	// 命令行中尾随的参数是要查找的文件内容
	                    for(int i = 1; i < args.length; i++)
	                	    if(words.contains(args[i]))
	                	    	return true;
        			}
                    return false;
                }
        	});
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
        	System.out.println(dirItem);
	}

}
