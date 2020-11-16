package containers;
import java.util.*;

class SlowSet<T> extends AbstractSet<T> {
	private List<T> list = new ArrayList<T>();
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public boolean add(T e) {
		if(!list.contains(e))
		    return list.add(e);
		else
			return false;
	}
}

public class Ex18_SlowSetTest {
	public static void printSet(Set<?> set) {
		System.out.println(set.getClass().getSimpleName());
    	System.out.println("Size: " + set.size());
    	System.out.println(set);
	}
    public static void test(Set<String> set) {
    	set.addAll(Countries.names(10));
    	printSet(set);
    	Iterator<?> it = set.iterator();
    	Object o = it.next();
    	set.remove(o);
    	System.out.println("Remove first element of set: " + o);
    	System.out.println("Size: " + set.size());
    	System.out.println(set);
    	it = set.iterator();
    	for(int i = 0; i < 5; i++)
    		o = it.next();
    	set.remove(o);
    	System.out.println("Remove fifth element of set: " + o);
    	System.out.println(set);
    	System.out.println("Size: " + set.size());
    	set.clear();
    	System.out.println("set.isEmpty(): " + set.isEmpty());
    	set.addAll(Countries.names(10));
    	set.add("China");
    	System.out.println(set);
    	System.out.println("set.contains(\"BOTSWANA\"): " + set.contains("BOTSWANA"));
    }
	public static void main(String[] args) {
        test(new SlowSet<String>());
        test(new LinkedHashSet<String>());
	}

}
