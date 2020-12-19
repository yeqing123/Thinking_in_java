package io;
import java.nio.*;
import java.util.Arrays;

public class Endians {
	
	public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        CharBuffer cb = bb.asCharBuffer();
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        cb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcedf");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcedf");
        System.out.println(Arrays.toString(bb.array()));
	}

}
