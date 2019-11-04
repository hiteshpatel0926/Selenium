package QA.Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.Navigation;

public class Test6 extends TestBase {
	
	@BeforeClass
	public void BeforeclassTest6() {
		System.out.println("This is Before Class Test6");
		Chromeinitialize();
	}
	
	
	@Test(priority = 17, description = "NavigationToProductATCpage")
	public void FinalProductCheck() throws InterruptedException {
		
		test = extent.createTest("NavigationToProductATCpage");
		Navigation Navigation = PageFactory.initElements(driver, Navigation.class);
		
		Navigation.NavigationToProductATCpage();
	}

	
	@AfterClass
	public void afterclassTest6() {
		System.out.println("This is After Class Test6");
		driver.quit();	
		}
	
}
