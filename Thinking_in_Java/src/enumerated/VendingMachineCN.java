// {Args: ./src/enumerated/VendingMachineInput_CN.txt}
package enumerated;
import static enumerated.InputCN.*;
import java.util.*;
import net.mindview.util.*;


enum CategoryCN {
/*
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
	ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
	QUIT_TRANSACTION(ABORT_TRANSACTION),
	SHUT_DOWN(STOP);
 */
    投币(五分硬币, 十分硬币, 二十五分硬币, 壹元),
    选择商品(牙膏, 炸薯条, 苏打水, 肥皂),
    取消操作(中止事务),
    关闭(停止);
	private InputCN[] values;
	CategoryCN(InputCN... types) { values = types; }
	private static EnumMap<InputCN, CategoryCN> categories = 
			new EnumMap<InputCN, CategoryCN>(InputCN.class);
	static {
		for(CategoryCN c : CategoryCN.values())
			for(InputCN type : c.values)
				categories.put(type, c);
	}
	public static CategoryCN categorize(InputCN input) {
		return categories.get(input);
	}
}


public class VendingMachineCN {
    private static State state = State.休眠中;
    private static int amount = 0;
    private static InputCN selection = null;
    enum StateDuration { 临时的 }
    enum State {
    	休眠中 {
    		void next(InputCN input) {
    			System.out.println("自动售货机休眠中...");
    			switch(CategoryCN.categorize(input)) {
    			case 投币:
    				amount += input.amount();
    				state = 运行中;
    				System.out.println("运行，投币中...");
    				break;
    			case 关闭:
    				state = 终止;
    				default:
    			}
    		}
    	},
    	运行中 {
    		void next(InputCN input) {
    			switch(CategoryCN.categorize(input)) {
    			case 投币:
    				amount += input.amount();
    				break;
    			case 选择商品:
    				selection = input;
    				System.out.println("选择商品：" + selection + "（单价：" + selection.amount() + "分）");
    				if(amount < selection.amount())
    					System.out.println("金额不足，请继续投币...");
    				else
    					state = 交货;
    				break;
    			case 取消操作:
    				System.out.println("取消操作！");
    				state = 找零;
    				break;
    			case 关闭:
    				state = 终止;
    				default:
    			}
    		}
    	},
    	交货(StateDuration.临时的) {
    		void next() {
    			System.out.println("交易成功！");
    			amount -= selection.amount();
    			state = 找零;
    		}
    	},
    	找零(StateDuration.临时的) {
    		void next() {
    			if(amount > 0) {
    				System.out.println("找零：" + amount + "美分");
    				amount = 0;
    			}
    			state = 休眠中;
    		}
    	},
    	终止 {
    		void output() { System.out.println("关机"); }
    	};
    	private boolean isTransient = false;
    	State() {}
    	State(StateDuration trans) { isTransient = true; }
    	void next(InputCN input) {
    		throw new RuntimeException("只有在非瞬时状态下才可以调用next(Input input)方法");
    	}
    	void next() {
    		throw new RuntimeException("只有在瞬时状态下才可以调用next()方法");
    	}
    	void output() { System.out.println("当前余额： " + amount + "分"); }
    }
    static  void run(Generator<InputCN> gen) {
    	while(state != State.终止) {
    		state.next(gen.next());
    		while(state.isTransient)
    			state.next();
    		state.output();
    	}
    }
	public static void main(String[] args) {
        Generator<InputCN> gen = new RandomInputGeneratorCN();
        if(args.length == 1)
        	gen = new FileInputGeneratorCN(args[0]);
        run(gen);
	}
}

class RandomInputGeneratorCN implements Generator<InputCN> {
	public InputCN next() { return InputCN.randomSelection(); }
}

class FileInputGeneratorCN implements Generator<InputCN> {
	private Iterator<String> input;
	public FileInputGeneratorCN(String fileName) {
		input = new TextFile(fileName, ";").iterator();
	}
	public InputCN next() {
		if(!input.hasNext())
			return null;
		return Enum.valueOf(InputCN.class, input.next().trim());
	}
}
