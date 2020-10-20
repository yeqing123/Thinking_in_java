package generics;

import java.util.Date;

interface TimeStamped { long getStamp(); }

class TimeStampedImp implements TimeStamped {
	private final long timeStamp;
	public TimeStampedImp() {
		timeStamp = new Date().getTime();
	}
	public long getStamp() { return timeStamp; }
}

interface SerialNumbered { long getSerialNumber(); }

class SerialNumberedImp implements SerialNumbered {
	private static long counter = 1;
	private final long serialNumber = counter++;
	public long getSerialNumber() { return serialNumber; }
}

interface Alias { 
	String getAlias();
    void setAlias(String alias);	
}

class AliasImp implements Alias {
	private String alias;
	public void setAlias(String alias) {
	 this.alias = alias;
	}
	public String getAlias() {
		return this.alias;
	}
}

interface Basic {
	public void set(String val);
	public String get();
}

class BasicImp implements Basic {
	private String value;
	public void set(String val) {
		value = val;
	}
	public String get() {
		return value;
	}
}

class Mixin extends BasicImp implements TimeStamped, SerialNumbered, Alias {
	private TimeStamped timeStamp = new TimeStampedImp();
	private SerialNumbered serialNumber = new SerialNumberedImp();
	private Alias alias = new AliasImp();
	public long getStamp() { return timeStamp.getStamp(); }
	public long getSerialNumber() { return serialNumber.getSerialNumber(); }
	public void setAlias(String s) { alias.setAlias(s); }
	public String getAlias() { return alias.getAlias(); }
}


public class Ex37_Mixins2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("mixin1:: ");
        mixin2.set("mixin2:: ");
        mixin1.setAlias("test Mixed01");
        mixin2.setAlias("test Mixed02");
        System.out.println(mixin1.get() + " serialNumber: " + 
            mixin1.getSerialNumber() + ", timeStamp: " + mixin1.getStamp() +
            ", alias: " + mixin1.getAlias());
        System.out.println(mixin2.get() + " serialNumber: " + 
        		mixin2.getSerialNumber() + ", timeStamp: " + mixin2.getStamp() + 
        		", alias: " + mixin2.getAlias());
	}

}
