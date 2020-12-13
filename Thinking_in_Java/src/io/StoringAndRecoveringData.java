package io;
import java.io.*;

public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(
        		new BufferedOutputStream(
        				new FileOutputStream("./src/io/Data.txt")));
        out.writeDouble(3.1415926);
        out.writeUTF("That was PI");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();
        DataInputStream in = new DataInputStream(
        		new BufferedInputStream(
        				new FileInputStream("./src/io/Data.txt")));
        System.out.println(in.readDouble());
        // 只有readUTF()方法才能正确地恢复 Java-UTF字符串：
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        in.close();
	}

}
