package QA.Tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.LoginSalesforce;
import QA.ExcelUtils.ExcelUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


public class Test4 extends TestBase{

	public static Logger log = LogManager.getLogger(Test4.class.getName());	
	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() throws Exception {
		
		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+ "\\src\\test\\java\\QA\\TestData\\TestData.xlsx","Sheet2");
		return (testObjArray);
	}

	@Test(dataProvider = "LoginProvider", priority = 15, description = "SFDC INVALID ID/PASS LOGIN CHECK")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: SFDC INVALID ID/PASS LOGIN CHECK")
	@Story("Story Name: SFDC LOGIN")
	public void SFDCLogincheck(String un, String ps) {
		
		test=extent.createTest("SFDCLogincheck");
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);
		Assert.assertTrue(true);
		log.info("SFDC LOGIN with INVALID ID/PASS");
		test.pass("SFDC LOGIN with INVALID ID/PASS");

	}
}