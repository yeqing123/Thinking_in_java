package chapter_strings;

public class Ex_09 {
    public static String knights = 
    		"ThEn, whEn you have found the shrubbery, you must " + 
            "cut down the mightiest tree in the forest... " +
            "with... a herring!";
    public static void replace(String regex) {
//    	Pattern p = Pattern.compile(regex);
//    	Matcher m = p.matcher(knights);
//    	System.out.println(m.replaceAll("_"));
        System.out.println(knights.replaceAll(regex, "_"));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        replace("(?i)[aeiou]");
	}
    /**
     * 正则表达式中嵌入了(?i)，可以不区分大小写进行元音字母的匹配。
     */
}
