package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

// A very simple version of the "grep" program.
// Args: JGrep.java "\b[sSct]\w+" pattern
public class Ex_15_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 3) {
            System.out.println("Usage: java Ex_15_2 file regex pattern");
        	System.out.println("pattern can take one of the following values:");
            System.out.println("CASE_INSENSITIVE, CANON_EQ, MULTILINE," +
        	    "COMMENTS, DOTALL, UNICODE_CASE, UNIX_LINES");
        	System.exit(0);
        }
        int flag = 0;
        if(args[2].equalsIgnoreCase("CANON_EQ")) {
        	flag = Pattern.CANON_EQ;
        } else if(args[2].equalsIgnoreCase("CASE_INSENSITIVE")) {
        	flag = Pattern.CASE_INSENSITIVE;
        } else if(args[2].equalsIgnoreCase("MULTILINE")) {
        	flag = Pattern.MULTILINE;
        } else if(args[2].equalsIgnoreCase("UNICODE_CASE")) {
        	flag = Pattern.UNICODE_CASE;
        } else if(args[2].equalsIgnoreCase("DOTALL")) {
        	flag = Pattern.DOTALL;
        } else if(args[2].equalsIgnoreCase("COMMENTS")) {
        	flag = Pattern.COMMENTS;
        } else if(args[2].equalsIgnoreCase("UNIX_LINES")) {
        	flag = Pattern.UNIX_LINES;
        }
        Pattern p = Pattern.compile(args[1], flag);
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
