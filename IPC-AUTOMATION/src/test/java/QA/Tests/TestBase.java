package QA.Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import QA.resources.ConfigPropertyManager;

public class TestBase {
	public static WebDriver driver = null;
	public WebDriverWait wait;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static final String testDataExcelFileName = "testdata.xlsx";
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	
	@BeforeTest
	public void initialize() throws IOException {

		if (ConfigPropertyManager.getInstance().getBrowser().equals("Chrome")) {
			String driverpath = System.getProperty("user.dir") + "\\BrowserDrivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverpath);
			driver = new ChromeDriver();
			// To maximize browser
			driver.manage().window().maximize();
			// Implicit wait
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// To open WebApplication
			// driver.get("http://automationpractice.com/index.php");
		}

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/MyOwnReport_" + timeStamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "WINDOW10");
		extent.setSystemInfo("Host Name", "HITESH");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "HITESHKUMAR PATEL");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Execution Report");
		htmlReporter.config().setReportName("EHR AUTOMATION");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = Screencapture(driver, result.getName());
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.log(Status.FAIL, result.getThrowable());
			test.fail(result.getThrowable());
			test.addScreenCaptureFromPath(screenShotPath);
			test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterTest
	// Test cleanup
	public void TeardownTest() {
		extent.flush();
		TestBase.driver.quit();

	}

	public static String Screencapture(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + "_" + timeStamp
				+ ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

}
