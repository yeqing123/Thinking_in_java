package io;
import java.io.*;

public class BufferedInputFile {
    public static String read(String filename) throws IOException {
    	// 创建一个BufferReader实例
    	BufferedReader in = new BufferedReader(new FileReader(filename));
    	StringBuffer buffer = new StringBuffer();
    	String s;
    	// 逐行读取字符
    	while((s = in.readLine()) != null)
    		buffer.append(s + "\n");
    	in.close();
    	return buffer.toString();
    }
	public static void main(String[] args) throws IOException {
        System.out.println(read("./src/io/BufferedInputFile.java"));
	}

}
