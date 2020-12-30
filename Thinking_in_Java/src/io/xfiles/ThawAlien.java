package io.xfiles;
// Try to recover a serialized file without the
// class of object that's stored in that file.
import java.io.*;

public class ThawAlien {

	public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
        		new FileInputStream(new File("./src/io", "X.file")));
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
	}

}
