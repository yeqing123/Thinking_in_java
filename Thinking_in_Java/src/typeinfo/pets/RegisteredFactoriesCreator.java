package typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import typeinfo.factory.*;

public class RegisteredFactoriesCreator extends PetCreator {
	private Random rand = new Random(47);
    static List<Factory<? extends Pet>> petFactories = 
            new ArrayList<Factory<? extends Pet>>();
    static {
    	petFactories.add(new typeinfo.pets2.Cymric.Factory());
    	petFactories.add(new typeinfo.pets2.EgyptianMau.Factory());
    	petFactories.add(new typeinfo.pets2.Gerbil.Factory());
    	petFactories.add(new typeinfo.pets2.Hamster.Factory());
    	petFactories.add(new typeinfo.pets2.Manx.Factory());
    	petFactories.add(new typeinfo.pets2.Mouse.Factory());
    	petFactories.add(new typeinfo.pets2.Mutt.Factory());
    	petFactories.add(new typeinfo.pets2.Pug.Factory());
    	petFactories.add(new typeinfo.pets2.Rat.Factory());
    }
    @Override
    public Pet randomPet() {
    	int n = rand.nextInt(petFactories.size());
    	return petFactories.get(n).create();
    }
    public List<Class<? extends Pet>> types() {
    	List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    	for(int i = 0; i < petFactories.size(); i++) {
    		types.add(petFactories.get(i).create().getClass());
    	}
    	return types;
    }
}
