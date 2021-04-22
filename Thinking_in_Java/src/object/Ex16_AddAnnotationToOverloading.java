package object;

//: initialization/Overloading.java 
//Demonstration of both constructor 
//and ordinary method overloading. 
import static net.mindview.util.Print.*;

/** A class used to represent a tree.
 * @author yeqing
 * @version 1.0
 */
class Tree {
	int height;

	Tree() {
		print("Planting a seedling");
		height = 0;
	}

	Tree(int initialHeight) {
		height = initialHeight;
		print("Creating new Tree that is " + height + " feet tall");
	}
    /** Used to print the height of tree.
     * @param param No param
     * @throws exception No exception
     */
	void info() {
		print("Tree is " + height + " feet tall");
	}
	/** Used to print the height of tree, and attach a string argument.
     * @param s A string argument
     * @throws exception No exception
     */
	void info(String s) {
		print(s + ": Tree is " + height + " feet tall");
	}
}

public class Ex16_AddAnnotationToOverloading {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Tree t = new Tree(i);
			t.info();
			t.info("overloaded method");
		}
// Overloaded constructor: 
		new Tree();
	}
}