// {Args: 12, "Hello" "Java"}
package object;

public class Ex10_CommandLine {

	public static void main(String[] args) {
        if(args.length != 3) {
        	System.err.println("usage: enter three command line arguments");
        	System.exit(1);
        }
        System.out.println(args[0] + ", " + args[1] + ", " + args[2]);
	}

}
