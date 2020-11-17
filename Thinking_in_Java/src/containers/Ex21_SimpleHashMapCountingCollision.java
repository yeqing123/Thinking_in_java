package containers;
import java.util.*;
import containers.SlowMap.MapEntry;

class SimpleHashMap3<K, V> extends SimpleHashMap<K, V> {
	@Override
	public V put(K key, V value) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		V oldValue = null;	
        boolean found = false;
        int counter = 0;
        for(Iterator<MapEntry<K, V>> it = bucket.iterator(); it.hasNext();) {
		    Map.Entry<K, V> entry = it.next();
		    counter++;
			if(key.equals(entry.getKey())) {
				oldValue = entry.getValue();
				entry.setValue(value);  // Replace old value
			    found = true;
			    System.out.println("在插入元素:\n" + pair + 
			    		"\n时在探寻了" + counter + "次之后，发现一个冲突！");
			    break;
			}
		} 
		if(!found)
			bucket.add(pair);
		return oldValue;
	}
}

public class Ex21_SimpleHashMapCountingCollision {
    public static void main(String[] args) {
    	Map<String, String> map = new SimpleHashMap3<String, String>();
    	map.putAll(Countries.capitals(20));
    	map.putAll(Countries.capitals(20));
    }
}
