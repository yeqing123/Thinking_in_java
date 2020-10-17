package generics;
import typeinfo.pets.*;

class Generic1<T> {
	private T item;
	void set(T item) {
		this.item = item;
	}
}

class Generic2<T> {
	private T item;
	T get() { return item; }
}


public class Ex28_ExtendsAndSuper {
    public static <T> void f1(Generic1<? super T> gen, T item) {
    	gen.set(item);
    }
    public static <T> T f2(Generic2<? extends T> gen) {
    	return gen.get();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Generic1<Cat> gen1 = new Generic1<Cat>();
        f1(gen1, new EgyptianMau("EgyptianMau"));
        f1(gen1, new Manx("manx"));
        f1(gen1, new Cymric("cymric"));
        // Compile error
        // f(gen1, new Pet("pet"));
        // f(gen1, new Object());
        // Compile error: Mouse is not a Cat.
        // f1(gen1, new Mouse("Mouse"));
        Generic2<Cat> gen2 = new Generic2<Cat>();
        Cat cat = f2(gen2);
        // Compile error
        // EgyptianMau egy = f2(gen2);
        // Cymric cym = f2(gen2);
        Pet p = f2(gen2);
        Individual ind = f2(gen2);
        Object obj = f2(gen2);
	}

}
