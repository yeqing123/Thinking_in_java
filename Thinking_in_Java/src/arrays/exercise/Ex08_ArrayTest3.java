package arrays.exercise;

import java.util.Arrays;

public class Ex08_ArrayTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Object[] array = new Object[5];
        array[0] = new Integer(10);
        array[1] = "yeqing";
        array[2] = true;
        array[3] = 'C';
        array[4] = 1.5D;
        System.out.println(Arrays.toString(array));
        Integer[] ints = (Integer[])array;
        System.out.println(Arrays.toString(ints));
	}

}
