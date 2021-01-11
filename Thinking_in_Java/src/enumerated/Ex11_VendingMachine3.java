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
	Input2 input;
	VendedItem item;
	ExtInput(Input2 input, VendedItem item) {
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

enum Input2 {
	NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
	VENDED_ITEM,
	ABORT_TRANSACTION {
		public int amount() {
			throw new RuntimeException("ABORT.amount()");
		}
	},
	STOP {   // 这个实例必须放在最后
		public int amount() {
			throw new RuntimeException("SHUT_DOWN.amount()");
		}
	};
	int value;  // 美分
	Input2(int value) { this.value = value; }
	Input2() {}
	int amount() { return value; }
	static Random rand = new Random(47);
	public static Input2 randomSelection() {
		// 不包含STOP
		return values()[rand.nextInt(values().length - 1)];
	}
}

enum Category2 {
	MONEY(Input2.NICKEL, Input2.DIME, Input2.QUARTER, Input2.DOLLAR),
	ITEM_SELECTION(Input2.VENDED_ITEM),
	QUIT_TRANSACTION(Input2.ABORT_TRANSACTION),
	SHUT_DOWN(Input2.STOP);
	private Input2[] values;
	Category2(Input2... types) { values = types; }
	private static EnumMap<Input2, Category2> categories = 
			new EnumMap<Input2, Category2>(Input2.class);
	static {
		for(Category2 c : Category2.class.getEnumConstants())
			for(Input2 type : c.values)
				categories.put(type, c);
	}
	public static Category2 categorize(Input2 input) {
		return categories.get(input);
	}
}

public class Ex11_VendingMachine3 {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static ExtInput selection = null;
    enum StateDuration { TRANSIENT }  // 标记enum
    enum State {
    	RESTING {
    		void next(ExtInput input) {
    			switch(Category2.categorize(input.input)) {
    			case MONEY:
    				amount += input.amount();
    				state = ADDING_MONEY;
    				break;
    			case SHUT_DOWN:
    				state = TERMINAL;
    				default:
    			}
    		}
    	},
    	ADDING_MONEY {
    		void next(ExtInput input) {
    			switch(Category2.categorize(input.input)) {
    			case MONEY:
    				amount += input.amount();
    				break;
    			case ITEM_SELECTION:
    				selection = input;
    				if(amount < selection.amount())
    					System.out.println("购买" + selection + "的钱不够！");
    				else
    					state = DISPENSING;
    				break;
    			case QUIT_TRANSACTION:
    				state = GIVING_CHANGE;
    				break;
    			case SHUT_DOWN:
    				state = TERMINAL;
    				default:
    			}
    		}
    	},
    	DISPENSING(StateDuration.TRANSIENT) {
    		void next() {
    			System.out.println("这是您买的 " + selection);
    			amount -= selection.amount();
    			state = GIVING_CHANGE;
    		}
    	},
    	GIVING_CHANGE(StateDuration.TRANSIENT) {
    		void next() {
    			if(amount > 0) {
    				System.out.println("找您的零钱：" + amount + "分");
    				amount = 0;
    			}
    			state = RESTING;
    		}
    	},
    	TERMINAL { void output() { System.out.println("停止"); } };
    	private boolean isTransient = false;
    	State() {}
    	State(StateDuration trans) { isTransient = true; }
    	void next(ExtInput input) {
    		throw new RuntimeException("只能在非瞬时状态时，调用next(ExInput input)方法");
    	}
    	void next() {
    		throw new RuntimeException("只能在StateDuration.TRANSIENT状态时，调用next()方法");
    	}
    	void output() { System.out.println(amount); }
    }
    static void run(Generator<ExtInput> gen) {
    	while(state != State.TERMINAL) {
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
			gen = new FileExtInputGenerator2(args[1]);
		else
			gen = new RandomExtInputGenerator2();
		// 解析出售项目的数据文件
		for(String data : new TextFile(args[0], ";"))
	        VendedItem.addItem(VendedItem.parse(data.trim()));
		run(gen);
	}
}

// 为了基本的正常检查：
class RandomExtInputGenerator2 implements Generator<ExtInput> {
	public ExtInput next() {
		return new ExtInput(Input2.randomSelection(), 
				VendedItem.randomSelection());
	}
}

// 将文件中的内容以字符串“;”进行分割，创建输入项
class FileExtInputGenerator2 implements Generator<ExtInput> {
	private Iterator<String> input;
	public FileExtInputGenerator2(String fileName) {
		input = new TextFile(fileName, ";").iterator();
	}
	public ExtInput next() {
		if(!input.hasNext())
			return null;
		String s = input.next().trim();
		try {
			return new ExtInput(Enum.valueOf(Input2.class, s), null);
		} catch(IllegalArgumentException e) {
			// b计划：可能是一个被破坏的项目...
			VendedItem item = VendedItem.lookup(s);
			if(item != null)
				return new ExtInput(Input2.VENDED_ITEM, item);
			throw e;   // 重新抛出捕获的异常
		}
	}
}
