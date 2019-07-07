/******************************************************************************
Created by : admin
Created\Updated on : 7/4/2018 6:55:25 PM
Module part of 'sample_1'
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

public class PCOMMLauncherDriver extends BaseWebModuleDriver {
	public PCOMMLauncherDriver(KeywordDriver kwDriver){
		super(kwDriver);
	}
	
	public void PCOMMLogin(DataRow input, DataRow output ){
		mfLogin.launchEmulator(input.get("path"));
		mfLogin.login(input.get("usernamepwd"));
	}
	
	public void PCOMMExit(){
		mfLogin.closeWindow();
	}
	
	public String toString(){
		 return "PCOMMLauncher";
	 }
}
