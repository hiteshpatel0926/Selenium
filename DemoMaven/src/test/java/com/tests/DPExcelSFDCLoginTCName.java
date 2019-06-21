package com.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.LoginSalesforce;

import Utils.excelutils.ExcelUtils;
import Utils.excelutils.ExcelUtilsTCName;

public class DPExcelSFDCLoginTCName extends TestBase{

	
	private String sTestCaseName;
	private int iTestCaseRow;

	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() throws Exception {
		
		// Setting up the Test Data Excel file
	 	ExcelUtilsTCName.setExcelFile("C:\\Users\\Hiteshkumar Patel\\git\\Selenium\\DemoMaven\\src\\test\\java\\resources\\TestData.xlsx","Sheet2");
		
	 	sTestCaseName = this.toString();

	  	// From above method we get long test case name including package and class name etc.

	  	// The below method will refine your test case name, exactly the name use have used

	  	sTestCaseName = ExcelUtilsTCName.getTestCaseName(this.toString());

	    // Fetching the Test Case row number from the Test Data Sheet

	    // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet

	 	iTestCaseRow = ExcelUtilsTCName.getRowContains(sTestCaseName,0);

	    Object[][] testObjArray = ExcelUtilsTCName.getTableArray("C:\\Users\\Hiteshkumar Patel\\git\\Selenium\\DemoMaven\\src\\test\\java\\resources\\TestData.xlsx","Sheet2",iTestCaseRow);

	    	return (testObjArray);
	 		
	}

	@Test(dataProvider = "LoginProvider")
	public void SFDCLogincheck(String un, String ps) {
		
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

	}
}