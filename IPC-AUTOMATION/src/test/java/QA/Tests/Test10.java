package QA.Tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.G99AddCustomerPage;
import PageObjects.G99HomePage;
import PageObjects.G99LoginPage;
import QA.resources.ConfigPropertyManager;

public class Test10 extends TestBase {

	public static Logger log = LogManager.getLogger(Test7.class.getName());
	
	@BeforeMethod
	public void navigation()
	{
		test = extent.createTest("AddCustomer Test");
		G99LoginPage lp = new G99LoginPage(driver);
		driver.get(ConfigPropertyManager.getInstance().G99URL());
		lp.setUserName(ConfigPropertyManager.getInstance().G99UName());
		lp.setPassword(ConfigPropertyManager.getInstance().G99PWord());
		lp.clickSubmit();

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			test.pass("G99Login test passed");
			log.info("G99Login test passed");
		} else {
			Assert.assertTrue(false);
			test.fail("G99Login test failed");
			log.error("G99Login test failed");
		}
		
	}
	
	
	@Test(priority = 20, description = "AddCustomer")
	public void AddCustomer() throws InterruptedException, IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/QA/TestData/LoginData2.xlsx";

		int rownum = QA.ExcelUtils.XLUtils.getRowCount(path, "Sheet1");
		int colcount = QA.ExcelUtils.XLUtils.getCellCount(path, "Sheet1", 1);

		for (int i = 1; i <= rownum; i++) {

			
			G99HomePage addcust = new G99HomePage(driver);
			addcust.clickAddNewCustomer();
			test.pass("G99ClickonAddCustomer test passed");

			G99AddCustomerPage Acust = new G99AddCustomerPage(driver);
			System.out.println("Iteration == >" + i);
			Acust.custName(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 0));
			Acust.custgender(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 1));
			Thread.sleep(2000);
			Acust.custdob(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 2),
					QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 3),
					QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 4));
			Thread.sleep(5000);
			Acust.custaddress(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 5));
			Acust.custcity(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 6));
			Acust.custstate(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 7));
			Acust.custpinno(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 8));
			Acust.custtelephoneno(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 9));

			String email = randomestring() + "@gmail.com";
			Acust.custemailid(email);
			Acust.custpassword(QA.ExcelUtils.XLUtils.getCellData(path, "Sheet1", i, 10));
			Acust.custsubmit();

			Thread.sleep(3000);

			log.info("validation started....");

			boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

			if (res == true) {
				Assert.assertTrue(true);
				log.info("test case passed....");

			} else {
				log.info("test case failed....");
				Assert.assertTrue(false);
			}

		}
	}

}
