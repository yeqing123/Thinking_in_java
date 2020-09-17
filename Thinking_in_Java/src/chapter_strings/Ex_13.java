package chapter_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_13 {
    private static class Display {
    	private boolean regexPrinted = false;
    	private String regex;
    	Display(String regex) {
    		this.regex = regex;
    	}
    	void display(String message) {
    		if(!regexPrinted) {
    			System.out.println(regex);
    			regexPrinted = true;
    		}
    		System.out.println(message);
    	}
    }
    static void examine(String s, String regex) {
    	Display d = new Display(regex);
    	Pattern p = Pattern.compile(regex);
    	Matcher m = p.matcher(s);
    	while(m.find()) {
    		d.display("find() '" + m.group() +
    				"' start = " + m.start() + " end = " + (m.end()-1));
    	}
    	if(m.lookingAt()) {
    		d.display("lookingAt() '" + m.group() +
    				"' start = " + m.start() + " end = " + (m.end()-1));
    	}
    	if(m.matches()) {
    		d.display("matches() '" + m.group() +
    				"' start = " + m.start() + " end = " + (m.end()-1));
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for(String in : Groups.POEM.split("\n")) {
        	System.out.println("input: " + in);
        	for(String regex : new String[]{"\\w*was\\w*",
            		"\\w*ble", "A\\w+", "T.*?."}) {
            	examine(in, regex);
        	}
        }
	}

}
