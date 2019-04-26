package Learnings;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleTabs {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.qaclickacademy.com/practice.php");
		System.out.println("This is Parent Window ==> "+driver.getTitle());

		System.out.println("TOTAL NUMBER OF LINKS IN THE PAGE >> "+driver.findElements(By.tagName("a")).size());
		WebElement footer=driver.findElement(By.xpath("//div[@id='gf-BIG']"));
		
		System.out.println("TOTAL NUMBER OF LINKS IN THE FOOTER >> "+footer.findElements(By.tagName("a")).size());
		
		WebElement footerCol1=driver.findElement(By.xpath("//td[1]//ul[1]"));
		System.out.println("TOTAL NUMBER OF LINKS IN THE COLUMN1 of FOOTER >> "+footerCol1.findElements(By.tagName("a")).size());
		String ctrlplusclick = Keys.chord(Keys.CONTROL,Keys.ENTER);
		
		for(int i=1;i<footerCol1.findElements(By.tagName("a")).size();i++)
		{
			
			footerCol1.findElements(By.tagName("a")).get(i).sendKeys(ctrlplusclick);
			Thread.sleep(5000);
		}
			Set<String> ids=driver.getWindowHandles();
			Iterator<String> it=ids.iterator();
			
			while(it.hasNext())
			{
				driver.switchTo().window(it.next());
				System.out.println(driver.getTitle());
			}
		
		driver.quit();		
	}

}
