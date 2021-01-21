package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class HashSetTest {
    HashSet<String> testObject = new HashSet<String>();
    @Test void initialization() {
    	assert testObject.isEmpty();
    }
    @Test void _contains() {
    	testObject.add("one");
    	assert testObject.isEmpty();
    }
    @Test void _remove() {
    	testObject.add("one");
    	testObject.remove("one");
    }
	public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit HashSetTest");
	}

}
