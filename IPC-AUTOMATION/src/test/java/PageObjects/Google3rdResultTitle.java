package PageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Google3rdResultTitle {

	WebDriver driver;

	public Google3rdResultTitle(WebDriver driver) {
		this.driver = driver;
	}

	public void TitleVerification() {

	
		String title = driver.getTitle();
		System.out.println("The 3rd Link Title is ===>> " + title);
	
		
	}

}
