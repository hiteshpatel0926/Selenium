package SeleniumLearnings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTables {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://www.toolsqa.com/automation-practice-table/");
	    System.out.println(driver.getTitle());
		
	    String Beforexpath="//*[@id=\"content\"]/table/tbody/tr[4]/td[";
	    String Afterxpath = "]";
	    
	    for(int i=1;i<=6;i++)
	    {
	    
	    WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[4]/td["+i+"]"));
	       
	    element.getText();
	    
	    System.out.println(element.getText());
	    }
	    
	    for(int i=1;i<=6;i++)
	    {
	    	WebElement element = driver.findElement(By.xpath(Beforexpath+i+Afterxpath));
		    element.getText();
	 	    System.out.println(element.getText());
	    	
	    }
	    
	}

}
