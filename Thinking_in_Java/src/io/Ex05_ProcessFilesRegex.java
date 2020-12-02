package io;
import java.io.File;
import java.io.IOException;

public class Ex05_ProcessFilesRegex {
	private String regex;
	private ProcessFiles2.Strategy strategy;
	public Ex05_ProcessFilesRegex(ProcessFiles2.Strategy strategy, String regex) {
		this.strategy = strategy;
		this.regex = regex;
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
		    			if(arg.matches(regex))
		    		        strategy.process(fileArg.getCanonicalFile());
		    		}
		    	}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void processDirectoryTree(File dir) throws IOException {
		for(File file : Directory.walk(dir, regex))
			strategy.process(file.getCanonicalFile());
	}
    
	public static void main(String[] args) {
		new Ex05_ProcessFilesRegex(new ProcessFiles2.Strategy() {
			@Override
			public void process(File file) {
                System.out.println(file);				
			}
		}, "D.*\\.java").start(args);
	}

}
