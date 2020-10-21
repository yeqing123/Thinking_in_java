package generics;
import java.util.*;
import java.lang.reflect.*;
import typeinfo.pets.*;

class Apply {
	public static <T, S extends Iterable<? extends T>> 
	    void apply(S sqe, Method method, Object... args) {
		for(T t : sqe) {
			try {
			    method.invoke(t, args);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}


public class Ex40_Apply2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        List<Pet> pets = new ArrayList<Pet>();
        pets.add(new Mouse());
        pets.add(new Rat());
        pets.add(new Hamster());
        pets.add(new Gerbil());
        pets.add(new Manx());
        pets.add(new Mutt());
        pets.add(new Cymric());
        pets.add(new Dog());
        pets.add(new Cat());
        Apply.apply(pets, Pet.class.getMethod("speak"));
	}

}
