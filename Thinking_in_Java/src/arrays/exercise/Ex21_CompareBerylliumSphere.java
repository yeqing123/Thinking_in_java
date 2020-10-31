package arrays.exercise;

import java.lang.reflect.Field;
import java.util.*;

import arrays.BerylliumSphere;
import net.mindview.util.Generated;
import net.mindview.util.Generator;

class BSGenerator implements Generator<BerylliumSphere> {

	@Override
	public BerylliumSphere next() {
		// TODO Auto-generated method stub
		return new BerylliumSphere();
	}
	
}

class ComparableBerylliumSphere extends BerylliumSphere implements Comparable<BerylliumSphere> {
	// Because the field id is private, so change its access first
	public long getId(BerylliumSphere s) {
		try {
			Field f = BerylliumSphere.class.getDeclaredField("id");
			f.setAccessible(true);
			return f.getLong(s);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}
	public int compareTo(BerylliumSphere s) {
		return (getId(this) < getId(s) ? -1 : (getId(this) == getId(s) ? 0 : 1));
	}
	
}

public class Ex21_CompareBerylliumSphere {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random(47);
		BerylliumSphere[] spheres = 
				Generated.array(BerylliumSphere.class, new BSGenerator(), 10);
		Collections.shuffle(Arrays.asList(spheres), r);
		System.out.println("Not comparable BS:");
		System.out.println(Arrays.toString(spheres));
		try {
			Arrays.sort(spheres);
		} catch(Exception e) {
			System.out.println("BerylliumSphere is not sortable!");
			System.out.println(e + "");
		}
		ComparableBerylliumSphere[] comparableSpheres = new ComparableBerylliumSphere[10];
        for(int i = 0; i < 10; i++)
        	comparableSpheres[i] = new ComparableBerylliumSphere();
        Collections.shuffle(Arrays.asList(comparableSpheres), r);
		System.out.println("Before sorting for comparable BS:");
		System.out.println(Arrays.toString(comparableSpheres));
		Arrays.sort(comparableSpheres);
		System.out.println("After sorting for comparable BS:");
		System.out.println(Arrays.toString(comparableSpheres));
	}

}
