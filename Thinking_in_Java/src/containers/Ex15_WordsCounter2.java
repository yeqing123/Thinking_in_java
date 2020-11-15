package containers;
import java.util.List;
import net.mindview.util.TextFile;

public class Ex15_WordsCounter2 {
    
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
        String fileName = path + "\\src\\containers\\Ex15_WordsCounter2.java";
        List<String> text = new TextFile(fileName, "\\W+");
        SlowMap<String, Integer> counter = new SlowMap<String, Integer>();
        for(String word : text) {
        	if(!counter.containsKey(word))
        		counter.put(word, 1);
        	else
        		counter.put(word, counter.get(word) + 1);
        }
        System.out.println(counter);
	}
}
