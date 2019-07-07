/******************************************************************************
Created by : Archana
Created\Updated on : 3/15/2018 12:32:34 PM
Module part of 'Cyclos'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cbf.utils.SleepUtils;
import cbfx.ui.web.KeywordDriver;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.BaseWebModuleDriver;
import static cbf.engine.TestResultLogger.*;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class VerifyTransactionDriver extends BaseWebModuleDriver {
	public VerifyTransactionDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void VerifyTransaction(DataRow input, DataRow output) {
		//1. Verify whether the CyclosLogo element present
		if (uiDriver.checkElementPresent("VerifyTransaction.eltCyclosLogo",15000)) {
			passed("checkElementPresent","checkElementPresent should pass","checkElementPresent passed");
		} else {
			failed("checkElementPresent","checkElementPresent should pass","checkElementPresent failed");
		}
		
		//2. Click on AccountTab WebElement
		uiDriver.click("VerifyTransaction.eltAccountTab");
		
		//3. Click on AccountInfoTab WebElement
		uiDriver.click("VerifyTransaction.eltAccountInfoTab");
		
		//4. Select $data from the LatestTransaction list
		uiDriver.click("VerifyTransaction.lstLatestTransaction");
		
		//5. Verify whether the Description element present
		if (uiDriver.checkElementPresent("VerifyTransaction.eltDescription",15000)) {
			passed("checkElementPresent","checkElementPresent should pass","checkElementPresent passed");
		} else {
			failed("checkElementPresent","checkElementPresent should pass","checkElementPresent failed");
		}
		
		JavascriptExecutor js = (JavascriptExecutor)uiDriver.webUIDriver.webDriver();
		WebElement Logout =uiDriver.webUIDriver.webDriver().findElement(By.xpath("//span[text()='Logout']"));					
	    js.executeScript("arguments[0].click();", Logout);
	    
	    uiDriver.webUIDriver.webDriver().switchTo().alert().accept(); 
	    
		SleepUtils.sleep(TimeSlab.LOW);
		
	}
	

	/**
	*Overriding toString() method of object class to print VerifyTransaction
	*format string
	**/
	public String toString(){
		return "VerifyTransactionDriver()";
	}
}
