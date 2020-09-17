package chapter_strings;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SplitDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String input = "I love java!!I love programming!!I love learning!!yeqing";
        Pattern p = Pattern.compile("!!");
        System.out.println(Arrays.toString(p.split(input)));
        System.out.println(Arrays.toString(p.split(input, 3)));
	}

}
