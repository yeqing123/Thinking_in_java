package generics;

public class Ex09_GenericsMethods {
    public static <T1, T2, T3> void f(T1 a, T2 b, T3 c) {
    	System.out.println(
    		a.getClass().getName() + ", " +
            b.getClass().getName() + ", " +
    		c.getClass().getName());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        f("", 'c', 10);
        f(1.5, "", 2L);
        f(4.6D, true, 1.1F);
	}

}
