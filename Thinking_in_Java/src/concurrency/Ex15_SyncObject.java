package concurrency;

class SingleSync {
	public synchronized void f() {
		for(int i = 0; i < 5; i++) {
	        System.out.println("f()");
		    Thread.yield();
		}
	}
	public synchronized void g() {
		for(int i = 0; i < 5; i++) {
		    System.out.println("g()");
		    Thread.yield();
		}
	}
	public synchronized void h() {
		for(int i = 0; i < 5; i++) {
		    System.out.println("h()");
		    Thread.yield();
		}
	}
}

class TripleSync {
	private Object syncObjectG = new Object();
	private Object syncObjectH = new Object();
	public synchronized void f() {
		for(int i = 0; i < 5; i++) {
	        System.out.println("f()");
		    Thread.yield();
		}
	}
	public void g() {
		synchronized(syncObjectG) {
			for(int i = 0; i < 5; i++) {
			    System.out.println("g()");
			    Thread.yield();
			}
		}
	}
	public void h() {
		synchronized(syncObjectH) {
			for(int i = 0; i < 5; i++) {
			    System.out.println("h()");
			    Thread.yield();
			}
		}
	}
}

public class Ex15_SyncObject {
	public static void main(String[] args) throws Exception {
		final SingleSync singleSync = new SingleSync();
    	final TripleSync tripleSync = new TripleSync();
    	System.out.println("Test SingleSync...");
		Thread t1 = new Thread() {
    		public void run() {
    			singleSync.f();
    		}
    	};
    	t1.start();
    	Thread t2 = new Thread() {
    		public void run() {
    			singleSync.g();
    		}
    	};
    	t2.start();
    	singleSync.h();
    	t1.join();   // Wait for t1 to finish its work
    	t2.join();   // Wait for t2 to finish its work
    	System.out.println("Test TripleSync...");
    	new Thread() {
    		public void run() {
    			tripleSync.f();
    		}
    	}.start();
    	new Thread() {
    		public void run() {
    			tripleSync.g();
    		}
    	}.start();
    	tripleSync.h();
	}

}
