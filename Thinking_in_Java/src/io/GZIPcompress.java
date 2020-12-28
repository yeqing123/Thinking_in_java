package io;
// {Args: ./src/io/GZIPcompress.java}
import java.io.*;
import java.util.zip.*;

public class GZIPcompress {
	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.out.println("Usage:\nGZIPcompress file\n" +
		                 "\tUses GZIPcompression to compress " +
					     "to file test.gz");
			System.exit(1);
		}
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream("./src/io/test.gz")));
		int c;
		System.out.println("Writing file");
		while((c = in.read()) != -1)
			out.write(c);
		in.close();
		out.close();
        BufferedReader in2 = new BufferedReader(
        		new InputStreamReader(
        				new GZIPInputStream(
        						new FileInputStream("./src/io/test.gz"))));
		String s;
        System.out.println("Reading file");
        while((s = in2.readLine()) != null)
        	System.out.println(s);
        in2.close();
	}
}