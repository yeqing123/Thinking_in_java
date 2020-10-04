package typeinfo;

interface Interface {
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface {
	public void doSomething() {
		System.out.println("Do something.");
	}
	public void somethingElse(String arg) {
		System.out.println("Something else " + arg);
	}
}

class SimpleProxy implements Interface {
	private Interface proxied;
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	public void doSomething() {
		proxied.doSomething();
		System.out.println("Proxy do Something.");
	}
	public void somethingElse(String arg) {
		proxied.somethingElse(arg);
		System.out.println("Proxy something else.");
	}
}

public class SimpleProxyDemo {
    public static void consumer(Interface iface) {
    	iface.doSomething();
    	iface.somethingElse("bonobo");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
	}

}
