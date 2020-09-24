package typeinfo.toys;

import static net.mindview.util.Print.*;

// Incorporate a new kind of interface
interface hasCPU {}

class FancierToy extends FancyToy implements hasCPU {}

public class Ex02_ToyTest2 {
    public static void printInfo(Class<?> c) {
    	print("Canonical name: " + c.getCanonicalName());
    	print("Simple name: " + c.getSimpleName());
    	print("Is this class an interface? [" + c.isInterface() + "]");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Class<?> c = null;
		try {
        	c = Class.forName("typeinfo.toys.FancierToy");
        } catch(ClassNotFoundException e) {
        	print("Couldn't found \"FancierToy\"");
        }
	 	printInfo(c);
    	print("Super class: " + c.getSuperclass());
    	Class<?>[] interfaces = c.getInterfaces();
    	print("Its all interfaces of implemented: ");
    	for(Class<?> face : interfaces) {
    		printInfo(face);
    	}
    	print();
    	print(c.getSimpleName() + " superclass instantiated:");
    	Class<?> up = c.getSuperclass();
    	Object obj = null;
    	try {
			obj = up.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			print("Cannot instantiate");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
            print("Cannot access");
		}
    	printInfo(obj.getClass());
	}

}
