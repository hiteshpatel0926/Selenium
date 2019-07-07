package com.tests;
import static org.testng.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.pages.*;

public class ScenarioTest2 extends TestBase
{

	public static Logger log = LogManager.getLogger(ScenarioTest2.class.getName());	
	
	@Test(enabled = false)
	 public void Test2() throws Exception
	{
		
		driver.get("https://www.google.com/");
		GoogleSearchPage searchpage=PageFactory.initElements(driver, GoogleSearchPage.class);
		searchpage.search();
		log.info("Google Search Successful");
		
		GoogleSearchResultsPage resultpage=PageFactory.initElements(driver, GoogleSearchResultsPage.class);
		resultpage.clickon3rdresult();
		log.info("Clicking on 3rd Result from Google Search Successful");
		
		Google3rdResultTitle result=PageFactory.initElements(driver, Google3rdResultTitle.class);
		result.TitleVerification();
		log.info("Title Verification of 3rd Result from Google Search Successful");
								
	}
	
	@Test
	public void Test3() {
		driver.get("https://news.google.com/");
		assertEquals(driver.getTitle(),"Google");
		
	}
}
