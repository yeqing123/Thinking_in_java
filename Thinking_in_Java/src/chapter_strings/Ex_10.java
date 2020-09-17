package chapter_strings;
import java.util.regex.*;

public class Ex_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String s = "Java now has regular experssions";
        String[] regexs = {"^Java", "\\Breg.*", "n.w\\s+h(a|i)s", "s?",
        		"s*", "s+", "s{4}", "s{1}", "s{0,3}"};
        System.out.println("Character sequence: " + s);
        for(String pattern : regexs) {
        	System.out.println("Regular experssion: \"" + pattern + "\"");
        	Pattern p = Pattern.compile(pattern);
        	Matcher m = p.matcher(s);
        	while(m.find()) {
        		System.out.println("Match \"" + m.group() + "\" at positions " + 
        	        m.start() + "-" + (m.end()-1));
        	}
        	System.out.println();
        }
	}

}
