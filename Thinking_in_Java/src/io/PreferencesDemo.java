package io;
import java.util.prefs.*;

public class PreferencesDemo {
    
	public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);
        // 获取与关键字“UsageCount”对应的值，如果没有则返回指定的默认值0
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for(String key : prefs.keys())
        	System.out.println(key + ": " + prefs.get(key, null));
        // 你必须总是提供一个默认值：
        System.out.println("How many companions does Dorothy have? " +
            prefs.getInt("Companions", 0));
	}

}
