package containers;

import java.util.*;

interface MySortedSet<E> extends Set<E>{
    Comparator<? super E> comparator();
    E first();
    SortedSet<E> headSet(E toElement);
    E last();
    SortedSet<E> subSet(E fromElement, E toElement);
    SortedSet<E> taiSet(E fromElement);
}

class MySortedSetImpl<T> implements MySortedSet<T> {
    private List<T> list = new LinkedList<T>();
	@Override
	public Comparator<? super T> comparator() {
		// TODO Auto-generated method stub
		return this.comparator();
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> headSet(T toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> taiSet(T fromElement) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

public class Ex10_MySortedSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
