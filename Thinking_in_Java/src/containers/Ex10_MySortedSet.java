package containers;
import java.util.*;

import net.mindview.util.RandomGenerator;

interface MySortedSet<E> extends Set<E> {
	Comparator<? super E> comparator();
	E first();
	MySortedSet<E> headSet(E toElement);
	E last();
	MySortedSet<E> subSet(E fromElement, E toElement);
	MySortedSet<E> tailSet(E fromElement);
}

class MySortedSetImpl<T> implements MySortedSet<T> {
    private final LinkedList<T> list;
    public MySortedSetImpl() { 
    	list = new LinkedList<T>(); 
    	list.sort(null);
    }
    public MySortedSetImpl(List<T> list2) {
    	this.list = (LinkedList<T>) list2;
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
		checkForValidIndex(ip);
		if(ip < 0) {   // 当ip值小于0，则表示list集合中没有该元素
			ip = -ip - 1;  // (-（insertion point） - 1)，即为插入位置
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
	public MySortedSet<T> subSet(T fromElement, T toElement) {
		// TODO Auto-generated method stub
		int fromIndex = list.indexOf(fromElement);
		int toIndex = list.indexOf(toElement);
		checkForValidIndex(fromIndex);
		checkForValidIndex(toIndex);
		try {
		    return new MySortedSetImpl<T>(list.subList(fromIndex, toIndex));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public MySortedSet<T> headSet(T toElement) {
		// TODO Auto-generated method stub
		int toIndex = list.indexOf(toElement);
		checkForValidIndex(toIndex);
		try {
		    return new MySortedSetImpl<T>(list.subList(0, toIndex));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public MySortedSet<T> tailSet(T fromElement) {
		// TODO Auto-generated method stub
		int fromIndex = list.indexOf(fromElement);
		checkForValidIndex(fromIndex);
		try {
		    return new MySortedSetImpl<T>(list.subList(fromIndex, list.size()));
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return list.getFirst();
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		return list.getLast();
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

public class Ex10_MySortedSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        RandomGenerator.String gen = new RandomGenerator.String();
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < 10; i++)
        	list.add(gen.next());
        System.out.println("LinkedList:" + list);
        MySortedSet<String> sortedSet = new MySortedSetImpl<String>(list);
        System.out.println("MySortedSet:" + sortedSet);
	}

}
