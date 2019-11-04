package QA.Tests;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.ApDefaultHomePage;
import PageObjects.ApHomePage;
import PageObjects.ApLoginPage;
import QA.resources.ConfigPropertyManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class EHRLoginTest2 extends TestBase {
	
	public static Logger log = LogManager.getLogger(EHRLoginTest2.class.getName());

	@BeforeClass
	public void BeforeclassTest2() {
		System.out.println("This is Before Class EHRLOGINTEST2");
		Chromeinitialize();
	}
	
	
	@Test(priority = 11, description = "Login1")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHRLoginTest21() throws InterruptedException {

		test = extent.createTest("NAVIGATE_TO_URL_CLICK_SIGN");
		driver.get(ConfigPropertyManager.getInstance().getURL());
		log.info("URL Success");
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		log.info("Signin Button Click Successful");

	}
	
	@Test(priority = 12, description = "Login2")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHRLoginTest22() throws InterruptedException {
		
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
	
	
	
	@Test(priority = 13, description = "Login3")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHRLoginTest23() throws InterruptedException {
		
		test = extent.createTest("CLICK_ON_WOMAN_TAB");
		ApHomePage womanTab = PageFactory.initElements(driver, ApHomePage.class);
		womanTab.clickOnWomantab();
		log.info("Clicking on Woman Tab Successful");
		test.pass("Clicking on Woman Tab Successful");
		
					
	}
	

	@AfterClass
	public void afterclassTest2() {
		System.out.println("This is After Class EHRLOGINTEST2");
		driver.quit();
	}
}
