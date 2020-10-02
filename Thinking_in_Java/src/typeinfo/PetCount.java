package typeinfo;
import typeinfo.pets.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class PetCount {
    static class PetCounter extends HashMap<String, Integer> {
    	public void count(String type) {
    		Integer quantity = get(type);
    		if(quantity == null) 
    			put(type, 1);
    		else 
    			put(type, quantity +1);
    	}
    }
    public static void countPets(PetCreator creator) {
    	PetCounter counter = new PetCounter();
    	for(Pet pet : creator.createArray(20)) {
    		// List each individual pet:
    		printnb(pet.getClass().getSimpleName() + " ");
    		if(pet instanceof Pet)
    			counter.count("Pet");
    		if(pet instanceof Dog)
    			counter.count("Dog");
    		if(pet instanceof typeinfo.pets2.Mutt)
    			counter.count("Mutt");
    		if(pet instanceof typeinfo.pets2.Pug)
    			counter.count("Pug");
    		if(pet instanceof Cat)
    			counter.count("Cat");
    		if(pet instanceof typeinfo.pets2.EgyptianMau)
    			counter.count("EgyptianMau");
    		if(pet instanceof typeinfo.pets2.Manx)
    			counter.count("Manx");
    		if(pet instanceof typeinfo.pets2.Cymric)
    			counter.count("Cymric");
    		if(pet instanceof Rodent)
    			counter.count("Rodent");
    		if(pet instanceof typeinfo.pets2.Rat)
    			counter.count("Rat");
    		if(pet instanceof typeinfo.pets2.Mouse)
    			counter.count("Mouse");
    		if(pet instanceof typeinfo.pets2.Hamster)
    			counter.count("Hamster");
    		if(pet instanceof typeinfo.pets2.Gerbil)
    			counter.count("Gerbil");
    	}
    	// Show the counts:
    	print();
    	print(counter);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        countPets(new ForNameCreator());
	}

}
