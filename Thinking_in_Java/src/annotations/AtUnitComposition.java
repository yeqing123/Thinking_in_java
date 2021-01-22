package annotations;
import net.mindview.atunit.*;
import net.mindview.util.OSExecute;

public class AtUnitComposition {
    AtUnitExample1 testObject = new AtUnitExample1();
    @Test boolean _methodOne() {
    	return testObject.methodOne().equals("This is methodOne");
    }
    @Test boolean _methodTwo() {
    	return testObject.methodTwo() == 2;
    }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/AtUnitComposition");
	}

}
