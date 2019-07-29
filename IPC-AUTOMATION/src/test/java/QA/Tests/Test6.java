package QA.Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import PageObjects.Navigation;

public class Test6 extends TestBase {
	
	@Test(priority = 17, description = "NavigationToProductATCpage")
	public void FinalProductCheck() throws InterruptedException {
		
		test = extent.createTest("NavigationToProductATCpage");
		Navigation Navigation = PageFactory.initElements(driver, Navigation.class);
		
		Navigation.NavigationToProductATCpage();
	}

}
