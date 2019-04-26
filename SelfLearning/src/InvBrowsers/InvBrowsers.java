package InvBrowsers;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;



public class InvBrowsers {

public void Chrome() {
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.get("https://www.google.com");
    System.out.println("Chrome invoked Successfully.!");
    System.out.println(driver.getTitle());
    driver.close();
    
}

public void Firefox() {
	
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\geckodriver.exe");
	
	File pathBinary = new File("C:\\Users\\hiteshpa\\AppData\\Local\\Mozilla Firefox\\Firefox.exe");
	FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
	DesiredCapabilities desired = DesiredCapabilities.firefox();
	FirefoxOptions options = new FirefoxOptions();
	desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
	WebDriver driver = new FirefoxDriver(options);
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.get("https://www.google.co.in/");
	
    System.out.println("Firefox invoked Successfully.!");
    driver.close();
	
}

public void IE() {
	System.setProperty("webdriver.ie.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\IEDriverServer.exe");
    WebDriver driver = new InternetExplorerDriver();
    driver.get("https://www.google.com");
    System.out.println("IE invoked Successfully.!");
    driver.close();
	       
	
}
		                  
}
	
