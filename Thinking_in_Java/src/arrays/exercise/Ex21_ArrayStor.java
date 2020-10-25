package arrays.exercise;

import java.util.Arrays;
import arrays.BerylliumSphere;

public class Ex21_ArrayStor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BerylliumSphere[] spheres = new BerylliumSphere[5];
		for(int i = 0; i < spheres.length; i++)
			spheres[i] = new BerylliumSphere();
		System.out.println(Arrays.toString(spheres));
	}

}
