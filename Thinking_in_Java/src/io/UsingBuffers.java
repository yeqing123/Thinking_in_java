package io;
import java.nio.*;

public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
    	while(buffer.hasRemaining()) {
    		buffer.mark();   // 对当前位置进行标记
    		char c1 = buffer.get();
    		char c2 = buffer.get();
    		buffer.reset();  // 将缓冲器的position值重置为先前标记的位置
    		buffer.put(c2).put(c1);
    	}
    }
	public static void main(String[] args) {
		char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length *2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        // rewind()方法会重置此缓冲区。 位置设置为零，标记被丢弃。然后返回一个Buffer实例。
        System.out.println(cb.rewind());  
        symmetricScramble(cb);
        System.out.println(cb.rewind());
        symmetricScramble(cb);
        System.out.println(cb.rewind());
	}

}
