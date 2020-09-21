package chapter_strings;

import java.util.Locale;
import java.util.Scanner;

public class Ex20_ScannerTest {
    private int i;
    private long l;
    private float f;
    private double d;
    private String s;
    public Ex20_ScannerTest(String s) {
    	Scanner stdin = new Scanner(s);
    	stdin.useLocale(Locale.US);
    	i = stdin.nextInt();
    	l = stdin.nextLong();
    	f = stdin.nextFloat();
    	d = stdin.nextDouble();
    	s = stdin.next("\\w+");
    }
    public String toString() {
    	return i + " " + l + " " + f + " " + d + " " + s;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(new Ex20_ScannerTest("512 1000000000000 12.35 56e6 Hello"));
        
	}

}
