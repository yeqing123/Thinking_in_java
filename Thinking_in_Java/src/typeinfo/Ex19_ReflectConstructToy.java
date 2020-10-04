package typeinfo;

import java.lang.reflect.Constructor;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface hasCPU {}

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

class FancierToy extends FancyToy implements hasCPU {
	int IQ;
	public FancierToy(int IQ) {
		this.IQ = IQ;
	}
	public String toString() {
		return "I am a FancierToy. I have a CPU.";
	}
}

class SuperToy extends FancierToy {
	int IQ;
	String name;
	public SuperToy(String name, int IQ) {
		super(IQ);
		this.IQ = IQ;
		this.name = name;
	}
	@Override
	public String toString() {
		return "I am a " + this.getClass().getName() + ". I am smarter than you.";
	}
}

public class Ex19_ReflectConstructToy {
	public static Toy makeToy(String name, int IQ) throws Exception {
		Class<?> c = Class.forName(name);
		for(Constructor cotr: c.getConstructors()) {
			// look for a constructor with a single parameter.
			Class<?>[] params = cotr.getParameterTypes();
			if(params.length == 1) {
				if(params[0] == int.class)
				    return (Toy)cotr.newInstance(new Object[]{Integer.valueOf(IQ)});
			}
			if(params.length == 2) {
				if(params[0] == String.class && params[1] == int.class) 
					return (Toy)cotr.newInstance(new Object[] {name, Integer.valueOf(IQ)});
			}
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
	    System.out.println("Create a Toy object through a non-default constructor using a reflection mechanism.");
	    System.out.println(makeToy("typeinfo.FancierToy", 110));
	    System.out.println(makeToy("typeinfo.SuperToy", 150));
	}
}
