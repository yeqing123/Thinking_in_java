package containers;

import net.mindview.util.Generator;
import java.util.*;

class Goverment implements Generator<String> {
	String[] foundation = ("strange women lying in ponds " + 
			 "distributing swords is no basis for a system of " + 
			  "government").split(" ");
	int index = 0;
	public String next() {
		return foundation[index++];
	}
}

public class CollectionDataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Goverment(), 10));
        System.out.println(set);
        // Using static method "list()"
        set = new LinkedHashSet<String>(CollectionData.list(new Goverment(), 15));
        System.out.println(set);
	}

}
