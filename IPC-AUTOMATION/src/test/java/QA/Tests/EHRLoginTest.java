package QA.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.ApDefaultHomePage;
import QA.resources.ConfigPropertyManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class EHRLoginTest extends TestBase {

	
	@BeforeClass
	public void BeforeclassTest() {
		System.out.println("This is Before Class EHRLOGINTEST");
		IEinitialize();
	}
	
	@Test(priority = 8, description = "Login")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHR_Login_Test() throws InterruptedException {

		test = extent.createTest("EHR_Login_Test");
	
		driver.get(ConfigPropertyManager.getInstance().getEHRURL());

		Actions actions = new Actions(driver);

		Thread.sleep(1000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

			driver.findElement(By.id("txtOrgName")).sendKeys(ConfigPropertyManager.getInstance().EHROrgCode());
			driver.findElement(By.id("txtPassword")).sendKeys(ConfigPropertyManager.getInstance().EHROrgPWord());
			driver.findElement(By.id("btnLogin")).click();
			Thread.sleep(3000);
			break;
		}

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

			for (String winHandle2 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle2);

				driver.findElement(By.id("txtUname")).sendKeys(ConfigPropertyManager.getInstance().EHRUName());
				driver.findElement(By.id("txtPassword")).sendKeys(ConfigPropertyManager.getInstance().EHRPWord());
				driver.findElement(By.id("btnLogin")).click();
				break;
			}

			break;
		}

		Thread.sleep(3000);

		actions.keyDown(Keys.ALT);
		actions.sendKeys(Keys.F4);
		actions.keyUp(Keys.ALT);
		actions.perform();

		for (String winHandle3 : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle3);
			break;
		}

		try {
			for (String winHandle4 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle4);
				String title = driver.getTitle();
				String screen_name_1 = "Patient Selection";
				String screen_name_2 = "Digital Dashboard";
				if (title.equalsIgnoreCase(screen_name_1) || title.equalsIgnoreCase(screen_name_2)) {
					driver.close();
					driver.switchTo().defaultContent();
					break;
				}
			}

			Thread.sleep(1000);

		} catch (Exception e) {
		}

	}
	
	@Test(priority = 9, description = "Login2")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHRLoginTest12() throws InterruptedException {
		
		test = extent.createTest("EHRLoginTest12");
		System.out.println("EHRLoginTest12 PASS");
		Assert.assertTrue(true);

	}
	
	
	
	@Test(priority = 10, description = "Login3")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: LOGIN TO EHR")
	@Story("Story Name: EHR")
	public void EHRLoginTest13() throws InterruptedException {
		
		test = extent.createTest("EHRLoginTest13");
		Assert.assertTrue(false);
		
	
	}
	
	@AfterClass
	public void afterclassTest1() {
		System.out.println("This is After Class EHRLOGINTEST");
		driver.quit();	
		}

}
