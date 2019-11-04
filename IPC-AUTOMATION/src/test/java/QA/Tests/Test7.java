package QA.Tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.G99HomePage;
import PageObjects.G99LoginPage;
import QA.resources.ConfigPropertyManager;

public class Test7 extends TestBase{
	public static Logger log = LogManager.getLogger(Test7.class.getName());
	
	@BeforeClass
	public void BeforeclassTest7() {
		System.out.println("This is Before Class Test7");
		Chromeinitialize();
	}
	
	
	
	@Test(priority = 18, description = "G99LoginPage")
	public void G99login() {
		
			test=extent.createTest("G99LoginPage");
			G99LoginPage lp = new G99LoginPage(driver);
			driver.get(ConfigPropertyManager.getInstance().G99URL());
			lp.setUserName(ConfigPropertyManager.getInstance().G99UName());
			
			lp.setPassword(ConfigPropertyManager.getInstance().G99PWord());
			
			lp.clickSubmit();
			
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				Assert.assertTrue(true);
				test.pass("G99Login test passed");
				log.info("G99Login test passed");
			}
			else
			{
				Assert.assertTrue(false);
				test.fail("G99Login test failed");
				log.error("G99Login test failed");
			}
		
			test=extent.createTest("G99ClickonAddCustomer");
			G99HomePage addcust=new G99HomePage(driver);
			addcust.clickAddNewCustomer();
			test.pass("G99ClickonAddCustomer test passed");
			
	}
	
	@AfterClass
	public void afterclassTest7() {
		System.out.println("This is After Class Test7");
		driver.quit();	
		}
	
	
}
