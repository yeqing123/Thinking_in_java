package typeinfo;

// Args: typeinfo.toys.FancyToy typeinfo.Circle
public class Ex08_RecursiveClassPrint  {
    public static void printClasses(Class<?> c) {
    	if(c == null) {
    		return ;
    	}
    	// print class name
    	System.out.println(c.getName());
    	// Gets all implemented interfaces 
    	for(Class<?> face : c.getInterfaces()) {
    		System.out.println("Interface: " + face.getName());
    		// Recursive calls the method to display each interface's superclass.
    		printClasses(face.getSuperclass());
    	}
    	// Recursive prints the parameter's superclass.
    	printClasses(c.getSuperclass());
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for(int i = 0; i < args.length; i++) {
		    System.out.println("Display " + args[i]);
		    Class<?> c = Class.forName(args[i]);
		    printClasses(c);
		    if(i < args.length -1)
		        System.out.println("==========================");
		}
	}

}
