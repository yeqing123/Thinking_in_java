package generics;

import typeinfo.pets.Hamster;

interface FactoryI<T> {
	T create(Object parameter);
}

class Foo2<T> {
	private T x;
	public <F extends FactoryI<T>> Foo2(F factory, Object parameter) {
		x = factory.create(parameter);
	}
	public T getX() { return x; }
}

class IntegerFactory implements FactoryI<Integer> {
	
	public Integer create(Object p) {
		return new Integer((int) p);
	}
}

class HamsterFactory implements FactoryI<Hamster> {
	public Hamster create(Object s) {
		return new Hamster((String)s);
	}
}

class Widget {
	public static class Factory implements FactoryI<Widget> {
		public Widget create(Object arg) {
			return new Widget();
		}
	}
}

public class Ex23_FactoryConstraint2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Foo2<Integer> f1 = 
        		new Foo2<Integer>(new IntegerFactory(), 11);
        System.out.println(f1.getX());
        Foo2<Hamster> f2 = 
        		new Foo2<Hamster>(new HamsterFactory(), "Hamster01");
        System.out.println(f2.getX());
        Foo2<Widget> f3 = 
        		new Foo2<Widget>(new Widget.Factory(), null);
        System.out.println(f3.getX());
	}

}
