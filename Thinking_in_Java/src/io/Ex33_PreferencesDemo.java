package io;

import java.util.Scanner;
import java.util.prefs.Preferences;

public class Ex33_PreferencesDemo {

	public static void main(String[] args) {
        Preferences prefs = Preferences.userNodeForPackage(Ex33_PreferencesDemo.class);
        String directory = prefs.get("base directory", "Not defined");
        System.out.println("base directory: " + directory);
        System.out.println("Enter a new directory:");
        Scanner sc = new Scanner(System.in);
        directory = sc.nextLine();
        prefs.put("base directory", directory);
        System.out.println("base directory: " + prefs.get("base directory", null));
	}

}
