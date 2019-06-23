package SeleniumLearnings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Spicejet {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://www.spicejet.com");
	    System.out.println(driver.getTitle());
	    
	    driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_0']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[@id='ctl00_mainContent_ddl_originStation1_CTXTaction']")).click();
	    driver.findElement(By.xpath(("//a[@value='AMD']"))).click();
	    
	    Thread.sleep(5000);

	   // driver.findElement(By.cssSelector("span[id='ctl00_mainContent_ddl_destinationStation1_CTXTaction']")).click();
	    driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='DEL'][contains(text(),'Delhi (DEL)')]")).click();
	    
	    
	    driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();

	    if(driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5"))

	    {

	    System.out.println("its disabled");

	    }

	    else

	    {

	    System.out.println("its Enabled");

	    }

	    driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

	    driver.findElement(By.id("divpaxinfo")).click();

	    Thread.sleep(2000);

	    for(int i=1;i<5;i++)

	    {

	    driver.findElement(By.id("hrefIncAdt")).click();

	    }

	    driver.findElement(By.id("btnclosepaxoption")).click();

	   System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
	   driver.findElement(By.cssSelector("input[value='Search']")).click();

}



	    
	    
	    
	    
	    
	    
	    
	    
	}


