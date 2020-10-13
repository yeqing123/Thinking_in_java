package generics;

import net.mindview.util.Generator;

class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;
	public BasicGenerator(Class<T> type) {
		this.type = type;
	}
	public T next() {
		try {
			return type.newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}

class CountedObject {
	private static long counter = 0;
	private final long id = counter++;
	public String toString() {
		return "CountedObject instance " + id;
	}
}

public class Ex14_BasicGeneratorDemo {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class);
        for(int i = 0 ; i < 9; i++)
        	System.out.println(gen.next());
	}

}
