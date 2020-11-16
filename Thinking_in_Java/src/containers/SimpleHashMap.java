package containers;
import java.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<Map.Entry<K, V>>[] buckets = new LinkedList[SIZE];
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		LinkedList<Map.Entry<K, V>> bucket = buckets[index];
		for(Iterator<Map.Entry<K, V>> it = bucket.iterator(); it.hasNext();) {
		    Map.Entry<K, V> entry = it.next();
			if(key.equals(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<Map.Entry<K, V>>();
		LinkedList<Map.Entry<K, V>> bucket = buckets[index];
		Map.Entry<K, V> entry = new Entry<K, V>(key, value);
		if(bucket.contains(o))
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		return null;
	}
   
	public static void main(String[] args) {
		
	}
}
