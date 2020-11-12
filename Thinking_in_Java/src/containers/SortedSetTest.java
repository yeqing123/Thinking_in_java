package containers;
import java.lang.reflect.Constructor;
import java.util.*;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

// 让元素自身具有比较性，对这种元素集合进行的排序，叫做“自然排序”
class Ordered implements Comparable<Object>  {
    private Integer i;
    public Ordered(Integer i) {
    	this.i = i;
    }
    public int getInt() { return i; }
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Ordered) {
			int j = ((Ordered) o).getInt();
		  	return this.i > j ? 1 : (this.i == j ? 0 : -1);
		} else {
			throw new IllegalStateException();
		}
	}
	public String toString() { return String.valueOf(i); }
}

// 自身不具备比较性的元素，如果要排序需要自己定制比较器
class Unordered {
	private Integer i;
	public Unordered(Integer i) { this.i = i; }
	public int getInt() { return i; }
	public String toString() { return String.valueOf(i); }
}

// 自己定制一个针对Unordered对象的比较器
class CustomComparator implements Comparator<Unordered> {

	@Override
	public int compare(Unordered o1, Unordered o2) {
		// TODO Auto-generated method stub
	  	return o1.getInt() > o2.getInt() ? 1 : 
	  		(o1.getInt() == o2.getInt() ? 0 : -1);
	}
	
}

public class SortedSetTest {
	private static Generator<Integer> gen = new RandomGenerator.Integer(100);
    public static <T> void fill(Collection<T> c, Class<T> type, int quantity) throws Exception {
    	Constructor<T> con = type.getDeclaredConstructor(Integer.class);
    	for(int i = 0; i < quantity; i++)
    		c.add(con.newInstance(gen.next()));
    }
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
        // List集合，其自身不具备排序性质
		List<Ordered> list = new LinkedList<Ordered>();
		// 填充集合元素
        fill(list, Ordered.class, 10);
        // 因为Ordered自身具备比较性，因此无需额外的比较器即可进行排序
        Collections.sort(list);
        System.out.println(list);
        
        List<Unordered> list2 = new LinkedList<Unordered>();
        fill(list2, Unordered.class, 10);
        // 因为Unordered类本身不具备比较性，因此需要加入额外的比较器
        Collections.sort(list2, new CustomComparator());
        System.out.println(list2);
        // TreeSet集合，其自身具备排序性质
        TreeSet<Ordered> set1 = new TreeSet<Ordered>();
        fill(set1, Ordered.class, 10);
        System.out.println(set1);
        // comparator()会返回null，因为使用的是“自然排序”，没有额外的比较器
        System.out.println(set1.comparator());
        // 因为Unordered不具备自比较能力，因此创建TreeSet集合的同时需要向其传递一个额外的比较器
        TreeSet<Unordered> set2 = new TreeSet<Unordered>(new CustomComparator());
        fill(set2, Unordered.class, 10);
        System.out.println(set2);
        System.out.println(set2.comparator());
	}

}
