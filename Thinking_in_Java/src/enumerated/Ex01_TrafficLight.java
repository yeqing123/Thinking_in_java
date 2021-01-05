package enumerated;
import static enumerated.Signal.*;

public class Ex01_TrafficLight {
    private Signal color = RED;
    public void change() {
    	switch(color) {
    	case RED:  color = GREEN;
    	break;
    	case YELLOW:  color = RED;
    	break;
    	case GREEN: color = YELLOW;
    	break;
    	}
    }
    public String toString() {  
    	return "The traffic light is " + color;
    } 
	public static void main(String[] args) {
        Ex01_TrafficLight t = new Ex01_TrafficLight();
        for(int i = 0; i < 7; i++) {
        	System.out.println(t);
        	t.change();
        }
	}

}
