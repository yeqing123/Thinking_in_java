package io;
import java.io.*;
import java.nio.file.NoSuchFileException;

public class ProcessFiles2 {
	interface Strategy {  // 如果向process()方法中传入一个不存在的文件，将抛出异常
		void process(File file) throws NoSuchFileException;
	}
	private Strategy strategy;
	private String ext;
	public ProcessFiles2(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}
	public void start(String[] args) {
		try {
		    if(args.length == 0)  // 如果没有命令行参数，则从项目当前位置开始
		    	processDirectoryTree(new File("."));
		    else
		    	for(String arg : args) {
		    		File fileArg = new File(arg);
		    		if(fileArg.isDirectory())  
		    			processDirectoryTree(fileArg);
		    		else {
		    			// 允许用户在命令行中写入一个没有扩展名的文件名
		    			if(!arg.endsWith("." + ext))
		    				arg += "." + ext;
		    		    strategy.process(new File(arg).getCanonicalFile());
		    		}
		    	}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void processDirectoryTree(File dir) throws IOException {
		// 调用Directory.walk()方法，返回当前目录下的所有符合正则表达式规则的File列表
		for(File file : Directory.walk(dir, ".*\\." + ext))
			strategy.process(file.getCanonicalFile());
	}
	public static void main(String[] args) {
		ProcessFiles2 pf = new ProcessFiles2(new ProcessFiles2.Strategy() {
			@Override
			public void process(File file) throws NoSuchFileException {
				if(!file.exists())
					throw new NoSuchFileException(file.getName());
				System.out.println(file);
			}
		}, "java");
		pf.start(args);
	}
}