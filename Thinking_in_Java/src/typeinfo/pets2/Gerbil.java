package typeinfo.pets2;
import typeinfo.pets.Rodent;

public class Gerbil extends Rodent {
	public static class Factory implements typeinfo.factory.Factory<Gerbil> {
    	public Gerbil create() { return new Gerbil(); }
    }
}
