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
                int index1, index2;
                // 首先获取元素总数，然后逐个递减。
                // 比起从零开始递增可以省去每次在hasNext()中重复调用size()方法.
                int count = size(); 
                boolean canRemove;
				@Override
				public boolean hasNext() {
					return count > 0;  
				}

				@Override
				public Entry<K, V> next() {
					// 再次进行判断
					if(hasNext()) {
						count--;
						canRemove = true;
						while(true) {
							// 跳过空桶位
		            		while(buckets[index1] == null) 
		            			index1++;
		            		try {
		            			// 实践证明，此处使用下标比起迭代器访问链表，要聪明很多！
		                		return buckets[index1].get(index2++);
		                		// 利用抛出的异常，可以省去额外的if判断
		            		} catch(IndexOutOfBoundsException e) {  
				        	    index1++;
		            		    index2 = 0;
		            		}
						}
					}
					throw new NoSuchElementException();
				}
	
			    @Override
			    public void remove() {
			    	if(!canRemove) 
			    		throw new IllegalStateException();
			    	canRemove = false;
			    	buckets[index1].remove(--index2);
			    	if(buckets[index1].isEmpty())   // 彻底清空桶位
			    		buckets[index1++] = null;
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

	public static void test(SimpleHashMap<Integer, String> map) {
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
