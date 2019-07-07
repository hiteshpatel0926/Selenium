package com.tests;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestBase {
	public static WebDriver driver = null;
	public WebDriverWait wait;

	public static final String testDataExcelFileName = "testdata.xlsx";

	@BeforeTest

	public void initialize() throws IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// To maximize browser
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// To open WebApplication
		// driver.get("http://automationpractice.com/index.php");

	}

	@AfterTest
	// Test cleanup
	public void TeardownTest() {
		TestBase.driver.quit();
	}

	public void getscreenshot(String result) throws IOException {

		File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotpath = System.getProperty("user,dir") + "/Screenshots/" + result + "_" + getCurrentDateTime()+ ".png";
		try {
			FileHandler.copy(SrcFile, new File("./Screenshots/" + result + "_" + getCurrentDateTime() + ".png"));
			System.out.println("Screenshot Captured");

		} catch (IOException e) {
			System.out.println("Unable to Capture Screenshot" + e.getMessage());
		}

	}

	public static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return formater.format(calendar.getTime());

	}

}
