package containers;
import java.util.*;
import containers.SlowMap.MapEntry;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		for(Iterator<MapEntry<K, V>> it = bucket.iterator(); it.hasNext();) {
		    MapEntry<K, V> entry = it.next();
			if(key.equals(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		V oldValue = null;	
        boolean found = false;
		for(Iterator<MapEntry<K, V>> it = bucket.iterator(); it.hasNext();) {
		    Map.Entry<K, V> entry = it.next();
			if(key.equals(entry.getKey())) {
				oldValue = entry.getValue();
				entry.setValue(value);  // Replace old value
			    found = true;
			    break;
			}
		} 
		if(!found)
			bucket.add(pair);
		return oldValue;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
		for(LinkedList<MapEntry<K, V>> bucket : buckets)
			if(bucket != null)
			    for(MapEntry<K, V> entry : bucket)
			        set.add(entry);
		return set;
	}
    
	public static void main(String[] args) {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<Integer, String>();
		map.putAll(new CountingMapData(25));
		System.out.println(map.getClass().getSimpleName());
		System.out.println("Size: " + map.size());
		System.out.println(map);
		System.out.println("Key: " + map.keySet());
		System.out.println("values: " + map.values());
		System.out.println("entrySet: " + map.entrySet());
		System.out.println("map.get(11): " + map.get(11));
	}
}
