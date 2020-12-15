package io;
import java.io.*;

public class Ex21_UpperCaseEcho {

	public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(
        		new InputStreamReader(System.in));
        String s;
        while((s = stdin.readLine()) != null && s.length() != 0)
        	System.out.println(s.toUpperCase());
	}

}
