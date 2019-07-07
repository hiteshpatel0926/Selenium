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
public class PolicyCreationDriver extends BaseWebModuleDriver {
	public PolicyCreationDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	public void Login(DataRow input, DataRow output){
		//1. Navigate to the input positions and enter data
		
		mfuiDriver.setText("Login.user", input.get("user")); 
		mfuiDriver.Wait(1000);
		mfuiDriver.setText("Login.pwd", input.get("password")); 
		mfuiDriver.Wait(1000);
		mfuiDriver.pressSpecialKey("Enter");  
		mfuiDriver.Wait(1000);
		mfuiDriver.pressSpecialKey("Enter"); 
		mfuiDriver.Wait(1000);
		mfuiDriver.pressSpecialKey("Enter"); 
		mfuiDriver.Wait(1000);
		
		//2. Verify whether theLogged in page present
		mfuiDriver.searchText(input.get("logintext"));
				
	}
	
	public void Connect2Screen(DataRow input, DataRow output) {
		mfuiDriver.setScreenConnection();
	}
	
	public void return2MainScreen(DataRow input, DataRow output) {
		mfuiDriver.pressSpecialKey("F3");
		mfuiDriver.pressSpecialKey("F3");
		mfuiDriver.pressSpecialKey("F3");
	}
	
public void ScreenActions(DataRow input, DataRow output) throws InvalidFormatException{
		
		
		String mytext = mfuiDriver.getText("ScreenActions.command1","8"); // reading text from screen position
		System.out.println("The text read fronm screen:" + mytext);
		if(mytext.equalsIgnoreCase("TEST50")) {
			passed("Verify text", "Expected Text: TEST50" , "Actuual : " + mytext);
		}else
		{
			failed("Verify text","Expected Text: TEST50" , "Actuual : " + mytext);
		}
		mfuiDriver.setCursorPos("ScreenActions.position1");
		mfuiDriver.Wait(1000);
		String textatsetposition = mfuiDriver.getScreenText();
		System.out.println("The text read from screen:" + textatsetposition);
		mfuiDriver.setText("ScreenActions.txtActions1", input.get("wrongtextInput")); 
		mfuiDriver.Wait(2000);
		mfuiDriver.setCursorPos("ScreenActions.position2"); 
		mfuiDriver.Wait(2000);
		mfuiDriver.clearText();
		mfuiDriver.Wait(2000);
		mfuiDriver.setText("ScreenActions.txtActions2", input.get("ProperInput")); 
		mfuiDriver.pressSpecialKey("Enter");
		mfuiDriver.Wait(3000);

		mfuiDriver.searchText(input.get("searchText"));
	}
	

public String toString(){
	 return "PolicyCreation";
}

}