package generics;

public class Ex26_CovariantArray2 {
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Number[] number = new Integer[10];
        number[0] = Integer.valueOf(10);  // OK
        try {
        	number[1] = Double.valueOf(1.5); 
        } catch(Exception e) {
        	System.out.println(e);
        }
        try {
        	number[1] = Byte.valueOf((byte)1); 
        } catch(Exception e) {
        	System.out.println(e);
        }
	}

}
