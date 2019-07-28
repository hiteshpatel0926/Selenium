package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_AddCustomerTest_4 extends BaseClass {


	@Test
	public void addNewCustomer() throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();

		
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData2.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		

		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		int i = 1;
		do {
		
			addcust.clickAddNewCustomer();
			logger.info("providing customer details....");

			addcust.custName(XLUtils.getCellData(path, "Sheet1", i, 0));
			addcust.custgender(XLUtils.getCellData(path, "Sheet1", i, 1));
			addcust.custdob(XLUtils.getCellData(path, "Sheet1", i, 2), XLUtils.getCellData(path, "Sheet1", i, 3),
					XLUtils.getCellData(path, "Sheet1", i, 4));
			Thread.sleep(5000);
			addcust.custaddress(XLUtils.getCellData(path, "Sheet1", i, 5));
			addcust.custcity(XLUtils.getCellData(path, "Sheet1", i, 6));
			addcust.custstate(XLUtils.getCellData(path, "Sheet1", i, 7));
			addcust.custpinno(XLUtils.getCellData(path, "Sheet1", i, 8));
			addcust.custtelephoneno(XLUtils.getCellData(path, "Sheet1", i, 9));

			String email = randomestring() + "@gmail.com";
			addcust.custemailid(email);
			addcust.custpassword(XLUtils.getCellData(path, "Sheet1", i, 10));
			addcust.custsubmit();

			Thread.sleep(3000);

			logger.info("validation started....");

			if (driver.getPageSource().contains("Customer Registered Successfully!!!")) {
				Assert.assertTrue(true);
				logger.info("test case passed....");

			} else {
				logger.info("test case failed....");
				captureScreen(driver, "addNewCustomer");
				Assert.assertTrue(false);
			}
			rownum--;
			i++;
		} while (rownum == 0);
	}
}
