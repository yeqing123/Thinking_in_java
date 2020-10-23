package arrays.exercise;
import java.util.Arrays;
import java.util.Random;

public class Ex03_ArrayTest2 {
    public static double[][] createArray(int size, double head, double tail) {
    	double[][] doubles = new double[size][size];
    	Random rand = new Random(47);
    	double difference = tail - head;
    	System.out.println(difference +", " +(int)tail);
    	for(int i = 0; i < size; i++)
    		for(int j = 0; j < size; j++)
    			doubles[i][j] = 
    			tail - (rand.nextInt(difference) + rand.nextDouble());
    	return doubles;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(Arrays.deepToString(createArray(5, 1.5, 9.9)));
	}

}
