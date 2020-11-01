package arrays.exercise;

import java.util.Arrays;
import net.mindview.util.*;

public class Ex22_BinarySearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Generator<Integer> gen = 
        		new RandomGenerator.Integer(1000);
        int[] a = ConvertTo.primitive(Generated.array(new Integer[20], gen));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
	    int index = Arrays.binarySearch(a, a[5]);
	    if(index >= 0) {
	    	System.out.println("a[" +index + "]: " + a[index]);
        } else {
        	System.out.println("Don't find a[" + index + "] element!");
        }
	    index = Arrays.binarySearch(a, a[10]);
	    if(index >= 0) {
	    	System.out.println("a[" +index + "]: " + a[index]);
	    } else {
	    	System.out.println("Don't find a[" + index + "] element!");
	    }
	}

}
