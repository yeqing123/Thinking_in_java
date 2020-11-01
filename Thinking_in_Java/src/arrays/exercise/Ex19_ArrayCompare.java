package arrays.exercise;

import java.util.Arrays;

class HoldingNumber {
	int number;
	public HoldingNumber(int number) {
		this.number = number;
	}
	public String toString() { return "" + number; } 
}

class HoldingNumberWithEquals extends HoldingNumber {
	HoldingNumberWithEquals(int number) {
		super(number);
	}	
	public boolean equals(Object o) {
		if (o instanceof HoldingNumberWithEquals && 
				((HoldingNumberWithEquals) o).number == this.number)
			return true;
		return false;
	}
}

public class Ex19_ArrayCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HoldingNumber[] a1 = new HoldingNumber[4];
		HoldingNumber[] a2 = new HoldingNumber[4];
		Arrays.fill(a1, new HoldingNumber(3));
		Arrays.fill(a2, new HoldingNumber(3));
		System.out.println( Arrays.equals(a1, a2));
		Arrays.fill(a1, new HoldingNumberWithEquals(3));
		Arrays.fill(a2, new HoldingNumberWithEquals(3));
		System.out.println(Arrays.equals(a1, a2));
	}

}
