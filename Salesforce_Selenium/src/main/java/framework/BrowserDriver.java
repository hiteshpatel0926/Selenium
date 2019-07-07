package main.java.framework;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserDriver {

	private static String  Chrome_Profile_Path = "";
	public static WebDriver driver;
	public static String sDriver;
	public static String strBrowser;

	public static WebDriver getDriver(String strDriver) {

		WebDriver objDriver = null;
		String sUserName = System.getProperty("user.name");
		if (strDriver.equalsIgnoreCase("chrome")) {
			
			String strBrowserDriver = TestDriver.folderpath + System.getProperty("chromeDriverName");
			System.setProperty("webdriver.chrome.driver", strBrowserDriver);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--start-maximized");
			Chrome_Profile_Path="C:\\Users\\"+sUserName+"\\AppData\\Local\\Google\\Chrome\\User Data";
			/* Adding Chrome profile by .addArguments to objChrome_Profile */
			 options.addArguments("user-data-dir=" + Chrome_Profile_Path);

			/*
			 * Initializing the Webdriver instance (i.e. driver) to open Chrome Browser and
			 * passing the Chrome Profile as argument
			 */
			objDriver = new ChromeDriver(options);

			// DesiredCapabilities capability = DesiredCapabilities.chrome();

			// System.setProperty("webdriver.chrome.driver", "path to chromedriver.exe");
			// capability.setBrowserName("chrome");
			// capability.setPlatform(PlatformAndEnvironmentSetUp.platformSetUp);

		} else if (strDriver.equalsIgnoreCase("IE")) {
			String strBrowserDriver = TestDriver.folderpath + "//BrowserDrivers//IEDriverServer.exe";
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			File file = new File(strBrowserDriver);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			objDriver = new InternetExplorerDriver(capabilities);

			// objDriver=new InternetExplorerDriver();
		} else if (strDriver.equalsIgnoreCase("firefox")) {
			objDriver = new FirefoxDriver();
		}
		driver = objDriver;
		return objDriver;
	}
}