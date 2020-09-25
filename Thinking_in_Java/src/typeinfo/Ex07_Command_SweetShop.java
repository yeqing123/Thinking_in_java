package typeinfo;

// Args: Java SweetShop typeinfo.Candy typeinfo.Gum typeinfo.Cookie
public class Ex07_Command_SweetShop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 3) {
        	System.out.println("Usage: Java SweetShop sweetName1 sweetName2 ...");
        	System.exit(0);
        }
        for(int i = 2; i < args.length; i++) {
	        try {
			    Class.forName(args[i]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
