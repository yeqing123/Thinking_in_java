package annotations;
import java.util.*;
import net.mindview.util.*;
import net.mindview.atunit.*;

public class Ex04_TestSetupFixture {
    HashSet<String> testObject = new HashSet<String>();
    @Test void _t1() {
    	assert testObject.isEmpty();
    	testObject.add("one");
    }
    @Test void _t2() {
    	assert testObject.isEmpty();
    	testObject.add("one");
    }
    
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/Ex04_TestSetupFixtrue");
	}
}