package containers;
import java.util.*;

public class Ex06_Unsupported {
    public static void test(String msg, List<String> list) {
    	System.out.println("----" + msg + "----");
    	List<String> subList = new ArrayList<String>(list.subList(1, 8));
    	try {
    		list.add(0, "X");
    	} catch(Exception e) {
    		System.out.println("list.add(0, \"X\"):" + e);
    	}
    	try {
    		list.addAll(1, subList);
    	} catch(Exception e) {
    		System.out.println("list.addAll(1, subList):" + e);
    	}
    	try {
    		list.removeAll(subList);
    	} catch(Exception e) {
    		System.out.println("list.removeAll(subList):" + e);
    	}
    	try {
    		list.remove(0);
    	} catch(Exception e) {
    		System.out.println("list.remove(0):" + e);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> list = 
        		Arrays.asList(("A B C D E F G H I J K L M").split(" "));
        test("modifiable list", new ArrayList<String>(list));
        test("Arrays.asList()", list);
        test("ummodifiable list", Collections.unmodifiableList(list));
	}

}
