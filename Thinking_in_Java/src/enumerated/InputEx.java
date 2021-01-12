package enumerated;
import java.util.*;

public enum InputEx {
    五分硬币(5), 十分硬币(10), 二十五分硬币(25), 壹元(100),
    牙膏(200), 炸薯条(75), 苏打水(100), 肥皂(50),
    中止事务 {
    	public int amount() {
    		throw new RuntimeException("ABORT.amount()");
    	}
    },
    停止 {
    	public int amount() {
    		throw new RuntimeException("SHUT_DOWN.amount()");
    	}
    };
	int value;
	InputEx(int value) { this.value = value; }
	InputEx() {}
	int amount() { return value; }
	static Random rand = new Random(47);
	public static InputEx randomSelection() {
		return values()[rand.nextInt(values().length - 1)];
	}
}
