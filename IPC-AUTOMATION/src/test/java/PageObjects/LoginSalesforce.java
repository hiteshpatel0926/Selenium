package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginSalesforce {
	
	WebDriver driver;
	
	public LoginSalesforce(WebDriver driver) {
		this.driver=driver;
	}

	// Using FindBy for locating elements
		@FindBy(how = How.XPATH, using = "//input[@id='username']")WebElement SFUsrname;
		@FindBy(how = How.XPATH, using = "//input[@id='password']")WebElement SFPasswrd;
		@FindBy(how = How.XPATH, using = "//input[@id='Login']")WebElement SFlogin;
		@FindBy(how = How.XPATH, using = "//div[@id='error']")WebElement SFloginError;
		
		
		public void SFDC(String strSFusrname, String strSFPasswrd) {
			SFUsrname.sendKeys(strSFusrname);
			SFPasswrd.sendKeys(strSFPasswrd);
			SFlogin.click();
			String SFerror=SFloginError.getText();
			SFerror.compareToIgnoreCase("Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
			SFerror.contains("Please check your username and password");		
		}
}
