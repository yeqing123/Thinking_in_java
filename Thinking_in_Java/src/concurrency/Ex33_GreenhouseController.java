// {Args: 5000}
package concurrency;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

abstract class Event implements Delayed, Runnable {
	private final long delayTime;
	private long trigger;
	public Event(long delayTime) {
		this.delayTime = delayTime;
	}
	public int compareTo(Delayed arg) {
		Event that = (Event)arg;
		if(trigger < that.trigger) return -1;
		if(trigger > that.trigger) return 1;
		return 0;
	}
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}
	public void start() {
		trigger = System.nanoTime() + NANOSECONDS.convert(delayTime, MILLISECONDS);
	}
	abstract public void run();
}

class Controller implements Runnable {
	private DelayQueue<Event> q;
	public Controller(DelayQueue<Event> q) { this.q = q; }
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Event event = q.take();
				System.out.println(event);
				event.run();
			}
		} catch(InterruptedException e) {
			// Accepted way to exit
		}
		System.out.println("Finished Controller");
	}
	public void addEvent(Event e) {
		e.start();
		q.put(e);
	}
}

class GreenhouseController extends Controller {
	public GreenhouseController(DelayQueue<Event> q) {
		super(q);
	}
	private boolean light = false;
	class LightOn extends Event {
		public LightOn(long delayTime) {
			super(delayTime);
		}
		public void run() {
			// Put hardware control code here:
			light = true;
		}
		public String toString() {
			return "Turning on the light";
		}
	}
	class LightOff extends Event {
		public LightOff(long delayTime) {
			super(delayTime);
		}
	    public void run() {
	    	// Put hardware control code here:
	    	light = false;
	    }
	    public String toString() {
	    	return "Turning off the light";
	    }
	}
	private boolean water = false;
	class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}
		public void run() {
			// Put hardware control code here:
			water = true;
		}
		public String toString() {
			return "Greenhouse's water is on";
		}
	}
	class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}
		public void run() {
			// Put hardware control code here:
			water = false;
		}
		public String toString() {
			return "Greenhouse's water is off";
		}
	}
	private String thermostat = "Day";
	class ThermostatNight extends Event {
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}
		public void run() {
			// Put hardware control code here:
			thermostat = "Night";
		}
		public String toString() {
			return "Thermostat on night setting";
		}
	}
	class ThermostatDay extends Event {
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}
		public void run() {
			// Put hardware control code here:
			thermostat = "Day";
		}
		public String toString() {
			return "Thermostat on day setting";
		}
	}
	// When run these events, , them insert themselves into event list
	class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}
		public void run() {
			addEvent(this);
		}
		public String toString() {
			return "Bing!";
		}
	}
	class Restart extends Event {
		private Event[] eventList;
		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for(Event e : eventList)
				addEvent(e);
		}
		public void run() {
			for(Event e : eventList)
				addEvent(e);
			addEvent(this);
		}
		public String toString() {
			return "Restarting system";
		}
	}
	// A static class, used to terminate the running system.
	static class Terminate extends Event {
		private ExecutorService exec;
		public Terminate(long delayTime, ExecutorService exec) {
			super(delayTime);
			this.exec = exec;
		}
		public void run() {
			exec.shutdownNow();
		}
		public String toString() {
			return "Terminating";
		}
	} 
}

public class Ex33_GreenhouseController {
   
	public static void main(String[] args) {
        GreenhouseController gc = new GreenhouseController(new DelayQueue<Event>());
        ExecutorService exec = Executors.newCachedThreadPool();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
        	gc.new ThermostatNight(0),
	        gc.new LightOn(200),
	        gc.new LightOff(400),
	        gc.new WaterOn(600),
	        gc.new WaterOff(800),
	        gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        if(args.length == 1) 
        	gc.addEvent(
        			new GreenhouseController.Terminate(new Integer(args[0]), exec));
    	exec.execute(gc);
	}

}
