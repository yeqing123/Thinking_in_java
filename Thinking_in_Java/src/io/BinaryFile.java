package io;
import java.io.*;

public class BinaryFile {
    public static byte[] read(File bFile) throws IOException {
    	BufferedInputStream bf = new BufferedInputStream(
    			new FileInputStream(bFile));
    	try {
    		byte[] bytes = new byte[bf.available()];
    		bf.read(bytes);
    		return bytes;
    	} finally {
    		bf.close();
    	}
    }
    public static byte[] read(String bFile) throws IOException {
    	return read(new File(bFile));
    }
 }
