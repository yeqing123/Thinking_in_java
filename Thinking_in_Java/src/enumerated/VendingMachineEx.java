package enumerated;
import static enumerated.InputEx.*;
import java.util.*;
import net.mindview.util.*;


enum CategoryEx {
    投币(五分硬币, 十分硬币, 二十五分硬币, 壹元),
    选择商品(牙膏, 炸薯条, 苏打水, 肥皂),
    取消操作(中止事务),
    关闭(停止);
	private InputEx[] values;
	CategoryEx(InputEx... types) { values = types; }
	private static EnumMap<InputEx, CategoryEx> categories = 
			new EnumMap<InputEx, CategoryEx>(InputEx.class);
	static {
		for(CategoryEx c : CategoryEx.values())
			for(InputEx type : c.values)
				categories.put(type, c);
	}
	public static CategoryEx categorize(InputEx input) {
		return categories.get(input);
	}
}
public class VendingMachineEx {
    private static State state = State.休眠中;
    private static int amount = 0;
    private static InputEx selection = null;
    enum StateDuration { 临时的 }
    enum State {
    	休眠中 {
    		void next(InputEx input) {
    			System.out.println("自动售货机休眠中...");
    			switch(CategoryEx.categorize(input)) {
    			case 投币:
    				amount += input.amount();
    				state = 运行;
    				System.out.println("运行，投币中...");
    				System.out.println("累计付款金额：");
    				break;
    			case 关闭:
    				state = 终止;
    				default:
    			}
    		}
    	},
    	运行 {
    		void next(InputEx input) {
    			switch(CategoryEx.categorize(input)) {
    			case 投币:
    				amount += input.amount();
    				break;
    			case 选择商品:
    				selection = input;
    				System.out.println("选择商品：" + selection + "， 标价：" + selection.amount());
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
    			System.out.println("");
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
    	void next(InputEx input) {
    		throw new RuntimeException("只有在非瞬时状态下才可以调用next(Input input)方法");
    	}
    	void next() {
    		throw new RuntimeException("只有在瞬时状态下才可以调用next()方法");
    	}
    	void output() { System.out.println(amount + "分"); }
    }
    static  void run(Generator<InputEx> gen) {
    	while(state != State.终止) {
    		state.next(gen.next());
    		while(state.isTransient)
    			state.next();
    		state.output();
    	}
    }
	public static void main(String[] args) {
        Generator<InputEx> gen = new RandomInputGenerator2();
        if(args.length == 1)
        	gen = new FileInputGenerator2(args[0]);
        run(gen);
	}
}

class RandomInputGenerator2 implements Generator<InputEx> {
	public InputEx next() { return InputEx.randomSelection(); }
}

class FileInputGenerator2 implements Generator<InputEx> {
	private Iterator<String> input;
	public FileInputGenerator2(String fileName) {
		input = new TextFile(fileName, ";").iterator();
	}
	public InputEx next() {
		if(!input.hasNext())
			return null;
		return Enum.valueOf(InputEx.class, input.next().trim());
	}
}
