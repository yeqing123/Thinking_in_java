package concurrency;
import java.util.concurrent.*;
import java.util.*;
import net.mindview.util.*;

class ExchangerProducer2<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> list;
	private Generator<T> gen;
	public ExchangerProducer2(Exchanger<List<T>> excg, List<T> list, Generator<T> gen) {
		this.exchanger = excg;
		this.list = list;
		this.gen = gen;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				for(int i = 0; i < 5; i++)
				    list.add(gen.next());
				exchanger.exchange(list);
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch(InterruptedException e) {
			// OK to terminate this way.
			System.out.println("ExchangerProducer2 interrupted");
		}
	}
}

class ExchangerConsumer2<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> list;
	public ExchangerConsumer2(Exchanger<List<T>> excg, List<T> list) {
		this.exchanger = excg;
		this.list = list;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				list = exchanger.exchange(list);
				for(T e : list) {
					list.remove(e);
					System.out.print(e + " is consumed, ");
				}
				System.out.println();
			}
		} catch(InterruptedException e) {
			// OK to terminate this way.
			System.out.println("ExchangerConsumer2 interrupted");
		}
	}
}

public class Ex34_ExchangerDemo2 {
	static int size = 5;
	static int delay = 10;
	public static void main(String[] args) throws Exception {
		if(args.length > 0)
			size = new Integer(args[0]);
		if(args.length > 1)
			delay = new Integer(args[1]);
		Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Integer> consumerList = new CopyOnWriteArrayList<Integer>();
		List<Integer> producerList = new CopyOnWriteArrayList<Integer>();
		exec.execute(new ExchangerProducer2<Integer>(
				exchanger, producerList, new CountingGenerator.Integer()));
        exec.execute(new ExchangerConsumer2<Integer>(exchanger, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
	}

}
