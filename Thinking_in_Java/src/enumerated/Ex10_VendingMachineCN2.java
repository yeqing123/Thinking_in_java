package enumerated;
import java.util.*;
import java.util.concurrent.locks.*;
import net.mindview.util.*;

class VendingMachineCN2 {
    private static class Context { 
        private State state = State.休眠中; 
	    private int amount; 
	    private InputCN selection; 
	} 
	private static Map<Machine,Context> em = 
	    Collections.synchronizedMap(
	    		new EnumMap<Machine,Context>(Machine.class)); 
	static { 
        for(Machine m : Machine.values()) 
		    em.put(m, new Context()); 
	} 
	enum Machine { M1, M2, M3 } 
	private static final ReentrantLock lock = new ReentrantLock();
    private static State state; 
	private static int amount;
	private static InputCN selection; 
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
    				System.out.println("选择商品：" + selection + 
    						"（单价：" + selection.amount() + "分）");
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
    		void output() { System.out.println("****** 关机   ******"); }
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
	// run()方法需要一个额外的参数，它表示自动售货机“实例”的id。
	static void run(Generator<InputCN> gen, Machine m) { 
		System.out.println("**** 机器：" + m.toString() + " ****");
		Context ctx = em.get(m); 
		while(ctx.state != State.终止) { 
		    lock.lock(); 
		    state = ctx.state; 
		    amount = ctx.amount; 
		    selection = ctx.selection; 
		    try { 
		        state.next(gen.next()); 
		        while(state.isTransient) 
		            state.next(); 
	            state.output(); 
	            ctx.state = state; 
	            ctx.amount = amount; 
	            ctx.selection = selection; 
	            em.put(m, ctx); 
		    } finally { 
		        lock.unlock(); 
		    } 
		    Thread.yield(); 
		} 
    } 
}


public class Ex10_VendingMachineCN2 {
	public static void main(String[] args) {
	    for(final VendingMachineCN2.Machine m : 
	        VendingMachineCN2.Machine.values()) { 
			Generator<InputCN> gen = new RandomInputGeneratorCN(); 
			if(args.length == 1) 
			   gen = new FileInputGeneratorCN(args[0]); 
			final Generator<InputCN> g = gen; 
			new Thread(new Runnable() { 
			    public void run() { VendingMachineCN2.run(g, m); } 
			}).start(); 
	    }
    }
}
