// 运行操作系统命令，并将输出发送到控制台。
package io;
import java.io.*;

public class OSExecute {
    public static void command(String command) {
    	boolean err = false;
    	try {
    		// 根据传入的命令序列，创建一个进程
    		Process process = new ProcessBuilder(command.split(" ")).start();
    		// 获得该进程的输出流
    		BufferedReader results = new BufferedReader(
    				new InputStreamReader(process.getInputStream()));
    		String s;
    		while((s = results.readLine()) != null)
    			System.out.println(s);
    		// 获得该进程的错误流
    		BufferedReader errors = new BufferedReader(
    				new InputStreamReader(process.getErrorStream(), "GBK"));
    		// 如果出现问题，报告错误并将非零值返回给调用进程：
    		while((s = errors.readLine()) != null) {
    			System.err.println(s);
    			err = true;
    		}
    	} catch(Exception e) {
    		// 如果是Windows 2000，它会抛出默认命令行的异常：
    		if(!command.startsWith("CMD /C"))
    			command("CMD /C" + command);
    		else
    			throw new RuntimeException(e);
    	}
    	// 如果进程自身在执行过程中产生错误，则抛出单独定制的异常
    	if(err)
    		throw new OSExecuteException("Errors executing " + command);
    }
}
