package typeinfo;
import java.lang.reflect.*;

class DynamicProxyHandler3 implements InvocationHandler {
	private Object proxied;
	public DynamicProxyHandler3(Object proxied) {
		this.proxied = proxied;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy: " + proxy); 
		return method.invoke(proxied, args);
	}
}


public class Ex23_SimpleDynamicProxy {
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
        		new DynamicProxyHandler3(real));
        consumer(proxy);
	}

}
