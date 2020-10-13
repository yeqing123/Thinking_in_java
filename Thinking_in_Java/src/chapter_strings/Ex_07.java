package chapter_strings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String s1 = "line terminators.";
        String s2 = "Line terminators.";
        String s3 = "Line terminators";
		Pattern p = Pattern.compile("^[A-Z].*\\.$");
        Matcher m1 = p.matcher(s1);
        boolean b1 = m1.matches();
        Matcher m2 = p.matcher(s2);
        boolean b2 = m2.matches();
        Matcher m3 = p.matcher(s3);
        boolean b3 = m3.matches();

//      boolean b1 = Pattern.matches("^[A-Z].*\\.$", s1);
//      boolean b2 = Pattern.matches("^[A-Z].*\\.$", s2);
//      boolean b3 = Pattern.matches("^[A-Z].*\\.$", s3);
        
        System.out.println("字符串 s1 匹配结果：" + b1);
        System.out.println("字符串 s2 匹配结果：" + b2);
        System.out.println("字符串 s3 匹配结果：" + b3);
        

	}

}
