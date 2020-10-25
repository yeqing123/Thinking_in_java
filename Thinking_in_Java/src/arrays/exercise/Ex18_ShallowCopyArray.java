package arrays.exercise;
import java.util.Arrays;

import arrays.BerylliumSphere;

public class Ex18_ShallowCopyArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        BerylliumSphere[] spheres = new BerylliumSphere[5];
        Arrays.fill(spheres, new BerylliumSphere());
        System.out.println("spheres = " + Arrays.toString(spheres));
        BerylliumSphere[] a = new BerylliumSphere[5];
        System.arraycopy(spheres, 0, a, 0, spheres.length);
        System.out.println("a = " + Arrays.toString(a));
	}

}
