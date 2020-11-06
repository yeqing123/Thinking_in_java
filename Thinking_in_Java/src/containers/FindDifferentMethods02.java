package containers;

import java.lang.reflect.Method;
import java.util.*;

// 定义一个方法分析器，从方法名、参数类型、返回值，三方面进行分析
class MethodAnalyzer {
	private Method method;
	private String name;
	private Class<?> returnType;
	private Class<?>[] parametersType;
	public MethodAnalyzer(Method m) {
		method = m;
		name = m.getName();
		returnType = m.getReturnType();
		parametersType = m.getParameterTypes();
	}
	// 覆盖equals()方法，用来比较两个MethodAnalyzer是否相等
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof MethodAnalyzer))
			return false;
		MethodAnalyzer ma = (MethodAnalyzer)o;
		if(name.equals(ma.name) && 
				returnType.equals(ma.returnType) &&
				Arrays.equals(parametersType, ma.parametersType))
		    return true;
        return false;			
	}
	// 覆盖hashCode()方法，因为如果将MethodAnalyzer对象放入HashSet中，
	// 就需要调用该方法来获取对象的哈希码值
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	public Method getMethod() {
		return method;
	}
}

// 利用MethodAnalyzer，找出指定两个类型所包含的不同方法
public class FindDifferentMethods02 {
    public static List<String> findDiff(String className1, String className2) 
    		throws Exception {
    	// 如果使用了不同的加载器，即便是同一个类型生成的Class对象也会视为不相等，
    	// 因此为了避免该情况，我们统一使用forName()方法生成Class对象
    	Method[] methods1 = Class.forName(className1).getDeclaredMethods();
    	Method[] methods2 = Class.forName(className2).getDeclaredMethods();
    	Set<MethodAnalyzer> maSet1 = new HashSet<MethodAnalyzer>();
    	Set<MethodAnalyzer> maSet2 = new HashSet<MethodAnalyzer>();
    	// 生成方法分析器，并放入Set集合
    	for(Method m : methods1)
    		maSet1.add(new MethodAnalyzer(m));
    	for(Method m : methods2)
    		maSet2.add(new MethodAnalyzer(m));
    	// 找出两个类型包含的不同方法
    	List<String> diff = new ArrayList<String>();
    	// 方法一：
//        for(MethodAnalyzer ma : maList1) {
//        	if(!maList2.contains(ma))
//        		diff.add(ma.getMethod().toString());
//        }
//        for(MethodAnalyzer ma : maList2) {
//        	if(!maList1.contains(ma)) 
//        		diff.add(ma.getMethod().toString());
//        }
    	// 方法二：
    	Set<MethodAnalyzer> large = maSet1.size() >= maSet2.size() ? maSet1 : maSet2;
    	Set<MethodAnalyzer> little = maSet1.size() < maSet2.size() ? maSet1 : maSet2;
    	Set<MethodAnalyzer> intersection = new HashSet<MethodAnalyzer>(large);
    	intersection.retainAll(little);
    	intersection.retainAll(little);
    	little.removeAll(intersection);
    	for(MethodAnalyzer ma : large)
    		diff.add(ma.getMethod().toString());
        return diff;
    }
    
    public static void print(String name1, String name2, List<String> diff) {
    	System.out.println("\"" + name1 + "\" VS \"" + name2 + "\": ");
    	System.out.println("=====================================");
    	for(int i = 0; i < diff.size(); i++) {
    		System.out.println(i + ": " + diff.get(i));
    	}
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        List<String> diff = findDiff("java.util.Collection", "java.util.List");
        print("java.util.Collection", "java.util.List", diff);
	}

}
