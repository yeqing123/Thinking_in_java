// Tested enumerated/TrafficLight.java
package annotations;
import net.mindview.atunit.*;
import net.mindview.util.*;

enum Signal { GREEN, YELLOW, RED; }

class TrafficLight {
    Signal color = Signal.RED;
    public void change() {
    	switch(color) {
    	 // Note that you don't have to say Signal.RED
    	 // in the case statement:
    	case RED: color = Signal.GREEN;
    	break;
    	case GREEN: color = Signal.YELLOW;
    	break;
    	case YELLOW: color = Signal.RED;
    	break;
    	}
    }
    public String toString() {
    	return "The trffic light is " + color;
    }
}

public class Ex10_TestTrafficLight {
    TrafficLight testObject = new TrafficLight();
    @Test void initializatin() {
        assert testObject.toString().equals(str(Signal.RED));
    }
    @Test void _change() {
        testObject.change();
        assert testObject.toString().equals(str(Signal.GREEN));
        testObject.change();
        assert testObject.toString().equals(str(Signal.YELLOW));
        testObject.change();
        assert testObject.toString().equals(str(Signal.RED));
    }
    @TestProperty static private String str(Signal color) {
    	return "The trffic light is " + color;
    }
	public static void main(String[] args) {
        OSExecute.command("cd bin & java net.mindview.atunit.AtUnit annotations/Ex10_TestTrafficLight");
	}

}
