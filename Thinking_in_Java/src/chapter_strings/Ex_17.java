package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;
/*This program matches all annotations.*/
// Args: java file
public class Ex_17 {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 1) {
        	System.out.println("Usage: java file");
        	System.exit(0);
        }
        /*
         * Embedding compile labels in regular expressions, 
         * allows you to specify multiple patterns at a time.
         */
        Pattern p = Pattern.compile("(?s)(?m)/\\*(.*?)\\*/|^\\s*//(.*?)$");
        Matcher m = p.matcher(TextFile.read(args[0]));
        while(m.find()) {
        	System.out.println(m.group().replaceAll(" {2,}", " ").trim());
        }
	}
}
