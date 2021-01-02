// Use the XOM library to write and read XML
// {Requires: nu.xom.Node; You must install
// the XOM library from http://www.xom.nu }
package io;
import nu.xom.*;
import java.util.*;
import java.io.*;

public class Person {
	private String first;
	private String last;
	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}
	// Constructor to restore a Person from XML Element:
	public Person(Element person) {
		this.first = person.getFirstChildElement("first").getValue();
		this.last = person.getFirstChildElement("last").getValue();
	}
	// Produce an XML Element from this Person object:
	public Element getXML() {
		Element firstName = new Element("first");
		Element lastName = new Element("last");
		firstName.appendChild(first);
		lastName.appendChild(last);
		Element person = new Element("person");
		person.appendChild(firstName);
		person.appendChild(lastName);
		return person;
	}
	// Make it human-readable:
	public static void format(OutputStream out, Document doc) throws IOException {
		Serializer serializer = new Serializer(out, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}
	public String toString() { return first + " " + last; }
	public static void main(String[] args) throws Exception {
		List<Person> people = Arrays.asList(
				new Person("Dr. Bunsen", "Honeydew"),
				new Person("Gonzo", "The Great"),
				new Person("Phillip J.", "Fry"));
		Element root = new Element("people");
		for(Person p : people)
		    root.appendChild(p.getXML());
        Document doc = new Document(root);
        BufferedOutputStream out = new BufferedOutputStream(
        		new FileOutputStream("./src/io/Person.xml"));
        format(System.out, doc);
        format(out, doc);
        System.out.println(people);
	}
}