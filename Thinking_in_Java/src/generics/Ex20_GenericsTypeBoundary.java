package generics;

interface Interface {
	void f();
	void g();
}

class Implementation implements Interface {
	public void f() { System.out.println("Interface :: Method f()"); }
	public void g() { System.out.println("Interface :: Method g()"); }
	public void h() { System.out.println("Implementation :: Method h()"); }
}

public class Ex20_GenericsTypeBoundary {
    public static <T extends Interface> void k(T a) {
    	a.f();
    	a.g();
    	// h() is not part of an interface. So don't call h() method
    	//a.h();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        k(new Implementation());
	}

}
