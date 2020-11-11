package containers;
import java.lang.reflect.Constructor;
import java.util.*;

import net.mindview.util.RandomGenerator;

// 让元素自身具有比较性，对这种元素集合进行的排序，叫做“自然排序”
class Ordered implements Comparable<Object>  {
    private int i;
    public Ordered(int i) {
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
	private int i;
	public Unordered(int i) { this.i = i; }
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
	static RandomGenerator.Integer gen = new RandomGenerator.Integer(100);
    public static <T> void fill(Collection<?> c, int quantity, Class<?> type) {
    	Constructor<T> con = type.getDeclaredConstructor(Integer.class);
    	for(int i = 0; i < quantity; i++)
    		c.add(con.newInstance(gen.next()));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // List集合，其自身不具备排序性质
		List<Ordered> list = new LinkedList<Ordered>();
        RandomGenerator.Integer gen = new RandomGenerator.Integer(100);
        for(int i = 0; i < 10; i++)
        	list.add(new Ordered(gen.next()));
        // 因为Ordered自身具备比较性，因此无需额外的比较器即可进行排序
        Collections.sort(list);
        System.out.println(list);
        
        List<Unordered> list2 = new LinkedList<Unordered>();
        for(int i = 0; i < 10; i++)
        	list2.add(new Unordered(gen.next()));
        // 因为Unordered类本身不具备比较性，因此需要加入额外的比较器
        Collections.sort(list2, new CustomComparator());
        System.out.println(list2);
        // TreeSet集合，其自身具备排序性质
        TreeSet<Ordered> set1 = new TreeSet<Ordered>();
        
        TreeSet<Unordered> set1 = new TreeSet<Unordered>(new CustomComparator());
        System.out.println(set.comparator().toString());
	}

}
