package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class Ex04_HashSetTest2 {
    HashSet<String> testObject = new HashSet<String>();
    @Test void initialization() {
    	System.out.println(this);
    	assert testObject.isEmpty();
    }
    @Test void _contains() {
    	System.out.println(this);
    	testObject.add("one");
    	assert testObject.isEmpty();
    }
    @Test void _remove() {
    	System.out.println(this);
    	testObject.add("one");
    	testObject.remove("one");
    }
	public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit HashSetTest");
	}

}