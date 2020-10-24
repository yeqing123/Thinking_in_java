package arrays.exercise;
import java.util.*;

class Banana {
	private static long length = 0;
	private final long counter = length++;
	public String toString() { 
		return this.getClass().getSimpleName() + " " + counter; 
	}
}

class Peel<T> {
	private T fruit;
	public Peel(T fruit) {
		this.fruit = fruit;
	}
	public void peel() {
		System.out.println("peeling " + fruit);
	}
}

public class Ex09_PeelBanana {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 不能创建一个泛型数组
        // Peel<Banana>[] p = new Peel<Panann>[5];
		// 改用ArrayList<Banana>
		List<Peel<Banana>> peels = new ArrayList<Peel<Banana>>();
		for(int i = 0; i < 5; i++)
		    peels.add(new Peel<Banana>(new Banana()));
		for(Peel<Banana> p : peels)
			p.peel();
	}

}
