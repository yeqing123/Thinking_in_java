package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String regex = "(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b";
        String charSeq = "Arline ate eight apples and one orange while Anita hadn't any";
 //       System.out.println("Match result: " + Pattern.matches(regex, charSeq));
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(charSeq);
        while(m.find()) {
        	System.out.println("Match \"" + m.group() + "\" at positions " +
                m.start() + "-" + (m.end()-1));
        }
	}

}
