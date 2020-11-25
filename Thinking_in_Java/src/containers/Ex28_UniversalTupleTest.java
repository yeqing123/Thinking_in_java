package containers;
import java.util.*;


interface Tuple {}

class TwoTuple<A, B> implements Tuple, Comparable<Tuple> {
    private static long count = 0;
    private long id = count++;
	public final A first;
	public final B second;
	public TwoTuple(A first, B second) {
		this.first = first;
		this.second = second;
	}
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	public int hashCode() {
		int result = 17;
		result = result * 37 + first.hashCode();
		result = result * 37 + second.hashCode();
		return result;
	}
	@SuppressWarnings("rawtypes")
	public boolean equals(Object o) {
		return o instanceof TwoTuple &&
				((TwoTuple)o).first.equals(first) &&
		        ((TwoTuple)o).second.equals(second);		
	}
	@Override
	public int compareTo(Tuple arg) {
		String firstName = first.getClass().getSimpleName();
		String argFirstName = ((TwoTuple)arg).getClass().getSimpleName();
		int firstCompare = firstName.compareTo(argFirstName);
		if(firstCompare != 0)
			return firstCompare;
		String secondName = second.getClass().getSimpleName();
		String argSecondName = ((TwoTuple)arg).second.getClass().getSimpleName();
	    int secondCompare = secondName.compareTo(argSecondName);
	    if(secondCompare != 0)
	    	return secondCompare;
		return id > ((TwoTuple)arg).id ? 1 : (id == ((TwoTuple)arg).id ? 0 : -1);
	}
}

class ThreeTuple<A, B, C> extends TwoTuple<A, B> implements Comparable<Tuple> {
    private static long count = 0;
    private long id = count++;
	public final C three;
    public ThreeTuple(A first, B second, C three) {
		super(first, second);
        this.three = three;
	}
	public String toString() {
		return "(" + first + ", " + second + ", " + three + ")";
	}
	public int hashCode() {
		return super.hashCode() * 37 + three.hashCode();
	}
	public boolean equals(Object o) {
		return o instanceof ThreeTuple && super.equals(o);
	}
	@Override
	public int compareTo(Tuple arg) {
		int superCompare = super.compareTo(arg);
		if(superCompare != 0)
			return superCompare;
		String threeName = three.getClass().getSimpleName();
		String argThreeName = ((ThreeTuple)arg).three.getClass().getSimpleName();
		int threeCompare = threeName.compareTo(argThreeName);
		if(threeCompare != 0)
			return threeCompare;
		return id > ((ThreeTuple)arg).id ? 1 : (id == ((ThreeTuple)arg).id ? 0 : -1);
	}
}

class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
	private static long count = 0;
	private long id = count++;
	public D four;
	public FourTuple(A first, B second, C three, D four) {
		super(first, second, three);
        this.four = four;
	}
	public String toString() {
		return "(" + first + ", " + second + ", " + three + ", " + four + ")";
	}
	public int hashCode() {
		return super.hashCode() + 37 + four.hashCode();
	}
	public boolean equals(Object o) {
		return o instanceof ThreeTuple && super.equals(o);
	}
//	@Override
//	public int compareTo(Tuple arg) {
//		int superCompare = super.compareTo(arg);
//		if(superCompare != 0)
//			return superCompare;
//		String fourName = four.getClass().getSimpleName();
//		String argFourName = ((FourTuple)arg).four.getClass().getSimpleName();
//		int fourCompare = fourName.compareTo(argFourName);
//		if(fourCompare != 0)
//			return fourCompare;
//		return id > ((FourTuple)arg).id ? 1 : (id == ((FourTuple)arg).id ? 0 : -1);
//	}
}

public class Ex28_UniversalTupleTest {
	@SuppressWarnings({ "null", "unchecked" })
	public static void main(String[] args) {
//        Map<Tuple, Integer> map = new HashMap<Tuple, Integer>();
//        TwoTuple<String, String>[] twoTuples = new TwoTuple[5];
//        ThreeTuple<String, String, Integer>[] threeTuples = new ThreeTuple[5];
//        FourTuple<String, String, Integer, Byte>[] fourTuples = new FourTuple[5];
//        Random rand = new Random(47);
//        byte[] bytes = null;
//        rand.nextBytes(bytes);
//        String[] array = "A B C D E".split(" ");
//        for(int i = 0; i < twoTuples.length; i++) {
//        	twoTuples[i] = new TwoTuple<String, String>("hi", "ye");
//        	map.put(twoTuples[i], i);
//        }
//        for(int i = 0; i < threeTuples.length; i++) {
//        	threeTuples[i] = new ThreeTuple<String, String, Integer>(
//        			"hi", "ye", rand.nextInt(100));
//        	map.put(threeTuples[i], i);
//        }
//        for(int i = 0; i < array.length; i++)
//        	bytes[i] = Byte.valueOf(array[i]);
//        for(int i = 0; i < fourTuples.length; i++) {
//        	fourTuples[i] = new FourTuple<String, String, Integer, Byte>(
//        			"hi", "ye", rand.nextInt(100), bytes[rand.nextInt(bytes.length)]);
//        	map.put(fourTuples[i], i);
//        }
        System.out.println(new TwoTuple<String, String>("hi", "ye"));
        System.out.println(new ThreeTuple<String, String, Integer>("hi", "ye", 10));
	}

}
