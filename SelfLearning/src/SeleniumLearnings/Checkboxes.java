package SeleniumLearnings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Checkboxes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
		System.out.println(driver.getTitle());

		Thread.sleep(5000);

//	    System.out.println(driver.findElement(By.xpath("//input[@id='isAgeSelected']")).isSelected());
//	    driver.findElement(By.xpath("//input[@id='isAgeSelected']")).click();
//	  
//	    System.out.println(driver.findElement(By.xpath("//input[@id='isAgeSelected']")).isSelected());

		// Check All
		// driver.findElement(By.xpath("//input[@value='Check All']")).click();

		// Count of Checkbox field on Page

		System.out.println(driver.findElements(By.xpath("//input[@type='checkbox']")).size());
		int size = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
		for (int i = 0; i < size; i++) {
			System.out.println(driver.findElements(By.xpath("//input[@type='checkbox']")).get(i).isSelected());
			Thread.sleep(2000);
			driver.findElements(By.xpath("//input[@type='checkbox']")).get(i).click();
			System.out.println(driver.findElements(By.xpath("//input[@type='checkbox']")).get(i).isSelected());
		}
		driver.close();
	}

}
