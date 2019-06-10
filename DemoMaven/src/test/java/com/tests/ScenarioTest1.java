package com.tests;

import org.apache.logging.log4j.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.pages.*;

public class ScenarioTest1 extends TestBase
{
	
	public static Logger log = LogManager.getLogger(ScenarioTest1.class.getName());	
	
	@Test (priority = 0)
	 public void Test1() throws Exception
	{
		driver.get("http://automationpractice.com/index.php");
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		log.info("Signin Button Click Successful");
		
		ApLoginPage loginpage = PageFactory.initElements(driver, ApLoginPage.class);
		loginpage.setEmail("gtl_test@thegatewaycorp.com");
		loginpage.setPassword("Gtl@123");
		loginpage.clickOnLoginButton();
		log.info("Login Successful");
		
		ApHomePage womanTab = PageFactory.initElements(driver, ApHomePage.class);
		womanTab.clickOnWomantab();
		log.info("Clicking on Woman Tab Successful");
		
		ApWomantab womanTab1 = PageFactory.initElements(driver, ApWomantab.class);
		womanTab1.clickOnTops();
		log.info("Clicking on Tops inside Woman Tab Successful");
		
		
		ApTopsTab womanTab2 = PageFactory.initElements(driver, ApTopsTab.class);
		womanTab2.clickOnTshirts();
		log.info("Clicking on Tshirts inside Woman Tab>>Tops Successful");
		
		ApClickonProduct product = PageFactory.initElements(driver, ApClickonProduct.class);
		product.clickOnProduct();
		log.info("Clicking on Product Successful");
		
		ApProductATC AddToCart = PageFactory.initElements(driver, ApProductATC.class);
		AddToCart.ATC();
		log.info("Product Added to Cart Successful");
		AddToCart.verification();
		log.info("Product verification Successful");
		
	}
	
	
}
