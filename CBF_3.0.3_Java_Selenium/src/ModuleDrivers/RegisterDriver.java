/******************************************************************************
Created by : admin
Created\Updated on : 2/8/2019 3:45:36 PM
Module part of 'Demo'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;

import java.io.IOException;

import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.BaseWebModuleDriver;
import cbfx.ui.web.KeywordDriver;
import static cbf.engine.TestResultLogger.*;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class RegisterDriver extends BaseWebModuleDriver {
	public RegisterDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}

	public void Launch_URL(DataRow input, DataRow output) {
		//1. Navigate to $data URL using Chrome browser
		uiDriver.launchApplication(input.get("Navigate@Chrome"));
		
	}
	
	public void Register(DataRow input, DataRow output) {
		//1. Click on Register WebElement
		uiDriver.click("Register.eltRegister");
		
		//2. Type $data in Username field
		uiDriver.setValue("Register.txtUsername", input.get("Type@Username"));
		
		//3. Type $data in First Name field
		uiDriver.setValue("Register.txtFirst_Name", input.get("Type@First_Name"));
		
		//4. Type $data in Last Name field
		uiDriver.setValue("Register.txtLast_Name", input.get("Type@Last_Name"));
		
	}
	

	/**
	*Overriding toString() method of object class to print Register
	*format string
	**/
	public String toString(){
		return "RegisterDriver()";
	}
}
