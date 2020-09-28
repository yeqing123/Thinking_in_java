package typeinfo;

class Building {}
class House extends Building {}

public class ClassGasts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        h = (House)b;  // ... or just do this
	}

}
