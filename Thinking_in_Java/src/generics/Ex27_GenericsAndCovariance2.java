package generics;

import java.util.*;

public class Ex27_GenericsAndCovariance2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<? extends Number> nlist = new ArrayList<Integer>();
        // Compiler Error: can't add any type of object:
        //nlist.add(new Integer(0));
        //nlist.add(new Long(100));
        //nlist.add(new Double(1.5));
        //nlist.add(new Object());  // Not even can adding an Object
        nlist.add(null);    // Legal but uninteresting
        // We know that it returns at least Number
        Number n = nlist.get(0);
	}

}
