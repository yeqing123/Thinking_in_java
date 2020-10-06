package typeinfo;
import static net.mindview.util.Print.*;

class SimpleProxy2 implements Interface {
	private Interface proxied;
	public SimpleProxy2(Interface proxied) {
		this.proxied = proxied;
	}
	public void doSomething() {
		System.out.println("Proxy do Something.");
		long start = System.nanoTime(); 
		proxied.doSomething(); 
		long duration = System.nanoTime() - start; 
		print("METHOD-CALL TIME: " + duration);
	}
	public void somethingElse(String arg) {
		System.out.println("Proxy something else " + arg);
		long start = System.nanoTime(); 
		proxied.somethingElse(arg); 
		long duration = System.nanoTime() - start; 
		print("METHOD-CALL TIME: " + duration);
	}
}

public class Ex21_SimpleProxyDemo {
    public static void consumer(Interface iface) {
    	iface.doSomething();
    	iface.somethingElse("bonobo");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        consumer(new RealObject());
        consumer(new SimpleProxy2(new RealObject()));
	}

}
