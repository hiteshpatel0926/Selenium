package com.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.LoginSalesforce;

import Utils.excelutils.ExcelUtil;
import Utils.excelutils.ExcelUtils;

public class ExcelSFDCLogin extends TestBase{

	@BeforeTest
	public void setupTestData() throws Exception {
		// Set Test Data Excel and Sheet
		ExcelUtil.setExcelFileSheet("Sheet1");
	}
	
	
	@Test
	public void SFDCLogincheck(String un, String ps) {
		
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

		int iRow,iCol;
		
		
			
			
		
		
	}
}