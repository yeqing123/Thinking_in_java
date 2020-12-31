package io;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Login implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;
    public Login(String username, String pwd) {
    	this.username = username;
    	this.password = pwd;
    }
    public String toString() {
    	return "Login info:\n	username: " + username + 
    			"\n	date: " + date + "\n	password: " + password;
    }
	public static void main(String[] args) throws Exception {
        Login a = new Login("Hulk", "myLittlePony");
        System.out.println("login a = " + a);
        ObjectOutputStream out = new ObjectOutputStream(
        		new FileOutputStream("./src/io/Login.out"));
        out.writeObject(a);
        out.close();
        TimeUnit.SECONDS.sleep(3);
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(
        		new FileInputStream("./src/io/Login.out"));
        System.out.println("Recovering object at " + new Date());
        a = (Login)in.readObject();
        System.out.println("login a = " + a);
	}

}
