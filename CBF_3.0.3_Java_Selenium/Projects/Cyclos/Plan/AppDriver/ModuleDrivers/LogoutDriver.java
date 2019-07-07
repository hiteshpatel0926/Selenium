/******************************************************************************
Created by : Archana
Created\Updated on : 3/15/2018 12:32:34 PM
Module part of 'Cyclos'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;
import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.KeywordDriver;
import cbfx.ui.web.BaseWebModuleDriver;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class LogoutDriver extends BaseWebModuleDriver {
	public LogoutDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void Logout(DataRow input, DataRow output) {
		
		SleepUtils.sleep(TimeSlab.LOW);
		
		//1. Click on Logout button
		uiDriver.click("Logout.btnLogout");
		
		uiDriver.webUIDriver.webDriver().switchTo().alert().accept(); 

		SleepUtils.sleep(TimeSlab.LOW);	
		
	}
	

	/**
	*Overriding toString() method of object class to print Logout
	*format string
	**/
	public String toString(){
		return "LogoutDriver()";
	}
}
