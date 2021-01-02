// {Requires: nu.xom.Node; You must install
// the XOM library from http://www.xom.nu}
// {RunFirst: Person.java}
package io;
import java.util.*;
import nu.xom.*;
import java.io.*;

public class People extends ArrayList<Person> {
    public People(String fileName) throws Exception {
    	Document doc = new Builder().build(new File(fileName));
    	Elements elements = 
    			doc.getRootElement().getChildElements();
    	for(int i = 0; i < elements.size(); i++) {
    		add(new Person(elements.get(i)));
    	}
    }
	public static void main(String[] args) throws Exception {
        People people = new People("./src/io/Person.xml");
        System.out.println(people);
    }
}
