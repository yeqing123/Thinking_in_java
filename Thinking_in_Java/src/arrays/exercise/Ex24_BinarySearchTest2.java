package arrays.exercise;

import java.util.Arrays;
import java.util.Comparator;

public class Ex24_BinarySearchTest2 {
    public static <T> void binarySearch(T ele, T[] a, Comparator<T> comp) {
    	int location = Arrays.binarySearch(a, ele, comp);
    	System.out.println("The posistion of \"" + ele + "\" in the Array is " + location);
    	if(location >= 0)
	        System.out.println("a[" + location + "]: " + a[location]);
    	else
			System.out.println("Can't find the \"" + ele + "\" in array!");
    }
    public static <T> void binarySearch(T ele, T[] a) {
    	for(int i = 0; i < a.length; i++) 
    		if(a[i].equals(ele)) {
    			System.out.println("The posistion of \"" + ele + "\" in the Array is " + i);
    			System.out.println("a[" + i + "]: " + a[i]);
    			return ;
    		}
    	System.out.println("Can't find the \"" + ele + "\" in array!");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comparator<HoldingNumber> comp = 
				 new Comparator<HoldingNumber>() {
					@Override
					public int compare(HoldingNumber arg0, HoldingNumber arg1) {
						// TODO Auto-generated method stub
						return (arg0.number > arg1.number ? 
								1 : (arg0.number == arg1.number ? 0 : -1));
					}
		};
		HoldingNumber[] a = new HoldingNumber[10];
		for(int i = 0; i < 10; i++)
			a[i] = new HoldingNumber(i);
		Arrays.sort(a, comp);
		binarySearch(a[4], a, comp);
		binarySearch(a[7], a, comp);
		binarySearch(new HoldingNumber(1001), a, comp);
		HoldingNumber[] b = new HoldingNumberWithEquals[10];
		for(int i = 0; i < 10; i++)
			b[i] = new HoldingNumberWithEquals(i);
		System.out.println("===================================");
		System.out.println(Arrays.toString(b));
		binarySearch(b[4], b);
		binarySearch(b[7], b);
		binarySearch(new HoldingNumberWithEquals(11), b);
	}

}
