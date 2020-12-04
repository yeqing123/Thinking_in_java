package io;
// {Args: ./src 2020-12-02}
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.mindview.util.ProcessFiles;

public class Ex06_FindGivenFiles {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println(
					"Command arguments:" +
					"\n First argument enter a given path:" +
					"\n Second argument enter date in \"yyyy-MM-dd\" format:");
		    System.exit(1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long tmp = 0;
		try {
		    tmp = sdf.parse(args[1]).getTime();
		} catch(ParseException pe) {
			pe.printStackTrace();
			return ;
		}
		final long modTime = tmp;
		new ProcessFiles(new ProcessFiles.Strategy() {
			@Override
			public void process(File f) {
				if(f.lastModified() >= modTime)
				    System.out.println(f + " \nLastModified " + 
				    sdf.format(new Date(f.lastModified())));
			}
		}, "java").start(new String[] {args[0]});

	}

}
