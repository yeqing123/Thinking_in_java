package containers;
import java.util.*;

public class Ex02_ACountries2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TreeMap<String, String> map = new TreeMap<String, String>(Countries.capitals());
        TreeSet<String> set = new TreeSet<String>(Countries.names());
        String b = null;
        for(String s : map.keySet())
        	if(s.startsWith("B")) {
        		b = s;
        		break;
        	}
        Map<String, String> aMap = map.headMap(b);
        Set<String> aSet = set.headSet(b);
        System.out.println("aMap = " + aMap);
        System.out.println("aSet = " + aSet);
	}

}
