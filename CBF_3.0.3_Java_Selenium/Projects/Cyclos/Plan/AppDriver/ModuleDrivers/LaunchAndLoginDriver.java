/******************************************************************************
Created by : Archana
Created\Updated on : 3/15/2018 12:32:34 PM
Module part of 'Cyclos'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;
import cbfx.ui.web.KeywordDriver;
import cbfx.ui.web.BaseWebModuleDriver;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class LaunchAndLoginDriver extends BaseWebModuleDriver {
	public LaunchAndLoginDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void LaunchAndLogin(DataRow input, DataRow output) {
		//1. Navigate to $data URL using Url browser
		uiDriver.launchApplication(input.get("Navigate@Url"));
		
		//2. Type $data in Username field
		uiDriver.setValue("LaunchAndLogin.txtUsername", input.get("Type@Username"));
		
		//3. Type $data in Password field
		uiDriver.setValue("LaunchAndLogin.txtPassword", input.get("Type@Password"));
		
		//4. Click on Login button
		uiDriver.click("LaunchAndLogin.btnLogin");
		
	}
	

	/**
	*Overriding toString() method of object class to print LaunchAndLogin
	*format string
	**/
	public String toString(){
		return "LaunchAndLoginDriver()";
	}
}
