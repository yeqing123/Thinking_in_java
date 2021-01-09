package enumerated;

public enum OverrideConstantSpecific {
    NUT, BOLT,
    WASHER {
    	void f() { System.out.println("Overridden method"); }
    };
	void f() { System.out.println("Default beharior"); }
	public static void main(String[] args) {
        for(OverrideConstantSpecific ocs : values()) {
        	System.out.print(ocs + ": ");
        	ocs.f();
        }
	}

}
