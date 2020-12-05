package innerclasses.controller;
import java.util.*;

public class Controller {
	private List<Event> eventList = new ArrayList<Event>();
    public boolean addEvent(Event e) { return eventList.add(e); }
    public void run() {
    	while(eventList.size() > 0) {
    		// 因为遍历时不能删除元素，因此我们重新定义一个列表
    		for(Event e : new ArrayList<Event>(eventList)) {
    			System.out.println(e);
    			e.start();
    			eventList.remove(e);
    		}
    	}
    }
}