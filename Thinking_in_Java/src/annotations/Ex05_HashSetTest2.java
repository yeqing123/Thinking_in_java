package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class Ex05_HashSetTest2 extends HashSet<String> {
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
		OSExecute.command("java net.mindview.atunit.AtUnit Ex05_HashSetTest2");
	}

}
