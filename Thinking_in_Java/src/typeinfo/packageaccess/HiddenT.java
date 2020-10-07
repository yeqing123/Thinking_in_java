package typeinfo.packageaccess;

import typeinfo.interfaced.A;

class T implements A {
	private String s1 = "I'm s1. Am I safe?";
	private final String s2 = "I'm s2. Am I safe?";
	public String toString() { return s1 + "  " + s2; }
    public void f() { System.out.println("T: f()"); }
    private void g() { System.out.println("T: g()"); }
    void v() { System.out.println("T: v()"); }
}

public class HiddenT {
	public static A makeA() { return new T(); }
}
