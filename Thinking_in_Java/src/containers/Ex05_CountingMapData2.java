// 相比CountingMapData.java，本程序完全实现了享元模式。
package containers;

import java.util.*;

public class Ex05_CountingMapData2 extends AbstractMap<Integer, String> {
	private static String[] chars = 
    		"A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
	private int size;
	public Ex05_CountingMapData2(int size) {
    	this.size = size < 0 ? 0 : size;
    }
	private class Entry implements Map.Entry<Integer, String> {
    	int index;
    	Entry(int index) { this.index = index; }
    	@Override
    	public boolean equals(Object o) {
    	    return chars[index].equals(o);	
    	}
    	@Override
    	public Integer getKey() {
    		return index;
    	}
    	@Override
    	public String getValue() {
    		return chars[index % chars.length] + 
    				Integer.toString(index / chars.length);
    	}
    	@Override
    	public int hashCode() {
    		return Integer.valueOf(index).hashCode();
    	}
    	@Override
    	public String setValue(String value) {
    		throw new UnsupportedOperationException();
    	}
    }
	// 创建了一个定制的Set类，来完全实现享元
	class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
		@Override
		public int size() { return size; }
		@Override
		public Iterator<Map.Entry<Integer, String>> iterator() {
			return new Iter();
		}
		private class Iter implements Iterator<Map.Entry<Integer, String>> {
			Entry entry = new Entry(-1);
			@Override
			public boolean hasNext() {
				return entry.index < size - 1;
			}
			@Override
			public Map.Entry<Integer, String> next() {
				entry.index++;
				return entry;
			}
		}
	}
	private Set<Map.Entry<Integer, String>> entries = new EntrySet();
	@Override 
    public Set<Map.Entry<Integer, String>> entrySet() {
        return entries;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(new Ex05_CountingMapData2(60));
	}

}
