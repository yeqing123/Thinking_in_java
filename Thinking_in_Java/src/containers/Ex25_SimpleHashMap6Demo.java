package containers;
import java.util.*;
import containers.CustomList.MapEntry2;
import containers.SlowMap.MapEntry;

class SimpleHashMap6<K, V> extends AbstractMap<K, V> {
    private final int SIZE = 997;
    @SuppressWarnings("unchecked")
	private CustomList<MapEntry2<K, V>>[] buckets = new CustomList[SIZE];
    private EntrySet entrySet = new EntrySet();
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	class EntrySet extends AbstractSet<Entry<K, V>> {
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K, V>>() {
                int index1, index2;
                int count = size();
                boolean canRemove;
                @Override
				public boolean hasNext() {
					return count > 0;
				}

				@Override
				public MapEntry2<K, V> next() {
                    if(hasNext()) {
                    	canRemove = true;
                    	count--;
                    	while(true) {
                    		while(buckets[index1] == null)
                    			index1++;
                    		try {
                    			return buckets[index1].get(index2++);
                    		} catch(IndexOutOfBoundsException e) {
                    			index1++;
                    			index2 = 0;
                    		}
                    	}
                    }
					throw new IllegalStateException();
				}
				@Override
				public void remove() {
					if(!canRemove)
						throw new IllegalStateException();
					buckets[index1].remove(--index2);
					if(buckets[index1].size() == 0)
						buckets[index1++] = null;
				}
			};
		}

		@Override
		public int size() {
			int sz = 0;
			for(CustomList<MapEntry2<K, V>> bucket : buckets)
				if(bucket != null)
					sz += bucket.size();
			return sz;
		}
		
	}
	@Override
	public V put(K key, V value) {
		int index1 = Math.abs(key.hashCode()) % SIZE;
		boolean found = false;
		V oldValue = null;
		MapEntry2<K, V> pair = new MapEntry2<K, V>(key, value, null);
		if(buckets[index1] == null)
			buckets[index1] = new CustomList<MapEntry2<K, V>>();
		CustomList<MapEntry2<K, V>> bucket = buckets[index1];
		int index2;
		if((index2 = bucket.indexOf(key)) != -1) { 
		    MapEntry2<K, V> me = bucket.get(index2);
			if(me.getKey().equals(key)) {
				oldValue = me.getValue();
				me.setValue(value);
				found = true;
			}
		}
		if(!found) {
			bucket.add(pair);
		}
		return oldValue;
	}
	@Override
	public V get(Object key) {
		int index1 = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index1] == null)
			return null;
		CustomList<MapEntry2<K, V>> bucket = buckets[index1];
		int index2;
		if((index2 = bucket.indexOf(key)) != -1) {
			MapEntry2<K, V> me = bucket.get(index2);
			if(key.equals(me.getKey()))
				return me.getValue();
		}
		return null;
	}
}

class CustomList<E> extends AbstractList<E> {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private MapEntry2<?, ?> header = new MapEntry2(null, null, null);
    private MapEntry2<?, ?> tail = header;
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException();
		MapEntry2<?, ?> result = header;
		for(int i = 0; i <= index; i++)
			result = result.next;
		return (E) result;
	}
	@Override
	public int indexOf(Object key) {
		MapEntry2<?, ?> element = header;
		int index = -1;
		while(element.next != null) {
		    element = element.next;
		    index++;
			if(key.equals(element.getKey()))
				return index;
		}
		return index;
	}

	@Override
	public int size() {
		int sz = 0;
		MapEntry2<?, ?> element = header;
		while(element.next != null) {
			element = element.next;
		    sz++;
		}
		return sz;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean add(E e) {
		if(!contains(e)) {
		    tail.next = (MapEntry2) e;
		    tail = (MapEntry2) e;
		    return true;
		}
		return false;
	}
	@Override
	public E remove(int index) {
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException();
		E element = get(index);
		remove(element);
	    return element;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean remove(Object o) {
		if(!(o instanceof MapEntry2))
			return false;
		MapEntry2<?, ?> element = header;
		MapEntry2<?, ?> pre = header;
		while(element.next != null) {
			pre = element;
			element = element.next;
			if(o.equals(element)) {
				pre.next = (MapEntry2) element.next;
				// 如果删除的是最后一个元素，则重新为tail赋值，使其始终保持指向链表最后一个元素
				if(tail.equals(element))
					tail = pre;
				return true;
			}
		}
		return false;
	}
	
    static class MapEntry2<K, V> extends MapEntry<K, V> {
    	private MapEntry2<K, V> next;
    	MapEntry2(K key, V value, MapEntry2<K, V> next) {
    		super(key, value);
    		this.next = next;
    	}
    }
	
}

public class Ex25_SimpleHashMap6Demo {
    
	public static void main(String[] args) {
        SimpleHashMap6<Integer, String> m = new SimpleHashMap6<Integer, String>();
        m.putAll(new CountingMapData(26));
        System.out.println(m.getClass().getSimpleName());
   	    System.out.println("Size: " + m.size());
        System.out.println("Key: " + m.keySet());
        System.out.println(m);
        System.out.println("m.get(11): " + m.get(11));
        m.remove(11);
        System.out.println("m.get(11): " + m.get(11));
        System.out.println("Size: " + m.size());
        System.out.println(m);
        m.putAll(new CountingMapData(26));
        System.out.println(m);
        m.entrySet().removeAll(m.entrySet());
        System.out.println("m.isEmpty(): " + m.isEmpty());
        m.putAll(new CountingMapData(26));
        m.values().removeAll(m.values());
        System.out.println("m.isEmpty(): " + m.isEmpty());
        m.putAll(new CountingMapData(26));
        m.keySet().removeAll(m.keySet());
        System.out.println("m.isEmpty(): " + m.isEmpty());
        m.putAll(new CountingMapData(26));
        m.put(0, "haha");
        System.out.println(m);
	}

}
