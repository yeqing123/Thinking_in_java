// Copying a file using channels and buffers
// {Args: ./src/io/ChannelCopy.java ./src/io/test.txt}
package io;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class ChannelCopy {
    private static final int BSIZE = 1024;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
        if(args.length != 2) {
        	System.err.println("Uages: sourcefile destfile");
        	System.exit(1);
        }
        FileChannel 
        	in = new FileInputStream(args[0]).getChannel(),
        	out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        while(in.read(buff) != -1) {
        	buff.flip();    // Prepare for writing
        	out.write(buff);
        	buff.clear();   // Prepare for reading
        }
	}

}
