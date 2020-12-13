package io;
import java.io.*;

public class FileOutputShortcut {
    static String file = "./src/io/FileOutputShortcut.out";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
				new StringReader(
						BufferedInputFile.read("./src/io/FileOutputShortcut.java")));
		// Here is the shortcut:
		PrintWriter out = new PrintWriter(file);
		String s;
		int lineCount = 1;
		while((s = in.readLine()) != null)
			out.println(lineCount++ + ": " + s);
		out.close();
		// Show the stored file:
		System.out.println(BufferedInputFile.read(file));
	}

}
