package containers;
import java.util.*;

import containers.SimpleHashMapS.CustomLinkedList.MapEntrySelf;

class SimpleHashMapS<K, V> extends AbstractMap<K, V> {
	private final int SIZE = 997;
	@SuppressWarnings("unchecked")
	private CustomLinkedList<MapEntrySelf<K, V>>[] buckets = new CustomLinkedList[SIZE];
	static class CustomLinkedList<E> extends AbstractList<MapEntrySelf<?, ?>> {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private MapEntrySelf<?, ?> header = new MapEntrySelf(null, null, null);
        private MapEntrySelf<?, ?> tail = header;
		@Override
		public MapEntrySelf<?, ?> get(int arg) {
			if(arg >= size() || arg < 0)
				throw new IllegalStateException();
			MapEntrySelf<?, ?> element = header;
			for(int i = 0; i <= arg; i++)
				element = element.next();
			return element;
		}

		@Override
		public int size() {
			int sz = 0;
			MapEntrySelf<?, ?> mes = header;
			while(mes.hasNext()) {
				mes = mes.next;
				sz++;
			}
			return sz;
		}
		public int indexOfKey(Object o) {
			int size = size();
			for(int i = 0; i < size; i++) 
				if(o == null ? get(i).getKey() == null : o.equals(get(i).getKey())) 
					return i;
			return -1;
		}
		@SuppressWarnings("rawtypes")
		public boolean add(MapEntrySelf e) {
			if(indexOfKey(e.getKey()) != -1)
				return false;
			tail.next = e;
			tail = e;
			return true;
		}
		@Override
		public MapEntrySelf<?, ?> remove(int index) {
			if(index < 0 || index >= size())
				throw new IndexOutOfBoundsException();
			MapEntrySelf previous = header;
			MapEntrySelf current = previous.next;
			int i = 0;
			while(i < index) {
				previous = current;
				current = current.next;
			}
			previous.next = current.next();
			return current;
		}
		
		static class MapEntrySelf<K, V> extends SlowMap.MapEntry<K, V> {
			private MapEntrySelf<K, V> next;
			MapEntrySelf(K key, V value, MapEntrySelf<K, V> next) {
				super(key, value);
				this.next = next;
			}
			public MapEntrySelf<K, V> next() {
				return next;
			}
			public boolean hasNext() {
				return next != null;
			}
		}
	}
    @SuppressWarnings("unchecked")
	@Override
    public V put(K key, V value) {
    	V oldValue = null;
    	int index1 = Math.abs(key.hashCode()) % SIZE;
    	int index2;
    	boolean found = false;
    	if(buckets[index1] == null)
    		buckets[index1] = new CustomLinkedList<MapEntrySelf<K, V>>();
		if((index2 = buckets[index1].indexOfKey(key)) != -1) {
            MapEntrySelf<K, V> mes =  (MapEntrySelf<K, V>) buckets[index1].get(index2);   				
			oldValue = mes.getValue();
			mes.setValue(value);
			found = true;
		}
    	if(!found)
    	    buckets[index1].add(new MapEntrySelf<K, V>(key, value, null));
    	return oldValue;
    }
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
		for(CustomLinkedList<MapEntrySelf<K, V>> bucket : buckets)
			if(bucket != null) {
				for(int i = 0; i < bucket.size(); i++) {
					set.add((Entry<K, V>) bucket.get(i));
				}
			}
		return set;
	}
	@Override
	public V remove(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		@SuppressWarnings("unchecked")
		MapEntrySelf<K, V> mes = (MapEntrySelf<K, V>) buckets[index].remove(buckets[index].indexOfKey(key));
		if(buckets[index].isEmpty())
			buckets[index] = null;
		return mes.getValue();
	}
	public int size() {
		int sz = 0;
		for(CustomLinkedList<MapEntrySelf<K, V>> bucket : buckets)
			if(bucket != null)
			    sz += bucket.size();
		return sz;
	}
}

public class Ex25_SimpleHashMapNonIterator {

	public static void main(String[] args) {
        SimpleHashMapS<Integer, String> m = new SimpleHashMapS<Integer, String>();
        m.putAll(new CountingMapData(26));
        System.out.println(m);
        System.out.println("Size: " + m.size());
        System.out.println("Keys:" + new TreeSet<Integer>(m.keySet()));
        System.out.println("m.get(11): " + m.get(11));
        m.remove(m.get(11));
        System.out.println(m.containsKey(11));
	}

}
