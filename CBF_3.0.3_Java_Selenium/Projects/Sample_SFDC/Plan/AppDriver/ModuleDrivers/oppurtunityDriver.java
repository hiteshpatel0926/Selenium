/******************************************************************************
Created by : admin
Created\Updated on : 4/3/2018 3:16:15 PM
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
import cbfx.ui.web.KeywordDriver;
/**
Extends BaseModuleDriver class and performs application specific operations
*/
public class oppurtunityDriver extends BaseWebModuleDriver {
	public oppurtunityDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void NewOpportunity(DataRow input, DataRow output) {
		//1. Click on _Opportunity_Record_Type WebElement
		uiDriver.click("NewOpportunity.elt_Opportunity_Record_Type");
		
		//2. Select $data from the CBRenewal list
		uiDriver.setValue("NewOpportunity.lstCBRenewal", input.get("Select@CBRenewal"));
		
		//3. Click on Continue_ button
		uiDriver.click("NewOpportunity.btnContinue_");
		
	}
	
	public void CreateSalesOppurtunity(DataRow input, DataRow output) {
		//1. Click on Opportunities WebElement
		uiDriver.click("CreateSalesOppurtunity.eltOpportunities");
		
		//2. Click on NewOpportunities WebElement
		uiDriver.click("CreateSalesOppurtunity.eltNewOpportunities");
		
	}
	
	public void EditOpportunity(DataRow input, DataRow output) {
		//1. Opportunities
		uiDriver.click("EditOpportunity.lstOpportunities");
		
		//2. Verify the text  $data in the RecentlyOpportunities field
		if (uiDriver.verifyText("EditOpportunity.eltRecentlyOpportunities", input.get("VerifyText@RecentlyOpportunities"))) {
			passed("verifyText","verifyText should pass","verifyText passed");
		} else {
			failed("verifyText","verifyText should pass","verifyText failed");
		}
		
		//3. Type $data in OpprotunityNumber field
		uiDriver.setValue("EditOpportunity.txtOpprotunityNumber", input.get("Type@OpprotunityNumber"));
		
		//4. Verify Opportunity_Outcome with $Data
		uiDriver.Verify("EditOpportunity.wbrOpportunity_Outcome");
		
		//5. Type $data in Search field
		uiDriver.setValue("EditOpportunity.txtSearch", input.get("Type@Search"));
		
		//6. Stage
		uiDriver.click("EditOpportunity.lstStage");
		
		//7. Select $data from the Stage list
		uiDriver.setValue("EditOpportunity.lstStage", input.get("Select@Stage"));
		
		//8. Click on Save button
		uiDriver.click("EditOpportunity.btnSave");
		
	}
	

	/**
	*Overriding toString() method of object class to print oppurtunity
	*format string
	**/
	public String toString(){
		return "oppurtunityDriver()";
	}
}
