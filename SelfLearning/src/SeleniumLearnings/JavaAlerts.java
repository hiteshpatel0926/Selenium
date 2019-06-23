package SeleniumLearnings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaAlerts {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
	    System.out.println(driver.getTitle());
	    
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
	    
	    
	    System.out.println(driver.switchTo().alert().getText());
	    Thread.sleep(5000);
	    driver.switchTo().alert().accept();
	    Thread.sleep(5000);
	    
	    driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg'][contains(text(),'Click me!')]")).click();
	    Thread.sleep(5000);
	    System.out.println(driver.switchTo().alert().getText());
	    Thread.sleep(5000);
	    driver.switchTo().alert().accept();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg'][contains(text(),'Click me!')]")).click();
	    Thread.sleep(5000);
	    driver.switchTo().alert().dismiss();
	    
	    driver.findElement(By.xpath("//button[contains(text(),'Click for Prompt Box')]")).click();
	    Thread.sleep(5000);
	    driver.switchTo().alert().sendKeys("HITESHKUMAR PATEL");
	    Thread.sleep(5000);
	    driver.switchTo().alert().accept();
	    
	    driver.findElement(By.xpath("//button[contains(text(),'Click for Prompt Box')]")).click();
	    Thread.sleep(5000);
	    driver.switchTo().alert().sendKeys("HITESHKUMAR PATEL");
	    Thread.sleep(5000);
	    driver.switchTo().alert().accept();
	    System.out.println(driver.findElement(By.id("prompt-demo")).getText());
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//button[contains(text(),'Click for Prompt Box')]")).click();
	    Thread.sleep(5000);
	    driver.switchTo().alert().sendKeys("I AM GOING TO CANCLE THIS.!!!");
	    Thread.sleep(5000);
	    driver.switchTo().alert().dismiss();
	    
	    
	    driver.close();
	}

}
