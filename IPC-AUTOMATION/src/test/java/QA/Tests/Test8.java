package QA.Tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.G99AddCustomerPage;
import PageObjects.G99HomePage;
import PageObjects.G99LoginPage;
import QA.resources.ConfigPropertyManager;

public class Test8 extends TestBase{
	
	public static Logger log = LogManager.getLogger(Test7.class.getName());
	
	@Test (invocationCount=2,priority = 19, description = "AddCustomer")
	public void AddCustomer() throws InterruptedException {
		
			test=extent.createTest("AddCustomer Test");
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
				
			G99HomePage addcust=new G99HomePage(driver);
			addcust.clickAddNewCustomer();
			test.pass("G99ClickonAddCustomer test passed");
			
			
			G99AddCustomerPage Acust = new G99AddCustomerPage(driver);
			
			Acust.custName("Pavan");
			Acust.custgender("male");
			Thread.sleep(2000);
			Acust.custdob("10","OCT","1985");
			Thread.sleep(5000);
			Acust.custaddress("INDIA");
			Acust.custcity("HYD");
			Acust.custstate("AP");
			Acust.custpinno("5000074");
			Acust.custtelephoneno("987890091");
			
			String email=randomestring()+"@gmail.com";
			Acust.custemailid(email);
			Acust.custpassword("abcdef");
			Acust.custsubmit();
			
			Thread.sleep(3000);
			
			log.info("validation started....");
			
			boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
			
			if(res==true)
			{
				Assert.assertTrue(true);
				log.info("test case passed....");
				
			}
			else
			{
				log.info("test case failed....");
				Assert.assertTrue(false);
			}
		
			
			
	}
	
}
