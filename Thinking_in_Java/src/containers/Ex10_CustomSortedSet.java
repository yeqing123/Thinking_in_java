package containers;
import java.util.*;


class CustomSortedSet<T> implements SortedSet<T> {
    private final List<T> list;
    public CustomSortedSet() { 
    	list = new LinkedList<T>(); 
    }
    public CustomSortedSet(List<T> list) {
    	this.list = list;
    	this.list.sort(null);
    }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return list.toArray(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		checkForNull(e);
		// 查找插入位置
		int ip = Collections.binarySearch((List<Comparable<T>>)list, e);
		if(ip < 0) {   // 当ip值小于0，则表示list集合中没有该元素
			ip = -ip - 1;  // (-(insertion point) - 1)，即为插入位置
			if(ip == list.size())
				list.add(e);
			else
				list.add(ip, e);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		checkForNull(o);
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		checkForNull(c);
		checkForNullElements(c);
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		checkForNull(c);
		checkForNullElements(c);
		boolean flag = false;
		for(T e : c)
			flag |= add(e);  // 只要list集合发生改变即返回true
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		checkForNull(c);
		checkForNullElements(c);
		return list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		checkForNull(c);
		checkForNullElements(c);
		return list.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	@Override
	public Comparator<? super T> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomSortedSet<T> subSet(T fromElement, T toElement) {
		// TODO Auto-generated method stub
		int fromIndex = list.indexOf(fromElement);
		int toIndex = list.indexOf(toElement);
		checkForValidIndex(fromIndex);
		checkForValidIndex(toIndex);
		try {
		    return new CustomSortedSet<T>(list.subList(fromIndex, toIndex));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public CustomSortedSet<T> headSet(T toElement) {
		// TODO Auto-generated method stub
		int toIndex = list.indexOf(toElement);
		checkForValidIndex(toIndex);
		try {
		    return new CustomSortedSet<T>(list.subList(0, toIndex));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public CustomSortedSet<T> tailSet(T fromElement) {
		// TODO Auto-generated method stub
		int fromIndex = list.indexOf(fromElement);
		checkForValidIndex(fromIndex);
		try {
		    return new CustomSortedSet<T>(list.subList(fromIndex, list.size()));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		try {
		    return list.get(0);
		} catch(IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		try {
		    return list.get(list.size() - 1);
		} catch(IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}
	public String toString() { return list.toString(); }
	private void checkForValidIndex(int index) {
		if(index == -1)
			throw new IllegalArgumentException();
	}
	private void checkForNull(Object o) { 
		if(o == null) 
		    throw new NullPointerException(); 
	}
	private void checkForNullElements(Collection<?> c) {
		for(Iterator<?> it = c.iterator(); it.hasNext();)
			if(it.next() == null)
				throw new NullPointerException();
	}
}

public class Ex10_CustomSortedSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Used non-argument constructors:");
		SortedSet<String> sortedSet = new CustomSortedSet<String>();
		Collections.addAll(sortedSet, ("One Two Three Four Five Six Seven Eight Nine Ten").split(" "));
        System.out.println(sortedSet);
        sortedSet.add("Eleven");
        System.out.println("add \"Eleven\":" + sortedSet);
        sortedSet.add("Twelve");
        System.out.println("add \"Twelve\":" + sortedSet);
        System.out.println("first():" + sortedSet.first());
        System.out.println("last():" + sortedSet.last());
        System.out.println("subSet(\"Eleven\", \"One\"):" + 
            sortedSet.subSet("Eleven", "One"));
        System.out.println("headSet(\"Nine\")" + sortedSet.headSet("Nine"));
        System.out.println("tailSet(\"Six\")" + sortedSet.tailSet("Six"));
        List<String> subList = Arrays.asList(("One Two Three Four").split(" "));
        System.out.println("Used iterator output:");
        for(Iterator<String> it = sortedSet.iterator(); it.hasNext();)
        	System.out.println(it.next());
        System.out.println("containAll():" + sortedSet.containsAll(subList));
        System.out.println("retainAll():" + sortedSet.retainAll(subList));
         
        System.out.println("========================================");
        System.out.println("Used argument constructors:");
        List<String> list2 = new LinkedList<String>(Countries.names(10));
        Collections.shuffle(list2);
        System.out.println("Non-order: " + list2);
        SortedSet<String> sortedSet2 = new CustomSortedSet<String>(list2);
        System.out.println("order: " + sortedSet2);
        // The checkForNullElement() method will throw NullPointerException exception, 
        // if you add a set containing NULL elements.
        List<String> list = Arrays.asList(null, null, null);
        sortedSet.addAll(list);
	}

}
