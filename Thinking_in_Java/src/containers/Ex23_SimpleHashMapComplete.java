package containers;
import containers.SlowMap.MapEntry;
import java.util.*;

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
				Iterator<MapEntry<K, V>> it = null;
                int num = 0;
                int index = 0;
                boolean canRemove;
                boolean flag = false;
				@Override
				public boolean hasNext() {
					return num < size();
				}

				@Override
				public Entry<K, V> next() {
                	for(int i = index; i < SIZE; i++) {
                	    if(buckets[i] != null && !flag) {
                		    flag = true;
                	    	it = buckets[i].iterator();
                	    }
	                	while(it.hasNext()) {
	                	    num++;
	                	    canRemove = true;
	                	    index = i;
	                		return it.next();
	                	}
	                	flag = false;
                	}
					return null;
				}
			    @Override
			    public void remove() {
			    	if(!canRemove) 
			    		throw new IllegalStateException();
			    	if(it != null) {
			    	    it.remove();
			    	    canRemove = false;
			    	}
			    }
			};
		}
        @Override
        public boolean removeAll(Collection<?> c) {
        	Iterator<Entry<K, V>> it = this.iterator();
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
	
	public static void main(String[] args) {
		Ex17_SlowMapCompleteMap.test(new SimpleHashMap<Integer, String>());
		System.out.println("========================");
		Ex17_SlowMapCompleteMap.test(new SimpleHashMap5<Integer, String>());
//		Map<Integer, String> map = new SimpleHashMap5<Integer, String>();
//		map.putAll(new CountingMapData(25));
//		System.out.println("map.entrySet(): " + map.entrySet());
//		System.out.println("map.keySet(): " + map.keySet());
//		System.out.println(map);
	}

}
