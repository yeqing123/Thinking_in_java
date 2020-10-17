package generics;

interface Top {
	void a();
	void b();
}
interface Low {
	void c();
	void d();
}

class TopLowImpl implements Top, Low {
	public void a() { System.out.println("Top :: a()"); }
	public void b() { System.out.println("Top :: b()"); }
	public void c() { System.out.println("Low :: c()"); }
	public void d() { System.out.println("Low :: d()"); }
}

public class Ex25_MethodBounds {
	public static <T extends Top> void basicA(T obj) {
		obj.a();
		obj.b();
	}
	public static <T extends Low> void basicB(T obj) {
		obj.c();
		obj.d();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TopLowImpl d = new TopLowImpl();
        basicA(d);
        basicB(d);
	}

}
