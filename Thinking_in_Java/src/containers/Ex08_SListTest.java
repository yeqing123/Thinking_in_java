package containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


interface SListIterator<E> {
	boolean hasNext();
	E next();
	void set(E e);
	void add(E e);
	void remove();
}

class SList<E> {
	private Link<E> head = new Link<E>(null);
	public SList() {}
	public SList(Collection<E> c) {
		SListIterator<E> it = this.iterator();
		for(E e : c)
			it.add(e);
	}
	static class Link<E> {
		E e;
		Link<E> next = null;
		private Link(E e) {
			this.e = e;
		}
	}
	class Iter implements SListIterator<E> {
		private Link<E> point = head;
		private Link<E> previous = null;
		@Override
		public boolean hasNext() {
			return point.next != null;
		}
		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			previous = point;
			point = point.next;
			return point.e;
		}
		@Override
		public void add(E e) {
			Link<E> newLink = new Link<E>(e);
			newLink.next = point.next;
			point.next = newLink;
			previous = point;
			point = newLink;
		}
		@Override
		public void remove() {
			if(point == head) {
				throw new IllegalStateException();
			} else {
			    previous.next = point.next;
			    previous = point;
			    point = point.next;
			}
		}
        @Override
		public void set(E e) {
			point.e = e;
		}
	}
	public SListIterator<E> iterator() { return new Iter(); }
	public String toString() {
		SListIterator<E> it = iterator();
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		while(it.hasNext()) {
			buffer.append(it.next().toString());
			if(it.hasNext()) { buffer.append(", "); }
		}
		buffer.append("]");
		return buffer.toString();
	}
}

public class Ex08_SListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SList<String> slist1 = new SList<String>(Countries.names(5));
        System.out.println("slist1: " + slist1);
        SList<String> slist2 = new SList<String>();
        SListIterator<String> it = slist2.iterator();
        for(int i = 0; i < 10; i++) {
        	it.add("element--" + i);
        }
        // 已经遍历到最后一个了，可以将SListIterator做成循环迭代器
     //   System.out.println(it.next());
        System.out.println("slist2: " + slist2);
        it = slist2.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        it.remove();
        
        System.out.println("it.remove():" + slist2);
        it.add("element--x");
        System.out.println("it.add(\"element--x\"): " + slist2);
        it.add("element--y");
        System.out.println("it.add(\"element--y\"): " + slist2);
        
        
//        List<String> list = new ArrayList<String>(Countries.names(5));
//        ListIterator<String> lit = list.listIterator();
//       
//        for(int i = 0; i < 10; i++) {
//        	lit.add("element--" + i);
//        }
//        lit.remove();
//        System.out.println(list);
//        lit = list.listIterator();
//        lit.next();
//        lit.remove();
//        System.out.println(list);
     
	}

}
