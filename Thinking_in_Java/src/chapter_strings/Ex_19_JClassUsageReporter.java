package chapter_strings;
import java.util.regex.*;
import net.mindview.util.*;

public class Ex_19_JClassUsageReporter {
    private static final String Identifier = 
    		"[$A-Za-z_][$A-Za-z_0-9]*";
    private static final String ClassOrInterfaceType = 
    		Identifier + "(?:\\." + Identifier + ")*";
    static final String CU_REP_REGEX = 
    		"class\\s+" + Identifier + 
    		"|extends\\s" + ClassOrInterfaceType + 
    		"|new\\s+" + ClassOrInterfaceType;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        if(args.length < 1) {
        	System.out.println("Usage: java Ex_19_JClassUsageReporter file");
        	System.exit(0);
        }
        String src = TextFile.read(args[0]);
        // Prune away comments...
        src = src.replaceAll(Ex_17.CMMT_EXT_REGEX, " ");
        // Prune away string literals ...
        src = src.replaceAll(
        		Ex_18_JStringExtractor.STR_EXT_REGEX, " ");
        Pattern p = Pattern.compile(CU_REP_REGEX);
        Matcher m = p.matcher(src);
        while(m.find()) {
        	System.out.println(m.group());
        }
	}
}