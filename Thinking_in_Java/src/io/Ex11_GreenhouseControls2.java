package io;

import innerclasses.GreenhouseControls;
import innerclasses.controller.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

class GreenhouseControls2 extends GreenhouseControls {
	class Restart extends Event {
		private Event[] eventList;
		public Restart(long delayTime) { 
			super(delayTime); 
		}
		public void action() {
			for(Event e : eventList) {
				e.start();    // 开始每个事件
				addEvent(e);  // 然后将事件对象加入到控制器的执行列表中
			}
			this.start();     // 开始Restart事件
			addEvent(this);   // 将Restart事件本身加入到执行列表中
		}
		public String toString() { return "Restart system"; }
		public void setEventList(Event[] eventList) { this.eventList = eventList; }
	}
	class GHEventFactory {
		private List<EventCreator> events = new ArrayList<EventCreator>();
		class EventCreator {   // 定义事件创造器
			private Constructor<Event> ctor;
			private long offset;
			EventCreator(Constructor<Event> ctor, long offset) {
				this.ctor = ctor;
				this.offset = offset;
			}
		}
		@SuppressWarnings("unchecked")
		public GHEventFactory(String eventsFile) {
			try {
			    BufferedReader in = new BufferedReader(new FileReader(eventsFile));
			    String s;
			    while((s = in.readLine()) != null) {
			    	int colon = s.indexOf(':');
			    	String eventName = s.substring(0, colon).trim();   // 获取冒号左边的字符串，即为事件的名称
			    	Class<?> outer = eventName.equals("Restart") ?     // 如果事件是Restart，则指定其外部类为GreenhouseControls2.class，这样就可以使用重定义的Restart类了
			    			GreenhouseControls2.class :
			    			GreenhouseControls.class;
			    	Class<Event> type = (Class<Event>) Class.forName(outer.getName() + "$" + eventName); 
			    	events.add(new EventCreator(                      // 创建一个EventCreator对象，并加入到列表中
			    			type.getConstructor(new Class[] {outer, long.class}), 
			    			Long.parseLong(s.substring(colon + 1).trim())
			    			));
			    }
			    in.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		// 定义一个迭代器，它将迭代EventCreator列表，然后利用元素中的信息创建并返回Event对象
		public Iterator<Event> iterator() {
			return new Iterator<Event>() {
				Iterator<EventCreator> it = events.iterator();
				public boolean hasNext() {
					return it.hasNext();
				}
				public Event next() {
					Event returnVal = null;
					EventCreator ec = it.next();
					try { // 利用元素信息创建一个Event对象
						returnVal = ec.ctor.newInstance(new Object[] {GreenhouseControls2.this, ec.offset});
					} catch(Exception e) {
						throw new RuntimeException(e);
					}
					return returnVal;
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
	}
	public GreenhouseControls2(String eventsFile) {
		GHEventFactory gheFactory = new GHEventFactory(eventsFile);
		Iterator<Event> it = gheFactory.iterator();
		List<Event> restartableEvents = new LinkedList<Event>();
		while(it.hasNext()) {
			Event e = it.next();
			if(e instanceof Bell || e instanceof Restart)
				continue;
			restartableEvents.add(e);
		}
		it = gheFactory.iterator();
		while(it.hasNext()) {
			Event e = it.next();
			if(e instanceof Restart)
				((Restart)e).setEventList(restartableEvents.toArray(new Event[0]));
			addEvent(e);
		}
	}
}

public class Ex11_GreenhouseControls2 {

	public static void main(String[] args) {
		GreenhouseControls2 gc = new GreenhouseControls2("./src/io/events.dat");
		if(args.length == 0) {
			System.err.println("Terminate is not added!");
		    System.exit(0);
		}
		gc.addEvent(new GreenhouseControls2.Terminate(Long.parseLong(args[0])));
		gc.run();
	}

}
