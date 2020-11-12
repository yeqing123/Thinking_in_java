package containers;
import java.util.*;

class Item implements Comparable<Item> {
	private static Random rand = new Random(47);
	private Integer num = rand.nextInt(100);
	@Override
	public int compareTo(Item arg) {
		return arg.num > num ? 1 : (arg.num == num ? 0 : -1);
	}
	public String toString() { return String.valueOf(num); }
}

public class Ex11_PriorityQueueTest {
    static void fill(Queue<Item> queue, int quantity) {
    	for(int i = 0; i < quantity; i++)
    		queue.add(new Item());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Queue<Item> queue = new PriorityQueue<Item>();
        fill(queue, 10);
        while(!queue.isEmpty())
        	System.out.print(queue.poll() + " ");
	}

}
