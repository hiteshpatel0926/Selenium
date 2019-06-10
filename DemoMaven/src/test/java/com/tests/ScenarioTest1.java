package com.tests;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.pages.*;

public class ScenarioTest1 extends TestBase
{
	
	Logger logger = (Logger) LogManager.getLogger(ScenarioTest1.class.getSimpleName());
	
	@Test (priority = 0)
	 public void Test1() throws Exception
	{
		driver.get("http://automationpractice.com/index.php");
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		logger.info("SignIn Button Click Successful");
		
		ApLoginPage loginpage = PageFactory.initElements(driver, ApLoginPage.class);
		loginpage.setEmail("gtl_test@thegatewaycorp.com");
		loginpage.setPassword("Gtl@123");
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
