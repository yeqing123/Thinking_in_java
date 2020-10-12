package generics;

import java.util.Iterator;
import java.util.Random;

import net.mindview.util.Generator;

class StoryCharacters {
	private static long counter = 0;
	private final long id = counter++;
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
}

class GoodGuys extends StoryCharacters {
	public String toString() {
		return super.toString() + " is a good guy.";
	}
}

class BadGuys extends StoryCharacters {
	public String toString() {
		return super.toString() + " is a bad guy.";
	}
}

class Lengfeng extends GoodGuys {}

class HarryPotter extends GoodGuys {}

class Clown extends BadGuys {}

class CaptainPhasma extends BadGuys {}

class StroyCharactersGenerator implements Generator<StoryCharacters>, Iterable<StoryCharacters> {
	private Class[] types = {Lengfeng.class, HarryPotter.class, Clown.class, CaptainPhasma.class};
	private static Random rand = new Random(47);
	public StroyCharactersGenerator() {};
	private int size = 0;
	public StroyCharactersGenerator(int size) { this.size = size; }
	public StoryCharacters next() {
		try {
			return (StoryCharacters)types[rand.nextInt(types.length)].newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Iterator<StoryCharacters> iterator() {
		return new Iterator<StoryCharacters>() {
	        private int count = size;
			public boolean hasNext() { return count > 0; }
			public StoryCharacters next() {
				count--;
				return StroyCharactersGenerator.this.next();
			}
		};
	}
}

public class Ex08_StoryCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for(StoryCharacters sc : new StroyCharactersGenerator(8))
        	System.out.println(sc);
        StroyCharactersGenerator gen = new StroyCharactersGenerator();
        for(int i = 0; i < 8; i++)
        	System.out.println(gen.next());
	}

}
