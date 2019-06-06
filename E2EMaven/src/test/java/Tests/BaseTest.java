package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public static WebDriver driver;
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
		BaseTest.driver.quit();
	}

	
}
