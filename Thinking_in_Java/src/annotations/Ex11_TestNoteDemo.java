package annotations;
import net.mindview.atunit.*;
import net.mindview.util.*;
import java.util.*;


public class Ex11_TestNoteDemo {
	HashSet<String> testObject = new HashSet<String>();
    @Test
    @TestNote("Tests if the new HashSet is empty")
    public void initialization() {
    	assert testObject.isEmpty();
    }
    @Test
    @TestNote("Tests the HashSet.contains(Object o) method")
    public void _containts() {
        testObject.add("one");
        assert testObject.contains("one");
    }
    @Test
    @TestNote("Tests the HashSet.remove(Object o) method")
    public void _remove() {
        testObject.add("one");
    	testObject.remove("one");
    	assert testObject.isEmpty();
    }
    @Test
    @TestNote("Test the HashSet.clear() method")
    public void _clear() {
        testObject.add("one");
        testObject.clear();
    	assert testObject.isEmpty();
    }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java annotations.Ex11_AtUnit2 annotations/Ex11_TestNoteDemo");
	}

}
