package arrays.exercise;
import java.util.Arrays;
import java.util.Collections;

import net.mindview.util.*;

public class Ex23_FillingIntegerArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
        Integer[] array = Generated.array(new Integer[20], gen);
        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(array));
        System.out.println("After sorting:");
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        Arrays.sort(array, Collections.reverseOrder());
        System.out.println("Reverse sorting:");
        System.out.println(Arrays.toString(array));
	}

}
