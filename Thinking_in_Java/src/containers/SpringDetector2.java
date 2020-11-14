package containers;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SpringDetector2 {
	public static <T extends Groundhog2>
    void detectorSpring(Class<T> type) throws Exception {
    	Constructor<T> ghog = type.getConstructor(int.class);
    	Map<Groundhog2, Prediction> map = new HashMap<Groundhog2, Prediction>();
    	for(int i = 0; i < 10; i++)
    		map.put(ghog.newInstance(i), new Prediction());
    	System.out.println("map = " + map);
    	Groundhog gh = ghog.newInstance(3);
    	System.out.println("Looking up prediction for " + gh);
    	if(map.containsKey(gh))
    		System.out.println(map.get(gh));
    	else
    		System.out.println("Key not found: " + gh);
    }
	public static void main(String[] args) throws Exception {
        detectorSpring(Groundhog2.class);  
	}
}
