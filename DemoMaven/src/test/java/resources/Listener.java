package resources;

import java.io.IOException;

import org.mortbay.log.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tests.TestBase;

public class Listener implements ITestListener {
	
	TestBase tb=new TestBase();
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+" Test Case Started");
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName()+" Test Case Success");
		
	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println(result.getName()+" Test Case failed");
		
		try {
			tb.getscreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		Log.info("Test Started");
		
	}

	public void onFinish(ITestContext context) {
		Log.info("Test Finished");
		
	}

}
