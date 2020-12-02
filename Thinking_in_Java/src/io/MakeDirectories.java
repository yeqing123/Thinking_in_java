package io;
// Demonstrates the use of the File class to
// create directories and manipulate files.
// {Args: MakeDirectoriesTest}
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MakeDirectories {
    private static void usage() {
    	System.err.println(
    			"Usage:MakeDirectories path1 ...\n" +
    	        "Creates each path\n" + 
    			"Usage:MakeDirectories -d path1 ...\n" + 
    	        "Deletes each path\n" +
    			"Uage:MakeDirectories -r path1 path2\n" +
    	        "Renames from path1 to path2"
    			);
    	System.exit(1);
    }
    private static void fileData(File f) {
    	SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
    	bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
    	System.out.println(
    			"Absolute path: " + f.getAbsolutePath() +
    			"\n Can read: " + f.canRead() + 
    			"\n Can write: " + f.canWrite() +
    			"\n getName: " + f.getName() + 
    			"\n getParent:" + f.getParent() + 
    			"\n length: " + f.length() +
    			"\n lastModified: " + bjSdf.format(new Date(f.lastModified()))
    			);
    	if(f.isFile())
    		System.out.println("It's a file");
    	else if(f.isDirectory())
    		System.out.println("It's a directory");
    }
	public static void main(String[] args) {
        if(args.length < 1) usage();
        if(args[0].equals("-r")) {
        	if(args.length != 3) usage();
        	File old = new File(args[1]), rname = new File(args[2]);
        	if(old.renameTo(rname))
        		System.out.println("Rename successful");
        	else
        		System.out.println("Rename failed");
        	fileData(old);
        	fileData(rname);
        	return ; // Exit main
        }
        int count = 0;
        boolean del = false;
        if(args[0].equals("-d")) {
        	count++;
        	del = true;
        }
        count--;
        while(++count < args.length) {
        	File f = new File(args[count]);
        	if(f.exists()) {
        		System.out.println(f + " exists");
        		if(del) {
        			System.out.println("deleting..." + f);
        			f.delete();
        		}
        	} else { // Doesn't exist
        		if(!del) {
        			f.mkdirs();
        			System.out.println("created " + f);
        		}
        	}
        	fileData(f);
        }
	}

}
