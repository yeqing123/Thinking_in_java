// Create a serialized output file.
package io;
import java.io.*;

public class FreezeAlien {

	public static void main(String[] args) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
        		new FileOutputStream("./src/io/X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
        out.close();
	}

}
