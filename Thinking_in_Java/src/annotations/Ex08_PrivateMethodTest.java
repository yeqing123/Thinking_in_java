package annotations;
import net.mindview.atunit.*;
import net.mindview.util.*;

class PrivateMethod {
    private boolean hidden() { return true; }
    @TestProperty boolean visible() {
    	return hidden();
    }
}

public class Ex08_PrivateMethodTest extends PrivateMethod {
    @Test void _hidden() { assert visible(); }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/Ex08_PrivateMethodTest");
	}

}
