package io;
import java.io.*;
import java.util.*;

public class Ex18_TextFile2 extends ArrayList<String> {
		public static String read(String fileName) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader in = new BufferedReader(
					new FileReader(new File(fileName).getAbsoluteFile()));
			String s;
			try {
				while((s = in.readLine()) != null)
					sb.append(s).append("\n");
			} finally {
				in.close();
			}
			return sb.toString();
		}
		
		// 向指定文件写入字符串
		public static void write(String fileName, String text) throws IOException {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		}
		public Ex18_TextFile2(String fileName, String splitter) throws IOException  {
			super(Arrays.asList(read(fileName).split(splitter)));
			if(get(0).equals(""))
				remove(0);
		}
		public Ex18_TextFile2(String fileName) throws IOException {
			this(fileName, "\n");
		}
		// 将ArrayList保存的内容写入指定文件
		public void write(String fileName) throws IOException {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				for(String item : this)
					out.println(item);
			} finally {
				out.close();
			}
		}
	public static void main(String[] args) throws IOException {
		 String file = Ex18_TextFile2.read("./src/io/Ex18_TextFile2.java");
        // 将内容写入test.txt文件
        write("./src/io/test.txt", file);  
        // 将test.txt文件中的内容保存到ArrayList中
        Ex18_TextFile2 text = new Ex18_TextFile2("./src/io/test.txt");
        // 将ArrayList中的内容写入test2.txt中
        text.write("./src/io/test2.txt");
        // 将唯一的单词保存在Set集合中，并进行排序
        TreeSet<String> words = new TreeSet<String>(
        		new Ex18_TextFile2("./src/io/Ex18_TextFile2.java", "\\W+"));
        // 在排序后的集合中，将排在"a"之前的单词打印
        System.out.println(words.headSet("a"));
	}

}
