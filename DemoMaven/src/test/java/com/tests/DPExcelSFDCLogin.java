package com.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.LoginSalesforce;

import Utils.excelutils.ExcelUtils;

public class DPExcelSFDCLogin extends TestBase{

	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\Hiteshkumar Patel\\git\\Selenium\\DemoMaven\\src\\test\\java\\resources\\TestData.xlsx","Sheet2");
		return (testObjArray);
	}

	@Test(dataProvider = "LoginProvider")
	public void SFDCLogincheck(String un, String ps) {
		
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

	}
}