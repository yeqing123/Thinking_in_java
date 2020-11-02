package arrays.exercise;

import java.util.*;


public class Ex25_JavaLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Integer> aList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(aList.getClass().getSimpleName());
        System.out.println(aList);
        System.out.println(aList.get(4));
        aList.add(6);
        aList.addAll(Arrays.asList(7, 8));
        System.out.println(aList);
        List<Integer> aSlice = aList.subList(2, 4);
        System.out.println(aSlice);
        class MyList<T> extends ArrayList<T> {
        	public MyList(Collection<? extends T> c) {
        		super(c);
        	}
        	public MyList(int initialCapacity) {
        		super(initialCapacity);
        	}
        	public List<T> getReversed() {
        		List<T> reversed = new MyList<T>(this.size());
        		ListIterator<T> iter = this.listIterator(this.size());
        		while(iter.hasPrevious()) {
        			reversed.add(iter.previous());
        		}
        		return reversed;
        	}
        }
        MyList<Integer> list2 = new MyList<Integer>(aList);
        System.out.println(list2.getClass().getSimpleName());
        System.out.println(list2.getReversed());
	}

}
