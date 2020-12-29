package io;
import java.io.*;
import java.util.*;

class Data implements Serializable {
	private int n;
	public Data(int n) { this.n = n; }
	public String toString() { return Integer.toString(n); }
}

public class Worm implements Serializable {
	private static Random rand = new Random(47);
	private char c;
	private Data[] d = {
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10))
	};
	private Worm next;
	// Value of i == number of segments
	public Worm(int i, char x) {
		System.out.println("Worm constructor: " + i);
		this.c = x;
		if(--i > 0)
		    this.next = new Worm(i, (char)(x + 1));
	}
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for(Data dat : d)
			result.append(dat);
		result.append(")");
		if(next != null)
		    result.append(next);
		return result.toString();
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("./src/io/worm.out"));
		Worm w = new Worm(6, 'a');
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close();
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("./src/io/worm.out"));
		String s = (String)in.readObject();
		Worm w2 = (Worm)in.readObject();
		System.out.println(s + "w2 = " + w2);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out2 = new ObjectOutputStream(bout);
		out2.writeObject("Worm storage\n");
		out2.writeObject(w);
		ObjectInputStream in2 = new ObjectInputStream(
				new ByteArrayInputStream(bout.toByteArray()));
		s = (String)in2.readObject();
		Worm w3 = (Worm)in2.readObject();
		System.out.println(s + "w3 = " + w3);
	}
}