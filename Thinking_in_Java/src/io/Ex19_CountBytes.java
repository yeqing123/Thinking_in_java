package io;
import java.util.*;
import java.io.*;

public class Ex19_CountBytes {
 
	public static void main(String[] args) throws IOException {
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		byte[] bytes = BinaryFile.read("./bin/io/Ex19_CountBytes.class");
		for(byte bt : bytes) {
			Integer freq = map.get(bt);
			map.put(bt, freq == null ? 1 : freq + 1);
		}
		List<Byte> keys = Arrays.asList(map.keySet().toArray(new Byte[0]));
        Collections.sort(keys);
        for(Byte bt : keys)
        	System.out.println(bt + " => " + map.get(bt));
	}
    
}
