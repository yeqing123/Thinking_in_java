package innerclasses;

import innerclasses.controller.Event;

public class GreenhouseController {
	public static void main(String[] args) {
		GreenhouseControls gc = new GreenhouseControls();
		gc.addEvent(gc.new Bell(200));
		Event[] eventList = {
				gc.new ThermostatNight(400),
				gc.new LightOn(600),
				gc.new LightOff(800),
				gc.new WaterOn(1000),
				gc.new WaterOff(1200),
				gc.new ThermostatDay(1400)
		};
		gc.new Restart(2000, eventList);
		if(args.length == 1)
		    gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
		gc.run();
	}
}