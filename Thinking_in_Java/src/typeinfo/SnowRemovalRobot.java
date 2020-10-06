package typeinfo;

import java.util.Arrays;
import java.util.List;

public class SnowRemovalRobot implements Robot {
    private String name;
	public SnowRemovalRobot(String name) {
    	this.name = name;
    }
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String model() {
		// TODO Auto-generated method stub
		return "SnowBot Series 11";
	}

	@Override
	public List<Operation> operations() {
		// TODO Auto-generated method stub
		return Arrays.asList(
				new Operation() {
					public String description() {
						return name + " can shovel snow";
					}
				    public void command() {
				    	System.out.println(name + " shoveling snow");
				    }
				},
				new Operation() {
					public String description() {
						return name + " can chip ice";
					}
					public void command() {
						System.out.println(name + " chipping ice");
					}
				},
				new Operation() {
					public String description() {
						return name + " can clear the roof";
					}
					public void command() {
						System.out.println(name + " clearing roof");
					}
				});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Robot.Test.test(new SnowRemovalRobot("Slusher"));
	}

}
