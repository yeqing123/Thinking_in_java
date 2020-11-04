package containers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FindDifferentMethods {
	public static String extract(String longMethod) {
		String[] s = longMethod.toString().split("\\.");
		List<String> l = new ArrayList<String>();
		boolean flag = false;
		for(int i = s.length - 1; i > -1; i--) {
			if(s[i].contains(")"))
				flag = true;
			if(s[i].contains("(")) {
				l.add(s[i]);
				break;
			}
			if(flag)
				l.add(s[i]);
		}
		Collections.reverse(l);
		StringBuffer buffer = new StringBuffer();
		for(String item : l)
			buffer.append(item);
		return l.toString();
	}
    public static List<String> differentMethods(Class<?> type1, Class<?> type2) {
    	List<String> diffList = new ArrayList<String>();
    	List<Method> methods1 = Arrays.asList(type1.getDeclaredMethods());
    	List<Method> methods2 = Arrays.asList(type2.getDeclaredMethods());
    	List<Method> temp = null;
    	if(methods1.size() > methods2.size())
    		temp = methods1;
    	else
    		temp = methods2;
    	for(Method m : temp) {
    		String s = extract(m.toString());
    		System.out.println(s);
    		if(!methods2.contains(s))
    			diffList.add(m.getName());
    	}
    	return diffList;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = differentMethods(Collection.class, List.class);
        System.out.println(list);
	}

}
