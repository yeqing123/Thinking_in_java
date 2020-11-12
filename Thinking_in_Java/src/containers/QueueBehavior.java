package containers;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

import net.mindview.util.Generator;

public class QueueBehavior {
    private static int count = 10;
    static <T> void test(Queue<T> queue, Generator<T> gen) {
    	for(int i = 0; i < count; i++)
    		queue.offer(gen.next());
    	while(queue.peek() != null)
    	    System.out.print(queue.remove() + " ");
    	System.out.println();
    }
    private static class Gen implements Generator<String> {
    	String[] s = 
    			("one two three four five six seven eight nine ten").split(" ");
    	int index = 0;
    	@Override
    	public String next() {
    		// TODO Auto-generated method stub
    		return s[index++];
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(new LinkedList<String>(), new Gen());
		test(new PriorityQueue<String>(), new Gen());
		test(new ArrayBlockingQueue<String>(count), new Gen());
		test(new ConcurrentLinkedQueue<String>(), new Gen());
		test(new PriorityBlockingQueue<String>(), new Gen());
	}

}
