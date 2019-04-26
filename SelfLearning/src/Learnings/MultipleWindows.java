package Learnings;

import java.util.Set;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleWindows {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hiteshpa\\Downloads\\SELENIUM\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://www.qaclickacademy.com/practice.php");
		System.out.println("This is Parent Window Title==> "+driver.getTitle());

		driver.findElement(By.xpath("//button[@id='openwindow']")).click();
		
		Set<String> ids=driver.getWindowHandles();
		Iterator<String> it=ids.iterator();
		String parentid=it.next();
		String childid=it.next();
		System.out.println("This is Parent WindowID ==> "+parentid);
		System.out.println("This is Child WindowID ==> "+childid);
		
		driver.switchTo().window(childid);
		System.out.println("This is Child Window Title==> "+driver.getTitle());
		
		driver.close();
		
		driver.switchTo().window(parentid);
		System.out.println("Switching back to Parent is successful");
		driver.close();
	}

}
