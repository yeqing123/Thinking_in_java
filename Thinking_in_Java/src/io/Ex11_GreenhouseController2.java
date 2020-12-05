package io;
import java.io.*;
import innerclasses.*;
import innerclasses.controller.*;
import java.lang.reflect.*;

public class Ex11_GreenhouseController2 {

	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.err.println("Uages:enter a file path:");
			System.exit(1);
		}
		GreenhouseControls gc = new GreenhouseControls();
		RandomAccessFile in = new RandomAccessFile(new File(args[0]), "r");
		String s;
		Event[] eventList = new Event[(int) in.length()];
		gc.addEvent(gc.new Bell(900));
		int i = 0;
		String outer = GreenhouseControls.class.getSimpleName();
		while((s = in.readLine()) != null) {
			String[] info = s.split("=");
			Class<Event> type = (Class<Event>)Class.forName(outer + "$ThermostatNight");
			eventList[i++] = type.getConstructor(
					Long.class).newInstance(new Long(args[1]));
		}
		gc.addEvent(gc.new Restart(2000, eventList));
		gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[2])));
		gc.run();
	}

}
