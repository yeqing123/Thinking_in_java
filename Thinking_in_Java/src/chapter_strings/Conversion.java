package chapter_strings;
import java.math.BigDecimal;
import java.util.Formatter;

public class Conversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Formatter frmt = new Formatter(System.out);
        
        int i = 13;
        System.out.println("i = " + i);
        frmt.format("d: %d\n", i);
        frmt.format("c: %c\n", i);
        frmt.format("b: %b\n", i);
        frmt.format("s: %s\n", i);
//      frmt.format("f: %f\n", i);
//      frmt.format("e: %e\n", i);
        frmt.format("x: %x\n", i);
        frmt.format("h: %h\n", i);
        System.out.println();
        
        char u = 'w';
        System.out.println("u = " + "'" + u + "'");
//      frmt.frmtormat("d: %d\n", u);
        frmt.format("c: %c\n", u);
        frmt.format("b: %b\n", u);
        frmt.format("s: %s\n", u);
//      frmt.format("f: %f\n", u);
//      frmt.format("e: %e\n", u);
//      frmt.format("x: %x\n", u);
        frmt.format("h: %h\n", u);
        System.out.println();
        
        boolean b = true;
        System.out.println("b = " + b);
//      frmt.format("d: %d\n", b);
//      frmt.format("c: %c\n", b);
        frmt.format("b: %b\n", b);
        frmt.format("s: %s\n", b);
//      frmt.format("f: %f\n", b);
//      frmt.format("e: %e\n", b);
//      frmt.frmtormat("x: %x\n", b);
        frmt.format("h: %h\n", b);
        System.out.println();

        String s = "abcd";
        System.out.println("s = " + s);
//      frmt.format("d: %d\n", s);
//      frmt.format("c: %c\n", s);
        frmt.format("b: %b\n", s);
//      frmt.format("s: %s\n", s);
//      frmt.format("f: %f\n", s);
//      frmt.format("e: %e\n", s);
//      frmt.format("x: %x\n", s);
        frmt.format("h: %h\n", s);
        System.out.println();
        
        double f = 123.159;
        System.out.println("f = " + f);
//      frmt.format("d: %d\n", f);
//      frmt.format("c: %c\n", f);
        frmt.format("b: %b\n", f);
        frmt.format("s: %s\n", f);
        frmt.format("f: %f\n", f);
        frmt.format("e: %e\n", f);
//      frmt.format("x: %x\n", f);
        frmt.format("h: %h\n", f);
        System.out.println();
        
        BigDecimal decimal = new BigDecimal("512.123456789123456789");
        System.out.println("decimal = new BigDecimal(\"512.123456789123456789\")");
//      frmt.format("d: %d\n", decimal);
//      frmt.format("c: %c\n", decimal);
        frmt.format("b: %b\n", decimal);
        frmt.format("s: %s\n", decimal);
        frmt.format("f: %f\n", decimal);
        frmt.format("e: %e\n", decimal);
//      frmt.format("x: %x\n", decimal);
        frmt.format("h: %h\n", decimal);
        System.out.println();
        
        Conversion conv = new Conversion();
        System.out.println("conv = new Conversion()");
//      frmt.format("d: %d\n", conv);
//      frmt.format("c: %c\n", conv);
        frmt.format("b: %b\n", conv);
        frmt.format("s: %s\n", conv);
//      frmt.format("f: %f\n", conv);
//      frmt.format("e: %e\n", conv);
//      frmt.format("x: %x\n", conv);
        frmt.format("h: %h\n", conv);
        System.out.println();
	}

}
