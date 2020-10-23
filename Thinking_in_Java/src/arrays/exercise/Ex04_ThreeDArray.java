package arrays.exercise;

import java.util.Locale;

public class Ex04_ThreeDArray {
	public static double[][][] threeDArray(int xLen, int yLen, int zLen, double valStart, double valEnd) {
		double[][][] array = new double[xLen][yLen][zLen];
		double increment = (valEnd - valStart) / (xLen * yLen * zLen);
		double val = valStart + increment;
		for(int i = 0; i < xLen; i++)
			for(int j = 0; j < yLen; j++)
				for(int k = 0; k < zLen; k++) {
					array[i][j][k] = val;
					val += increment;
				}
		return array;
	}
	public static void printArray(double[][][] array) {
		for(double[][] twoD : array) {
			for(double[] oneD : twoD) {
				for(double val : oneD) {
					System.out.printf(Locale.US, "%.2f ", val);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        double[][][] array = threeDArray(3, 4, 5, 40.0, 90.5);
        printArray(array);
        System.out.println("=====================================");
        array = threeDArray(3, 2, 3, 11.0, 40.9);
        printArray(array);
	}

}
