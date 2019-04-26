package Learnings;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://www.makemytrip.com/hotels/");
	    System.out.println(driver.getTitle());
		
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//input[@id='city']")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")).sendKeys("AHMEDABAD");
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")).sendKeys(Keys.ENTER);
	    
	    
	    driver.findElement(By.xpath("//input[@id='checkin']")).click();
	    
	    driver.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--start DayPicker-Day--selected']")).click();
	    driver.findElement(By.xpath("//div[@class='DayPicker-wrapper']//div[1]//div[3]//div[4]//div[7]")).click();
	    
	    driver.findElement(By.xpath("//button[@id='hsw_search_button']")).click();
	    
	    WebDriverWait wait=new WebDriverWait(driver,20);
	    
	    WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mmClose']")));
	    driver.findElement(By.xpath("//span[@class='mmClose']")).click();
	    
	    driver.findElement(By.xpath("//div[@id='Listing_hotel_0']//div//div[@class='makeFlex flexOne padding20 relative']")).click();
	    System.out.println(driver.getTitle());
	    
	}

}
