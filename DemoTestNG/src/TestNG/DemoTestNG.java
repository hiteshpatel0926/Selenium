package TestNG;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class DemoTestNG {
	
	public String baseUrl = "http://demo.guru99.com/test/newtours/";
    String driverPath = "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe";
    public WebDriver driver ; 
    
    @AfterTest                            //Jumbled
	  public void terminateBrowser(){
	      driver.close();
	  }
    
    
    @BeforeTest                            //Jumbled
    public void launchBrowser() {
    	System.out.println("launching Chrome browser"); 
    	System.setProperty("webdriver.chrome.driver", driverPath);
   	  	driver = new ChromeDriver();
   	  	driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get(baseUrl);
   	  	}
    
    @Test
    public void verifyHomepageTitle() {
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
  }
  
	 
  
}
