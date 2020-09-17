/*! A very simple version of the "grep" program.!*/
package chapter_strings;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

/* 
 * A very simple version of the "grep" program.
 */
// Args: file or folder "\b[sSct]\w+"
public class Ex_16 {
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 2) {
        	System.out.println("Usage: java file or folder regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        Matcher m = p.matcher("");
        File f = new File(args[0]);
        File[] files = null;
        if(f.isDirectory()) {
        	System.out.println("The parameter is a directory!");
            files = f.listFiles();
        } else {
        	System.out.println("The parameter is a file!");
        	files = new File[]{f};
        }
         
         // Add a annotation
        /* A very simple version of the "grep" program.*/
        for(File file : files) {
        	System.out.println("file name: " + file.getName());
        	int index = 0;
        	for(String line : new TextFile(file.getAbsolutePath())) {
            	m.reset(line);
            	while(m.find()) {
            		System.out.println("index: " + index + " " + m.group() + ": " + m.start());
            		index++;
            	}
            }
   	    }
	}

}
