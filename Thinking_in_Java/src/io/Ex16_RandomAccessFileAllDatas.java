package io;
import java.io.*;

public class Ex16_RandomAccessFileAllDatas {
    static String file = "./src/io/rtest2.dat";
    public static void display() throws IOException {
    	RandomAccessFile rf = new RandomAccessFile(file, "r");
    	System.out.println(rf.readBoolean());
    	System.out.println(rf.readByte());
    	System.out.println(rf.readUnsignedByte());
    	System.out.println(rf.readChar());
    	System.out.println(rf.readDouble());
        System.out.println(rf.readInt());
        System.out.println(rf.readLong());
        System.out.println(rf.readShort());
        System.out.println(rf.readUnsignedShort());
        System.out.println(rf.readFloat());
        System.out.println(rf.readUTF());
        rf.close();
    }
	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		rf.writeBoolean(true);
		rf.writeByte(100);
		rf.writeByte(255);
		rf.writeChar('A');
		rf.writeDouble(3.141592);
        rf.writeInt(10000);
        rf.writeLong(20000L);
        rf.writeShort((short)1200);
        rf.writeShort((short)65535);
        rf.writeFloat(1.41413f);
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file, "rw");
        rf.seek(3); // 1 boolean + 2 bytes
        rf.writeChar('B');
        rf.close();
        display();
	}

}
