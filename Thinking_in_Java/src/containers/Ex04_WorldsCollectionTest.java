package containers;

import java.io.File;
import java.util.*;

import net.mindview.util.TextFile;

class WordsCollection extends TreeSet<String> { // 继承TreeSet集合，可以对单词进行自动排序并去掉重复的单词
	// 将构造器私有化，防止直接调用该构造器
	private WordsCollection(String file) {
		for(String word : new TextFile(file, "\\W+")) {
			this.add(word);
		}
	}
	// 使用该Collection生成器时只能通过wordsList()方法进行，返回一个通用的Collection对象
	public static Collection<String> wordsList(String fileName) {
		return new WordsCollection(fileName);
	}
}

public class Ex04_WorldsCollectionTest {

	public static void main(String[] args) throws Exception {
		// 获取当前项目的路径
		String path = new File("").getCanonicalPath() + "\\src\\containers\\";
		String fileName = path +  "Ex04_CollectionDataTest.java";
        String filenName2 = path + "CollectionData.java";		
        List<String> set = new ArrayList<String>(WordsCollection.wordsList(fileName));
        System.out.println("Ex04_CollectionDataTest.java: " + set);
        Set<String> set2 = new HashSet<String>(WordsCollection.wordsList(filenName2));
        System.out.println("CollectionData.java: " + set2);
	}

}
