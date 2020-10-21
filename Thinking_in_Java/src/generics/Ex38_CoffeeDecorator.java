package generics;

interface Coffee2 {
	public double getCost();
	public String getIngredient(); 
}

class OriginalCoffee implements Coffee2 {
	public double getCost() {
		return 10.00;
	}
	public String getIngredient() {
		return "coffee";
	}
}

abstract class CoffeeDecorator implements Coffee2 {
	private final Coffee2 decoratedCoffee;
	public CoffeeDecorator(Coffee2 decoratedCoffee) {
		this.decoratedCoffee = decoratedCoffee;
	}
	public double getCost() {
		return decoratedCoffee.getCost();
	}
	public String getIngredient() {
		return decoratedCoffee.getIngredient();
	}
}

class WithMilk extends CoffeeDecorator {
	public WithMilk(Coffee2 decoratedCoffee) {
        super(decoratedCoffee);
	}
	@Override
	public double getCost() {
		return super.getCost() + 2.0;
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + " milk";
	}
}

class WithFoam extends CoffeeDecorator {
	public WithFoam(Coffee2 decoratedCoffee) {
		super(decoratedCoffee);
	}
	@Override
	public double getCost() {
		return super.getCost() + 1.0;
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + " foam";
	}
}

// 为添加巧克力再增加一个装饰器，扩展一个新的功能，区别白巧克力和黑巧克力
abstract class ChocolateKind extends CoffeeDecorator {
	String kind;
	public ChocolateKind(Coffee2 decoratedCoffee, String kind) {
		super(decoratedCoffee);
		this.kind = kind;
	}
	@Override
	public double getCost() {
		if(kind.equalsIgnoreCase("black"))
		    return super.getCost() + 1.8;
		if(kind.equalsIgnoreCase("white"))
			return super.getCost() + 1.0;
		return super.getCost();
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + " " + kind;
	}
}

class WithChocolate extends ChocolateKind {
	public WithChocolate(Coffee2 decoratedCoffee, String kind) {
		super(decoratedCoffee, kind);
	}
	@Override
	public double getCost() {
		return super.getCost();
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + "-chcolate";
	}
}

class WithCaramel extends CoffeeDecorator {
	public WithCaramel(Coffee2 decoratedCoffee) {
		super(decoratedCoffee);
	}
	@Override
	public double getCost() {
		return super.getCost() + 1.0;
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + " caramel";
	}
}

class WithWhippedCream extends CoffeeDecorator {
	public WithWhippedCream(Coffee2 decoratedCoffee) {
		super(decoratedCoffee);
	}
	@Override
	public double getCost() {
		return super.getCost() + 2.0;
	}
	@Override
	public String getIngredient() {
		return super.getIngredient() + " whipped-cream";
	}
}

public class Ex38_CoffeeDecorator {

	public static void print(Coffee2 coffee, String name) {
		System.out.println(name);
		System.out.println("Ingredient: " + coffee.getIngredient() + 
				", cost: " + coffee.getCost());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        OriginalCoffee originalCoffee = new OriginalCoffee();
        print(originalCoffee, "Original Coffee");
        Coffee2 americano = 
        		new WithCaramel(new WithChocolate(new WithMilk(originalCoffee), "black"));
        print(americano, "Americano");
        Coffee2 breve = new WithFoam(new WithMilk(originalCoffee));
        print(breve, "Breve");
        Coffee2 latte = 
        		new WithChocolate(new WithMilk(new WithWhippedCream(originalCoffee)), "white");
        print(latte, "Latte");
	}

}
