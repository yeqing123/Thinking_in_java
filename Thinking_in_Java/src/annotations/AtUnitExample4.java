package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class AtUnitExample4 {
    static String theory = "All brontosaurses are thin at one end, " +
        "much MUCH thicker in the middle, and then thin again at the far end.";
    private String word;
    private Random rand = new Random(); // Time-based seed
    public AtUnitExample4(String word) { this.word = word; }
    public String getWord() { return word; }
    public String scrambleWord() {
    	List<Character> chars = new ArrayList<Character>();
    	for(Character c : word.toCharArray())
    		chars.add(c);
    	Collections.shuffle(chars);
    	StringBuilder result = new StringBuilder();
    	for(char ch : chars)
    		result.append(ch);
    	return result.toString();
    }
    @TestProperty static List<String> input = Arrays.asList(theory.split(" "));
    @TestProperty static Iterator<String> words = input.iterator();
    @TestObjectCreate static AtUnitExample4 create() {
    	if(words.hasNext())
    		return new AtUnitExample4(words.next());
    	else
    		return null;
    }
    @Test boolean words() {
    	System.out.println("'" + getWord() + "'");
    	return getWord().equals("All");
    }
    @Test boolean scramble1() {
    	// Change to a specific seed to get verifiable results:
    	rand = new Random(47);
    	System.out.println("'" + getWord() + "'");
    	String scrambled = scrambleWord();
    	System.out.println(scrambled);
    	return scrambled.equals("nasssotroebur");
    }
    @Test boolean scramble2() {
    	rand = new Random(74);
    	System.out.println("'" + getWord() + "'");
    	String scrambled = scrambleWord();
    	System.out.println(scrambled);
    	return scrambled.equals("rea");
    }
	public static void main(String[] args) {
        System.out.println("starting");
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/AtUnitExample4");
	}

}
