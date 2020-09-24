package typeinfo;

class Rhomboid extends Shape {
	public String toString() { return "Rhomboid"; }
}

public class Ex03_Rhomboid {
	public static void main(String argsp[]) {
		// Upcast to Shape
	    Shape shape = new Rhomboid();
	    shape.draw();
	    // Downcast to Rhomboid
	    Rhomboid rhomboid = (Rhomboid) shape;
	    System.out.println(rhomboid);
	    // Downcast to Circle. Succeed at compile time,
	    // but fails at runtime with a ClassCastException.
	    Circle circle = (Circle) shape;
	}
}
