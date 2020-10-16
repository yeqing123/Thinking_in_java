package generics;

import java.util.HashMap;
import java.util.Map;

public class Ex24_FactoryCapture2 {
    Map<String, FactoryI<?>> factorys = new HashMap<String, FactoryI<?>>();
    public <T> Object createNew(String typename, T parameter) {
    	FactoryI<?> factory = factorys.get(typename);
		try {
			return factory.create(parameter);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Not a registered typename: " + typename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
    	return null;
    }
    public void addFactory(String typename, FactoryI<?> factory) {
    	this.factorys.put(typename, factory);
    }
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Ex24_FactoryCapture2 fc = new Ex24_FactoryCapture2();
		fc.addFactory("Integer", new IntegerFactory());
		fc.addFactory("Hamster", new HamsterFactory());
		fc.addFactory("Widget", new Widget.Factory());
		System.out.println(fc.createNew("Integer", 899));
		System.out.println(fc.createNew("Hamster", "hello"));
		System.out.println(fc.createNew("Widget", null));
	}

}