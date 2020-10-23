package arrays.exercise;


public class Ex06_Filling2DArray {
    public static BerylliumSphere[][] fill(int xLen, int yLen) {
    	BerylliumSphere[][] array = new BerylliumSphere[xLen][yLen];
    	for(int i = 0; i < xLen; i++)
    		for(int j = 0; j < yLen; j++)
    			array[i][j] = new BerylliumSphere();
    	return array;
    }
    public static void printArray(BerylliumSphere[][] array) {
    	for(int i = 0; i < array.length; i++) {
    		for(int j = 0; j < array[i].length; j++)
    			System.out.print(array[i][j] + "\t");
    		System.out.println();
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        printArray(fill(3, 2));
        System.out.println("===================================");
        printArray(fill(2, 5));
	}

}
