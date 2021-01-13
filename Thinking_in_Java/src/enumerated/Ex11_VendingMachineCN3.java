package enumerated;
import java.util.*;
import net.mindview.util.*;

// 一个简单的数据持有者类
class VendedItem {
	int amount;
	String name;
	VendedItem(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
    // 数据的格式预计为: <name> <amount>
	public static VendedItem parse(String data) {
		String[] s = data.split(" ");
		return new VendedItem(s[0], Integer.parseInt(s[1]));
	}
	private static List<VendedItem> items = 
			new ArrayList<VendedItem>();
	public static void addItem(VendedItem item) {
		items.add(item);
	}
	// 一个非常缓慢的查找过程
	public static VendedItem lookup(String name) {
		for(VendedItem item : items) 
			if(item.name.equals(name))
				return item;
		return null;
	}
	private static Random rand = new Random(47);
	public static VendedItem randomSelection() {
		return items.get(rand.nextInt(items.size()));
	}
}

// 表示状态机输入的类
class ExtInput {
	InputCN2 input;
	VendedItem item;
	ExtInput(InputCN2 input, VendedItem item) {
		this.input = input;
		this.item = item;
	}
	public int amount() {
		return item != null ? item.amount : input.amount();
	}
	public String toString() {
		return item != null ? item.name : input.toString();
	}
}

enum InputCN2 {
    五分硬币(5), 十分硬币(10), 二十五分硬币(25), 壹元(100),
    牙膏(200), 炸薯条(75), 苏打水(100), 肥皂(50),
    欲出售商品,
    中止事务 {
    	public int amount() {
    		throw new RuntimeException("中止.amount()");
    	}
    },
    停止 {
		public int amount() {
			throw new RuntimeException("关闭.amount()");
		}
    };
	int value;
	InputCN2(int value) { this.value = value; }
	InputCN2() {}
	int amount() { return value; }
	static Random rand = new Random(47);
	public static InputCN2 randomSelection() {
		return values()[rand.nextInt(values().length - 1)];
	}
}

enum CategoryCN2 {
	投币(InputCN2.五分硬币, InputCN2.十分硬币, InputCN2.二十五分硬币, InputCN2.壹元),
	选择商品(InputCN2.牙膏, InputCN2.炸薯条, InputCN2.苏打水, InputCN2.肥皂),
	取消操作(InputCN2.中止事务),
	关闭(InputCN2.停止);
	private InputCN2[] values;
	CategoryCN2(InputCN2... types) { values = types; }
	private static EnumMap<InputCN2, CategoryCN2> categories = 
			new EnumMap<InputCN2, CategoryCN2>(InputCN2.class);
	static {
		for(CategoryCN2 c : CategoryCN2.class.getEnumConstants())
			for(InputCN2 type : c.values)
				categories.put(type, c);
	}
	public static CategoryCN2 categorize(InputCN2 input) {
		return categories.get(input);
	}
}

public class Ex11_VendingMachineCN3 {
    private static State state = State.休眠中;
    private static int amount = 0;
    private static ExtInput selection = null;
    enum StateDuration { 临时的 }
    enum State {
    	休眠中 {
    		void next(ExtInput input) {
    			System.out.println("自动售货机休眠中...");
    			switch(CategoryCN2.categorize(input.input)) {
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
    		void next(ExtInput input) {
    			switch(CategoryCN2.categorize(input.input)) {
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
    	void next(ExtInput input) {
    		throw new RuntimeException("只有在非瞬时状态下才可以调用next(Input input)方法");
    	}
    	void next() {
    		throw new RuntimeException("只有在瞬时状态下才可以调用next()方法");
    	}
    	void output() { System.out.println("当前余额： " + amount + "分"); }
    }
    static void run(Generator<ExtInput> gen) {
    	while(state != State.终止) {
    		state.next(gen.next());
    		while(state.isTransient)
    			state.next();
    		state.output();
    	}
    }
	public static void main(String[] args) {
        if(args.length < 2) {
        	System.out.println("未给定包含出售项的文件！");
            return ;
        }
		Generator<ExtInput> gen;
		if(args.length == 2)
			gen = new FileExtInputGeneratorCN2(args[1]);
		else
			gen = new RandomExtInputGeneratorCN2();
		// 解析出售项目的数据文件
		for(String data : new TextFile(args[0], ";"))
	        VendedItem.addItem(VendedItem.parse(data.trim()));
		run(gen);
	}
}

// 为了基本的正常检查：
class RandomExtInputGeneratorCN2 implements Generator<ExtInput> {
	public ExtInput next() {
		return new ExtInput(InputCN2.randomSelection(), 
				VendedItem.randomSelection());
	}
}

// 将文件中的内容以字符串“;”进行分割，创建输入项
class FileExtInputGeneratorCN2 implements Generator<ExtInput> {
	private Iterator<String> input;
	public FileExtInputGeneratorCN2(String fileName) {
		input = new TextFile(fileName, ";").iterator();
	}
	public ExtInput next() {
		if(!input.hasNext())
			return null;
		String s = input.next().trim();
		try {
			return new ExtInput(Enum.valueOf(InputCN2.class, s), null);
		} catch(IllegalArgumentException e) {
			// b计划：可能是一个被破坏的项目...
			VendedItem item = VendedItem.lookup(s);
			if(item != null)
				return new ExtInput(InputCN2.欲出售商品, item);
			throw e;   // 重新抛出捕获的异常
		}
	}
}
