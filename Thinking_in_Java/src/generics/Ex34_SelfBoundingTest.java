package generics;

abstract class SelfBounding<T extends SelfBounding<T>> {
	abstract T f(T a);
	public T g() {
		return f(null);
	}
}

class Derived extends SelfBounding<Derived> {
	public Derived f(Derived d) {
		if(d == null)
		    return this;
		return d;
	}
}


public class Ex34_SelfBoundingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Derived d = new Derived();
        System.out.println(d.f(d).getClass().getSimpleName());
        System.out.println(d.g().getClass().getSimpleName());
	}

}
