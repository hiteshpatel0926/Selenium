package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import QA.Tests.TestBase;
import QA.resources.ConfigPropertyManager;


public class Navigation{
	
	WebDriver driver;
	 
    public Navigation(WebDriver driver)
    { 
            this.driver=driver; 
    }

    public void NavigationToProductATCpage() throws InterruptedException {
    	
    	
    	driver.get(ConfigPropertyManager.getInstance().getURL());
		
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		
		
		ApLoginPage loginpage = PageFactory.initElements(driver, ApLoginPage.class);
		loginpage.setEmail(ConfigPropertyManager.getInstance().getUsername());
	

		loginpage.setPassword(ConfigPropertyManager.getInstance().getPassword());

		loginpage.clickOnLoginButton();

		ApHomePage womanTab = PageFactory.initElements(driver, ApHomePage.class);
		womanTab.clickOnWomantab();
		
		ApWomantab womanTab1 = PageFactory.initElements(driver, ApWomantab.class);
		womanTab1.clickOnTops();
		
		ApTopsTab womanTab2 = PageFactory.initElements(driver, ApTopsTab.class);
		womanTab2.clickOnTshirts();
    
		ApClickonProduct product = PageFactory.initElements(driver, ApClickonProduct.class);
		product.clickOnProduct();
		
		ApProductATC AddToCart = PageFactory.initElements(driver, ApProductATC.class);
		AddToCart.ATC();
		AddToCart.verification();
		
    }
    
    
	
    

}
