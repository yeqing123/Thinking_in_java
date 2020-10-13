package chapter_strings;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

// A very simple version of the "grep" program.
// Args: Ex_15.java "\b[sct]\w+" CASE_INSENSITIVE
// The matching mode is optional!
public class Ex_15_1 {
    private static Map<String, Integer> modes = new HashMap<String, Integer>();
    public static void init() {
    	modes.put("CASE_INSENSITIVE", Pattern.CASE_INSENSITIVE);
    	modes.put("CANON_EQ", Pattern.CANON_EQ);
    	modes.put("MULTILINE", Pattern.MULTILINE);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
        if(args.length < 2) {
        	System.out.println("Usage: java JGrep file regex [mode]");
            System.exit(0);
        }
        Pattern p = null;
        if(args.length == 3) { 
            p = Pattern.compile(args[1], modes.get(args[2]));
	    } else {
	    	p = Pattern.compile(args[1]);
	    }
        Matcher m = p.matcher("");
        int index = 0;
        for(String line : new TextFile(args[0])) {
        	m.reset(line);
        	while(m.find()) {
        		System.out.println("index: " + index + " " + m.group() + ": " + m.start());
        		index++;
        	}
        }
	}
}
