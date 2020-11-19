package containers;
import containers.SlowMap.MapEntry;
import java.util.*;
import java.util.Map.Entry;

class SimpleHashMap5<K, V> extends SimpleHashMap<K, V> {
	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}
	private EntrySet entrySet = new EntrySet();
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iterator<Entry<K, V>>() {
<<<<<<< HEAD
				ListIterator<MapEntry<K, V>> bit = null;
=======
				Iterator<MapEntry<K, V>> bit = null;
>>>>>>> 7f28a97... modify(containers/):modified Ex23
                int index = 0;
                int num = 0;
                boolean canRemove;
				@Override
				public boolean hasNext() {
<<<<<<< HEAD
					return num < size();
=======
					return num < size() && index < SIZE - 2;
>>>>>>> 7f28a97... modify(containers/):modified Ex23
				}

				@Override
				public Entry<K, V> next() {
<<<<<<< HEAD
					while(this.hasNext()) {
	            		while(buckets[index] == null) 
	            			index++;
	            		if(bit == null)
	            	        bit = buckets[index].listIterator();
			        	while(bit.hasNext()) {
			        		num++;
			        		canRemove = true;
	                		return bit.next();
	            		}
			        	index++;
			        	bit = null;
=======
					while(hasNext()) {
	            		while(buckets[index] == null) 
	            			index++;
	            		if(bit == null)
	            	        bit = buckets[index].iterator();
			        	if(bit.hasNext()) {
			        		num++;
			        		canRemove = true;
	                		return bit.next();
	            		}else {
			        	    index++;
	            		    bit = null;
	            		}
>>>>>>> 7f28a97... modify(containers/):modified Ex23
					}
					throw new NoSuchElementException();
				}
			    @Override
			    public void remove() {
			    	System.out.println("remove");
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
        		MapEntry<K, V> me = (MapEntry<K, V>)it.next();
        		System.out.println("Before compare:" + me);
                if(c.contains(me)) {
                	System.out.println("After compare:" +me);
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
<<<<<<< HEAD
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
	//    	map.entrySet().removeAll(map.entrySet());
	    	System.out.println("****");
	    	for(Entry<Integer, String> entry : map.entrySet())
	    		map.remove((Object)entry);
	    	System.out.println("map.isEmpty(): " + map.isEmpty());
	    	System.out.println(map);
	    	map.putAll(new CountingMapData(25));
	    	map.keySet().removeAll(map.keySet());
	    	System.out.println("map.isEmpty(): " + map.isEmpty());
	    	System.out.println(map);
	    	map.putAll(new CountingMapData(25));
	    	map.values().removeAll(map.values());
	    	System.out.println("map.isEmpty(): " + map.isEmpty());
	    	System.out.println(map);
	    }
	public static void main(String[] args) {
		test(new SimpleHashMap<Integer, String>());
		System.out.println("========================");
		test(new SimpleHashMap5<Integer, String>());
//		Map<Integer, String> map = new SimpleHashMap5<Integer, String>();
=======
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
		//    	map.entrySet().removeAll(map.entrySet());
		System.out.println("****");
//		for(Entry<Integer, String> entry : map.entrySet())
//			map.entrySet().remove((Object)entry);
		Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
		System.out.println("map.isEmpty(): " + map.isEmpty());
		System.out.println(map);
>>>>>>> 7f28a97... modify(containers/):modified Ex23
//		map.putAll(new CountingMapData(25));
//		map.keySet().removeAll(map.keySet());
//		System.out.println("map.isEmpty(): " + map.isEmpty());
//		System.out.println(map);
//		map.putAll(new CountingMapData(25));
//		map.values().removeAll(map.values());
//		System.out.println("map.isEmpty(): " + map.isEmpty());
//    	System.out.println(map);
    }    
	public static void main(String[] args) {
		test(new SimpleHashMap<Integer, String>());
		System.out.println("========================");
		test(new SimpleHashMap5<Integer, String>());
	}

}
