package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//rahulonlinetutor@gmail.com
public class portalHomePage {

	
	public WebDriver driver;
	
	By searchBox=By.xpath("//input[@id='search-courses']");
	
	
	
	
	
	public portalHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		
	}




	
	public WebElement getSearchBox()
	{
		return driver.findElement(searchBox);
	}
	
	
	
}
