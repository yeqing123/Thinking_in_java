package generics;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import generics.watercolors.Watercolors;
import static generics.watercolors.Watercolors.*;

class Sets2 {
	@SuppressWarnings("unchecked")
	public static <T> Set<T> cope(Set<T> s) {
		if(s instanceof EnumSet) 
			return ((EnumSet) s).clone();
		return new HashSet<T>(s);
	}
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = cope(a);
		result.addAll(b);
		return result;
	}
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
		Set<T> result = cope(a);
		result.retainAll(b);
		return result;
	}
	public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
		Set<T> result = cope(superset);
		result.removeAll(subset);
		return result;
	}
	public static <T> Set<T> complement(Set<T> a, Set<T> b) {
		return difference(union(a, b), intersection(a, b));
	}
}

public class Ex17_Sets2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<Watercolors> set1 = 
        		EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 = 
        		EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println("union(set1, set2): " + Sets2.union(set1, set2));
        Set<Watercolors> subset = Sets2.intersection(set1, set2);
        System.out.println("intersection(set1, set2): " + subset);
        System.out.println("difference(set1, subset): " + Sets2.difference(set1, subset));
        System.out.println("difference(set2, subset): " + Sets2.difference(set2, subset));
        System.out.println("complement(set1, set2): " + Sets2.complement(set1, set2));
        
	}

}
