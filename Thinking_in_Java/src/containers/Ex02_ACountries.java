package containers;
import java.util.*;

public class Ex02_ACountries {
    public static Map<String, String> getMap() {
    	Map<String, String> map = new HashMap<String, String>();
    	for(Map.Entry<String, String> entry : Countries.capitals().entrySet())
    		if(entry.getKey().startsWith("A"))
    			map.put(entry.getKey(), entry.getValue());
    	return map;
    }
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> aMap = getMap();
        System.out.println("aMap = " + aMap);
        System.out.println("aSet = " + aMap.keySet());
	}

}
