package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApHomePage {

	WebDriver driver;

	public ApHomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
	@FindBy(how = How.XPATH, using = "//a[@class='sf-with-ul'][contains(text(),'Women')]")
	WebElement woman;

	public void clickOnWomantab() {
		woman.click();
	}
	
}
