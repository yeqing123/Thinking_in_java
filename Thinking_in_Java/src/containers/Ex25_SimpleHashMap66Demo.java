package containers;
import java.util.*;

class SimpleHashMap66<K, V> extends AbstractMap<K, V> {
	final int SIZE = 997;
	@SuppressWarnings("unchecked")
	private Entry2<K, V>[] buckets = new Entry2[SIZE];
	public V put(K key, V value) {
		int index = Math.abs(key.hashCode()) % SIZE;
		Entry2<K, V> newPair = new Entry2<K, V>(key, value);
		V oldValue = null;
		if(buckets[index] == null)
			buckets[index] = newPair;
		else {
			Entry2<K, V> prevPair;
			for(Entry2<K, V> pair = buckets[index]; pair != null; pair = pair.getNext()) {
				prevPair = pair;
				if(pair.getKey().equals(key)) {
					oldValue = pair.getValue();
					newPair.setNext(pair.getNext());
					prevPair.setNext(newPair);
					break;
				}
			}
		}
		return oldValue;
	}
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		for(Entry2<K, V> pair = buckets[index]; pair != null; pair = pair.getNext())
			if(pair.getKey().equals(key))
				return pair.getValue();
		return null;
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	private EntrySet entrySet = new EntrySet();
	@SuppressWarnings("unused")
	private class EntrySet extends AbstractSet<Entry<K, V>> {
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K, V>>() {
                int index;
                Entry2<K, V> prev, current, next;
                int count = size();
                boolean canRemove;
				@Override
				public boolean hasNext() {
					return count > 0;
				}

				@Override
				public Entry2<K, V> next() {
                    if(hasNext()) {
                    	count--;
                    	canRemove = true;
                    	while(true) {
	                    	while(buckets[index] == null)
	                    		index++;
	                    	if(current == null)
	                    	    next = buckets[index];
	                    	prev = current;
	                    	current = next;
                    		try {
                    		    next = next.getNext();
                    		    return current;
                    		} catch(NullPointerException e) {
                    			index++;
                    		}
                    	}
                    }
					throw new IllegalStateException();
				}
				@Override
				public void remove() {
					if(prev == null)
						buckets[index] = next;
					else
						prev.setNext(next);
					current = null;
				}
			};
		}

		@Override
		public int size() {
			int sz = 0;
			for(Entry2<K, V> bucket : buckets)
				for(Entry2<K, V> pair = bucket; pair != null; pair = pair.getNext())
					sz++;
			return sz;
		}
		
	}
}

class Entry2<K, V> implements Map.Entry<K, V> {
	private K key;
	private V value;
	private Entry2<K, V> next;
	public Entry2(K key, V value) { this.key = key; this.value = value; }
	public K getKey() { return key; }
	public V getValue() { return value; }
	public Entry2<K, V> getNext() { return next; }
	public V setValue(V v) { 
		V oldValue = this.value;
		this.value = v; 
		return oldValue;
	}
	public void setNext(Entry2<K, V> next) { this.next = next; }
	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode()) ^
				(value == null ? 0 : value.hashCode());
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Entry2))
			return false;
		@SuppressWarnings("unchecked")
		Entry2<K, V> e = (Entry2<K, V>) o;
		return (key == null ? e.getKey() == null : key.equals(e.key)) &&
			   (value == null ? e.getValue() == null : value.equals(e.getValue()));
	}
	public String toString() {
		return String.valueOf(key) + "=" + String.valueOf(value);
	}
}

public class Ex25_SimpleHashMap66Demo {

	public static void main(String[] args) {
        SimpleHashMap66<Integer, String> m = new SimpleHashMap66<Integer, String>();
        m.putAll(new CountingMapData(26));
        System.out.println(m);
        System.out.println("Size: " + m.size());
        System.out.println("Keys:" + new TreeSet<Integer>(m.keySet()));
        System.out.println("m.get(11): " + m.get(11));
        m.remove((Object)m.get(11));
        System.out.println("m.containsKey(m.get(11)): " + m.containsKey(m.get(11)));
        m.entrySet().removeAll(m.entrySet());
        System.out.println("m.isEmtry(): " + m.isEmpty());
	}

}
