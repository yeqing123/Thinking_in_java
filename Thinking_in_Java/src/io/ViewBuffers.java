package io;
import java.nio.*;

public class ViewBuffers {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[] {0, 0, 0, 0, 0, 0, 0, 'a'});
		bb.rewind();
		System.out.print("Byte Buffer ");
		while(bb.hasRemaining())
			System.out.print(bb.position() + " -> " + bb.get() + ", ");
		System.out.println();
		Buffer buffer = bb.rewind();
		CharBuffer cb = ((ByteBuffer)buffer).asCharBuffer();
		System.out.print("Char Buffer ");
		while(cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		System.out.println();
		buffer = bb.rewind();
		ShortBuffer sb = ((ByteBuffer)buffer).asShortBuffer();
		System.out.print("Short Buffer ");
		while(sb.hasRemaining())
			System.out.print(sb.position() + " -> " + sb.get() + ", ");
		System.out.println();
		buffer = bb.rewind();
		IntBuffer ib = ((ByteBuffer)buffer).asIntBuffer();
		System.out.print("Int Buffer ");
		while(ib.hasRemaining())
			System.out.print(ib.position() + " -> " + ib.get() + ", ");
		System.out.println();
		buffer = bb.rewind();
		LongBuffer lb = ((ByteBuffer)buffer).asLongBuffer();
		System.out.print("Long Buffer ");
		while(lb.hasRemaining())
			System.out.print(lb.position() + " -> " + lb.get() + ", ");
		System.out.println();
	    buffer = bb.rewind();
		FloatBuffer fb = ((ByteBuffer)buffer).asFloatBuffer();
		System.out.print("Float Buffer ");
		while(fb.hasRemaining())
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		System.out.println();
		buffer = bb.rewind();
		DoubleBuffer db = ((ByteBuffer)buffer).asDoubleBuffer();
		System.out.print("Double Buffer ");
		while(db.hasRemaining())
			System.out.print(db.position() + " -> " + db.get() + ", ");
	}

}
