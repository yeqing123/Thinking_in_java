package generics;
import typeinfo.pets.*;

public class Ex01_Holder3<T> {
    private T a;
    public Ex01_Holder3(T a) {
    	this.a = a;
    }
    public T get() {
    	return a;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Ex01_Holder3<Pet> holder = new Ex01_Holder3<Pet>(new Cymric());
        System.out.println(holder.get());
        holder = new Ex01_Holder3<Pet>(new EgyptianMau());
        System.out.println(holder.get());
        holder = new Ex01_Holder3<Pet>(new Gerbil());
        System.out.println(holder.get());
        holder = new Ex01_Holder3<Pet>(new Mutt());
        System.out.println(holder.get());
	}

}
