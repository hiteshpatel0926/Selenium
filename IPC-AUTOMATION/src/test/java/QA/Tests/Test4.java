package QA.Tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.LoginSalesforce;
import QA.ExcelUtils.ExcelUtils;


public class Test4 extends TestBase{

	
	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() throws Exception {
		
		Object[][] testObjArray = ExcelUtils.getTableArray(System.getProperty("user.dir")+ "\\src\\test\\java\\QA\\TestData\\TestData.xlsx","Sheet2");
		return (testObjArray);
	}

	@Test(dataProvider = "LoginProvider")
	public void SFDCLogincheck(String un, String ps) {
		
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

	}
}