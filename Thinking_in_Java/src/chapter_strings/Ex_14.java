package chapter_strings;

import java.util.Arrays;

public class Ex_14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "I love java!!I love programming!!I love learning!!yeqing";
		System.out.println(Arrays.toString(input.split("!!")));
		System.out.println(Arrays.toString(input.split("!!", 3)));
	}

}
