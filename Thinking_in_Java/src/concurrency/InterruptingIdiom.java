// 中断任务的惯用法。
// {Args: 1100}
package concurrency;

import java.util.concurrent.TimeUnit;

class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident) {
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}
	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;
	public void run() {
		try {
			while(!Thread.interrupted()) {  // 判断任务中断状态
				// point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				// 定义n1之后立刻开始try-finally， 以保证n1被正确的清除
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					// point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					// 保证正确的清除n2
					try {
						System.out.println("Calculating");
						// 一个费时的非阻塞操作：
						for(int i = 1; i < 2500000; i++)
							d = d + (Math.PI + Math.E) / d;
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch(InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
}

public class InterruptingIdiom {

	public static void main(String[] args) throws Exception {
        if(args.length != 1) {
        	System.out.println("usage: java InterruptingIdiom delay-in-mS");
        	System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
	}

}
