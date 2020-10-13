package generics;
import net.mindview.util.*;

class Tuple {
	public static <A,B> TwoTuple<A, B> tuple(A a, B b) {
		return new TwoTuple<A, B>(a, b);
	}
	public static <A,B,C> ThreeTuple<A,B,C> tuple(A a, B b, C c) {
		return new ThreeTuple<A,B,C>(a, b, c);
	}
	public static <A,B,C,D> FourTuple<A,B,C,D> tuple(A a, B b, C c, D d) {
		return new FourTuple<A,B,C,D>(a, b, c, d);
	}
	public static <A,B,C,D,E> FiveTuple<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) {
		return new FiveTuple<A,B,C,D,E>(a, b, c, d, e);
	}
}

public class Ex15_TupleTest {
    public static TwoTuple<Integer, String> f() {
    	return Tuple.tuple(10, "hi");
    }
    public static TwoTuple f2() {
    	return Tuple.tuple(true, 'c');
    }
    public static ThreeTuple<String, Integer, Character> g() {
    	return Tuple.tuple("hi", 12, 'a');
    }
    public static FourTuple<Boolean, Double, String, Long> h() {
    	return Tuple.tuple(false, 12.5, "hello", 100L);
    }
    public static FiveTuple<String, Float, Integer, Double, Character> y() {
    	return Tuple.tuple("world", 11.5F, 14, 10.6, 'd');
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TwoTuple<Integer, String> ttle = f();
        System.out.println(ttle);
        // Compiler issued a warning
        System.out.println((TwoTuple<Boolean, Character>)f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(y());
	}

}
