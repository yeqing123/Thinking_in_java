package containers;
import java.util.*;
import containers.SlowMap.MapEntry;

class SimpleHashMap4<K, V> extends SimpleHashMap<K, V> {
	@Override
	public void clear() {
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = null;
		}
	}
	@Override
	public V remove(Object k) {
		int index = Math.abs(k.hashCode()) % SIZE;
		if(buckets[index] == null) 
			return null;
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		for(Iterator<MapEntry<K, V>> it = bucket.iterator(); it.hasNext();) {
		    MapEntry<K, V> me = it.next();
			if(me.getKey().equals(k)) {
				V result = me.getValue();
				it.remove();
				return result;
			}
		}
		return null;
	}
}

public class Ex22_SimpleHashMap4Test {

	public static void main(String[] args) {
        Map<String, String> map = new SimpleHashMap4<String, String>();
        map.putAll(Countries.capitals(20));
        System.out.println(map);
        System.out.println("Before remove called map.get(\"CHAD\"): " + map.get("CHAD"));
        System.out.println("Before remove called map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        map.remove("CHAD");
        map.remove("ALGERIA");
        System.out.println("After remove called map.get(\"CHAD\"): " + map.get("CHAD"));
        System.out.println("After remove called map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        map.clear();
        System.out.println("map.isEmpty(): " + map.isEmpty());
	}

}
