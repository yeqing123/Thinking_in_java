package io;
import java.io.*;
import java.util.*;
import net.mindview.util.PPrint;

public class Directory {
    public static File[] local(String path, String regex) {
    	return local(new File(path), regex);
    }
    public static File[] local(File path, String regex) {
    	Set<File> set = new TreeSet<File>();
    	File[] files = path.listFiles();
    	for(File f : files)
    		if(f.getName().matches(regex))
    			set.add(f);
    	File[] result = set.toArray(new File[0]);
    	return result;
    }
    static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();
		@Override
		public Iterator<File> iterator() {
			return files.iterator();
		}
		public void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}
		public String toString() {
			return "files:" + PPrint.pformat(files) + 
					"\n\ndirs:" + PPrint.pformat(dirs);
		}
    }
    public static TreeInfo walk(File start, String regex) {
        return recursionDirs(start, regex);
    }
    public static TreeInfo walk(String start, String regex) {
    	return recursionDirs(new File(start), regex);
    }
    public static TreeInfo walk(File start) {
    	return recursionDirs(start, ".*");
    }
    public static TreeInfo walk(String start) {
    	return recursionDirs(new File(start), ".*");
    }
    public static TreeInfo recursionDirs(File startDir, String regex) {
    	TreeInfo result = new TreeInfo();
    	for(File item : startDir.listFiles()) {
    		if(item.isDirectory()) {
    			result.dirs.add(item);
    		    result.addAll(recursionDirs(item, regex));
    		} else {
    		    if(item.getName().matches(regex))
    			    result.files.add(item);
    		}
    	}
    	return result;
    }
    public static void main(String[] args) {
    	if(args.length == 0)
    		System.out.println(walk("."));
    	else
    		for(String arg : args)
    			System.out.println(walk(arg));
    }
}
