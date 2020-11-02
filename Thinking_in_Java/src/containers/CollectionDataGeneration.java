package containers;
import java.util.*;
import net.mindview.util.RandomGenerator;

public class CollectionDataGeneration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> list = new ArrayList<String>(
        		CollectionData.list(new RandomGenerator.String(9), 10));
        System.out.println(list);
        Set<Integer> set = new LinkedHashSet<Integer>(
        		CollectionData.list(new RandomGenerator.Integer(1000), 10));
        System.out.println(set);
	}

}
