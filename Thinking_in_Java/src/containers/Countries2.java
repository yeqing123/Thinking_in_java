package containers;
import java.util.*;

public class Countries2 {
    public static final String[][] DATA2 = Countries.DATA;
    private static class FlyweightMap extends AbstractMap<String, String> {
        private static Set<Map.Entry<String, String>> entries = 
        		new EntrySet(DATA2.length);
    	@Override
    	public Set<Map.Entry<String, String>> entrySet() {
    		return entries;
    	}
    	private static class Entry implements Map.Entry<String, String> {
            int index;
            Entry(int index) { this.index = index; }
            @Override
            public boolean equals(Object o) {
            	return DATA2[index][0].equals(o);
            }
            @Override
            public String getKey() {
            	return DATA2[index][0];
            }
            @Override
            public String getValue() {
            	return DATA2[index][1];
            }
            @Override
            public int hashCode() {
            	return DATA2[index][0].hashCode();
            }
            @Override
            public String setValue(String value) {
            	throw new UnsupportedOperationException();
            }
    	}
    	static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
    		int size;
    		EntrySet(int size) {
    			if(size < 0)
    				this.size = 0;
    			else if(size > DATA2.length)
    				this.size = DATA2.length;
    			else 
    				this.size = size;
    		}
    		private class Iter implements Iterator<Map.Entry<String, String>> {
    			Entry entry = new Entry(-1);
    			@Override
    			public boolean hasNext() {
    				return entry.index < size - 1;
    			}
    			@Override
    			public Map.Entry<String, String> next() {
    				entry.index++;
    				return entry;
    			}
    		}
			@Override
			public Iterator<java.util.Map.Entry<String, String>> iterator() {
				// TODO Auto-generated method stub
				return new Iter();
			}
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return size;
			}
    		
    	}
    }
    private static Map<String, String> map = new FlyweightMap();
    private static Map<String, String> select(final int size) {
    	return new FlyweightMap() {
    		public Set<Map.Entry<String, String>> entrySet() {
    			return new EntrySet(size);
    		}
    	};
    }
    public Map<String, String> countries() {
    	return map;
    }
    public Map<String, String> countries(int size) {
    	return select(size);
    }
    private static List<String> names = new ArrayList<String>(map.keySet());
    public List<String> names() {
    	return names;
    }
    public List<String> names(int size) {
    	return new ArrayList<String>(countries(size).keySet());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Countries2 c = new Countries2();
        System.out.println(c.countries());
        System.out.println(c.countries(10));
        System.out.println(c.names());
        System.out.println(c.names(5));
	}

}
