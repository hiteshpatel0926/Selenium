package com.tests;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	public static WebDriver driver = null;
	public WebDriverWait wait;
	
	public static final String testDataExcelFileName = "testdata.xlsx";
	
	@BeforeSuite

	public void initialize() throws IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// To maximize browser
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// To open WebApplication
		//driver.get("http://automationpractice.com/index.php");

	}

	@AfterSuite
	// Test cleanup
	public void TeardownTest() {
		TestBase.driver.quit();
	}
	
	
	public void getscreenshot(String result) throws IOException {
		
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Generating file Name with Data & Time Stamp

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		String S1 = formatter.format(ts);
		//System.out.println(formatter.format(ts));

		StringJoiner sj1 = new StringJoiner("_");
		String joined = sj1.add(result).add(S1).toString();

		StringJoiner sj2 = new StringJoiner(".");
		String fname = sj2.add(joined).add("png").toString();

		String flpath = "C:\\Users\\hiteshpa\\git\\Selenium\\DemoMaven\\Screenshots";
		StringJoiner sj3 = new StringJoiner("\\");
		String DestFile = sj3.add(flpath).add(fname).toString();

		//System.out.println(DestFile);
		FileUtils.copyFile(SrcFile, new File(DestFile));
		
	}
	

}
