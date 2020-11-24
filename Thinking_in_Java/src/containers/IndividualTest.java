package containers;
import java.util.*;
import typeinfo.pets.*;
import holding.MapOfList;

public class IndividualTest {

	public static void main(String[] args) {
        Set<Individual> pets = new TreeSet<Individual>();
        for(List<? extends Pet> lp :
        	MapOfList.petPeople.values())
        	for(Pet p : lp)
        		pets.add(p);
        System.out.println(pets);
	}

}
