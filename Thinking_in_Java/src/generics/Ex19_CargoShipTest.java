package generics;

import java.util.*;

import net.mindview.util.Generator;

class Cargo {
	private final int id;
	private String description;
	private double price;
	public Cargo(int IDnumber, String descr, double price) {
		this.id = IDnumber;
		this.description = descr;
		this.price = price;
	    System.out.println(toString());
	}
	public String toString() {
		return id + ": " + description + ", price: $" + price;
	}
	public static Generator<Cargo> generator = 
		new Generator<Cargo>() {
		    private Random rand = new Random(47);
		    public Cargo next() {
		    	return new Cargo(rand.nextInt(1000), "Cargo", 
		    			Math.round(rand.nextDouble() * 1000.0) + 0.99);
		    }
	    };
}

class Container extends ArrayList<Cargo> {
	public Container(int nCargos) {
		Generators.fill(this, Cargo.generator, nCargos);
	}
}


class  CargoHold extends ArrayList<Container> {
	public CargoHold(int nContainers, int nCargos) {
		for(int i = 0; i < nContainers; i++)
			add(new Container(nCargos));
	}
}

class Pilothouse {}
class Kitchen {}

class CargoShip extends ArrayList<CargoHold> {
    private Pilothouse pilothouse = new Pilothouse();
	private Kitchen kitchen = new Kitchen();
	public CargoShip(int nCargoHolds, int nContainers, int nCargos) {
		for(int i = 0; i < nCargoHolds; i++)
			add(new CargoHold(nContainers, nCargos));
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("Cargo detail following: \n");
		for(CargoHold ch : this) {
			for(Container ct : ch)
				for(Cargo cg : ct) {
					result.append(cg);
				    result.append("\n");
				}
		}
		return result.toString();
	}
}

public class Ex19_CargoShipTest {
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(new CargoShip(10, 9, 10));
	}

}
