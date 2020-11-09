// 很显然，FindDifferentMethods02.java的解决方案较前一个，更符合面向对象编程思想，程序结构也更合理。
package containers;
import java.lang.reflect.Method;
import java.util.*;

// 定义一个方法分析器
class MethodAnalyzer {
	// 包含一个内部类，它保存了一个方法的方法名、参数类型、返回值，我们就是通过这三信息来识别不同方法的
	static class Info {
		private String completeName;  // 方法的完整表述
		private String name;
		private Class<?> returnType;
		private Class<?>[] parametersType;
		// 私有权限的构造器，只能在MethodAnalyzer类的内部使用。
		private Info(Method m) {
			this.completeName = m.toString();
			this.name = m.getName();
			this.returnType = m.getReturnType();
			this.parametersType = m.getParameterTypes();
		}
		public String getCompleteName() { return completeName; }
		// 覆盖equals()方法，用于直接对两个Info实例进行相等判断
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
	// 一个静态方法，可以根据参数中包含的Method对象，生成Info对象，并放入集合中
	public static List<Info> analyse(Collection<Method> methods) {
		List<Info> infoList = new ArrayList<Info>();
		for(Method m : methods) {
			infoList.add(new Info(m));
		}
		return infoList;
	}
}

// 利用MethodAnalyzer，找出两个类型之间各自所特有的方法
public class FindDifferentMethods02 {
    public static List<String> findDiffer(String className1, String className2) 
    		throws Exception {
    	// 如果使用了不同的加载器，即便是同一个类型生成的Class对象也会视为不相等，
    	// 因此为了避免该情况，我们统一使用forName()方法生成Class对象。
    	Method[] methods1 = Class.forName(className1).getDeclaredMethods();
    	Method[] methods2 = Class.forName(className2).getDeclaredMethods();
    	// 利用方法分析器，生成包含方法信息的Info对象
    	List<MethodAnalyzer.Info> infoList1 = 
    			MethodAnalyzer.analyse(Arrays.asList(methods1));
    	List<MethodAnalyzer.Info> infoList2 = 
    			MethodAnalyzer.analyse(Arrays.asList(methods2));
    	// 我们将内部类Info的构造器访问权限设置为私有，目的就是不允许其他类从外部自行创建Info实例，
    	// 而只允许通过调用方法分析器（MethodAnalyzer）的analyse()静态方法来生成。
    	// MethodAnalyzer.Info info3 = new MethodAnalyzer.Info(methods1[0]);
    	// 定义List集合，用于存放结果
    	List<String> differ = new ArrayList<String>();
    	// 方法一：分别遍历包含Info实例的集合，逐个查询
        for(MethodAnalyzer.Info info : infoList1) 
        	if(!infoList2.contains(info))
        		differ.add(info.getCompleteName());
        for(MethodAnalyzer.Info info : infoList2) 
        	if(!infoList1.contains(info)) 
        		differ.add(info.getCompleteName());

        // 方法二：
//    	List<MethodAnalyzer.Info> intersection = new ArrayList<MethodAnalyzer.Info>();
//    	intersection.addAll(infoList1);    
//    	intersection.retainAll(infoList2);  // 获得两个List集合的交集
//    	// 将交集中所有元素删除，剩下的就是各自的特有元素
//    	infoList1.removeAll(intersection);  
//    	infoList2.removeAll(intersection);
//    	// 保存结果到differ集合中
//    	for(MethodAnalyzer.Info info : infoList1)
//    		differ.add(info.getCompleteName());
//    	for(MethodAnalyzer.Info info : infoList2)
//    		differ.add(info.getCompleteName());
        
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
