package SeleniumLearnings;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Generating file Name with Data & Time Stamp

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		String S1 = formatter.format(ts);
		System.out.println(formatter.format(ts));

		StringJoiner sj1 = new StringJoiner("_");
		String joined = sj1.add("screen print").add(S1).toString();

		StringJoiner sj2 = new StringJoiner(".");
		String fname = sj2.add(joined).add("png").toString();

		String flpath = "C:\\Users\\hiteshpa\\Downloads\\SELENIUM";
		StringJoiner sj3 = new StringJoiner("\\");
		String DestFile = sj3.add(flpath).add(fname).toString();

		System.out.println(DestFile);
		FileUtils.copyFile(SrcFile, new File(DestFile));

		driver.close();
	}
	
}
