package com.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.LoginSalesforce;

public class DataProviderSFDCLogin extends TestBase{

	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "Guru99", "India" }, { "Krishna", "UK" }, { "Bhupesh", "USA" } };

	}

	@Test(dataProvider = "LoginProvider")
	public void SFDCLogincheck(String un, String ps) {
		
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

	}
}