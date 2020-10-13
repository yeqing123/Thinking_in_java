package typeinfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DynamicProxyHandler2 implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler2(Object proxied) {
		this.proxied = proxied;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.print("*** proxy: " + proxy.getClass().getSimpleName() + ", method: " + method +
				", arguments: ");
        if(args != null) {
        	for(Object arg : args)
        		System.out.print(arg + "  ");
        	System.out.println();
        } else {
        	System.out.println("null");
        }
        long start = System.nanoTime(); 
        Object ret = method.invoke(proxied, args); 
        long duration = System.nanoTime() - start; 
        System.out.println("METHOD-CALL TIME: " + duration); 
        return ret;
	}
}


public class Ex22_SimpleDynamicProxy {
    public static void consumer(Interface iface) {
    	iface.doSomething();
    	iface.somethingElse("bonobo");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface)Proxy.newProxyInstance(
        		Interface.class.getClassLoader(), 
        		new Class[] {Interface.class}, 
        		new DynamicProxyHandler2(real));
        consumer(proxy);
	}

}
