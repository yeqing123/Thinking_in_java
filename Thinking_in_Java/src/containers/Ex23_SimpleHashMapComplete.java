package containers;
import containers.SlowMap.MapEntry;
import java.util.*;

class SimpleHashMap5<K, V> extends SimpleHashMap<K, V> {
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	private EntrySet entrySet = new EntrySet();
	// Use "Flyweight" design mode to share Map data, 
	// so the method entrySet() only return Map element views.
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K, V>>() {
				Iterator<MapEntry<K, V>> bit = null;  // 表示“桶位”上链表的迭代器
                int index = 0;
                int count = size();  // 获取map集合中键-值对的个数
                boolean canRemove;
				@Override
				public boolean hasNext() {
					return count > 0;  // 如果count等于0，表示所有元素已经遍历，即便数组还没有被全部访问
				}

				@Override
				public Entry<K, V> next() {
					while(hasNext()) {
	            		while(buckets[index] == null) 
	            			index++;
	            		if(bit == null)
	            	        bit = buckets[index].iterator();
			        	if(bit.hasNext()) {
			        		count--;      // 每返回一个元素，就减少一个
			        		canRemove = true;
	                		return bit.next();
	            		}else {
			        	    index++;
	            		    bit = null;
	            		}
					}
					throw new NoSuchElementException();
				}
				
			    @Override
			    public void remove() {
			    	if(!canRemove) 
			    		throw new IllegalStateException();
			    	bit.remove();
			    	canRemove = false;
			    }
			};
		}
        @Override
        public boolean removeAll(Collection<?> c) {
        	Iterator<Entry<K, V>> it = iterator();
        	boolean flag = false;
        	while(it.hasNext()) {
                if(c.contains(it.next())) {
                	it.remove();
                	flag = true;
                }
        	}
        	return flag;
        }
        @Override
		public boolean add(Map.Entry<K, V> entry) {
        	if(SimpleHashMap5.this.put(entry.getKey(), entry.getValue()) != null)
        		return true;
        	return false;
        }
		@Override
		public int size() {
			int size = 0;
			for(LinkedList<MapEntry<K, V>> bucket : buckets)
				if(bucket != null) 
					size += bucket.size();
			return size;
		}
	}
}

public class Ex23_SimpleHashMapComplete {
	 
	public static void test(Map<Integer, String> map) {
    	map.putAll(new CountingMapData(25));
    	System.out.println(map.getClass().getSimpleName() + ":");
    	System.out.println(map);
    	System.out.println("Size: " + map.size() + ", Keys: " + map.keySet());
    	System.out.println("First key: " + map.keySet().iterator().next());
    	System.out.println("map.get(11): " + map.get(11));
    	System.out.println("map.containsKey(11): " + map.containsKey(11));
    	Map.Entry<Integer, String> first = map.entrySet().iterator().next();
    	// Producing a Collection of the values:
    	map.keySet().remove(first.getKey());
    	System.out.println("Remove first element of map: " + first);
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
    	map.entrySet().add(new MapEntry<Integer, String>(1, "yq"));
    	System.out.println(map);
    }

	public static void main(String[] args) {
		test(new SimpleHashMap<Integer, String>());
		System.out.println("========================");
		test(new SimpleHashMap5<Integer, String>());
	}

}
