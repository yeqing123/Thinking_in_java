package io;
import java.nio.*;

public class GetData {
    private static final int BSIZE = 1024;
	public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while(i++ < bb.limit())
        	if(bb.get() != 0)
        		System.out.println("nonzero");
        System.out.println("i = " + i);
        bb.rewind();
        // 存储和读取一个字符数组:
        bb.asCharBuffer().put("Howdy!");
        char c;
        while((c = bb.getChar()) != 0)
        	System.out.print(c + " ");
        System.out.println();
        bb.rewind();
        // 存储和读取一个short:
        bb.asShortBuffer().put((short)471142);
        System.out.println(bb.getShort());
        bb.rewind();
        // 存储和读取一个int:
        bb.asIntBuffer().put(99471142);
        System.out.println(bb.getInt());
        bb.rewind();
        // 存储和读取一个long:
        bb.asLongBuffer().put(99471142);
        System.out.println(bb.getLong());
        bb.rewind();
        // 存储和读取一个float:
        bb.asFloatBuffer().put(99471142);
        System.out.println(bb.getFloat());
        bb.rewind();
        // 存储和读取一个double:
        bb.asDoubleBuffer().put(99471142);
        System.out.println(bb.getDouble());
        bb.rewind();
	}

}
