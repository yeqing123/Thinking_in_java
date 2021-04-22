package object;

class DataOnly2 { 
	 int i; 
	 double d; 
	 boolean b; 
}

public class Ex05_DataOnly2 {

	public static void main(String[] args) {
        DataOnly2 dataOnly = new DataOnly2();
        dataOnly.i = 100;
        dataOnly.d = 1.56D;
        dataOnly.b = false;
        System.out.println("i = " + dataOnly.i + ", d = " + 
            dataOnly.d + ", b = " + dataOnly.b);
	}

}
