package containers;

import java.lang.reflect.Method;
import java.util.*;

// 定义一个方法分析器，从方法名、参数类型、返回值，三方面进行分析
class MethodAnalyzer {
	private static class Info {
		Method m;
		String name;
		Class<?> returnType;
		Class<?>[] parametersType;
		Info(Method m) {
			this.m = m;
			this.name = m.getName();
			this.returnType = m.getReturnType();
			this.parametersType = m.getParameterTypes();
		}
		String getName() { return name; }
		Class<?> getReturnType() { return returnType; }
		Class<?>[] getParametersType() { return parametersType; }
		void setName(String name) { this.name = name; }
		void setReturnType() { this.returnType = returnType; }
		void setParametersType() { this.parametersType = parametersType; }
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Info))
				return false;
			Info info = (Info)o;
			if(name.equals(info.name) && 
					returnType.equals(info.returnType) &&
					Arrays.equals(parametersType, info.parametersType))
			    return true;
	        return false;			
		}
	}
	private static List<Info> infoList = new ArrayList<Info>();
	public static void analyse(List<Method> mlist) {
		for(Method m : mlist) {
			infoList.add(new Info(m));
		}
	}
}

// 利用MethodAnalyzer，找出两个类型之间各自所特有的方法
public class FindDifferentMethods02 {
    public static List<String> findDiffer(String className1, String className2) 
    		throws Exception {
    	// 如果使用了不同的加载器，即便是同一个类型生成的Class对象也会视为不相等，
    	// 因此为了避免该情况，我们统一使用forName()方法生成Class对象
    	Method[] methods1 = Class.forName(className1).getDeclaredMethods();
    	Method[] methods2 = Class.forName(className2).getDeclaredMethods();
    	Set<MethodAnalyzer> maSet1 = new HashSet<MethodAnalyzer>();
    	Set<MethodAnalyzer> maSet2 = new HashSet<MethodAnalyzer>();
    	// 将每个方法放入分析器，并放入Set集合
    	for(Method m : methods1)
    		maSet1.add(new MethodAnalyzer(m));
    	for(Method m : methods2)
    		maSet2.add(new MethodAnalyzer(m));
    	// 存放找到的结果
    	List<String> differ = new ArrayList<String>();
    	// 方法一：分别遍历两个Set集合，逐个查询，并将符合条件的放入List集合中
        for(MethodAnalyzer ma : maSet1) 
        	if(!maSet2.contains(ma))
        		differ.add(ma.getMethod().toString());
        for(MethodAnalyzer ma : maSet2) 
        	if(!maSet1.contains(ma)) 
        		differ.add(ma.getMethod().toString());

        // 方法二：
//    	Set<MethodAnalyzer> intersection = new HashSet<MethodAnalyzer>();
//    	intersection.addAll(maSet1);    
//    	intersection.retainAll(maSet2);  // 获得两个Set集合的交集
//    	// 将交集中所有元素删除，剩下的就是各自的特有元素
//    	maSet1.removeAll(intersection);  
//    	maSet2.removeAll(intersection);
//    	// 保存结果到List集合中
//    	for(MethodAnalyzer ma : maSet1)
//    		diff.add(ma.getMethod().toString());
//    	for(MethodAnalyzer ma : maSet2)
//    		diff.add(ma.getMethod().toString());
        
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
        List<String> differ = findDiffer(className1, className2);
        print(className1, className2, differ);
	}

}
