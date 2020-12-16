package io;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class GetChannel {
    private static final int BSIZE = 1024;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
	    // 生成一条输出通道，用于向文件写入
		FileChannel fc = new FileOutputStream("./src/io/data.txt").getChannel();
	    // 将数据放入缓冲器，并利用通道写入文件
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
	    fc.close();
	    // 向文件的末尾追加内容
	    fc = new RandomAccessFile("./src/io/data.txt", "rw").getChannel();
	    // 移动到文件末尾
	    fc.position(fc.size());
	    // 利用通道将数据写入文件
	    fc.write(ByteBuffer.wrap(" Some more text".getBytes()));
	    fc.close();
	    // 生成一条输入通道，用于读取文件
	    fc = new FileInputStream("./src/io/data.txt").getChannel();
	    // 为缓冲器分配大小
	    ByteBuffer buff = ByteBuffer.allocate(BSIZE);
	    fc.read(buff);
	    // flip()方法表示缓冲器已准备就绪，可以读取数据
	    buff.flip();
	    while(buff.hasRemaining())
	    	System.out.print((char)buff.get());
	}

}
