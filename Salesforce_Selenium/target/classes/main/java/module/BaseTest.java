package main.java.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.script.ScriptException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;



import main.java.framework.BrowserDriver;
import main.java.framework.CSVReporter;
import main.java.framework.CSV_IO;
/*import main.java.framework.DriverBase;*/
import main.java.framework.Framework;
import main.java.framework.TestDriver;
import main.java.framework.XMLDriver;

public class BaseTest extends BrowserDriver {
	public static int intM;
	
	//private final WebDriver driver = getDriver();
	
	@BeforeSuite
	public void init() throws IOException, ScriptException, InstantiationException, IllegalAccessException {

		String strBrowser = System.getProperty("browser");
		new TestDriver(System.getProperty("folderPath"));
		XMLDriver.createTestNGXML();
		
		BrowserDriver.strBrowser = strBrowser;
		BrowserDriver.getDriver(strBrowser);
		// Create object of TestNG Class
		TestNG runner = new TestNG();
		new CSVReporter();
		// Create a list of String
		List<String> suitefiles = new ArrayList<String>();
		// Add xml file which you have to execute
		suitefiles.add("testng.xml");
		// now set xml file for execution
		runner.setTestSuites(suitefiles);

	}

	@BeforeTest
	public void getTestName(final ITestContext testContext) {
		intM=0;
		Framework.result="Pass";
		TestDriver.testName = testContext.getName();
		System.out.println("=======Test Execution Starts:"+TestDriver.testName);
	}
	
	
	@AfterTest
	public void writeSummary(){
		String[] arrSummaryRecord={TestDriver.testName,Framework.result};
		try {
			CSV_IO.writeinCSV(CSVReporter.csvSummaryReport, arrSummaryRecord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@AfterSuite
	public void quitBrowser() {
		if (null != BrowserDriver.driver) {
			BrowserDriver.driver.quit();
			BrowserDriver.driver = null;
        }
	}
}
