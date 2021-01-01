package io;
import java.io.*;
import java.util.*;

abstract class Shape2 implements Serializable {
	public static final int RED = 1, BLUE = 2, GREEN = 3;
	private int xPos, yPos, dimension;
	private static Random rand = new Random(47);
	private static int counter = 0;
	public abstract void setColor(int newColor);
	public abstract int getColor();
	public Shape2(int xVal, int yVal, int dim) {
		xPos = xVal;
		yPos = yVal;
		dimension = dim;
	}
	public String toString() {
		return getClass() + "color[" + getColor() + "] xPos[" +
	        xPos + "] yPos[" + yPos + "] dim[" + dimension + "]\n";
	}
	public static Shape randomFactory() {
		int xVal = rand.nextInt(100);
		int yVal = rand.nextInt(100);
		int dim = rand.nextInt(100);
		switch(counter++ % 3) {
		    default:
		    case 0: return new Circle2(xVal, yVal, dim);
		    case 1: return new Square2(xVal, yVal, dim);
		    // Line class in the StoreCADState.java used:
		    case 2: return new Line(xVal, yVal, dim);
		}
	}
}

class Circle2 extends Shape {
	private static int color = RED;
	public Circle2(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}
	public static void serializeStaticState(ObjectOutputStream out) 
		    throws IOException {
			out.writeInt(color);
	}

	public static void deserializeStaticState(ObjectInputStream in)
	    throws IOException {
		color = in.readInt();
	}
	public void setColor(int newColor) { color = newColor; }
	public int getColor() { return color; }
}

class Square2 extends Shape {
	private static int color;
	public Square2(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
		color = RED;
	}
	public static void serializeStaticState(ObjectOutputStream out) 
		    throws IOException {
			out.writeInt(color);
	}

	public static void deserializeStaticState(ObjectInputStream in)
	    throws IOException {
		color = in.readInt();
	}
	public void setColor(int newColor) { color = newColor; }
	public int getColor() { return color; }
}


public class Ex30_RepairCADState {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
         List<Shape> shapes = new ArrayList<Shape>();
         // Make some shapes:
         for(int i = 0; i < 10; i++)
         	shapes.add(Shape.randomFactory());
         // Set all the static colors to GREEN:
         for(int i = 0; i < 10; i++)
         	((Shape)shapes.get(i)).setColor(Shape.GREEN);
         // Save the state vector:
         ObjectOutputStream out = new ObjectOutputStream(
         		new FileOutputStream("./src/io/CADState.out"));
         Circle2.serializeStaticState(out);
         Square2.serializeStaticState(out);
         Line.serializeStaticState(out);
         out.writeObject(shapes);
         // Display the shapes:
         System.out.println(shapes);
         // Now get them back:
         ObjectInputStream in = new ObjectInputStream(
         		new FileInputStream("./src/io/CADState.out"));
         // Read in the same order they were written:
         Circle2.deserializeStaticState(in);
         Square2.deserializeStaticState(in);
         Line.deserializeStaticState(in);
         List<Shape> shapes2 = (List<Shape>)in.readObject();
         System.out.println(shapes2);
    }
}
