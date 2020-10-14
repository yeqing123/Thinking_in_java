package generics;

import java.util.*;

import net.mindview.util.*;

class BigFish {
	private static long counter = 1;
	private final long id = counter++;
	private BigFish() {}
	public String toString() { return "BigFish " + id; }
	// A single Generator object:
	public static Generator<BigFish> generator = 
		new Generator<BigFish>() {
		    public BigFish next() { return new BigFish(); }
	    };
}

class LitterFish {
	private static long counter = 1;
	private final long id = counter++;
	private LitterFish() {}
	public String toString() { return "LitterFish " + id; }
	// A method to produce Generator objects:
	public static Generator<LitterFish> generator() {
		return new Generator<LitterFish>() {
			public LitterFish next() { return new LitterFish(); }
		};
	}
}

public class Ex18_OceanLife {
    public static void action(BigFish bf, LitterFish lf) {
    	System.out.println(bf + " ate " + lf);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Random rand = new Random(47);
        List<LitterFish> preys = new LinkedList<LitterFish>();
        Generators.fill(preys, LitterFish.generator(), 20);
        List<BigFish> predators = new ArrayList<BigFish>();
        Generators.fill(predators, BigFish.generator, 5);
        for(LitterFish lf : preys) {
        	BigFish bf = predators.get(rand.nextInt(predators.size()));
        	action(bf, lf);
        }
	}

}
