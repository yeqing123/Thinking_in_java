package object;

class DataOnly { 
	 int i = 21; 
	 double d = 1.5D; 
	 boolean b = true; 
	 public void f() {
		 System.out.println("i = " + i + ", d = " + d + ", b = " + b);
	 }
}

public class Ex04_DataOnly {

	public static void main(String[] args) {
        new DataOnly().f();
	}

}
