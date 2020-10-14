package generics;
import java.util.*;

class Building {}
class House extends Building {}

public class Ex21_ClassTypeCapture2<T> {
    Class<T> kind;
    Map<String, Class<?>> map;
    @SuppressWarnings("unchecked")
    public T createNew(String typename) throws Exception {
    	Class<?> type = Class.forName(typename);
    	if(type.isInstance(kind))
    	    return (T) type.newInstance();
    	throw new RuntimeException("Not find " + typename);
    }
    public void addType(String typename, Class<?> kind) {
    	this.map.put(typename, kind);
    }
	public Ex21_ClassTypeCapture2(Class<T> kind) {
    	this.kind = kind;
        this.map = new HashMap();
    }
	public Map<String, Class<?>> getMap() {
		return map;
	}
	public String toString() {
		return this.map.toString();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Ex21_ClassTypeCapture2<Building> ctc1 = 
				new Ex21_ClassTypeCapture2<Building>(Building.class);
		
		ctc1.addType("Building", Building.class);
		ctc1.addType("House", House.class);
		System.out.println(ctc1);
		Map<String, Class<?>> map = ctc1.getMap();
	    Set<String> keys = map.keySet();
	    Iterator<String> iter = keys.iterator();
	    while(iter.hasNext()) {
	    	String typename = iter.next();
	    	Object obj = ctc1.createNew(typename);
	    	System.out.println(obj.getClass().getSimpleName());
	    }
	}

}
