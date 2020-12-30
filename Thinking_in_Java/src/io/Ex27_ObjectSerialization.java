package io;
import java.io.*;

class Thing1 implements Serializable {
	private Thing2 next;
	public Thing1(int id) { this.next = new Thing2(id); }
	public String toString() { return "Thing1(Thing2(" + next + "))"; }
}

class Thing2 implements Serializable {
	private int n;
	public Thing2(int n) { this.n = n; }
	public String toString() { return Integer.toString(n); }
}

public class Ex27_ObjectSerialization {
   
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Thing1 t = new Thing1(2);
        System.out.println("t = " + t);
		ObjectOutputStream out = new ObjectOutputStream(
        		new FileOutputStream("./src/io/thing1.out"));
        out.writeObject("Thing1 storage\n");
        out.writeObject(t);
        out.close();
        ObjectInputStream in = new ObjectInputStream(
        		new FileInputStream("./src/io/thing1.out"));
        String s = (String)in.readObject();
        Thing1 t1 = (Thing1)in.readObject();
        System.out.println(s + "t1 = " + t1);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Thing1 storage\n");
        out2.writeObject(t);
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(
        		new ByteArrayInputStream(bout.toByteArray()));
        s = (String)in2.readObject();
        Thing1 t2 = (Thing1)in2.readObject();
        System.out.println(s + "t2 = " + t2);
	}

}
