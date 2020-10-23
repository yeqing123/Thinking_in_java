package arrays.exercise;


public class Ex07_Filling3DArray {
	public static BerylliumSphere[][][] fill(int xLen, int yLen, int zLen) {
		BerylliumSphere[][][] array = new BerylliumSphere[xLen][yLen][zLen];
		for (int i = 0; i < xLen; i++)
			for (int j = 0; j < yLen; j++)
				for (int k = 0; k < zLen; k++)
					array[i][j][k] = new BerylliumSphere();
		return array;
	}

	public static void printArray(BerylliumSphere[][][] array) {
		for (BerylliumSphere[][] twoD : array) {
			for (BerylliumSphere[] oneD : twoD) {
				for (BerylliumSphere bs : oneD) {
					System.out.print(bs + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        printArray(fill(3, 4, 5));
        System.out.println("===============================");
        printArray(fill(5, 3, 4));
	}

}
