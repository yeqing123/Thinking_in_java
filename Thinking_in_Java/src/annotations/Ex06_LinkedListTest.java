package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class Ex06_LinkedListTest {
    LinkedList<String> testObject = new LinkedList<String>();
    @Test void initialization() {
    	assert testObject.isEmpty();
    }
    @Test void _contains() {
    	testObject.add("one");
    	assert testObject.contains("one");
    }
    @Test void _remove() {
    	testObject.add("one");
    	testObject.remove("one");
    	assert testObject.isEmpty();
    }
	public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit Ex06_LinkedListTest");
	}
}
