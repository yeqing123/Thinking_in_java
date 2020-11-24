package containers;

public class StringHashCode {

	public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        for(String s : hellos)
        	System.out.println(s.hashCode());
	}

}
