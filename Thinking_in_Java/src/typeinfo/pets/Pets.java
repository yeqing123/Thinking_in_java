// Facade to produce a default PetCreator.
package typeinfo.pets;
import java.util.ArrayList;

import typeinfo.pets2.RegisteredFactoriesCreator;

public class Pets {
    public static final PetCreator creator = 
    		new RegisteredFactoriesCreator();
    public static Pet randomPet() {
    	return creator.randomPet();
    }
    public static Pet[] createArray(int size) {
    	return creator.createArray(size);
    }
    public static ArrayList<Pet> arrayList(int size) {
    	return creator.arrayList(size);
    }
}
