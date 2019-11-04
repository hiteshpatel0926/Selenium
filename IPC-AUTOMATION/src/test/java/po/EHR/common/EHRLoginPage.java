package po.EHR.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EHRLoginPage {
	
	WebDriver driver;

	public EHRLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Using FindBy for locating elements
		@FindBy(how = How.ID, using = "txtOrgName")
		WebElement OrgName;
		
		@FindBy(how = How.ID, using = "txtPassword")
		WebElement PWord;
		
		@FindBy(how = How.ID, using = "btnLogin")
		WebElement Click;
		
		@FindBy(how = How.ID, using = "txtUname")
		WebElement UName;
				
	
			
		
		

		public void OrgLogin(String Ocode, String OPass) {
			
			OrgName.sendKeys(Ocode);
			PWord.sendKeys(OPass);
			Click.click();
			
		}
		
		public void ProviderLogin(String Pcode, String PPass) {
		
			UName.sendKeys(Pcode);
			PWord.sendKeys(PPass);
			Click.click();
			
		}
	
}
