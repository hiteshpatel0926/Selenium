package QA.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.LogStatus;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


public class Test2 extends TestBase{
	
	public static Logger log = LogManager.getLogger(Test2.class.getName());	
	
	@BeforeClass
	public void BeforeclassTest2() {
		System.out.println("This is Before Class Test2");
		Chromeinitialize();
	}
	
	@Test(priority = 9, description = "PATIENT DEMOGRAPHIC")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: PATIENT DEMOGRAPHIC")
	@Story("Story Name: PATIENTS INFORMATION")
	public void PATINET_DEMO_CREATION() {
		test = extent.createTest("EHR PATIENT CREATION");
		log.info("EHR PATIENT CREATION SUCCESS");
		test.pass("EHR PATIENT CREATION SUCCESS");
		Assert.assertTrue(true);
			
	}

	@Test(priority = 10, description = "PATIENT DEMOGRAPHIC UPDATE")
	@Severity(SeverityLevel.TRIVIAL)
	@Description("Test Case Description: PATIENT DEMOGRAPHIC UPDATE")
	@Story("Story Name: PATIENT INFORMATION")
	public void PATINET_DEMO_UPDATE() {
		test = extent.createTest("PATIENT DEMOGRAPHIC UPDATE");
		log.error("PATIENT DEMOGRAPHIC UPDATE FAILED");
		test.fail("PATIENT DEMOGRAPHIC UPDATE FAILED");
		driver.get("https://www.google.com");
		Assert.assertTrue(false);
	}
	
	@AfterClass
	public void afterclassTest2() {
		System.out.println("This is After Class Test2");
		driver.quit();	
		}


}
