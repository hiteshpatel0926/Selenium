package QA.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertyManager {
	
	String ConfigPath = System.getProperty("user.dir") + "\\Config\\Config.properties"; 
    private static ConfigPropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+"\\Config\\Config.properties";
    private static String URL;
    private static String UName;
    private static String PWord;
    private static String Browser;
    private static String ScreenshotPath;
    //Create a Singleton instance. We need only one instance of Property Manager.
    public static ConfigPropertyManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new ConfigPropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }
 
    //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();
 
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
 
        //Get properties from configuration.properties
        URL = prop.getProperty("URL");
        UName = prop.getProperty("UName");
        PWord = prop.getProperty("PWord");
        Browser = prop.getProperty("Browser");
        ScreenshotPath = prop.getProperty("ScreenshotPath");
    }
 
    public String getURL () {
      return URL;
    }
 
    public String getUsername () {
        return UName;
    }
 
    public String getPassword () {
        return PWord;
    }
    
    public String getBrowser () {
        return Browser;
    }

    public String getScreenshotPath() {
    	return ScreenshotPath;
    }
}

