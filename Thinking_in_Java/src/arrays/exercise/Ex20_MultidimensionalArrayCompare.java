package arrays.exercise;

import java.util.Arrays;

public class Ex20_MultidimensionalArrayCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[][] intArray1 = {
        		{1, 2, 3},
        		{4, 5},
        		{6, 7, 8, 9}};
        int[][] intArray2 = {
        		{1, 2, 3},
        		{4, 5},
        		{6, 7, 8, 9}};
        Integer[][] intArray3 = {
        		{1, 2, 3},
        		{4, 5},
        		{6, 7, 8, 9}};
        int[][] intArray4 = {
        		{1, 2, 3},
        		{4, 5, 6, 7},
        		{8, 9}};
       
        System.out.println("intArray1 and intArray2 compare: " + Arrays.deepEquals(intArray1, intArray2));
        // Arrays.deepEquals() don't report intArray1 and intArray3 as equals,
        // though they are semantically same, because compiler sees they as different.
        System.out.println("intArray1 and intArray3 compare: " + Arrays.deepEquals(intArray1, intArray3));
        System.out.println("intArray1 and intArray4 compare: " + Arrays.deepEquals(intArray1, intArray4));
	}

}
