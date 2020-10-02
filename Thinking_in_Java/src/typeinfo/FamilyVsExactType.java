package typeinfo;
import static net.mindview.util.Print.*;

class Base2 {}
class Derived2 extends Base2 {}

public class FamilyVsExactType {
    static void test(Object x) {
    	print("Testing x of type " + x.getClass());
    	print("x instanceof Base2 " + (x instanceof Base2));
    	print("x instanceof Derived2 " + (x instanceof Derived2));
    	print("Base2.isInstance(x) " + Base2.class.isInstance(x));
    	print("Derived2.isInstance(x) " + Derived2.class.isInstance(x));
    	print("x.getClass() == Base2.class " + (x.getClass() == Base2.class));
    	print("x.getClass() == Derived2.class " + (x.getClass() == Derived2.class));
    	print("x.getClass().equals(Base2.class) " + x.getClass().equals(Base2.class));
    	print("x.getClass().equals(Derived2.class) " + x.getClass().equals(Derived2.class));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        test(new Base2());
        print();
        test(new Derived2());
	}

}
