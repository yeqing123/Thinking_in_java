package chapter_strings;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_12 {
    public static final String POEM = 
    		"Twas brillig, and the slithy toves\n" + 
            "Did gyre and gimble in the wabe.\n" +
    	    "All mimsy were the borogoves,\n" +
            "And the mome raths outgrabe.\n\n" +
    	    "Beware the Jabberwock, my son,\n" +
            "The jaws that bite, the claws that catch.\n" +
    	    "Beware the Jubjub bird, and shun\n" +
            "The frumious Bandersnatch.";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Matcher m = Pattern.compile("\\b((?![A-Z])\\w+)\\b")
        		.matcher(POEM);
        Set<String> words = new HashSet<String>();
        while(m.find()) {
        	words.add(m.group(1));
        }
        System.out.println("Number of unique words:" + words.size());
        System.out.println(words);
	}

}
