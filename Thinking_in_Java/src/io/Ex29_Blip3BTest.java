package io;
import java.io.*;

class Blip3B extends Blip3 {
	public Blip3B() {}
	public Blip3B(int i, String s) {
		super(i, s);
	}
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("Blip3B.readExternal");
		// You must do this:
	//	this.i = in.readInt();
	//	this.s = (String)in.readObject();
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip3B.writeExternal");
		// You must do this:
	//	out.writeInt(i);
	//	out.writeObject(s);
	}
}

public class Ex29_Blip3BTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		   System.out.println("Constructing objects:");
	        Blip3B b3 = new Blip3B(47, "A String ");
	        System.out.println(b3);
	        ObjectOutputStream out = new ObjectOutputStream(
	        		new FileOutputStream("./src/io/Blip3B.out"));
	        System.out.println("Saving object:");
	        out.writeObject(b3);
	        out.close();
	        // Now get it back:
	        ObjectInputStream in = new ObjectInputStream(
	        		new FileInputStream("./src/io/Blip3B.out"));
	        System.out.println("Recovering object:");
	        b3 = (Blip3B)in.readObject();
	        System.out.println(b3);
	}

}
