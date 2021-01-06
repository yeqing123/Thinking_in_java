// An enum can implement an interface
package enumerated.cartoons;
import net.mindview.util.*;
import java.util.*;

enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);
	@Override
	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
	
}

public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
    	System.out.print(rg.next() + ", ");
    }
	public static void main(String[] args) {
        // Choose any instance:
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i = 0; i < 10; i++)
			printNext(cc);
	}

}
