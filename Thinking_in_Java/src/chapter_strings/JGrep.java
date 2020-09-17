package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

// A very simple version of the "grep" program.
// Args: JGrep.java "\b[sSct]\w+"
public class JGrep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 2) {
        	System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
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
