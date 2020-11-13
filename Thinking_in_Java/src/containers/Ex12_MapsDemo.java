package containers;
import java.util.*;


public class Ex12_MapsDemo {
    private static void test(Map<String, String> map) {
    	map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "brown");
        map.put("earth", "tall");
        map.put("sun", "warm");
        try {
        	map.put("extra", "object"); 
        } catch(ArrayIndexOutOfBoundsException e) {
        	// Never happen!
        	System.out.println("Too many objects!");
        }
        System.out.println(map);
        System.out.println(map.get("ocean"));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(new HashMap<String, String>());
		test(new TreeMap<String, String>());
		test(new LinkedHashMap<String, String>());
	}
}

