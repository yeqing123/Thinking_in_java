package containers;

import java.util.*;


class StringAddress {
	String s;
	public StringAddress(String s) {
		this.s = s;
	}
	public String toString() {
		return super.toString() + " " + s;
	}
}

public class FillingLists {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<StringAddress> list = new ArrayList<StringAddress>(
        		Collections.nCopies(5, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World"));
        System.out.println(list);
	}

}
