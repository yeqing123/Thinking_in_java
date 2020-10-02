package generics.coffee2;

public class Cappuccino extends Coffee {
	public static class Factory implements typeinfo.factory.Factory<Cappuccino> {
		public Cappuccino create() { return new Cappuccino(); }
	}
}
