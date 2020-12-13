package io;
import java.io.*;
import java.util.*;

public class TextFile extends ArrayList<String> {
    // 将文件读取为单个字符串：
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(new File(fileName).getAbsoluteFile()));
			String s;
			try {
				while((s = in.readLine()) != null)
					sb.append(s).append("\n");
			} finally {
				in.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	// 向指定文件写入字符串
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	// 读取文件，然后按照正则表达式分割： 
	public TextFile(String fileName, String splitter) {
		// 利用read()方法获得文件内容，然后将字符串按指定分割符分割后保存在ArrayList中
		super(Arrays.asList(read(fileName).split(splitter)));
		// 用正则表达式split()方法通常在第一个位置留下一个空字符串：
		if(get(0).equals(""))
			remove(0);
	}
	// 默认是按换行符分割的
	public TextFile(String fileName) {
		this(fileName, "\n");
	}
	// 将ArrayList保存的内容写入指定文件
	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				for(String item : this)
					out.println(item);
			} finally {
				out.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
        String file = TextFile.read("./src/io/TextFile.java");
        // 将内容写入test.txt文件
        write("./src/io/test.txt", file);  
        // 将test.txt文件中的内容保存到ArrayList中
        TextFile text = new TextFile("./src/io/test.txt");
        // 将ArrayList中的内容写入test2.txt中
        text.write("./src/io/test2.txt");
        // 将唯一的单词保存在Set集合中，并进行排序
        TreeSet<String> words = new TreeSet<String>(
        		new TextFile("./src/io/TextFile.java", "\\W+"));
        // 在排序后的集合中，将排在"a"之前的单词打印
        System.out.println(words.headSet("a"));
	}

}
