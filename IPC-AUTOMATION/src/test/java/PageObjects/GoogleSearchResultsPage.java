package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSearchResultsPage {

	WebDriver driver;

	public GoogleSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.TAG_NAME, using = "h3")WebElement searchtag;

	public void clickon3rdresult() 
	{
		
		int total=driver.findElements(By.tagName("h3")).size();
        //System.out.println(total);
        
        for(int i=1;i<total;i++)

        {
        	while(i==3)
        	{
        		Actions c=new Actions(driver);
        		c.moveToElement(driver.findElements(By.tagName("h3")).get(i)).click().build().perform();
        		break;
        	}
        	
        }
        		
	}
	
}
