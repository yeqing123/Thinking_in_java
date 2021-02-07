package concurrency;
import java.util.concurrent.*;

public class Ex12_SynchronizedAtomicityTest extends AtomicityTest {
	public synchronized int getValue() { 
		return super.getValue(); 
	}
	
	public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex12_SynchronizedAtomicityTest at = new Ex12_SynchronizedAtomicityTest();
        exec.execute(at);
        while(true) {
        	int val = at.getValue();
        	if(val % 2 != 0) {
        		System.out.println(val);
        		System.exit(0);
        	}
        }
	}
}