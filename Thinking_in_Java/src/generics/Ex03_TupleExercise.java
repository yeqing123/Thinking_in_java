package generics;
import net.mindview.util.*;

class Amphibian {}
class Vehicle {}
class Instrument {}

@SuppressWarnings("hiding")
class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
	public final F sixth;
	public SixTuple(A a, B b, C c, D d, E e, F f) {
		super(a, b, c, d, e);
		this.sixth = f;
	}
	public String toString() {
		return "(" + first + ", " + second + ", " + third + 
				", " + fourth + ", " + fifth + ", " +  sixth + ")";
	}
}

public class Ex03_TupleExercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SixTuple<String, Integer, Double, Amphibian, Vehicle, Instrument> sixTuple = 
        		new SixTuple<String, Integer, Double, Amphibian, Vehicle, Instrument>(
        				"hello world", 11, 14.5, new Amphibian(), new Vehicle(), new Instrument());
        System.out.println(sixTuple);
	}

}
