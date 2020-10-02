package generics;
import java.util.Iterator;
import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import typeinfo.pets.TypeCounter;

public class CoffeeCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TypeCounter counter = new TypeCounter(Coffee.class);
        Iterator<Coffee> iter = new CoffeeGenerator(10).iterator();
        while(iter.hasNext()) {
        	Coffee coffee = iter.next();
        	counter.count(coffee);
        	System.out.print(coffee.getClass().getSimpleName() + " ");
        }
        System.out.println();
        System.out.println(counter);
	}

}
