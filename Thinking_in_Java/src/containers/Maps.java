package containers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {
	public static void printKeys(Map<Object, Object> map) {
		System.out.println("Size:" + map.size());
		System.out.print("Keys:");
    	for(Object key : map.keySet())// Produce a Set of the keys
    		System.out.print(key + " ");
    	System.out.println();
    }
    static void test(Map<Object, Object> map) {
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
    	map.keySet().removeAll(map.keySet());
    	System.out.println("map.isEmpty():" + map.isEmpty());
    	map.putAll(new CountingMapData(25));  
    	map.values().removeAll(map.values());
    	System.out.println("map.isEmpty():" + map.isEmpty());
    }
	public static void main(String[] args) {
    	test(new Properties());
	}
}
