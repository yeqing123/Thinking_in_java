package typeinfo;

abstract class Instrument {
	void prepareInstrument() {
		System.out.println("Preparing the " + this.getClass().getSimpleName());
	}
}

class Wind extends Instrument {
	public void clearSpitValve() {
		System.out.println("clearing the spit valve");
	}
	@Override
	public void prepareInstrument() {
		super.prepareInstrument();
		this.clearSpitValve();
	}
}

class Percussion extends Instrument {
	
}

class Electronic extends Instrument {
	
}

class Stringed extends Instrument {
	
}

public class Ex26_ImplementationClearSpitValve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Instrument[] instruments = {new Wind(), new Percussion(), new Electronic(), new Stringed()};
        for(Instrument instrument : instruments)
        	instrument.prepareInstrument();
	}

}
