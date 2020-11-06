package containers;

import java.lang.reflect.Method;
import java.util.*;

public class FindDifferentMethods01 {
	// 从完整的方法名称中，提取只包含：方法名称、参数类型的简单方法名
	public static String getSimpleName(String completeName) {
		String[] s = completeName.split("\\.");
		StringBuffer buffer = new StringBuffer();
		boolean flag = false;
		for(int i = 0; i < s.length; i++) {
			if(s[i].contains("("))
				flag = true;
			if(s[i].contains(")")) {
				buffer.append(s[i]);
				break;
			}
			if(flag)
				buffer.append(s[i]).append("."); // 只保存方法名和参数类型，并补上丢失的“.”
		}
		return buffer.toString();
	}
	
	// 根据两个Class类型，找出它们对应类型中方法（不包含继承自父类型的方法）的差异部分，返回含差异方法的List集合
    public static List<String> findDiffer(String className1, String className2) 
    		throws Exception {
    	Method[] methods1 = Class.forName(className1).getDeclaredMethods();
    	Method[] methods2 = Class.forName(className2).getDeclaredMethods();
    	List<String> diff = new ArrayList<String>();
        List<Method> list1 = Arrays.asList(methods1);
        List<Method> list2 = Arrays.asList(methods2);
        
        boolean flag = false;
        // 找出list1中所有不在list2中的方法
        for(Method m1 : list1) {
    		String simple = getSimpleName(m1.toString());
    		for(Method m2 : list2) {
    			if(m2.toString().contains(simple)) {
    				flag = true;
    			    break;
    			}
    		}
    		if(!flag)
    			diff.add(m1.toString());
    		else 
    			flag = false;
        }
        // 找出list2中所有不在list1中的方法
        for(Method m2 : list2) {
    		String simple = getSimpleName(m2.toString());
    		for(Method m1 : list1) {
    			if(m1.toString().contains(simple)) {
    				flag = true;
    			    break;
    			}
    		}
    		if(!flag)
    			diff.add(m2.toString());
    		else 
    			flag = false;
        }
    	return diff;
    }
    public static void print(String className1, String className2, List<String> diff) {
    	System.out.println("\n========== \"" + 
                className1 + "\" VS \"" + className2 + 
                "\" ==========\n");
    	for(int i = 0; i < diff.size(); i++) {
    		System.out.println("Line-" + i + ": " + diff.get(i));
    	}
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String className1 = "java.util.Collection";
		String className2 = "java.util.List";
        List<String> diff = findDiffer(className1, className2);
        print(className1, className2, diff);
	}

}
