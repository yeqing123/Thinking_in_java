package io;
// Demonstrates the use of the File class to
// create directories and manipulate files.
// {Args: MakeDirectriesTest}
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeDirectories {
	private static void usage() {
		System.err.println(
				"Usage:MakeDirectries path1...\n" + 
		        "Creates each path\n" +
				"Usage:MakeDirectries -d path1...\n" +
		        "Deletes each path\n" +
				"Usage:MakeDirectries -r path1 path2\n" +
		        "Rename frome path1 to path2\n"
				);
		System.exit(1);
	}
	private static void fileData(File f) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(
			"Absolute path: " + f.getAbsoluteFile() + 
			"\n Can read: " + f.canRead() + 
			"\n Can write: " + f.canWrite() +
			"\n getName: " + f.getName() + 
			"\n getParent: " + f.getParent() + 
			"\n getPath: " + f.getPath() + 
			"\n length: " + f.length() + 
			"\n lastModified: " + sdf.format(new Date(f.lastModified()))
			);
		if(f.isFile())
			System.out.println("It's a file");
		else
			System.out.println("It's a directory");
	}
	public static void main(String[] args) {
		if(args.length == 0) usage();
		if(args[0].equals("-r")) {
			if(args.length != 3) usage();
		    File old = new File(args[1]), rename = new File(args[2]);
		    if(old.renameTo(rename))
		    	System.out.println("Rename successful");
		    else
		    	System.out.println("Rename failed");
		    fileData(old);
		    fileData(rename);
		} else {
			int count = 0;
			boolean deletable = false;
			if(args[0].equals("-d")) {
				count++;
				deletable = true;
			}
			while(count < args.length) {
				File f = new File(args[count++]);
				if(f.exists()) {
					if(deletable) {
						f.delete();
						System.out.println("delete directory " + f);
					}
				} else {  // Doesn't exists
					if(!deletable) {
					    f.mkdirs();
					    System.out.println("created directory");
					    fileData(f);
					}
				}
			}
		}
		return ; // Eixt main
	}
}