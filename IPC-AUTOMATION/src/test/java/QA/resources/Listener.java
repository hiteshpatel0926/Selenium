package QA.resources;

import org.mortbay.log.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;



public class Listener implements ITestListener {

	WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		System.out.println("===================================================================");
		System.out.println(result.getName() + " Test Case Started");
		System.out.println("===================================================================");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("===================================================================");
		System.out.println(result.getName() + " Test Successfully Finished");
		System.out.println("===================================================================");
			
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("===================================================================");
		System.out.println(result.getName() + " Test Case failed");
		System.out.println("===================================================================");
						
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("===================================================================");
		System.out.println(result.getName() + " Test Case Skipped");
		System.out.println("===================================================================");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("===================================================================");
		System.out.println("Test Failed but within success percentage" + result.getName());
		System.out.println("===================================================================");

	}

	public void onStart(ITestContext context) {
		System.out.println("===================================================================");
		Log.info("Test Started");
		System.out.println("This is onStart method" + context.getOutputDirectory());
		System.out.println("===================================================================");
	}

	public void onFinish(ITestContext context) {
		System.out.println("===================================================================");
		Log.info("Test Finished");
		System.out.println("This is onFinish Passed method" + context.getPassedTests());
		System.out.println("This is onFinish Failed method" + context.getFailedTests());
		System.out.println("===================================================================");

	}

}
