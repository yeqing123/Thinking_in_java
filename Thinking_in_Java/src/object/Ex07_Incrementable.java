package object;

class StaticTest { 
	static int i = 47; 
} 

public class Ex07_Incrementable {
	static void increment() { StaticTest.i++; }
	public static void main(String[] args) {
        increment();
        increment();
        increment();
        System.out.println(StaticTest.i);
	}

}
