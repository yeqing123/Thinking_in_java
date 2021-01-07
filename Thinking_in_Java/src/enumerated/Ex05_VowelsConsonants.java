package enumerated;
import java.util.*;

enum CharacterCategory {
	VOWEL('a','e','i','o','u') {
		public String toString() { return "vowel"; }
	}, 
	SOMETIME_A_VOWEL('y','w') {
		public String toString() { return "sometimes a vowel"; }
	}, 
	CONSONANTS {
		public String toString() { return "consonants"; }
	};
	private Set<Character> chars = 
			new HashSet<Character>();
	private CharacterCategory(Character...cs) {
		if(cs != null)
		    chars.addAll(Arrays.asList(cs));
	}
	public static CharacterCategory getCategory(Character c) {
		if(VOWEL.chars.contains(c))
			return VOWEL;
		else if(SOMETIME_A_VOWEL.chars.contains(c))
			return SOMETIME_A_VOWEL;
		else
			return CONSONANTS;
	}
}

public class Ex05_VowelsConsonants {
    public static void main(String[] args) { 
        Random rand = new Random(47);
        for(int i = 0; i < 25; i++) {
        	int c = rand.nextInt(26) + 'a';
        	System.out.print((char)c + ", " + c + ": ");
        	System.out.println(CharacterCategory.getCategory((char)c));
        }
    }

}
