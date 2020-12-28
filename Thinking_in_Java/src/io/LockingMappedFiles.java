package io;
import java.nio.channels.*;
import java.io.*;
import java.nio.*;

public class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFFF;  // 128M
    static FileChannel fc;
	public static void main(String[] args) throws Exception {
        fc = new RandomAccessFile("./src/io/test.dat", "rw").getChannel();
        MappedByteBuffer out = 
        		fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for(int i = 0; i < LENGTH; i++)
        	out.put((byte)'x');
        new LockAndModify(out, 0, 0 + LENGTH/3);
        new LockAndModify(out, LENGTH/2, LENGTH/2 + LENGTH/4);
	}
	private static class LockAndModify extends Thread {
		private ByteBuffer buffer;
		private int start, end;
		LockAndModify(ByteBuffer mbb, int start, int end) {
			this.start = start;
			this.end = end;
			mbb.limit(end);
			mbb.position(start);
			buffer = mbb.slice();
			start();
		}
		public void run() {
			try {
				// 无重叠的独占锁
				FileLock fl = fc.lock(start, end, false);
				System.out.println("Locked: " + start + " to " + end);
				// 执行修改：
				while(buffer.position() < buffer.limit() - 1)
					buffer.put((byte)(buffer.get() + 1));
				fl.release();
				System.out.println("Released: " + start + " to " + end);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}