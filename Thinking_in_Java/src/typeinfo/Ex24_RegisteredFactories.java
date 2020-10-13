// Registering Class Factories in the base class.
package typeinfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mindview.util.Null;
import typeinfo.factory.Factory;

class NullPartProxyHandler implements InvocationHandler {
	private String nullName;
	private IPart proxied = new NPart();
	NullPartProxyHandler(Class<? extends IPart> type) {
		nullName = type.getSimpleName() + ": " + "[Null Part]";
	}
	private class NPart implements Null, IPart {
		public String toString() { return nullName; }
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(proxied, args);
	}
}

interface IPart {}

class Part3 implements IPart {
	public String toString() {
		return getClass().getSimpleName();
	}
	static List<Factory<? extends Part3>> partFactories = 
			new ArrayList<Factory<? extends Part3>>();
	static {
		// Collections.addAll() gives an "unchecked generic
		// array creation ... for varargs parameter" warning.
		partFactories.add(new FuelFilter3.Factory());
		partFactories.add(new AirFilter3.Factory());
		partFactories.add(new CabinAirFilter3.Factory());
		partFactories.add(new OilFilter3.Factory());
		partFactories.add(new FanBelt3.Factory());
		partFactories.add(new PowerSteeringBelt3.Factory());
		partFactories.add(new GeneratorBelt3.Factory());
		
	}
	private static Random rand = new Random(47);
	public static Part3 createRandom() {
		int n = rand.nextInt(partFactories.size());
		return partFactories.get(n).create();
	}
	public static IPart newNullPart(Class<? extends IPart> type) {
		return (IPart)Proxy.newProxyInstance(
				Part3.class.getClassLoader(), 
				new Class[] {IPart.class, Null.class}, 
				new NullPartProxyHandler(type));
	}
}


class Filter3 extends Part3 {}

class FuelFilter3 extends Filter3 {
	// Create a Class Factory for each specific type:
	public static class Factory 
	implements typeinfo.factory.Factory<FuelFilter3> {
		public FuelFilter3 create() { return new FuelFilter3(); }
	}
}

class AirFilter3 extends Filter3 {
	public static class Factory
	implements typeinfo.factory.Factory<AirFilter3> {
		public AirFilter3 create() { return new AirFilter3(); }
	}
}

class CabinAirFilter3 extends Filter3 {
	public static class Factory
	implements typeinfo.factory.Factory<CabinAirFilter3> {
		public CabinAirFilter3 create() {
			return new CabinAirFilter3();
		}
	}
}

class OilFilter3 extends Filter3 {
	public static class Factory
	implements typeinfo.factory.Factory<OilFilter3> {
		public OilFilter3 create() { return new OilFilter3(); }
	}
}

class Belt3 extends Part3 {}

class FanBelt3 extends Belt3 {
	public static class Factory
	implements typeinfo.factory.Factory<FanBelt3> {
		public FanBelt3 create() { return new FanBelt3(); }
	}
}

class GeneratorBelt3 extends Belt3 {
	public static class Factory
	implements typeinfo.factory.Factory<GeneratorBelt3> {
		public GeneratorBelt3 create() {
			return new GeneratorBelt3();
		}
	}
}

class PowerSteeringBelt3 extends Belt3 {
	public static class Factory
	implements typeinfo.factory.Factory<PowerSteeringBelt3> {
		public PowerSteeringBelt3 create() {
			return new PowerSteeringBelt3();
		}
	}
}

public class Ex24_RegisteredFactories {
	
    public static void main(String[] args) {
    	for(int i = 0; i < 10; i++) {
    		Part3 part = Part3.createRandom();
    		System.out.println(part);
    	    System.out.println(Part3.newNullPart(part.getClass()));
    	}
    }
}
