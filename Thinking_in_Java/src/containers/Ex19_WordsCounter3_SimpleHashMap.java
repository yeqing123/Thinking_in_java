package containers;
import java.util.Map;
import net.mindview.util.*;

public class Ex19_WordsCounter3_SimpleHashMap {
    public static void countingWords(Map<String, Integer> map, String[] words) {
    	for(String w : words) {
        	if(map.containsKey(w))
        		map.put(w, map.get(w) + 1);
        	else
        		map.put(w, 1);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String path = System.getProperty("user.dir");
        String fileName = path + "\\src\\containers\\Ex19_WordsCounter3_SimpleHashMap.java";
        String[] words = new TextFile(fileName, "\\W+").toArray(new String[0]);
        SimpleHashMap<String, Integer> fastCounter = new SimpleHashMap<String, Integer>();
        SlowMap<String, Integer> slowCounter = new SlowMap<String, Integer>();
        countingWords(fastCounter, words);
        countingWords(slowCounter, words);
        System.out.println("fastCounter:\n" + fastCounter);
        System.out.println("slowCounter:\n" + slowCounter);
	}

}
