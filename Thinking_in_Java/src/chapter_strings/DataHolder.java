package chapter_strings;

import java.util.Locale;

public class DataHolder {
    private int intField = 13;
    private long longField = 199L;
    private float floatField = 3.158f;
    private double doubleField = 125.1598D;
    public String toString() {
    	StringBuilder result = new StringBuilder();
    	result.append("DataHolder: \n");
    	result.append(String.format(Locale.CHINA, "intField = %d\n", intField));
    	result.append(String.format(Locale.CHINA, "longField = %d\n", longField));
    	result.append(String.format(Locale.CHINA, "floatField = %f\n", floatField));
    	result.append(String.format(Locale.CHINA, "doubleField = %e\n", doubleField));
    	return result.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(new DataHolder());
	}

}
