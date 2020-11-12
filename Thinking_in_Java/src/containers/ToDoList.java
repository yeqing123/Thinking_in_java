package containers;

import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem> {
    	char primary;
    	int secondary;
    	String item;
    	ToDoItem(String item, char primary, int secondary) {
    		this.item = item;
    		this.primary = primary;
    		this.secondary = secondary;
    	}
		@Override
		public int compareTo(ToDoItem arg) {
			// TODO Auto-generated method stub
			if(this.primary > arg.primary)
				return +1;
			else if(this.primary == arg.primary)
				if(this.secondary > arg.secondary)
					return  +1;
				else if(this.secondary == arg.secondary)
					return 0;
				else
					return -1;
			else
     			return -1;
		}
		public String toString() {
			return primary + String.valueOf(secondary) + ":" + item;
		}
    }
    public void add(String ite, char pri, int sec) {
    	super.add(new ToDoItem(ite, pri, sec));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ToDoList queue = new ToDoList();
        queue.add("Java", 'A', 1);
        queue.add("PHP", 'B', 1);
        queue.add("Python", 'A', 2);
        queue.add("C++", 'B', 2);
        queue.add("JavaScript", 'C', 2);
        queue.add("HTML", 'C', 1);
        while(queue.peek() != null)
        	System.out.println(queue.poll());
	}
}
/* Output:
A1:Java
A2:Python
B1:PHP
B2:C++
C1:HTML
C2:JavaScript
*/

