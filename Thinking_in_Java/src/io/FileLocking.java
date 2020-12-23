package io;
import java.io.*;
import java.nio.channels.*;
import java.util.concurrent.*;

public class FileLocking {

	public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("./src/io/file.txt");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null) {
        	System.out.println("Locked File");
        	TimeUnit.MILLISECONDS.sleep(1000);
        	fl.release();
        	System.out.println("Released Lock");
        }
        fos.close();
	}

}
