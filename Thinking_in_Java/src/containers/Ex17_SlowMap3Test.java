package containers;
import java.util.*;

class SlowMap3<K, V> implements Map<K, V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();
	@Override
	public int size() {
		return keys.size();
	}
	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	@Override
	public V get(Object key) {
		return values.get(keys.indexOf(key));
	}

	@Override
	public V put(K key, V value) {
		V result = values.get(values.indexOf(value));
		keys.add(key);
		values.add(value);
		return result;
	}

	@Override
	public V remove(Object key) {
		int index = keys.indexOf(key);
		V result = values.get(index);
		keys.remove(index);
		values.remove(index);
		return result;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
        for(K k : m.keySet())
        	keys.add(k);
        for(V v : m.values())
        	values.add(v);
	}

	@Override
	public void clear() {
        keys.clear();
        values.clear();
	}

	@Override
	public Set<K> keySet() {
		return keySet;
	}
	private KeySet keySet = new KeySet();
    private class KeySet extends AbstractSet<K> {
		@Override
		public Iterator<K> iterator() {
			return new Iterator<K>() {
                int index = -1;
                boolean canRemove;
				@Override
				public boolean hasNext() {
					return index < keys.size() - 1;
				}

				@Override
				public K next() {
					canRemove = true;
					return keys.get(++index);
				}
				@Override
				public void remove() {
					if(canRemove) {
						canRemove = false;
						keys.remove(index);
						values.remove(index--);
					}
				}
			};
		}
		@Override
		public int size() {
			return keys.size();
		}
    	
    }
	@Override
	public Collection<V> values() {
		return valueCollection;
	}
	private Values valueCollection = new Values(); 
	private class Values extends AbstractCollection<V> {

		@Override
		public Iterator<V> iterator() {
			return new Iterator<V>() {
                int index = -1;
                boolean canRemove;
				@Override
				public boolean hasNext() {
					return index < values.size() - 1;
				}

				@Override
				public V next() {
                    canRemove = true;
					return values.get(++index);
				}
				@Override
				public void remove() {
					if(canRemove) {
						canRemove = false;
						keys.remove(index);
						values.remove(index--);
					}
				}
			};
		}

		@Override
		public int size() {
			return values.size();
		}
    	
    }

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	
	private EntrySet entrySet = new EntrySet();
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K, V>>() {
				int index = -1;
				boolean canRemove;
				MapEntry<K, V> me = new MapEntry<K, V>(null, null);
				@Override
				public boolean hasNext() {
					return index < keys.size() - 1;
				}

				@Override
				public Entry<K, V> next() {
					index++;
					canRemove = true;
				    me.key = keys.get(index);
				    me.value = values.get(index);
					return me;
				}
				@Override
				public void remove() {
					if(canRemove) {
						canRemove = false;
						keys.remove(index);
						values.remove(index--);
					}
				}
			};
		}

		@Override
		public int size() {
			return keys.size();
		}	
    }
   
    private static class MapEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        MapEntry(K k, V v) {
        	key = k;
        	value = v;
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
		public V setValue(V value) {
            V result = this.value;
            this.value = value;
			return result;
		}
    	@SuppressWarnings("unchecked")
		@Override
    	public boolean equals(Object o) {
    		return (o instanceof Map.Entry) &&
    				((Map.Entry<K, V>)o).getKey().equals(key) &&
    				((Map.Entry<K, V>)o).getValue().equals(value);
    			
    	}
    	@Override
    	public int hashCode() {
    		return key.hashCode() ^ value.hashCode();
    	}
    	public String toString() {
    		return String.valueOf(key) + "=" + String.valueOf(value);
    	}
    }
    
	public String toString() {
		StringBuffer buffer = new StringBuffer("{") ;
		for(int i = 0; i < keys.size(); i++) {
			buffer.append(keys.get(i) + "=" + values.get(i));
			if(i < keys.size() - 1)
				buffer.append(", ");
		}
		buffer.append("}");
		return buffer.toString();
	}
}

public class Ex17_SlowMap3Test {
	
    public static void test(Map<Integer, String> map) {
    	map.putAll(new CountingMapData(25));
    	System.out.println(map.getClass().getSimpleName() + ":");
    	System.out.println(map);
    	System.out.println("Size: " + map.size() + ", Keys: " + map.keySet());
    	System.out.println("First key: " + map.keySet().iterator().next());
    	System.out.println("map.get(11): " + map.get(11));
    	System.out.println("map.containsKey(11): " + map.containsKey(11));
    	Map.Entry<Integer, String> en = map.entrySet().iterator().next();
    	// Producing a Collection of the values:
    	map.keySet().remove(en.getKey());
    	System.out.println("Remove first element of map: " + en);
    	System.out.println("Size: " + map.size());
    	System.out.println(map);
    	map.entrySet().removeAll(map.entrySet());
    	System.out.println("map.isEmpty(): " + map.isEmpty());
    	map.putAll(new CountingMapData(25));
    	map.keySet().removeAll(map.keySet());
    	System.out.println("map.isEmpty(): " + map.isEmpty());
    	map.putAll(new CountingMapData(25));
    	map.values().removeAll(map.values());
    	System.out.println("map.isEmpty(): " + map.isEmpty());
    }
	public static void main(String[] args) {
        test(new SlowMap3<Integer, String>());
        test(new SlowMap2<Integer, String>());
        test(new HashMap<Integer, String>());
	}
}
