package containers;
import java.util.*;

public class Ex03_CountriesSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<String> set1 = new HashSet<String>();
        Set<String> set2 = new LinkedHashSet<String>();
        Set<String> set3 = new TreeSet<String>();
        for(int i = 0; i < 5; i++) {
            set1.addAll(Countries.names(10));
            set2.addAll(Countries.names(10));
            set3.addAll(Countries.names(10));
        }
        System.out.println("HashSet: " + set1);
        System.out.println("LinkedHashSet: " + set2);
        System.out.println("TreeSet: " + set3);
	}

}
