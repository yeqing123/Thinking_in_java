package generics;

public class Ex30_AutoboxingHolder {
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Holder<Integer> hi = new Holder<Integer>(1);
        System.out.println(hi.get());
        hi.set(2);
        System.out.println(hi.get());
        Holder<Float> hf = new Holder<Float>(1.0F);
        System.out.println(hf.get());
        hf.set(2.0F);
        System.out.println(hf.get());
        Holder<Boolean> hb = new Holder<Boolean>(true);
        System.out.println(hb.get());
        hb.set(false);
        System.out.println(hb.get());
        Holder<String> hs = new Holder<String>("hello");
        System.out.println(hs.get());
        hs.set("world");
        System.out.println(hs.get());
        Holder<Byte> hbyte = new Holder<Byte>((byte)7);
        System.out.println(hbyte.get());
        hbyte.set((byte)9);
        System.out.println(hbyte.get());
        Holder<Double> hd = new Holder<Double>(2.0);
        System.out.println(hd.get());
        hd.set(3.0);
        System.out.println(hd.get());
	}

}
