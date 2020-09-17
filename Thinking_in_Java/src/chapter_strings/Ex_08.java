package chapter_strings;

import java.util.Arrays;

public class Ex_08 {
    public static String knights = 
    		"Then, when you have found the shrubbery, you must " + 
            "cut down the mightiest tree in the forest... " +
            "with... a herring!";
    public static void split(String regex) {
    	System.out.println(Arrays.deepToString(knights.split(regex)));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        split("the|you");
	}

}
