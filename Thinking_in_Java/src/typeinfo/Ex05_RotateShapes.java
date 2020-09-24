package typeinfo;

import java.util.Arrays;
import java.util.List;

abstract class RShape {
	void draw() { System.out.println(this + ".draw()"); }
	void rotate(int degreen) {  
        System.out.println("Rotating " + this + " by " + degreen + " degreen.");
	}
	abstract public String toString();
}

class RCircle extends RShape {
	public String toString() { return "Circle"; }
	@Override
	void rotate(int degreen) {
		throw new UnsupportedOperationException();
	}
}

class RTriangle extends RShape {
	public String toString() { return "Triangle"; }
}

class RSquare extends RShape {
	public String toString() { return "Square"; }
}

public class Ex05_RotateShapes {
	public static void rotateAll(List<RShape> shapes) {
		for(RShape shape : shapes) {
			try {
				shape.rotate(45);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
    public static void main(String args[]) {
    	List<RShape> shapes = Arrays.asList(new RCircle(), new RTriangle(), new RSquare());
        rotateAll(shapes);    	
    }
}
