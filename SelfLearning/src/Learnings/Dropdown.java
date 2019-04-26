package Learnings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("http://www.qaclickacademy.com/practice.php");
	    System.out.println(driver.getTitle());
	    
	    Select s=new Select(driver.findElement(By.xpath("//select[@name='dropdown-class-example']")));
	    
	    s.selectByValue("option1");
	    Thread.sleep(2000);
	    s.selectByValue("option2");
	    Thread.sleep(2000);
	    s.selectByValue("option3");
	    
	    s.selectByIndex(1);
	    Thread.sleep(2000);
	    s.selectByIndex(2);
	    Thread.sleep(2000);
	    s.selectByIndex(3);
	}

}
