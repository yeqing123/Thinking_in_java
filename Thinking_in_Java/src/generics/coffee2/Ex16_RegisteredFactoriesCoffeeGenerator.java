// Generate different types of Coffee:
package generics.coffee2;
import java.util.*;
import net.mindview.util.*;
import typeinfo.factory.Factory;

public class Ex16_RegisteredFactoriesCoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private static List<Factory<? extends Coffee>> coffeeFactories = Arrays.asList(
    		new Americano.Factory(), new Breve.Factory(), new Cappuccino.Factory(),
    		new Latte.Factory(), new Mocha.Factory());
    private static Random rand = new Random(47);
    public Ex16_RegisteredFactoriesCoffeeGenerator() {}
    // For iteration:
    private int size = 0;
    public Ex16_RegisteredFactoriesCoffeeGenerator(int sz) { size = sz; }
    public Coffee next() {
    	int n = rand.nextInt(coffeeFactories.size());
        return coffeeFactories.get(n).create();
    }
    class CoffeeIterator implements Iterator<Coffee> {
    	int count = size;
    	public boolean hasNext() { return count > 0; }
    	public Coffee next() {
    		count--;
    		return Ex16_RegisteredFactoriesCoffeeGenerator.this.next();
    	}
    	public void remove() {  // Not implemented
    		throw new UnsupportedOperationException();
    	}
    }
    public Iterator<Coffee> iterator() {
    	return new CoffeeIterator();
    }
    public static void main(String[] args) {
    	Ex16_RegisteredFactoriesCoffeeGenerator gen = new Ex16_RegisteredFactoriesCoffeeGenerator();
    	for(int i = 0; i < 5; i++)
    		System.out.println(gen.next());
    	for(Coffee c : new Ex16_RegisteredFactoriesCoffeeGenerator(5))
    		System.out.println(c);
    }
}
