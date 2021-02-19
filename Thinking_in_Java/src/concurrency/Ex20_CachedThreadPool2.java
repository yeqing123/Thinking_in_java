package concurrency;
import java.util.concurrent.*;

class LiftOff2 implements Runnable {
 protected int countDown = 10;     // Default
 private static int taskCount = 0;
 private final int id = taskCount++;
 public LiftOff2() {}
 public LiftOff2(int countDown) {
 	this.countDown = countDown;
 }
 public String status() {
 	return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), "; 
 }
 public void run() {
 	while(countDown-- > 0) {
 		if(Thread.currentThread().isInterrupted()) {   // 判断当前运行的线程是否已经被中断
 			System.out.println("Interrupted: #" + id);
 			return;
 		}
 		System.out.print(status());
 		Thread.yield();
 	}
 }
}


public class Ex20_CachedThreadPool2 {

	public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
        	exec.execute(new LiftOff2());
        Thread.yield();
        exec.shutdownNow();
	}

}
