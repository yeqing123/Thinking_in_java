package io;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
	

public class Ex23_CharBufferPrinter {
    public static void printCharBuffer(ByteBuffer cb) {
    	String encoding = System.getProperty("file.encoding");
    	Charset charset = Charset.forName(encoding);
 //   	ByteBuffer bb = charset.encode(cb);
    	System.out.println(charset.decode(cb));
    }
    public static final int BSIZE = 1024;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileInputStream("./src/io/data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        fc.close();
        printCharBuffer(buff);
	}

}
