package arrays.exercise;

import java.util.Arrays;

public class Ex02_ReturnArray {
    public static BerylliumSphere[] createArray(int size) {
    	BerylliumSphere[] bs = new BerylliumSphere[size];
    	for(int i = 0; i < bs.length; i++)
    		bs[i] = new BerylliumSphere();
    	return bs;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(Arrays.toString(createArray(3)));
        System.out.println(Arrays.toString(createArray(5)));
	}

}
