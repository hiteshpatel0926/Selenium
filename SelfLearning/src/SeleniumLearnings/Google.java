package SeleniumLearnings;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Google {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("http://www.google.com");
	    System.out.println(driver.getTitle());
	 
	    driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Hiteshkumar Patel");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
	 
        int total=driver.findElements(By.tagName("h3")).size();
        System.out.println(total);
        
        for(int i=1;i<total;i++)

        {
        	while(i==3)
        	{
        		Actions c=new Actions(driver);
        		c.moveToElement(driver.findElements(By.tagName("h3")).get(i)).sendKeys(Keys.DOWN.CONTROL).click().build().perform();
        		break;
        	}
        	
        }
        
        Set<String> ids=driver.getWindowHandles();
		Iterator<String> it=ids.iterator();
		
		String parentId=it.next();
		String childId=it.next();
		
        driver.switchTo().window(childId);
        System.out.println("The 3rd Link Opened in New Tab's Title is = "+driver.getTitle());
        
        
        driver.close();
        driver.switchTo().window(parentId);
        System.out.println(driver.getTitle());
        driver.close();
	}

}
