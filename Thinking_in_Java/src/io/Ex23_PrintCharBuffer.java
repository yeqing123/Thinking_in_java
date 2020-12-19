package io;
import java.nio.*;
import java.util.*;

public class Ex23_PrintCharBuffer {
	static BitSet isPrintable = new BitSet(127);
	// 假设遵守ASCII eg.iso-8859-1的编码。 字符从32到127表示可打印字符。
	static String encoding = System.getProperty("file.encoding");
	static {
		for(int i = 32; i <= 127; i++)
			isPrintable.set(i);
	}
	// 将位置设置为最后的可打印字符
	public static void setPrintableLimit(CharBuffer cb) {
		cb.rewind();
		while(isPrintable.get(cb.get()));
		cb.limit(cb.position() - 1);
		cb.rewind();
	}
	public static void main(String[] args) {
		System.out.println("Default Encoding is: " + encoding);
		CharBuffer buffer = ByteBuffer.allocate(16).asCharBuffer();
		buffer.put("ABCDE" + (char)0x01 + "FG");
		buffer.rewind();
		System.out.println(buffer);
		setPrintableLimit(buffer);
		System.out.println(buffer);
	}
}