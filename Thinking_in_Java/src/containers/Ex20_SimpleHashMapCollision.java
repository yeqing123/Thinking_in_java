package containers;
import java.util.*;
import containers.SlowMap.MapEntry;

class SimpleHashMap2<K, V> extends SimpleHashMap<K, V> {
	@Override
	public V put(K key, V value) {
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		else {
			System.out.println("Collision while adding\n" + pair +
					"\nBucket already contains:");
			for(Iterator<MapEntry<K, V>> it = buckets[index].iterator(); it.hasNext();)
				System.out.println(it.next());
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
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
}

public class Ex20_SimpleHashMapCollision {
	
	public static void main(String[] args) {
		Map<String, String> map = new SimpleHashMap2<String, String>();
        map.putAll(Countries.capitals(10));
        map.putAll(Countries.capitals(10));
        System.out.println(map);
	}

}
