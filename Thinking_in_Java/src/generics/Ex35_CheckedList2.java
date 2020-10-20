package generics;
import java.util.*;
import generics.coffee.*;

class Tea {}

public class Ex35_CheckedList2 {
	@SuppressWarnings("unchecked")
    static void oldStlyMethod(List probablyCoffees) {
    	probablyCoffees.add(new Tea());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<Coffee> coffees1 = new ArrayList<Coffee>();
        oldStlyMethod(coffees1);
        List<Coffee> coffees2 = 
        		Collections.checkedList(new ArrayList<Coffee>(), Coffee.class);
        try {
        	oldStlyMethod(coffees2);
        } catch(Exception e) {
        	System.out.println(e);
        }
        coffees2.add(new Latte());
        coffees2.add(new Cappuccino());
        coffees2.add(new Americano());
	}

}
