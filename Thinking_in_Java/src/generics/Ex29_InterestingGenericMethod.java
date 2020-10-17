package generics;
import java.util.*;

public class Ex29_InterestingGenericMethod {

	public static void f1(Holder<List<?>> holder) {
    	List<?> list = holder.get();
    	System.out.println(holder.equals(list));
    	// Compile error
    	// list.add(1);
    	// list.add(new Object());
    	Integer i = (Integer) list.get(0);
    	System.out.println(i);
    	System.out.println(list.contains(i));
    	System.out.println(list.remove(i));
    	holder.set(new ArrayList<Float>());
    }
	public static void f2(List<Holder<?>> list) {
		list.add(new Holder<Integer>(1));
		Holder<?> holder = list.get(0);
		System.out.println(holder.get());
		System.out.println(holder.equals(1));
		System.out.println(list.contains(list.get(0)));
		System.out.println(list.remove(0));
		System.out.println(list.size());
		// Compile error
		// holder.set(2);
		// holder.set(new Object());
		list.add(new Holder<Float>(1.5F));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        Holder<List<?>> holder1 = new Holder<List<?>>(list);
        Holder<Integer> holder2 = new Holder<Integer>(1);
        List<Holder<?>> lh = new ArrayList<Holder<?>>();
        lh.add(holder2);
        f1(holder1);
        System.out.println("***********************");
        f2(lh);
	}

}
