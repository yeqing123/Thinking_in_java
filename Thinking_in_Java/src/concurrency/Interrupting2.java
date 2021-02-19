// 中断使用ReentrantLock阻塞的任务。
package concurrency;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		// 立即获取它(锁住)，以演示在ReentrantLock上阻塞的任务的中断
		lock.lock();
	}
	public void f() {
		try {
			// 这将永远无法用于第二个任务
			lock.lockInterruptibly();  // 特殊的调用
			System.out.println("lock acquired in f()");
		} catch(InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}

public class Interrupting2 {

	public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
	}

}
