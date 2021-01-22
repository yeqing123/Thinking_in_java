package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class Ex09_HashMapTest {
    HashMap<Integer, String> testObject = new HashMap<Integer, String>();
    @Test void initialization() {
    	assert testObject.isEmpty();
    }
    @Test void _put() {
    	testObject.put(1, "one");
    	assert !testObject.isEmpty();
    }
    @Test void _get() {
    	testObject.put(1, "one");
    	String v = testObject.get(1);
    	assert v.equals("one");
    }
    @Test void _remove() {
    	testObject.put(1, "one");
    	testObject.remove(1);
    	assert testObject.isEmpty();
    }
    @Test void _containsKey() {
    	testObject.put(1, "one");
    	assert testObject.containsKey(1);
    }
    @Test void _containsValue() {
    	testObject.put(1, "one");
    	assert testObject.containsValue("one");
    }
    @Test void _clear() {
    	testObject.put(1, "one");
    	assert !testObject.isEmpty();
    	testObject.clear();
    	assert testObject.isEmpty();
    }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/Ex09_HashMapTest");
	}

}
