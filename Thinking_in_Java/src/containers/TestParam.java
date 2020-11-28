package containers;
// 一个“数据”传输对象。

public class TestParam {
    public final int size;
    public final int loops;
    public TestParam(int size, int loops) {
    	this.size = size;
    	this.loops = loops;
    }
    // 通过参数序列创建一个TestParam对象数组
    public static TestParam[] array(int... values) {
    	int size = values.length / 2;
    	TestParam[] result = new TestParam[size];
    	int n = 0;
    	for(int i = 0; i < size; i++)
    		result[i] = new TestParam(values[n++], values[n++]);
    	return result;
    }
    // 将字符串数组转换为TestParam数组
    public static TestParam[] array(String[] values) {
    	int[] vals = new int[values.length];
    	for(int i = 0; i < vals.length; i++)
    		vals[i] = Integer.decode(values[i]);
    	return array(vals);
    }
}
