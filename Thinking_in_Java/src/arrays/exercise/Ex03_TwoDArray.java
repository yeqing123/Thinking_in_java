package arrays.exercise;
import java.util.Locale;

public class Ex03_TwoDArray {
    public static double[][] twoDArray(int xLen, int yLen, double headVal, double tailVal) {
    	double[][] dArray = new double[xLen][yLen];
        double increment = (tailVal - headVal) / (xLen * yLen);
        double val = headVal;
    	for(int i = 0; i < xLen; i++)
    		for(int j = 0; j < yLen; j++) {
    			dArray[i][j] = val;
    		    val += increment;
    		}
    	return dArray;
    }
    public static void printArray(double[][] dArray) {
    	for(int i = 0; i < dArray.length; i++) {
    		for(int j = 0; j < dArray[i].length; j++)
    			System.out.printf(Locale.US, "%.2f ", dArray[i][j]);
    		System.out.println();
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        double[][] dArray = twoDArray(3, 4, 2.5, 9.8);
        printArray(dArray);
        System.out.println("------------------------");
        dArray = twoDArray(5, 4, 4.0, 8.5);
        printArray(dArray);
	}

}
