package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class Ex07_LinkedListTest2 extends LinkedList {
    @Test void initialization() {
	    assert this.isEmpty();
    }
    @Test void _contains() {
    	this.add("one");
    	assert this.contains("one");
    }
    @Test void _remove() {
    	this.add("one");
    	this.remove("one");
    	this.isEmpty();
    }
	public static void main(String[] args) {
		OSExecute.command("cd bin & java net.mindview.atunit.AtUnit" + 
	        " annotations/Ex07_LinkedListTest2");
	}

}
