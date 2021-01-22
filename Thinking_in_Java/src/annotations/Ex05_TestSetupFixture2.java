package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class Ex05_TestSetupFixture2 extends HashSet<String> {
	@Test void _t1() {
    	assert this.isEmpty();
    	this.add("one");
    }
    @Test void _t2() {
    	assert this.isEmpty();
    	this.add("one");
    }
	public static void main(String[] args) {
		OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/Ex05_TestSetupFixture2");
	}

}
