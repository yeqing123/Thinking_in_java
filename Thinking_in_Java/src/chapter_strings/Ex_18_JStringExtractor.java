package chapter_strings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.*;

public class Ex_18_JStringExtractor {
    static final String STR_EXT_REGEX = 
    		"\"(?:[^\"\\\\\\n\\r]|(?:\\\\(?:[untbrf\\\\'\"]"
    		+ "|[0-9A-Fa-f]{4}|[0-7]{1,2}|[0-3][0-7]{2})))*\"";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        if(args.length < 1) {
        	System.out.println("Usage: java Ex_18_JStringExtractor file");
        	System.exit(0);
        }
        String src = TextFile.read(args[0]);
        Pattern p = Pattern.compile(STR_EXT_REGEX);
        Matcher m = p.matcher(src);
        while(m.find()) {
        	System.out.println(m.group().substring(1, m.group().length() - 1));
        	
        	// "This is NOT a string but a comment!"
        	String dummy = "\u003F\u003f\n\060\607";
        }
	}

}
