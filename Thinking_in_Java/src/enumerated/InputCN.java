package enumerated;
import java.util.*;

public enum InputCN {
	// 为了便于理解，我们将枚举实例用中文定义
/*	  NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
	  TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
	  ABORT_TRANSACTION {
	  ...     
*/
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
	InputCN(int value) { this.value = value; }
	InputCN() {}
	int amount() { return value; }
	static Random rand = new Random(47);
	public static InputCN randomSelection() {
		return values()[rand.nextInt(values().length - 1)];
	}
}
