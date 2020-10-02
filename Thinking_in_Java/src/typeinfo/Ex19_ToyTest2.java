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
		// TODO Auto-generated method stub
        Class<?> c = null;
		try {
        	c = Class.forName("typeinfo.toys.FancyToy");
        } catch(ClassNotFoundException e) {
        	print("Couldn't found \"FancyToy\"");
        }
	 	printInfo(c);
    	print("Super class: " + c.getSuperclass());
    	Class<?>[] interfaces = c.getInterfaces();
    	print("Its all interfaces of implemented: ");
    	for(Class<?> i : interfaces) {
    		printInfo(i);
    	}
    	print();
    	print("Call newInstance(): ");
    	Class<?> up = c.getSuperclass();
    	Constructor<?>[] ctors = c.getConstructors();
    	System.out.println(ctors.length);
    	Object obj = null;
		try {
			obj = ctors[0].newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	printInfo(obj.getClass());
	}

}
