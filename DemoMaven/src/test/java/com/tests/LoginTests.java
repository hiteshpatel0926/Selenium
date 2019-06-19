package com.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.ApLoginPage;

import Utils.excelutils.ExcelUtil;

public class LoginTests extends TestBase {

	@BeforeTest
	public void setupTestData() throws Exception {
		// Set Test Data Excel and Sheet
		ExcelUtil.setExcelFileSheet("Sheet1");
	}

	@Test(priority = 0, description = "LOGIN") 
	
	public void TestLogin() throws Exception {
		driver.get("http://automationpractice.com/index.php");
		ApDefaultHomePage signPage = PageFactory.initElements(driver, ApDefaultHomePage.class);
		signPage.clickOnSingin();
		
		ApLoginPage loginpage = PageFactory.initElements(driver, ApLoginPage.class);
		ExcelUtil.setRowNumber(1);
		ExcelUtil.setColumnNumber(1);
		
//		loginpage.setEmail("gtl_test@thegatewaycorp.com");
//		loginpage.setPassword("Gtl@123");
		
		loginpage.setEmail(ExcelUtil.getCellData(1,1));
		loginpage.setPassword(ExcelUtil.getCellData(1,2));
		loginpage.clickOnLoginButton();
		
		System.out.println(ExcelUtil.getCellData(1,1));
		System.out.println(ExcelUtil.getCellData(1,2));

	}

}
