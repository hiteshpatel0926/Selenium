package SeleniumLearnings;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadAutoIT {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "E:\\WebDrivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://the-internet.herokuapp.com/upload");
	   
	    
		
		driver.findElement(By.xpath("//input[@id='file-upload']")).click();
		Thread.sleep(5000);
		Runtime.getRuntime().exec("C:\\Users\\Hiteshkumar Patel\\Downloads\\FileUpload1.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='file-submit']")).click();
		
		String check=driver.findElement(By.xpath("//h3[contains(text(),'File Uploaded!')]")).getText();
		
		
		System.out.print(check.contains("File Uploaded!"));
	}

}
