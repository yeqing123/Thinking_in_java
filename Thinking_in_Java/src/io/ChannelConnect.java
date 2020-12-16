// Using special method to connect two channels.
// {Args: ./src/io/ChannelConnect.java ./src/io/test.txt}
package io;
import java.nio.channels.*;
import java.io.*;

public class ChannelConnect {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.err.println("Uages: sourcefile deskfile");
			System.exit(1);
		}
        FileChannel 
            in = new FileInputStream(args[0]).getChannel(),
            out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        // or
//        out.transferFrom(in, 0, in.size());
	}

}
