package object;

public class Ex08_StaticTest {

	public static void main(String[] args) {
        StaticTest st1 = new StaticTest();
        StaticTest st2 = new StaticTest();
        StaticTest st3 = new StaticTest();
        System.out.println(st1.i + ", " + st2.i + ", " + st3.i);
        st1.i++;
        System.out.println(st1.i + ", " + st2.i + ", " + st3.i);
	}

}
