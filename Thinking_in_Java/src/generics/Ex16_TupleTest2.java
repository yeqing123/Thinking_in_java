package generics;

class Tuple2 extends Tuple {
	public static <A, B, C, D, E, F> SixTuple<A, B, C, D, E, F> 
	tuple(A a, B b, C c, D d, E e, F f) {
		return new SixTuple<A, B, C, D, E, F>(a, b, c, d, e, f);
	}
}

public class Ex16_TupleTest2 {
    public static SixTuple<String, Integer, Character, Boolean, Double, Float> f() {
    	return Tuple2.tuple("hello", 14, 'c', true, 15.55, 1.21F);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(f());
	}

}
