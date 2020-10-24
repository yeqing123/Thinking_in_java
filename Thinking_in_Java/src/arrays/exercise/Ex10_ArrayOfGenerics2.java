package arrays.exercise;
import java.util.*;

import arrays.BerylliumSphere;

public class Ex10_ArrayOfGenerics2 {
	public static void main(String[] args) {
		List<List<String>> ls = new ArrayList<List<String>>();
		ls.add(new ArrayList<String>());
		ls.get(0).add("A String in ArrayList");
		System.out.println(ls.get(0).get(0));
		// Compiler error:
		// A Integer object cann't cast to String type.
		// ls.add(new ArrayList<Integer>);
		List<List<BerylliumSphere>> spheres = new ArrayList<List<BerylliumSphere>>(10);
		for (int i = 0; i < spheres.size(); i++)
			spheres.add(new ArrayList<BerylliumSphere>());
	}
}