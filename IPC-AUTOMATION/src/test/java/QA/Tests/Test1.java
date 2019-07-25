package QA.Tests;


import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import PageObjects.ApClickonProduct;
import PageObjects.ApDefaultHomePage;
import PageObjects.ApHomePage;
import PageObjects.ApLoginPage;
import PageObjects.ApProductATC;
import PageObjects.ApTopsTab;
import PageObjects.ApWomantab;
import QA.resources.ConfigPropertyManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Test1 extends TestBase
{
	
	
	public static Logger log = LogManager.getLogger(Test1.class.getName());	
	
	@Test(priority = 1, description = "Navigate to URL")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Navigate to URL")
	@Story("Story Name: NAVIGATION")
	
	public void NAVIGATE_TO_URL_CLICK_SIGN() throws Exception
	{
		test = extent.createTest("NAVIGATE_TO_URL_CLICK_SIGN");
		driver.get(ConfigPropertyManager.getInstance().getURL());
		log.info("URL Success");
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		log.info("Signin Button Click Successful");
	}
	
	@Test(priority = 2, description = "LOGIN")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN VALIDATION")
	@Story("Story Name: LOGIN")
	public void ENTER_UNAME_PWRD_LOGIN() throws Exception{
		test = extent.createTest("ENTER_UNAME_PWRD_LOGIN");
		ApLoginPage loginpage = PageFactory.initElements(driver, ApLoginPage.class);
		loginpage.setEmail(ConfigPropertyManager.getInstance().getUsername());
		log.info("USERNAME Fetch Success");
		test.pass("USERNAME Fetch Success");
		loginpage.setPassword(ConfigPropertyManager.getInstance().getPassword());
		log.info("PASSWORD Fetch Success");
		test.pass("PASSWORD Fetch Success");
		loginpage.clickOnLoginButton();
		log.info("Login Successful");
		test.pass("LOGIN Successful");
	}	
		
	@Test(priority = 3, description = "CLICK ON WOMAN TAB")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: WOMAN Tab")
	@Story("Story Name: PRODUCT")
	public void CLICK_ON_WOMAN_TAB() throws Exception{
		test = extent.createTest("CLICK_ON_WOMAN_TAB");
		ApHomePage womanTab = PageFactory.initElements(driver, ApHomePage.class);
		womanTab.clickOnWomantab();
		log.info("Clicking on Woman Tab Successful");
		test.pass("Clicking on Woman Tab Successful");
	}
	
	@Test(priority = 4, description = "NAVIGATE TO TOPS")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: WOMAN >> TOPS")
	@Story("Story Name: PRODUCT ADD INTO CART")
	public void CLICK_ON_WOMAN_TAB_TOPS() throws Exception{
		test = extent.createTest("CLICK_ON_WOMAN_TAB_TOPS");
		ApWomantab womanTab1 = PageFactory.initElements(driver, ApWomantab.class);
		womanTab1.clickOnTops();
		log.info("Clicking on Tops inside Woman Tab Successful");
		test.pass("Clicking on Tops inside Woman Tab Successful");
		test.createNode("CLICK_ON_WOMAN_TAB_TOPS_SUBTEST1");
		test.createNode("CLICK_ON_WOMAN_TAB_TOPS_SUBTEST2");
		test.info("THIS IS INFO TEST");
		
	}
	
	@Test(priority = 5, description = "CLICK ON TSHIRTS")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: TSHIRT SECTION")
	@Story("Story Name: PRODUCT ADD INTO CART")
	public void CLICK_ON_WOMAN_TSHIRTS() throws Exception{
		test = extent.createTest("CLICK_ON_WOMAN_TSHIRTS");
		ApTopsTab womanTab2 = PageFactory.initElements(driver, ApTopsTab.class);
		womanTab2.clickOnTshirts();
		log.info("Clicking on Tshirts inside Woman Tab>>Tops Successful");
		test.pass("Clicking on Tshirts inside Woman Tab>>Tops Successful");
	}	
	
	@Test(priority = 6, description = "CLICK ON PRODUCT TEST")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: CLICK ON Product")
	@Story("Story Name: PRODUCT ADD INTO CART")
	public void CLICK_ON_PRODUCT() throws Exception{
		test = extent.createTest("CLICK_ON_PRODUCT");
		ApClickonProduct product = PageFactory.initElements(driver, ApClickonProduct.class);
		product.clickOnProduct();
		log.info("Clicking on Product Successful");
		test.pass("Clicking on Product Successful");
	}	
	
	@Test(priority = 7, description = "ADD TO CART")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: ADD TO CART TEST")
	@Story("Story Name: PRODUCT ADD INTO CART")
	public void PRODUCT_ADD_TO_CART() throws Exception{
		test = extent.createTest("CLICK_ON_PRODUCT");
	ApProductATC AddToCart = PageFactory.initElements(driver, ApProductATC.class);
		AddToCart.ATC();
		log.info("Product Added to Cart Successful");
		test.pass("Product Added to Cart Successful");
		AddToCart.verification();
		log.info("Product verification Successful");
		test.info("Product verification Successful");
		
	}
	
	
}
