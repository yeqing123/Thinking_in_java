package containers;

import java.util.List;
import net.mindview.util.*;

public class Ex13_WordsCounter {

	public static void main(String[] args) {
		// 获取当前项目所在路径
		String path = System.getProperty("user.dir");
		// 找到当前java文件所在的绝对路径
		String fileName = path + "\\src\\containers\\Ex13_WordsCounter.java";
		List<String> words = new TextFile(fileName, "\\W+");
		// 使用先前定义的关联数组：AssociativeArray
		AssociativeArray<String, Integer> counter = 
				new AssociativeArray<String, Integer>(words.size());
        for(String s : words)
            if(counter.get(s) != null)
            	counter.put(s, counter.get(s) + 1);
            else
            	counter.put(s, 1);
        System.out.println(counter);
	}
}
