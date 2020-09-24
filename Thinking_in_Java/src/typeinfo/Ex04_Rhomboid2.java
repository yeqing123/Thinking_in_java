package typeinfo;


public class Ex04_Rhomboid2 {
	public static void main(String argsp[]) {
		// Upcast to Shape
	    Shape shape = new Rhomboid();
	    shape.draw();
	    System.out.println("Upcast to Shape.");
	    // Downcast to Circle. 
	    // Because of the use of instanof, so you can check out an error.
	    if (shape instanceof Circle) {
	        Circle circle = (Circle) shape;
	    } else {
	    	System.out.println("This shape not a circle.");
	    }
	}
}
