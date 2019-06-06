package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.ApDefaultHomePage;
import com.pages.ApLoginPage;

import Utils.excelutils.ExcelUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginTests extends BaseTest {

	@BeforeTest
	public void setupTestData() throws Exception {
		// Set Test Data Excel and Sheet
		System.out.println("************Setup Test Level Data**********");
		ExcelUtil.setExcelFileSheet("Sheet1");
	}

	@Test(priority = 0, description = "LOGIN")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Login with Correct User Name & Password")
	@Story("Valid username and password login test")
	
	public void Test1() throws Exception {
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
