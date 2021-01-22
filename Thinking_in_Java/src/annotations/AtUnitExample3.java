package annotations;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class AtUnitExample3 {
    private int n;
    public AtUnitExample3(int n) { this.n = n; }
    public int getN() { return this.n; }
    public String methodOne() {
    	return "This is methodOne";
    }
    public int methodTwo() {
    	System.out.println("This is methodTwo");
    	return 2;
    }
    @TestObjectCreate static AtUnitExample3 create() {
    	return new AtUnitExample3(89);
    }
    @Test boolean initialization() {
    	return n == 89;
    }
    @Test boolean methodOneTest() {
    	return methodOne().equals("This is methodOne");
    }
    @Test boolean m2() {
    	return methodTwo() == 2;
    }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/AtUnitExample3");
	}

}
