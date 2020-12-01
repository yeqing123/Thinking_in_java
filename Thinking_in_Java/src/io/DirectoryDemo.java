package io;
import net.mindview.util.PPrint;

public class DirectoryDemo {
   
	public static void main(String[] args) {
        // All directories:
		PPrint.pprint(Directory.walk("./src/typeinfo").dirs);
		System.out.println("--------------------------");
		// All files beginning with 'D':
		PPrint.pprint(Directory.local("./src/io", "D.*"));
        System.out.println("--------------------------");
		// All Java files beginning with 'D':
        PPrint.pprint(Directory.walk("./src", "D.*\\.java").files);
        System.out.println("==========================");
        // Class files containing "Z" or "z":
        PPrint.pprint(Directory.walk(".", ".*[Zz].*\\.class").files);
	}

}
