package containers;

import java.util.HashMap;
import java.util.Map;

public class Ex26_CountedString2 extends CountedString {
    private char c;
	public Ex26_CountedString2(String s, char c) {
		super(s);
		this.c = c;
	}
	@Override
	public String toString() {
		return " char:" + c + " " + super.toString();
	}
    @Override
    public int hashCode() {
    	// Using Joshua Bloch's recipe:
    	int result = super.hashCode();
    	result = result * 37 + (int)c;
    	return result;
    }
    @Override
    public boolean equals(Object o) {
    	return o instanceof Ex26_CountedString2 &&
    			((Ex26_CountedString2)o).c == c &&
    			super.equals(o);
    }
	public static void main(String[] args) {
        Map<Ex26_CountedString2, Integer> map = 
        		new HashMap<Ex26_CountedString2, Integer>();
        Ex26_CountedString2[] cs2 = new Ex26_CountedString2[10];
        for(int i = 0; i < cs2.length; i++) {
        	cs2[i] = new Ex26_CountedString2("hi", 'y');
            map.put(cs2[i], i);
        }
        System.out.println(map);
        for(Ex26_CountedString2 cstring2 : cs2) {
        	System.out.println("Looking up " + cstring2);
            System.out.println(map.get(cstring2));
        }
	}

}
