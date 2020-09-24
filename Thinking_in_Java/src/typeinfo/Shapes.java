package typeinfo;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

abstract class Shape {
	void draw() { System.out.println(this + ".draw()"); }
	abstract public String toString();
}

class Circle extends Shape {
	public String toString() { return "Circle"; }
}

class Triangle extends Shape {
	public String toString() { return "Triangle"; }
}

class Square extends Shape {
	public String toString() { return "Square"; }
}

public class Shapes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Shape> shapeList = Arrays.asList(new Circle(), new Triangle(), new Square());
        Iterator<Shape> iterator = shapeList.listIterator();
        while (iterator.hasNext()) {
        	iterator.next().draw();
        }
	}

}
