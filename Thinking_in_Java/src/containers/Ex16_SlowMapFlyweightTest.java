package containers;
import java.util.*;


class SlowMap2<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();
    public V put(K key, V value) {
    	V oldValue = get(key);
    	if(keys.contains(key)) {
    		values.set(keys.indexOf(key), value);
    	} else {
    		keys.add(key);
    		values.add(value);
    	}
    	return oldValue;
    }
    public  V get(Object key) {
    	if(keys.contains(key)) {
    	    return values.get(keys.indexOf(key));
    	}
    	return null;
    }
    @Override
    public V remove(Object key) {
    	if(key == null)
    		throw new NullPointerException();
    	V value;
    	if(!keys.contains(key))
    		return null;
    	else {
            keys.remove(key);
            value = get(key);
            values.remove(value);
    	    return value;
    	}
    }
    @Override
    public void clear() {
    	keys.clear();
    	values.clear();
    }
    // 一个SlowMap对象只产生一个EntrySet实例
    private Set<Map.Entry<K, V>> entrySet = new EntrySet();
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }
    // 使用“享元（Flyweight）”设计模式，使得EntrySet能共享Map集合元素，而不是重新拷贝新的键-值对副本
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return new Iterator<Map.Entry<K, V>>() {
				int index = -1;  // 注意，index的初始值不可为0，否则在调用removeAll()方法时Map中的第一个元素将无法删除
				boolean canRemove;
				// 每个迭代器只包含一个MapEntry实例，它被用作数据的视窗
				MapEntry<K, V> entry = new MapEntry<K, V>(null, null);
				@Override
				public boolean hasNext() {
					return index < keys.size() - 1;
				}
                // 每次调用next()方法是，它都会返回单一的MapEntry实例，该实例会包含index索引对应的键-值对
				@Override
				public Entry<K, V> next() {
					canRemove = true;
					++index;
					entry.key = keys.get(index);
					entry.value = values.get(index);

					return entry;
				}
				@Override
				public void remove() {
					if(canRemove) {   // canRemove保证了一次只能删除一个，而不可重复调用remove()方法
					    keys.remove(index);
					    values.remove(index--);
					    canRemove = false;
					} else
						throw new IllegalStateException();
				}
			};
		}
		@Override
		public int size() {
			return keys.size();
		}
		@Override
		public boolean removeAll(Collection<?> c) {
			Iterator<Map.Entry<K, V>> it = this.iterator();
			boolean flag = false;
			while(it.hasNext()) {
				Object o = it.next();
				if(c.contains(o)) {
					flag = true;
			        it.remove();
				}
			}
			return flag;
		}
		@Override
		public boolean add(Map.Entry<K, V> entry) {
			return keys.add(entry.getKey()) && values.add(entry.getValue());
		}
    }
    // A simple MapEntry for sample Map implementations.
    static class MapEntry<K, V> implements Map.Entry<K, V> {
    	private K key;
    	private V value;
    	public MapEntry(K key, V value) {
    		this.key = key;
    		this.value = value;
    	}
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V v) {
			V result = value;
			value = v;
			return result;
		}
		public int hashCode() {
			return (key == null ? 0 : key.hashCode()) ^
					(value == null ? 0 : value.hashCode());
		}
		public boolean equals(Object o) {
			if(!(o instanceof MapEntry)) return false;
			MapEntry me = (MapEntry)o;
			return
					(key == null ?
					me.getKey() == null : key.equals(me.getKey())) &&
					(value == null ?
					me.getValue() == null : value.equals(me.getValue()));
		}
		public String toString() { 
			return String.valueOf(key) + "=" + String.valueOf(value); 
		}
    }
}

public class Ex16_SlowMapFlyweightTest {
    public static void printKeys(Map<Integer, String> map) {
    	System.out.print("Size = " + map.size() + ", ");
    	System.out.print("Keys: ");
    	System.out.println(map.keySet());
    }
    public static void test(Map<Integer, String> map) {
    	System.out.println(map.getClass().getSimpleName());
    	map.putAll(new CountingMapData(25));
    	// Map has 'Set' behavior for keys
    	printKeys(map);
    	// Producing a Collection of the values:
    	System.out.print("Values:");
    	System.out.println(map.values());
    	System.out.println(map);  	
    	System.out.println("map.containsKey(11):" + map.containsKey(11));
    	System.out.println("map.get(11):" + map.get(11));
    	System.out.println("map.containsValue(\"F0\"):" + map.containsValue("F0"));
    	Integer key = (Integer) map.keySet().iterator().next();
    	System.out.println("First key in map:" + key);
    	map.remove(key);
    	printKeys(map);
    	map.clear();
    	System.out.println("map.isEmpty():" + map.isEmpty());
    	map.putAll(new CountingMapData(25));
    	// Operations on the Collection change the Map:
    	System.out.println(map.entrySet());
    	map.entrySet().removeAll(map.entrySet());  
        System.out.println("map.isEmpty():" + map.isEmpty());
        map.putAll(new CountingMapData(25));
    	map.keySet().removeAll(map.keySet());
    	System.out.println("map.isEmpty():" + map.isEmpty());
    	map.putAll(new CountingMapData(25));  
    	map.values().removeAll(map.values());
    	System.out.println("map.isEmpty():" + map.isEmpty());
    	map.entrySet().add(new SlowMap2.MapEntry(1, "yeqing"));
    	System.out.println(map);
    }
	public static void main(String[] args) {
		test(new SlowMap<Integer, String>());
		System.out.println("========================");
        test(new SlowMap2<Integer, String>());
	}

}
