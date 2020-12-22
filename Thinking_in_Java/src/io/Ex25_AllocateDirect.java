package io;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;
import java.nio.charset.*;


abstract class CompareAllocations {
	private String name;
	private int size;
	protected ByteBuffer buffer;
	public CompareAllocations(String name, int size) {
		this.name = name;
		this.size = size;
	}
	public void runComparison() {
		try {
			System.out.println("Program Name: " + name);
			long startTime = System.nanoTime();
			directAllocate();
			long endTime = System.nanoTime();
			System.out.println("Direct Allocation cost of size <" + size + ">: <"
					+ (endTime - startTime) + ">");
			startTime = System.nanoTime();
			execute();
			endTime = System.nanoTime();
		    System.out.println("Execute direct cost <" + (endTime - startTime) + ">");
		    startTime = System.nanoTime();
		    indirectAllocate();
		    endTime = System.nanoTime();
		    System.out.println("Indirect Allocation cost of size <" + size + ">: <"
					+ (endTime - startTime) + ">");
		    startTime = System.nanoTime();
		    execute();
		    endTime = System.nanoTime();
		    System.out.println("Execute indirect cost <" + (endTime - startTime) + ">");
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void directAllocate() {
		buffer = ByteBuffer.allocateDirect(size);
	}
	public void indirectAllocate() {
		buffer = ByteBuffer.allocate(size);
	}
	public abstract void execute() throws IOException;
}

public class Ex25_AllocateDirect {
	
}
