package generics;
import java.util.*;
import generics.coffee.Coffee;
import typeinfo.pets.Person;

class Building {}
class House extends Building {}

public class Ex21_ClassTypeCapture2 {
    Map<String, Class<?>> types = new HashMap<String, Class<?>>();
    public Object createNew(String typename) {
    	Class<?> kind = types.get(typename);
		try {
			return kind.newInstance();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Not a registered typename: " + typename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
    	return null;
    }
    public void addType(String typename, Class<?> kind) {
    	this.types.put(typename, kind);
    }
	public String toString() {
		return this.types.toString();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Ex21_ClassTypeCapture2 ctc1 = new Ex21_ClassTypeCapture2();
		
		ctc1.addType("Building", Building.class);
		ctc1.addType("House", House.class);
		ctc1.addType("String", String.class);
		ctc1.addType("Coffee", Coffee.class);		
		ctc1.addType("Fibonacci", Fibonacci.class);
		ctc1.addType("Person", Person.class);
		ctc1.addType("Integer", Integer.class);
		Building bui = (Building) ctc1.createNew("Building");
		House h = (House) ctc1.createNew("House");
		String s = (String) ctc1.createNew("String");
		Coffee c = (Coffee) ctc1.createNew("Coffee");
		Fibonacci f = (Fibonacci) ctc1.createNew("Fibonacci");
		// Can compile, but runtime failed. Because Integer no default constructor
	//	ctc1.createNew("Integer");
		ctc1.createNew("Bus");
		ctc1.createNew("Person");
		System.out.println(bui.getClass());
		System.out.println(h.getClass());
		System.out.println(s.getClass());
		System.out.println(c.getClass());
		System.out.println(f.getClass());
	}

}
