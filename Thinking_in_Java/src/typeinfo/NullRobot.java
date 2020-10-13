// Using a dynamic proxy to create a Null Object.
package typeinfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import net.mindview.util.*;

class NullRobotProxyHandler implements InvocationHandler {
	private String nullName;
	private Robot proxied = new NRobot();
	NullRobotProxyHandler(Class<? extends Robot> type) {
		nullName = type.getSimpleName() + " NullRobot";
	}
	private class NRobot implements Robot, Null {
		public String name() {
			return nullName;
		}
		public String model() {
			return nullName;
		}
		public List<Operation> operations() {
			return Collections.emptyList();
		}
	}
	public Object invoke(Object proxy, Method method, Object[] args) 
	throws Throwable {
		return method.invoke(proxied, args);
	}
}

public class NullRobot {
    public static Robot newNullRobot(Class<? extends Robot> type) {
    	return (Robot)Proxy.newProxyInstance(
    			NullRobotProxyHandler.class.getClassLoader(),
    			new Class[] {Null.class, Robot.class}, 
    			new NullRobotProxyHandler(type));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Robot[] bots = {
        		new SnowRemovalRobot("SnowBee"),
        		newNullRobot(SnowRemovalRobot.class)
        };
        for(Robot r : bots)
        	Robot.Test.test(r);
	}

}
