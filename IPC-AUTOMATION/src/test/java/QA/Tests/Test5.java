package QA.Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.LoginSalesforce;
import QA.ExcelUtils.ExcelUtilsTCName;

public class Test5 extends TestBase{

	
	private String sTestCaseName;
	private int iTestCaseRow;

	@DataProvider(name = "LoginProvider")
	public Object[][] getDataFromDataprovider() throws Exception {
		
		// Setting up the Test Data Excel file
	 	ExcelUtilsTCName.setExcelFile(System.getProperty("user.dir")+ "\\src\\test\\java\\QA\\TestData\\TestData.xlsx","Sheet2");
		
	 	sTestCaseName = this.toString();

	  	// From above method we get long test case name including package and class name etc.

	  	// The below method will refine your test case name, exactly the name use have used

	  	sTestCaseName = ExcelUtilsTCName.getTestCaseName(this.toString());

	    // Fetching the Test Case row number from the Test Data Sheet

	    // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet

	 	iTestCaseRow = ExcelUtilsTCName.getRowContains(sTestCaseName,0);

	    Object[][] testObjArray = ExcelUtilsTCName.getTableArray(System.getProperty("user.dir")+ "\\src\\test\\java\\QA\\TestData\\TestData.xlsx","Sheet2",iTestCaseRow);

	    	return (testObjArray);
	 		
	}

	@Test(dataProvider = "LoginProvider",priority = 16, description = "SFDC LOGIN CHECK with DP")
	public void SFDCLogincheck(String un, String ps) {
		
		test=extent.createTest("SFDCLogincheck with TestCase Name");
		driver.get("https://Login.salesforce.com");
		LoginSalesforce sfdcloginpage = PageFactory.initElements(driver, LoginSalesforce.class);
		sfdcloginpage.SFDC(un, ps);

	}
}
