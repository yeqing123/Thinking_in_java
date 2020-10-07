package typeinfo;

import java.lang.reflect.*;
import typeinfo.interfaced.A;
import typeinfo.packageaccess.HiddenT;

public class Ex25_ExternalClass {
    public static void callHiddenMethod(Object a, String methodName) throws Exception {
    	Method method = a.getClass().getDeclaredMethod(methodName);
    	method.setAccessible(true);
	    method.invoke(a);
    }
    public static void modifyingPrivateField(Object a, String fieldName) throws Exception {
    	Field field = a.getClass().getDeclaredField(fieldName);
    	field.setAccessible(true);
    	field.set(a, "I'm " + field.getName() + ". I am ont safe!");
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        A a = HiddenT.makeA();
        System.out.println(a);
        a.f();
        // Compile time error
        //a.g();
        //a.v();
        // Call T's all method by reflect
        callHiddenMethod(a, "f");
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "v");
        
        // Modifying private fields
        modifyingPrivateField(a, "s1");
        modifyingPrivateField(a, "s2");
        System.out.println(a);
	}

}
