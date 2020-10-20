package generics;
import java.awt.Color;
import java.lang.reflect.*;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Tuple.*;

class MixinProxy implements InvocationHandler {
	Map<String, Object> delegatesByMethod;
	public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
		delegatesByMethod = new HashMap<String, Object>();
		for(TwoTuple<Object, Class<?>> pair : pairs) {
			for(Method method : pair.second.getMethods()) {
				String methodName = method.getName();
				if(!delegatesByMethod.containsKey(methodName))
					delegatesByMethod.put(methodName, pair.first);
			}
		}
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object delegate = delegatesByMethod.get(methodName);
		return method.invoke(delegate, args);
	}
	@SuppressWarnings("unchecked")
	public static Object newInstance(TwoTuple... pairs) {
		Class[] interfaces = new Class[pairs.length];
		for(int i = 0; i < pairs.length; i++) {
			interfaces[i] = (Class)pairs[i].second;
		}
		ClassLoader cl = 
				pairs[0].first.getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
	}
}

interface Colored {
	Color getColor();
}

class ColoredImp implements Colored {
    private final Random rand = new Random(47);
    private Color color = new Color(rand.nextInt(16777216));
	public Color getColor() {
		return color;
	}
}

public class Ex39_DynamicProxyMixin2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Object mixin = MixinProxy.newInstance(
        		tuple(new BasicImp(), Basic.class),
        		tuple(new TimeStampedImp(), TimeStamped.class),
        		tuple(new SerialNumberedImp(), SerialNumbered.class), 
        		tuple(new ColoredImp(), Colored.class));
        Basic b = (Basic)mixin;
        TimeStamped t = (TimeStamped)mixin;
        SerialNumbered s = (SerialNumbered)mixin;
        Colored c = (Colored)mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
        System.out.println(c.getColor());
	}

}
