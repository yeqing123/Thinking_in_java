package typeinfo;

import static net.mindview.util.Print.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
	// Comment out the following default constructor 
	// to see NoSuchMethodError from (*1*)
	Toy() {}
	Toy(int i) {}
}

class FancyToy extends Toy 
    implements HasBatteries, Waterproof, Shoots {
	FancyToy() { super(1); }
}

public class Ex19_ToyTest2 {
    public static void printInfo(Class<?> c) {
    	print("Canonical name: " + c.getCanonicalName());
    	print("Simple name: " + c.getSimpleName());
    	print("Is this class an interface? [" + c.isInterface() + "]");
    }
	public static void main(String[] args) {
		
	}

}
