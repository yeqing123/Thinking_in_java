package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReFlags {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Pattern p = Pattern.compile("^java", 
        		Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        String input = "java support regular expressions\n" +
        		"java has regex\nJava has regex\n" + 
        		"JAVA has pretty good regular expressions\n" +
        		"Regular expressions are in Java";
        Matcher m = p.matcher(input);
        while(m.find()) {
        	System.out.println(m.group());
        }
	}

}
