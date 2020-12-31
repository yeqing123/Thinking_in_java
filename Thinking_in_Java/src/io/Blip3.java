package io;
import java.io.*;

public class Blip3 implements Externalizable {
    protected int i;
    protected String s;
    public Blip3() {
    	System.out.println("Blip3 default constructor");
    	// i, s not initialized
    }
    public Blip3(int i, String s) {
    	System.out.println("Blip3(int i, String s)");
    	this.i = i;
    	this.s = s;
    	// i & s initialized only in non-default constructor.
    }
    public String toString() { return s + i; }
	@Override
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("Blip3.readExternal");
		// You must do this:
		this.i = in.readInt();
		this.s = (String)in.readObject();
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip3.writeExternal");
		// You must do this:
		out.writeInt(i);
		out.writeObject(s);
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3(47, "A String ");
        System.out.println(b3);
        ObjectOutputStream out = new ObjectOutputStream(
        		new FileOutputStream("./src/io/Blip3.out"));
        System.out.println("Saving object:");
        out.writeObject(b3);
        out.close();
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(
        		new FileInputStream("./src/io/Blip3.out"));
        System.out.println("Recovering object:");
        b3 = (Blip3)in.readObject();
        System.out.println(b3);
	}

}
