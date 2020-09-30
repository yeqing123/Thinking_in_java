package typeinfo;
import typeinfo.pets.TypeCounter;

public class Ex13_PartCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TypeCounter counter = new TypeCounter(Part.class);
        for(int i = 0; i < 10; i++) {
        	Part part = Part.createRandom();
        	counter.count(part);
        	System.out.print(part + "  ");
        }
        System.out.println();
        System.out.println(counter);
	}

}
