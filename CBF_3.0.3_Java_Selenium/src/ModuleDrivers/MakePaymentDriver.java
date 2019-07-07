/******************************************************************************
Created by : Archana
Created\Updated on : 3/15/2018 12:32:34 PM
Module part of 'Cyclos'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cbf.utils.DataRow;
import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.KeywordDriver;
import cbfx.ui.web.BaseWebModuleDriver;
import static cbf.engine.TestResultLogger.*;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class MakePaymentDriver extends BaseWebModuleDriver {
	public MakePaymentDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void MakePayment(DataRow input, DataRow output) {
		//1. Verify whether the CyclosLogo element present

		if (uiDriver.checkElementPresent("MakePayment.eltCyclosLogo", 15000)) {
			passed("checkElementPresent","checkElementPresent should pass","checkElementPresent passed");
		} else {
			failed("checkElementPresent","checkElementPresent should pass","checkElementPresent failed");
		}
		
		//2. Click on PersonalTab WebElement
		uiDriver.click("MakePayment.eltPersonalTab");
		
		//3. Click on ContactsTab WebElement
		uiDriver.click("MakePayment.eltContactsTab");
		
		//4. Select $data from the Contact list
				uiDriver.click("MakePayment.lstContact");
		
		//5. Click on MakePayment button
		uiDriver.click("MakePayment.btnMakePayment");
		
		//6. Type $data in Amount field
		uiDriver.setValue("MakePayment.txtAmount", input.get("Type@Amount"));
		
		//7. Type $data in Description field
		uiDriver.setValue("MakePayment.txtDescription", input.get("Type@Description"));
		
		//8. Click on Submit button
		uiDriver.click("MakePayment.btnSubmit");
		
		//9. Click on ConfirmSubmit button
		uiDriver.click("MakePayment.btnConfirmSubmit");
		
		//10. Verify whether the SuccessMsg element present
		if (uiDriver.checkElementPresent("MakePayment.eltSuccessMsg",15000)) {
			passed("checkElementPresent","checkElementPresent should pass","checkElementPresent passed");
		} else {
			failed("checkElementPresent","checkElementPresent should pass","checkElementPresent failed");
		}
		
		//11. Click on Logout button
		WebElement Logout =uiDriver.webUIDriver.webDriver().findElement(By.xpath("//span[text()='Logout']"));	
		JavascriptExecutor js = (JavascriptExecutor)uiDriver.webUIDriver.webDriver();
	    js.executeScript("arguments[0].click();", Logout);
	    
	    uiDriver.webUIDriver.webDriver().switchTo().alert().accept(); 

		SleepUtils.sleep(TimeSlab.LOW);		
		
	}
	

	/**
	*Overriding toString() method of object class to print MakePayment
	*format string
	**/
	public String toString(){
		return "MakePaymentDriver()";
	}
}
