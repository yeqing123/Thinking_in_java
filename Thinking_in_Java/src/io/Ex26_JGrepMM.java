//{Args: ./src/io/Ex26_JGrepMM.java \b[Ssct]\w+} 
package io;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.io.*;
import java.util.regex.*;

public class Ex26_JGrepMM {
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Usage: java Ex26_JGrepMM file regex");
			System.exit(0);
		}
		Pattern p = Pattern.compile(args[1]);
		int index = 0;
		Matcher m = p.matcher("");
		FileChannel fc = new FileInputStream(args[0]).getChannel();
		ByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		CharBuffer cb = Charset.forName(System.getProperty("file.encoding")).decode(buffer);
		String[] fileAsArray = cb.toString().split("\n");
		for(String line : fileAsArray) {
			m.reset(line);
			while (m.find())
				System.out.println(index++ + ": " + m.group() + ": " + m.start());
		}
		fc.close();
	}
}