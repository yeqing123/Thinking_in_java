// A class with a Null Object.
package typeinfo;
import net.mindview.util.*;

class Person {
    public final String first;
    public final String last;
    public final String address;
    // etc.
    public Person(String first, String last, String address) {
    	this.first = first;
    	this.last = last;
    	this.address = address;
    }
    public String toString() {
    	return "Person: " + first + " " + last + " " + address;
    }
    public static class NullPerson extends Person implements Null {
    	private NullPerson() { super("None", "None", "None"); }
    	public String toString() { return "NullPerson"; }
    }
    public static final Person Null = new NullPerson();
}

class Position {
	private String title;
	private Person person;
	public Position(String jobTitle, Person employee) {
		title = jobTitle;
		person = employee;
		if(person == null) 
			person = Person.Null;
	}
	public Position(String jobTitle) {
		title = jobTitle;
		person = Person.Null;
	}
	public String getTitle() { return title; }
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	public Person getPerson() { return person; }
	public void setPerson(Person newPerson) {
		person = newPerson;
		if(person == null)
			person = Person.Null;
	}
	public String toString() {
		return "Position: " + title + " " + person;
	}
}
