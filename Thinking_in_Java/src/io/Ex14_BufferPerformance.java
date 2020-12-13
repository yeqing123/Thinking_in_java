package io;
import java.io.*;
import java.util.List;

public class Ex14_BufferPerformance {
	static String file = "./src/io/Ex14_BufferPerformance.out";
	public static void main(String[] args) throws IOException {
		List<String> list = Ex07_ReadTextFile.read("./src/io/Ex14_BufferPerformance.java");
		PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		PrintWriter out2 = new PrintWriter(new FileWriter(file));
		int lineCount = 1;
		long t1 = System.currentTimeMillis();  //返回当前时间(以毫秒为单位)。
		for(String s : list) 
			for(int i = 0; i < 1000000; i++) {
				out1.println(lineCount + ": " + s);
			    lineCount++;
			}
		long t2 = System.currentTimeMillis();
		System.out.println("buffered: " + (t2 - t1) + " (millisecond)");
		out1.close();
		lineCount = 1;
		t1 = System.currentTimeMillis();
		for(String s : list)
			for(int i = 0; i < 1000000; i++) {
			    out2.println(lineCount + ": " + s);
			    lineCount++;
            }
		t2 = System.currentTimeMillis();
		System.out.println("unbuffered: " + (t2 - t1) + " (millisecond)");
		out2.close();
	}
}
