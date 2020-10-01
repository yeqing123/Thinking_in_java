package typeinfo.pets2;

public class Cymric extends Manx {
    public static class Factory implements typeinfo.factory.Factory<Cymric> {
    	public Cymric create() { return new Cymric(); }
    }
}
