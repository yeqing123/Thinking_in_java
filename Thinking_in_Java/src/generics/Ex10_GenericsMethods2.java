package generics;

public class Ex10_GenericsMethods2 {
    public static <T1, T2> void f(T1 a, T2 b, String s) {
    	System.out.println(
        		a.getClass().getName() + ", " +
                b.getClass().getName() + ", " +
        		s.getClass().getName());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f(false, 'c', "");
        f(1.5, "", "abc");
	}

}
