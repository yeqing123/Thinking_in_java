// Unsupported operations in Java containers.
package containers;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Unsupported {
    public static void test(String msg, List<String> list) {
    	System.out.println("----" + msg + "----");
    	Collection<String> c = list;
    	// list.subList()方法返回list集合中指定部分的“图视”，
    	// 因此改变其中一个，另一个也会随之改变！解决办法就是重新开辟一个新的List集合。
    	Collection<String> subList = list.subList(1, 8);
    	Collection<String> c2 = new ArrayList<String>(subList);
    	try {
    		c.retainAll(c2);
    	} catch(Exception e) {
    		System.out.println("c.retainAll(c2):" +e);
    	}
    	try {
    		c.removeAll(c2);
    	} catch(Exception e) {
    		System.out.println("c.removeAll(c2):" + e);
    	}
    	try {
    		c.clear();
    	} catch(Exception e) {
    		System.out.println("c.clear():" + e);
    	}
    	try {
    		c.add("X");
    	} catch(Exception e) {
    		System.out.println("c.add(\"X\"):" + e);
    	}
    	try {
    		c.addAll(c2);
    	} catch(Exception e) {
    		System.out.println("c.addAll(c2):" + e);
    	}
    	try {
    		c.remove("C");
    	} catch(Exception e) {
    		System.out.println("c.remove(\"C\"):" + e);
    	}
    	// The List.set() method modifies the value but
    	// doesn't change the size of the data structure:
    	try {
    		list.set(0, "S");
    	} catch(Exception e) {
    		System.out.println("list.set(\"S\"):" + e);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> list = 
        		Arrays.asList(("A B C D E F G H I J K L").split(" "));
        test("Modifiable List", new ArrayList<String>(list));
        test("Arrays.asList()", list);
        test("Unmodifiable List", Collections.unmodifiableList(list));
	}

}
