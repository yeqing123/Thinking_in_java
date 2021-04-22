package object;

public class Ex11_AllTheColorsOfTheRainbow {
    private String[] colors = {"red", "yellow", "green", "orange"};
    int anIntegerRepresentingColors = 2;
    public void getColor() {
        System.out.println(colors[anIntegerRepresentingColors]);
    }
    public void changeTheHueOfTheColor(int newHue) {
    	anIntegerRepresentingColors = newHue;
    }
	public static void main(String[] args) {
        Ex11_AllTheColorsOfTheRainbow allColors = new Ex11_AllTheColorsOfTheRainbow();
        allColors.getColor();
        allColors.changeTheHueOfTheColor(0);
        allColors.getColor();
	}

}
