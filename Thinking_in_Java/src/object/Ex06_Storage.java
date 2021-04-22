package object;

public class Ex06_Storage {
	int storage(String s) { 
		 return s.length() * 2; 
	}
	public static void main(String[] args) {
        int length = new Ex06_Storage().storage("Hello world!");
		System.out.println("storage(\"Hello world!\"): " + length);
	}
}
