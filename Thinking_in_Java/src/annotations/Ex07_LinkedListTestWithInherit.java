package annotations;
import java.util.*;
import net.mindview.util.*;
import net.minview.atunit.*;

public class Ex07_LinkedListTestWithInherit extends LinkedList {
    @Test void initialization() {
	    assert this.isEmpty();
    }
    @Test void _contains() {
    	this.add("one");
    	assert this.isEmpty();
    }
    @Test void _remove() {
    	this.add("one");
    	this.remove("one");
    	this.isEmpty();
    }
	public static void main(String[] args) {
		OSExecute.command(
				"java net.mindview.atunit.AtUnit Ex07_LinkedListTestWithInherit");
	}

}
