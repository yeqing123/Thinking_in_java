package io;
import nu.xom.*;
import java.io.*;
import java.util.*;

class Person2 {
	private String first, last, state, city, address;
	private int zipCode;
	public Person2(String first, String last, String state, 
			String city, String address, int zipCode) {
		this.first = first;
		this.last = last;
		this.state = state;
		this.city = city;
		this.address = address;
		this.zipCode = zipCode;
	}
	// Constructor to restore a Person2 from an XML Element:
	public Person2(Element person2) {
		this.first = person2.getFirstChildElement("first").getValue(); 
		this.last = person2.getFirstChildElement("last").getValue();
		this.state = person2.getFirstChildElement("state").getValue();
		this.city = person2.getFirstChildElement("city").getValue();
		this.address = person2.getFirstChildElement("address").getValue();
		this.zipCode = Integer.valueOf(
				person2.getFirstChildElement("zipCode").getValue());
	}
	// Produce an XML Element form this Person object:
	public Element getXML() {
		Element firstName = new Element("first");
		Element lastName = new Element("last");
		Element stateName = new Element("state");
		Element cityName = new Element("city");
		Element addressName = new Element("address");
		Element zp = new Element("zipCode");
		firstName.appendChild(first);
		lastName.appendChild(last);
		stateName.appendChild(state);
		cityName.appendChild(city);
		addressName.appendChild(address);
		zp.appendChild(String.valueOf(zipCode));
		Element person = new Element("person");
		person.appendChild(firstName);
		person.appendChild(lastName);
		person.appendChild(stateName);
		person.appendChild(cityName);
		person.appendChild(addressName);
		person.appendChild(zp);
		return person;
	}
	public String toString() { 
		return first + " " + last + ": " + 
	        address + " " + city + " " + state + " " + zipCode; 
	}
	public static void format(OutputStream out, Document doc) throws Exception {
		Serializer serializer = new Serializer(out, "UTF-8");
		serializer.setIndent(4);
		serializer.setMaxLength(80);
		serializer.write(doc);
		serializer.flush();
	}
}

class People2 extends ArrayList<Person2> {
	
	public People2(String fileName) throws Exception {
		Document doc = new Builder().build(new File(fileName));
		Elements elements = doc.getRootElement().getChildElements();
	    for(int i = 0; i < elements.size(); i++)
	    	add(new Person2(elements.get(i)));
	}
	
}

public class Ex31_PeopleWithAddresses {

	public static void main(String[] args) throws Exception {
        List<Person2> people = Arrays.asList(
        		new Person2("Wang", "Ming", "Jiangsu", 
        				"Nanjing", "Street 1", 210012),
        		new Person2("Dong", "Yan", "Shanxi", 
        				"Xi'an", "Street 2", 710100),
        		new Person2("Dr. Bunsen", "Honeydew", "NY", 
        				 "New York",  "Street 3",  10001));
        Element root = new Element("people");
        for(Person2 p : people)
        	root.appendChild(p.getXML());
        Document doc = new Document(root);
        BufferedOutputStream out = new BufferedOutputStream(
        		new FileOutputStream("./src/io/Person.xml"));
        System.out.println("Before writing:\n" + people);
        Person2.format(System.out, doc);
        Person2.format(out, doc);
        // Now read the file restore people object:
        People2 people2 = new People2("./src/io/Person.xml");
        System.out.println("After writing:\n" + people2);
	}

}
