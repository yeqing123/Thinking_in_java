package containers;

import java.lang.reflect.Method;
import java.util.*;

public class FindDifferentMethods01 {
	// 判断两个方法是否相同
	public static boolean isEquals(Method m1, Method m2) {
		return m1.getName().equals(m2.getName()) && 
				m1.getReturnType().equals(m2.getReturnType()) &&
				Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes());
	}
	// 根据两个Class类型，找出它们对应类型中方法（不包含继承自父类型的方法）的差异部分，返回含差异方法的List集合
    public static List<String> findDiffer(String className1, String className2) 
    		throws Exception {
    	Method[] methods1 = Class.forName(className1).getDeclaredMethods();
    	Method[] methods2 = Class.forName(className2).getDeclaredMethods();
    	List<String> differ = new ArrayList<String>();
        List<Method> list1 = Arrays.asList(methods1);
        List<Method> list2 = Arrays.asList(methods2);
        boolean flag = true;
        // 找出list1中所有不在list2中的方法
        for(Method m1 : list1) {
        	flag = true;
    		for(Method m2 : list2) {
    			if(isEquals(m1, m2)) {
    			    flag = false;
    				break;
    			}
    		}
    		if(flag)
    			differ.add(m1.toString());
        }
        // 找出list2中所有不在list1中的方法
        for(Method m2 : list2) {
        	flag = true;
    		for(Method m1 : list1) {
    			if(isEquals(m1, m2)) {
    			    flag = false;
    				break;
    			}
    		}
    		if(flag)
    			differ.add(m2.toString());
        }
    	return differ;
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
