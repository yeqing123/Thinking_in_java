package io;
import java.io.*;

public class SerialCtl implements Serializable {
    private String a;
    private transient String b;
    public SerialCtl(String aa, String bb) {
    	a = "Not transient: " + aa;
    	b = "Transient: " + bb;
    }
    private void writeObject(ObjectOutputStream stream) throws IOException {
    	stream.defaultWriteObject();
    	stream.writeObject(b);
    }
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException {
    	stream.defaultReadObject();
    	b = (String)stream.readObject();
    }
    public String toString() { return a + "\n" + b; }
	public static void main(String[] args) 
			throws Exception, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before: \n" + sc);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bout);
		out.writeObject(sc);
		// Now get it back:
		ObjectInputStream in = new ObjectInputStream(
				new ByteArrayInputStream(bout.toByteArray()));
		SerialCtl sc2 = (SerialCtl)in.readObject();
		System.out.println("After: \n" + sc2);
	}

}
