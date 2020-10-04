package typeinfo;

public class Ex20_DisplayClass {
    public static void display(String msg, Object[] objs) {
    	System.out.println("$ " + msg);
    	for(Object o : objs)
    		System.out.println(o.toString().replaceAll("\\w+\\.", ""));
    }
    public static void classInfo(Class<?> c) {
    	System.out.println("$ Simple name: " + c.getSimpleName());
    	System.out.println("$ Super class: " + c.getSuperclass());
    	display("All implemented interfaces: ", c.getInterfaces());
    	display("All constructors: ", c.getDeclaredConstructors());
    	display("All methods: ", c.getDeclaredMethods());
    	display("All fields: ", c.getDeclaredFields());
    	display("All classes: ", c.getDeclaredClasses());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        for(String arg : args)
			try {
				classInfo(Class.forName(arg));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
