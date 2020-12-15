package io;
import java.util.*;
import java.io.*;

class OSExecute2 {
	public static List<String> command(String command) {
		boolean err = false;
		List<String> output = new ArrayList<String>();
		try {
		    Process process = new ProcessBuilder(command.split(" ")).start();
		    BufferedReader results = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
		    String s;
		    while((s = results.readLine()) != null)
		    	output.add(s);
            BufferedReader errors = new BufferedReader(
            		new InputStreamReader(process.getErrorStream(), "GBK"));
            while((s = errors.readLine()) != null) {
            	System.err.println(s);
            	err = true;
            }
		} catch(Exception e) {
			// 针对Windows 2000 所做的补充
			if(!command.startsWith("CMD /C"))
			    command("CMD /C" + command);
			else
				throw new RuntimeException(e);
		}
		if(err)
			throw new OSExecuteException("Errors execute " + command);
		return output;
	}
}

public class Ex22_OSExecute2Demo {

	public static void main(String[] args) {
        for(String s : OSExecute2.command("javap /bin/io/Ex22_OSExecute2Demo"))
        	System.out.println(s);
        
	}

}
