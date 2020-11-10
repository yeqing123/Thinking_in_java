package containers;
import java.util.*;

import net.mindview.util.RandomGenerator;

public class Ex09_TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Set<String> treeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		RandomGenerator.String gen = new RandomGenerator.String();
        treeSet.addAll(CollectionData.list(gen, 10));
		System.out.println(treeSet);
	}

}
