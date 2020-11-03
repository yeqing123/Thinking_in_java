package containers;
import java.util.*;

import net.mindview.util.Generated;

public class Ex01_CountryList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random(47);
        List<String> list = new ArrayList<String>(Countries.names(10));
        Collections.sort(list);
        System.out.println("sorted: " + list);
        System.out.println("==============================");
        for(int i = 0; i < 10; i++) {
        	Collections.shuffle(list, r);
        	System.out.println("shuffled " + i + ": " + list);
        }
	}

}
