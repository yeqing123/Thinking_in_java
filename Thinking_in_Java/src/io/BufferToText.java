package io;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.io.*;

public class BufferToText {
    private static final int BSIZE = 1024;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileOutputStream("./src/io/data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc = new FileInputStream("./src/io/data.txt").getChannel();
		fc.read(buff);
		buff.flip();
		// Doesn't work
		System.out.println(buff.asCharBuffer());
		buff.rewind();
		// Decoding using this system's default Charset:
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoding using " + encoding + ": "
		    + Charset.forName(encoding).decode(buff));
		// Or, encode when data is written a file:
		fc = new FileOutputStream("./src/io/data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try reading again:
		fc = new FileInputStream("./src/io/data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		// Use a CharBuffer to write through:
		fc = new FileOutputStream("./src/io/data2.txt").getChannel();
		buff = ByteBuffer.allocate(24);   // More than needed
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();
		// Read and display:
		fc = new FileInputStream("./src/io/data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}

}
