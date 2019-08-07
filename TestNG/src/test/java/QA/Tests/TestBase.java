package QA.Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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
	public void BaseClassMethod1() throws IOException {

		System.out.println("Before Test Base Class Method");

	}
	
	@BeforeClass
	public void BaseClassMethod2() throws IOException {

		System.out.println("Before Class Base Class Method");

	}
	
	@BeforeMethod
	public void BaseClassMethod3() throws IOException {

		System.out.println("Before Method Base Class Method");

	}
	
	@AfterClass
	public void BaseClassMethod4() throws IOException {

		System.out.println("After Class Base Class Method");

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		
		System.out.println("After Method Base Class Method");
	}

	@AfterTest
	
	public void TeardownTest() {
		System.out.println("After Test Base Class Method");

	}

	public static String Screencapture(String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + "_" + timeStamp
				+ ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}
	
}
