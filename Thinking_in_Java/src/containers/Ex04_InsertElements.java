package containers;
import java.util.*;

public class Ex04_InsertElements {
    public static void print(List<String> list) {
    	Iterator<String> iter = list.iterator();
    	int i = 1;
        while(iter.hasNext()) {
        	System.out.print(iter.next() + ", ");
        	if(i++ % 6 == 0)
        		System.out.println();
        }
        System.out.println();
    }
    // 将source列表中的元素从末尾开始逐个向前，插入到target列表中。
    // 这种方式插入是存在一个风险，就是当target列表长度比source列表长度少两个元素以上时（包括两个），
    // 运行程序index就会越界，从而抛出异常。例如本示例中当target长度为8时。
    public static void reverseInsert(List<String> source, List<String> target) {
    	ListIterator<String> reverseIter = source.listIterator(source.size());
    	int index = 0;
    	while(reverseIter.hasPrevious()) {
    		String s = reverseIter.previous();
    		target.add(index, s + "(source)");
    		index += 2;
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String> list1 = new ArrayList<String>(Countries.names(10));
        List<String> list2 = new LinkedList<String>(Countries.names(9));
        System.out.println("========= List1 =========");
        print(list1);
        System.out.println("========= List2 =========");
        print(list2);
        System.out.println("\n======= Inserted with list1's " + 
            "elements from tail start into list2:======\n");
        reverseInsert(list1, list2);
        print(list2);
	}

}
