package io;
import java.util.*;
import innerclasses.GreenhouseControls;
import innerclasses.controller.*;
import java.lang.reflect.*;
import java.io.*;

class GreenhouseControls2x extends GreenhouseControls {
	class Restart extends Event {
        private Event[] eventList;
		public Restart(long delayTime) {
			super(delayTime);
		}
		public void action() {
			for(Event e : eventList) {
				e.start();
			    addEvent(e);
			}
			start();
			addEvent(this);
		}
        public void setEventList(Event[] eventList) { this.eventList = eventList; }
        public String toString() { return "Restart system"; }
	}
	class GHEventFactory {
		List<EventCreator> events = new ArrayList<EventCreator>();
		class EventCreator {
			private Constructor<Event> ctor;
			private long offset;
			public EventCreator(Constructor<Event> ctor, long offset) {
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
			    	String eventName = s.substring(0, colon).trim();
			    	Class<?> outer = eventName.equals("Restart") ? 
			    			GreenhouseControls2x.class : 
			    			GreenhouseControls.class;
			    	Class<Event> type = (Class<Event>) Class.forName(outer.getName() + "$" + eventName);
			    	events.add(new EventCreator(
			    			type.getConstructor(new Class[] {outer, long.class}), 
			    			Long.parseLong(s.substring(colon + 1).trim())));
			    }
			    in.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		Iterator<Event> iterator() {
			return new Iterator<Event>() {
                Iterator<EventCreator> it = events.iterator();
				@Override
				public boolean hasNext() {
					return it.hasNext();
				}
				@Override
				public Event next() {
					EventCreator ec = it.next();
					System.out.println(ec.ctor.getName() + ":" + ec.offset);
					Event event = null;
					try {
//						if(ec.ctor.getName().contains("Restart"))
//							event = new GreenhouseControls2b.Restart(ec.offset);
//						else
					        event = ec.ctor.newInstance(new Object[] {GreenhouseControls2x.this, ec.offset});
					} catch(Exception ex) {
						throw new RuntimeException(ex);
					}
					return event;
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
	}
	public GreenhouseControls2x(String eventsFile) {
		GHEventFactory gheFactory = new GHEventFactory(eventsFile);
		Iterator<Event> it = gheFactory.iterator();
		LinkedList<Event> restartableEvents = new LinkedList<Event>();
		while(it.hasNext()) {
			Event e = it.next();
			if(e instanceof Bell || e instanceof Restart)
				continue;
			restartableEvents.add(e);
		}
		it = gheFactory.iterator();
		while(it.hasNext()) {
			Event e = it.next();
			addEvent(e);
			if(e instanceof Restart)
				((Restart)e).setEventList(restartableEvents.toArray(new Event[0]));
		}
	}
}

public class Ex11_GreenhouseControls2x {

	public static void main(String[] args) {
		GreenhouseControls2x gc = new GreenhouseControls2x("./src/io/events.dat");
		
		if(args.length != 0)
			gc.addEvent(new GreenhouseControls.Terminate(Long.parseLong(args[0])));
		else { 
		    System.err.println("Terminate event is not added!");
		    System.exit(0);
		}
		gc.run();
	}

}
