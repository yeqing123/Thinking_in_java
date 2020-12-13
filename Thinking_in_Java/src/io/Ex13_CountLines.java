package io;
import java.io.*;

public class Ex13_CountLines {
	static String file = "./src/io/Ex13_BasicFileOutput2.out";
	public static void main(String[] args) throws IOException {
		// LineNumberReader is inherited from BufferedReader
		// so we don't need to explicitly buffer it:
		LineNumberReader in = new LineNumberReader(
				new StringReader(
						BufferedInputFile.read("./src/io/Ex13_BasicFileOutput2.java")));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		String s;
		while((s = in.readLine()) != null)
			out.println(in.getLineNumber() + ": " + s);
		out.close();
		// show the stored file:
		System.out.println(BufferedInputFile.read(file));
	}

}/* (Execute to see output) *///