// A coffee generator
package generics.coffee;
import java.util.*;
import java.util.Random;

import net.mindview.util.Generator;

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class[] types = { Americano.class, Breve.class, Cappuccino.class,
			Latte.class, Mocha.class};
	private int size = 0;
	private Random rand = new Random(47);
	public CoffeeGenerator() {}
	public CoffeeGenerator(int size) {
		this.size = size;
	}
	public Coffee next() {
		try {
			return (Coffee)types[rand.nextInt(types.length)].newInstance();
		} catch (Exception e) {
		    throw new RuntimeException(e); 
		}
	}
	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;
		public boolean hasNext() { return count > 0; }
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}
	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator();
		for(int i = 0; i < 5; i++)
			System.out.println(gen.next());
		for(Coffee c : new CoffeeGenerator(5))
			System.out.println(c);
	}
}