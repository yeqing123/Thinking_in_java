package containers;

import java.util.*;

import static net.mindview.util.Print.*;

public class CollectionMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Collection<String> c = new ArrayList<String>();
        c.addAll(Countries.names(6));
        c.add("ten");
        c.add("eleven");
        print("c: " + c);
        // Make an array from the List:
        Object[] array1 = c.toArray();
        print("Object[]: " + Arrays.toString(array1));
        // Make a String array from the List:
        String[] array2 = c.toArray(new String[0]);
        print("String[]: " + Arrays.toString(array2));
        // Find max and min elements; this means different things depending
        // on the way the Comparable interface is implemented:
        String max = Collections.max(c);
        print("max: " + max);
        String min = Collections.min(c);
        print("min: " + min);
        // Add a Collection to another Collection:
        Collection<String> c2 = new ArrayList<String>();
        c2.addAll(c);
        c.remove(Countries.DATA[0][0]);
        print(c);
        c.remove(Countries.DATA[1][0]);
        print(c);
        // Remove all components that are in the argument collection:
        c.removeAll(c2);
        print("c.isEmpty(): " + c.isEmpty());
        c.addAll(c2);
        print(c);
        // Is an element in this Collection?
        String val = Countries.DATA[3][0];
        print("c.contains(" + val + "): " + c.contains(val));
        // Is a Collection in this Collection?
        Collection<String> c3 = ((List<String>)c).subList(3, 5);
        print("c3: " + c3);
        print("c.containsAll(c3): " + c.containsAll(c3));
        // Keep all the elements that are in both c2 and c3 
        // (an intersection of sets):
        c2.retainAll(c3);
        print("c2: " + c2);
        // Throw away all the elements in c2 that also appear in c3:
        c2.removeAll(c3);
        print("c2.isEmpty(): " + c2.isEmpty());
        c = new ArrayList<String>();
        c.addAll(Countries.names(6));
        c.clear();  // Remove all elements.
        print("after c.clear: " + c);
	}

}
