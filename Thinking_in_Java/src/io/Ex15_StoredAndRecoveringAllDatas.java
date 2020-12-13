package io;
import java.io.*;

import com.sun.org.apache.bcel.internal.util.ByteSequence;


public class Ex15_StoredAndRecoveringAllDatas {
	
	public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(
        		new BufferedOutputStream(
        				new FileOutputStream("./src/io/Data2.txt")));
        out.writeDouble(3.141592);
        out.writeUTF("This is a pi");
        out.writeBoolean(true);
        out.writeByte(100);
        out.writeByte(255);
        out.writeChar('A');
        out.writeInt(10000);
        out.writeLong(20000L);
        out.writeShort((short)1200);
        out.writeShort((short)65535);
        out.writeFloat(1.41413f);
        out.close();
        DataInputStream in = new DataInputStream(
        		new BufferedInputStream(
        				new FileInputStream("./src/io/Data2.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        System.out.println(in.readUnsignedByte());
        System.out.println(in.readChar());
        System.out.println(in.readInt());
        System.out.println(in.readLong());
        System.out.println(in.readShort());
        System.out.println(in.readUnsignedShort());
        System.out.println(in.readFloat());
        in.close();
	}

}
