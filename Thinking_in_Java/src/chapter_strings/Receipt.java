package chapter_strings;
import java.util.*;

public class Receipt {
	public static final int itemWidth = 15;
	public static final int qtyWidth = 5;
	public static final int priceWidth = 10;
	public static String TITLE_FRMT = 
			"%-" + itemWidth + "s %" + qtyWidth + "s %" + priceWidth + "s\n";
	public static String ITEM_FRMT = 
			"%-" + itemWidth + ".15s %" + qtyWidth + "s %" + priceWidth + ".2f\n";
    public static String TOTAL_FRMT =
    		"%-" + itemWidth + "s %" + qtyWidth + "s %" + priceWidth + ".2f\n";
    private double total = 0;
    private Formatter f = new Formatter(System.out);
    public void printTitle() {
    	f.format(TITLE_FRMT, "Item", "Qty", "Price");
    	f.format(TITLE_FRMT, "----", "---", "-----");
    }
    public void print(String name, int qty, double price) {
    	f.format(ITEM_FRMT, name, qty, price);
    	total += price;
    }
    public void printTotal() {
    	f.format(TOTAL_FRMT, "Tax", "", total*0.06);
    	f.format(TITLE_FRMT, "", "", "-----");
    	f.format(TOTAL_FRMT, "Total", "", total*1.06);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porrideg", 1, 14.29);
        receipt.printTotal();
	}

}
