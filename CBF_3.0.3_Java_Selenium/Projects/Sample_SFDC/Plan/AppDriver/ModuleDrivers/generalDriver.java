/******************************************************************************
Created by : admin
Created\Updated on : 4/3/2018 3:16:15 PM
Module part of 'sample_1'
Module created using Cafenext Selenium Builder
******************************************************************************/
package ModuleDrivers;
import cbf.utils.DataRow;
import java.io.IOException;

import org.openqa.selenium.By;

import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbfx.ui.web.BaseWebModuleDriver;
import static cbf.engine.TestResultLogger.*;

/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class generalDriver extends BaseWebModuleDriver {
	public generalDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void LaunchApp(DataRow input, DataRow output) {
		//1. Launch the $data browser and navigate to $URL and wait for few seconds
		uiDriver.launchApplication(input.get("LaunchBrowser"));
		
	}
	
	public void LoginDeatils(DataRow input, DataRow output) {
		//1. Type $data in UserName field
		uiDriver.setValue("LoginDeatils.txtUserName", input.get("Type@UserName"));
		
		//2. Type $data in Password field
		uiDriver.setValue("LoginDeatils.txtPassword", input.get("Type@Password"));
		
		//3. Click on LoginButton WebElement
		uiDriver.click("LoginDeatils.eltLoginButton");
		
	if (lightningMode.equalsIgnoreCase("Yes")) {				
			
			if (uiDriver.webDr.findElements(By.xpath("//a [@class='switch-to-lightning']")).size() != 0){
				uiDriver.click("LoginDeatils.lnkLightning-View");
				Thread.sleep(20000);
				
			}			
		}else {
			if (uiDriver.webDr.findElements(By.xpath("//a [text()='Switch to Salesforce Classic']")).size() != 0){
				uiDriver.click("LoginDeatils.imgUserProfile");
				Thread.sleep(100);
				uiDriver.click("LoginDeatils.lnkClassic-View");
				Thread.sleep(20000);
			}
		}
		
	}
	
	public void TearDown(DataRow input, DataRow output) {
		//1. Click on UserProfile button
		uiDriver.click("TearDown.btnUserProfile");
		
		//2. Click on LogOut button
		uiDriver.click("TearDown.btnLogOut");
		
	}
	

	/**
	*Overriding toString() method of object class to print general
	*format string
	**/
	public String toString(){
		return "generalDriver()";
	}
}
