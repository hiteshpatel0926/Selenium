package SeleniumLearnings;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    
	    
	    driver.get("https://www.facebook.com/");
	    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("TEST");
	    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("TEST");
	    driver.findElement(By.xpath("//*[@id='u_0_2']")).click();
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	   
	}

}
