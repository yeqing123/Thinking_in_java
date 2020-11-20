package containers;
import java.util.*;

class SimpleHashSet<T> extends AbstractSet<T> {
    static final int SIZE = 997;
    @SuppressWarnings("unchecked")
	private LinkedList<T>[] buckets = new LinkedList[SIZE];
	@Override
	public Iterator<T> iterator() {
		// 这也许是实现Iterator接口的唯一方法！
		return new Iterator<T>() {
			
			int index1, index2;
			int count = size();
			boolean canRemove;
			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public T next() {
				if(hasNext()) {
					canRemove = true;
					count--;
					while(true) {
					    while(buckets[index1] == null)
						   index1++;
					    try {
					    	// 实践证明，此处使用下标比起迭代器访问链表，要聪明很多！
					    	return buckets[index1].get(index2++);
					    	// 利用抛出的异常，可以省去额外的if判断
						} catch(IndexOutOfBoundsException e) {
					        index1++;
                            index2 = 0;
						}
					}
				}
				throw new NoSuchElementException();
			}
			@Override
			public void remove() {
				if(!canRemove)
					throw new IllegalStateException();
				canRemove = false;
				buckets[index1].remove(--index2);
                if(buckets[index1].isEmpty())
                	buckets[index1++] = null;
			}
		};
	}

	@Override
	public boolean add(T e) {
		int index = Math.abs(e.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<T>();
		if(buckets[index].contains(e))  // 避免重复添加
			return false;
		return buckets[index].add(e);
	}

	@Override
	public int size() {
		int sz = 0;
		for(LinkedList<T> bucket : buckets)
			if(bucket != null)
			    sz += bucket.size();
		return sz;
	}
	
}

public class Ex24_SimpleHashSetDemo {

	public static void main(String[] args) {
		SimpleHashSet<String> set = new SimpleHashSet<String>();
        set.addAll(Countries.names(15));
        // 测试去重
        set.addAll(Countries.names(5));
        System.out.println(set.getClass().getSimpleName());
        System.out.println("Size: " + set.size());
        System.out.println(set);
        String first = set.iterator().next();
        set.remove(first);
        System.out.println("Remove first element: " + first);
        System.out.println("set.contains(\"" + first + "\"): " + set.contains(first));
        set.clear();
        System.out.println("set.isEmtry(): " + set.isEmpty());
	}

}
