package io;
import java.io.*;

public class Ex28_BlipCheck implements Externalizable {
//	public Ex28_BlipCheck() {
//		System.out.println("BlipCheck constructor");
//	}
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("BlipCheck.writeExternal");
	}
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("BlipCheck.readExternal");
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        // To make it run with Ant.
		Blips2.main(args);
	}

}


class Blips2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects: ");
        Blip1 b1 = new Blip1();
        Ex28_BlipCheck b2 = new Ex28_BlipCheck();
        ObjectOutputStream out = new ObjectOutputStream(
        		new FileOutputStream("./src/io/Blips.out"));
        System.out.println("Saving objects:");
        out.writeObject(b1);
        out.writeObject(b2);
        out.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
        		new FileInputStream("./src/io/Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1)in.readObject();
        // OOPS! Throw an exception:
        System.out.println("Recovering b2:");
        b2 = (Ex28_BlipCheck)in.readObject();
	}

}