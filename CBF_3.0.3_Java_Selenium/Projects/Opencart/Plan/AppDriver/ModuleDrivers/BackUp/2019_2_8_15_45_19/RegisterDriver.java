/******************************************************************************
Created by : admin
Created\Updated on : 2/7/2019 4:10:20 PM
Module part of 'Demo'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;
import java.io.IOException;
import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.BaseWebModuleDriver;
import static cbf.engine.TestResultLogger.*;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class RegisterDriver extends BaseWebModuleDriver {
	public RegisterDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void Register(DataRow input, DataRow output) {
	}
	
	public void Launch_Application(DataRow input, DataRow output) {
	}
	

	/**
	*Overriding toString() method of object class to print Register
	*format string
	**/
	public String toString(){
		return "RegisterDriver()";
	}
}
