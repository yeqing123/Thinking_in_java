package containers;
import java.util.*;

import net.mindview.util.RandomGenerator;

public class Ex09_TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<String> treeSet = new TreeSet<String>();
		RandomGenerator.String gen = new RandomGenerator.String();
        for(int i = 0; i < 20; i++)
        	treeSet.add(gen.next());
		System.out.println(treeSet);
	}

}
