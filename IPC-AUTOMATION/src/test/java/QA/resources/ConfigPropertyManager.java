package QA.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertyManager {

	String ConfigPath = System.getProperty("user.dir") + "\\Config\\Config.properties";
	private static ConfigPropertyManager instance;
	private static final Object lock = new Object();
	private static String propertyFilePath = System.getProperty("user.dir") + "\\Config\\Config.properties";
	private static String URL;
	private static String UName;
	private static String PWord;
	private static String Browser;
	private static String ScreenshotPath;
	private static String G99URL;
	private static String G99UName;
	private static String G99PWord;
	private static String EHROrgCode;
	private static String EHROrgPWord;
	private static String EHRUName;
	private static String EHRPWord;
	private static String EHRURL;

	// Create a Singleton instance. We need only one instance of Property Manager.
	public static ConfigPropertyManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new ConfigPropertyManager();
				instance.loadData();
			}
		}
		return instance;
	}

	// Get all configuration data and assign to related fields.
	private void loadData() {
		// Declare a properties object
		Properties prop = new Properties();

		// Read configuration.properties file
		try {
			prop.load(new FileInputStream(propertyFilePath));
			// prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
		} catch (IOException e) {
			System.out.println("Configuration properties file cannot be found");
		}

		// Get properties from configuration.properties
		URL = prop.getProperty("URL");
		UName = prop.getProperty("UName");
		PWord = prop.getProperty("PWord");
		Browser = prop.getProperty("Browser");
		ScreenshotPath = prop.getProperty("ScreenshotPath");
		G99URL = prop.getProperty("G99URL");
		G99UName = prop.getProperty("G99UName");
		G99PWord = prop.getProperty("G99PWord");
		EHROrgCode = prop.getProperty("EHROrgCode");
		EHROrgPWord = prop.getProperty("EHROrgPWord");
		EHRUName = prop.getProperty("EHRUName");
		EHRPWord = prop.getProperty("EHRPWord");
		EHRURL = prop.getProperty("EHRURL");

	}

	public String getURL() {
		return URL;
	}

	public String getUsername() {
		return UName;
	}

	public String getPassword() {
		return PWord;
	}

	public String getBrowser() {
		return Browser;
	}

	public String getScreenshotPath() {
		return ScreenshotPath;
	}

	public String G99URL() {
		return G99URL;
	}

	public String G99UName() {
		return G99UName;
	}

	public String G99PWord() {
		return G99PWord;
	}

	public String getEHRURL() {
		return EHRURL;
	}

	public String EHROrgCode() {
		return EHROrgCode;
	}

	public String EHROrgPWord() {
		return EHROrgPWord;
	}

	public String EHRUName() {
		return EHRUName;
	}

	public String EHRPWord() {
		return EHRPWord;
	}

}
