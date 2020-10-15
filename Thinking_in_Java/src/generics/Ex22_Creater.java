package generics;

import java.lang.reflect.Constructor;
import typeinfo.pets.Hamster;

class Employee {
	int id;
	String name;
	public Employee (Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public String toString() { 
		return "Employee :: id: " + id + ", name: " + name; 
	}
}

class Factory<T> {
	Class<T> type;
	public Factory(Class<T> type) {
		this.type = type;
	}
	public T create(Object... parameters) {
		Constructor<?> con;
		Class<?>[] ptypes;
		if(parameters != null) {
			ptypes = new Class<?>[parameters.length];
			for(int i = 0; i < parameters.length; i++)
				ptypes[i] = parameters[i].getClass();
			try {
			con = type.getConstructor(ptypes);
    		return type.cast(con.newInstance(parameters));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
			    return type.newInstance();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
    }
}

public class Ex22_Creater {
  
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Factory<Employee> fe = new Factory<Employee>(Employee.class);
		Employee e = fe.create(11011, "yeqing");
		Factory<Fibonacci> ff = new Factory<Fibonacci>(Fibonacci.class);
        Fibonacci f = ff.create();
        Factory<Hamster> fh = new Factory<Hamster>(Hamster.class);
        Hamster h = fh.create();
		Factory<Boolean> fb = new Factory<Boolean>(Boolean.class);
        // Can compile,but runtime failed. Because Boolean no default constructor.
		// So can used a constructor with parameters.
		//Boolean b = fb.create();
		Boolean b = fb.create("true");
        System.out.println(e);
        System.out.println(f);
        System.out.println(h);
        System.out.println(b);
	}

}
