package QA.Tests;

import static org.testng.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Test3 extends TestBase {

	
	public static Logger log = LogManager.getLogger(Test3.class.getName());

	@Test(priority = 11, description = "Google Search")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Google Search Validation")
	@Story("Story Name: Google Search")
	public void NAVIGATE_TO_GOOGLE() throws Exception {
		test = extent.createTest("NAVIGATE_TO_GOOGLE");
		driver.get("https://www.google.com/");
		log.info("Navigation to Google Successful");
		GoogleSearchPage searchpage = PageFactory.initElements(driver, GoogleSearchPage.class);
		searchpage.search();
		log.info("Google Search Successful");
	}

	@Test(priority = 12, description = "Click on 3rd Result of Search")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Click on 3rd Result of Search")
	@Story("Story Name: Google Search")
	public void CLICK_ON_3rdRESULT() throws Exception

	{
		test = extent.createTest("CLICK_ON_3rdRESULT");
		GoogleSearchResultsPage resultpage = PageFactory.initElements(driver, GoogleSearchResultsPage.class);
		resultpage.clickon3rdresult();
		log.info("Clicking on 3rd Result from Google Search Successful");
		test.info("Clicking on 3rd Result from Google Search Successful");
	}

	@Test(priority = 13, description = "3rd RESULT TITLE VERIFY")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: 3rd RESULT TITLE VERIFY")
	@Story("Story Name: Google Search")
	public void RESULT_TITLE_VERIFY() throws Exception {
		test = extent.createTest("RESULT_TITLE_VERIFY");
		Google3rdResultTitle result = PageFactory.initElements(driver, Google3rdResultTitle.class);
		result.TitleVerification();
		log.info("Title Verification of 3rd Result from Google Search Successful");
		test.pass("Title Verification of 3rd Result from Google Search Successful");
	}

	@Test(priority = 14, description = "Google NEWS Title Verify")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: 3rd RESULT TITLE VERIFY")
	@Story("Story Name: Google News")
	public void GoogleNews_TitleVerify() {
		test = extent.createTest("GoogleNews_TitleVerify");
		driver.get("https://news.google.com/");
		if(driver.getTitle().equalsIgnoreCase("Google"))
		{
		log.info("Title Verification Successful");
		test.pass("Title Verification Successful");
		}
		else
		{
		log.info("Title Verification FAIL");
		test.fail("Title Verification FAIL");
		Assert.assertTrue(false);
		}
		
	}
}
