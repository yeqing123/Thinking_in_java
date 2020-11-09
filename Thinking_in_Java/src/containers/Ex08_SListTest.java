package containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// 自定义一个迭代器接口，它包含我们所需的一些方法
interface SListIterator<E> {
	boolean hasNext();
	E next();
	void add(E e);
	void remove();
}

class SList<E> {
	private final Link<E> head = new Link<E>(null, null);  // 每个链表都有一个头结点，又叫“卫兵节点”
	public SList() {}
	public SList(Collection<E> c) {
		SListIterator<E> it = this.iterator();
		for(E e : c)
			it.add(e);
	}
	// 每个Link对象是链表中的一个元素（或节点），其中保存着元素值和指向下一个元素的引用
	private static class Link<E> {
		E e;
		Link<E> next;
		Link(E e, Link<E> next) {
			this.e = e;
			this.next = next;
		}
	}
	// One-way cycle iterator
	private class SListIteratorImpl implements SListIterator<E> {
		private Link<E> point = head;
		private Link<E> previous = null;   // 在遍历时previous始终指向当前节点的前一个节点
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
		// 向当前节点的后面插入一个新节点
		@Override
		public void add(E e) {
			Link<E> newLink = new Link<E>(e, null);
			if(previous == null && point.next == null) { // is Empty
				point.next = newLink;
				point = newLink;
			} else {
			    previous.next = newLink;
			    previous = newLink;
			    newLink.next = point;
			    point = point.next;
			}
		}
		@Override
		public void remove() {
			if(point == head) {   // 如果试图删除“卫兵节点”，则抛出异常
				throw new IllegalStateException();
			} else {
			    previous.next = point.next;
			    point = point.next;
			}
		}
	}
	public SListIterator<E> iterator() { return new SListIteratorImpl(); }
	// 按通用格式输出链表内容
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
		// First,show some use cases for SListIterator
		System.out.println("Demonstrating SListIterator...");
        SList<String> slist1 = new SList<String>(Countries.names(5));
        System.out.println("slist1: " + slist1);
        SList<String> slist2 = new SList<String>();
        SListIterator<String> sit = slist2.iterator();
        for(int i = 0; i < 10; i++) {
        	sit.add("element--" + i);
        }
        System.out.println("slist2: " + slist2);
        // it迭代器已经遍历到最后一个了，因此在调用next()方法会抛出异常
   //     System.out.println("循环遍历：" + sit.next());
        // 重新生成迭代器，即重新指向链表头
        sit = slist2.iterator();
        // 迭代器向前移动两位
        System.out.println(sit.next());
        System.out.println(sit.next());
        // 此时迭代器指向第二个元素
        sit.remove();
        System.out.println("sit.remove():" + slist2);
        // 向链表第二个元素后面插入新的元素，并且迭代器会后移一位，指向新插入的元素
        sit.add("element--x");
        System.out.println("sit.add(\"element--x\"): " + slist2);
        // 继续插入新元素
        sit.add("element--y");
        System.out.println("sit.add(\"element--y\"): " + slist2);
        
        // Now, show the same use cases for ListIerator, too
        System.out.println("\nDemostrating ListIterator...");
        List<String> list1 = new ArrayList<String>(Countries.names(5));
        System.out.println(list1);
        List<String> list2 = new ArrayList<String>();
        ListIterator<String> it = list2.listIterator();
        for(int i = 0; i < 10; i++) {
        	it.add("element--" + i);
        }
        System.out.println("list2: " + list2);
        // it迭代器已经遍历到最后一个了
   //     System.out.println("循环遍历：" + it.next());
        it = list2.listIterator();
        // 迭代器向前移动两位
        System.out.println(it.next());
        System.out.println(it.next());
        // 此时迭代器指向第二个元素
        it.remove();
        System.out.println("it.remove():" + list2);
        // 向链表第二个元素后面插入新的元素，并且迭代器会后移一位，指向新插入的元素
        it.add("element--x");
        System.out.println("it.add(\"element--x\"): " + list2);
        // 继续插入新元素
        it.add("element--y");
        System.out.println("it.add(\"element--y\"): " + list2);
	}

}
