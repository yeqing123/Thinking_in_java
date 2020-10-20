package generics;

abstract class SelfBounded<T extends SelfBounded<T>> {
	abstract T process(T a);
    T test() {
    	return process(null);
    }
}

class Derived extends SelfBounded<Derived> {
	Derived process(Derived d) { 
	    if(d == null)
		    return this; 
		return d;
	}
}


public class Ex34_SelfBoundTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Derived d = new Derived();
        System.out.println(d.test() == d.process(d));
	}

}
