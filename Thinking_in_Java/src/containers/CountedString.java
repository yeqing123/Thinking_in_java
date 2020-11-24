package containers;
import java.util.*;

public class CountedString {
    private static List<String> created = new ArrayList<String>();
    private String s;
    private int id = 0;
    public CountedString(String s) {
    	this.s = s;
    	created.add(s);
    	for(String s2 : created)
    		if(s2.equals(s))
    			id++;
    }
    public String toString() {
    	return "String: " + s + " id: " + id + " hashCode(): " + hashCode();
    }
    public int hashCode() {
    	int result = 17;
    	result = result * 37 + s.hashCode();
    //	result = result * 37 + id;
    	return result;
    }
    public boolean equals(Object o) {
    	return (o instanceof CountedString) &&
    			s.equals(((CountedString)o).s) && 
    			((CountedString)o).id == id;
    }
	public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
        CountedString[] cs = new CountedString[10];
        for(int i = 0; i < 10; i++) {
        	cs[i] = new CountedString("hi");
            map.put(cs[i], i);
        }
        System.out.println(map);
        for(CountedString cstring : cs) {
        	System.out.println("Looking up " + cstring);
            System.out.println(map.get(cstring));
        }
	}

}
