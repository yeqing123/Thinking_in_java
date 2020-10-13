package chapter_strings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Args: "abcabcabcdefabc", "abc+", "(abc)+", "(abc){2,}";
 */
public class TestRegularExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 2) {
        	System.out.print("java TestRelugarExpression " +
                "characterSequence regularExpression+");
        	System.exit(0);
        }
        System.out.println("Input:" + args[0]);
        for(int i = 1; i < args.length; i++) {
        	System.out.println("Regular experssion: \"" + args[i] + "\"");
        	Pattern p = Pattern.compile(args[i]);
        	Matcher m = p.matcher(args[0]);
        	while(m.find()) {
        		System.out.println("Match \"" + m.group() + "\" at positions " +
        	        m.start() + "-" + (m.end()-1));
        	}
        }
	}

}
