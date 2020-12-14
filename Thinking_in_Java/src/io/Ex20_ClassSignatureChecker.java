package io;
import java.io.*;

public class Ex20_ClassSignatureChecker {
	final static byte[] signature = { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };

	public static void main(String[] args) throws IOException { 
        String dir = "."; 
        if(args.length == 1) 
            dir = args[0]; 
        for(File file : Directory.walk(dir, ".*\\.class")) { 
        	byte[] bt = BinaryFile.read(file); 
            for(int i = 0; i< signature.length; i++) 
            	if(bt[i] != signature[i]) { 
            		System.err.println(file + " is corrupt!"); 
            		break; 
            	}
        }
	}

}
