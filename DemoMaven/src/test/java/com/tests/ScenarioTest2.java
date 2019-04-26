package com.tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.pages.*;

public class ScenarioTest2 extends TestBase
{

	@Test (priority = 1)
	 public void Test2() throws Exception
	{
		
		driver.get("https://www.google.com/");
		GoogleSearchPage searchpage=PageFactory.initElements(driver, GoogleSearchPage.class);
		searchpage.search();
		
		GoogleSearchResultsPage resultpage=PageFactory.initElements(driver, GoogleSearchResultsPage.class);
		resultpage.clickon3rdresult();
		
		Google3rdResultTitle result=PageFactory.initElements(driver, Google3rdResultTitle.class);
		result.titleverification();
								
	}
}
