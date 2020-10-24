package arrays.exercise;

import java.util.Arrays;
import arrays.BerylliumSphere;


public class Ex01_ArrayTest {
    public static void f(BerylliumSphere[] bs) {
    	System.out.println(Arrays.toString(bs));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        f(new BerylliumSphere[] {
        		new BerylliumSphere(), 
        		new BerylliumSphere(), 
        		new BerylliumSphere()});
        BerylliumSphere[] bs1 = {
        		new BerylliumSphere(), 
        		new BerylliumSphere(), 
        		new BerylliumSphere()};
        f(bs1);   // 应该这样使用“普通聚集初始化”
        // 不能直接使用“普通聚集初始化”
//      f({new BerylliumSphere(), 
//    		new BerylliumSphere(), 
//    		new BerylliumSphere()});
     // 这样使用“动态聚集初始化”会显得多余
        BerylliumSphere[] bs2 = new BerylliumSphere[] {
        		new BerylliumSphere(), 
        		new BerylliumSphere(), 
        		new BerylliumSphere()};
        f(bs2); 
	}

}
