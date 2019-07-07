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
public class OppRecordTypeDriver extends BaseWebModuleDriver {
	public OppRecordTypeDriver(KeywordDriver kwDriver) {
		super(kwDriver);
	}
	
	public void NewCBRenewal(DataRow input, DataRow output) {
		//1. Type $data in BrokerLocation field
		uiDriver.setValue("NewCBRenewal.txtBrokerLocation", input.get("Type@BrokerLocation"));
		
		//2. Click on Calendar button
		uiDriver.click("NewCBRenewal.btnCalendar");
		
		//3. Type $data in Dateterm field
		uiDriver.setValue("NewCBRenewal.txtDateterm", input.get("Type@Dateterm"));
		
		//4. Type $data in AccountName field
		uiDriver.setValue("NewCBRenewal.txtAccountName", input.get("Type@AccountName"));
		
		Thread.sleep(3000);
		uiDriver.click("NewCBRenewal.actName");
		
		//5. Type $data in OpportunityName field
		uiDriver.setValue("NewCBRenewal.txtOpportunityName", input.get("Type@OpportunityName"));
		
		
		
		//6. Type $data in Opportunity_Number field
		uiDriver.setValue("NewCBRenewal.txtOpportunity_Number", input.get("Type@Opportunity_Number"));
		
		//7. Click on RenewableDate button
		uiDriver.click("NewCBRenewal.btnRenewableDate");
		Thread.sleep(2000);
		
		//8. Type $data in RenewableDate field
		uiDriver.setValue("NewCBRenewal.txtRenewableDate", input.get("Type@RenewableDate"));
		
		//9. NonRenewable
		uiDriver.click("NewCBRenewal.chkNonRenewable");
		
		//10. SalesType
		uiDriver.click("NewCBRenewal.lstSalesType");
		
		//11. Select $data from the SalesType list
		uiDriver.setValue("NewCBRenewal.lstSalesType", input.get("Select@SalesType"));
		
		//12. ReasonsForOpportunity
		uiDriver.click("NewCBRenewal.lstReasonsForOpportunity");
		
		//13. Select $data from the ReasonsForOpportunity list
		uiDriver.setValue("NewCBRenewal.lstReasonsForOpportunity", input.get("Select@ReasonsForOpportunity"));
		
		//14. Stage
		uiDriver.click("NewCBRenewal.lstStage");
		
		//15. Select $data from the Stage list
		uiDriver.setValue("NewCBRenewal.lstStage", input.get("Select@Stage"));
		
		//16. BusinessUnit
		uiDriver.click("NewCBRenewal.lstBusinessUnit");
		
		//17. Select $data from the BusinessUnit list
		uiDriver.setValue("NewCBRenewal.lstBusinessUnit", input.get("Select@BusinessUnit"));
		
		//18. Type $data in Product field
		uiDriver.setValue("NewCBRenewal.txtProduct", input.get("Type@Product"));
		
		//19. Click on ClosedDate button
		uiDriver.click("NewCBRenewal.btnClosedDate");
		
		//20. Type $data in ClosedDate field
		uiDriver.setValue("NewCBRenewal.txtClosedDate", input.get("Type@ClosedDate"));
		
		//added bys
		Thread.sleep(3000);
		//uiDriver.click("NewCBRenewal.lstStop Future Opportunity");
		uiDriver.JScriptClick("//span[(text()='Stop Future Opportunity')]//parent::label/following-sibling::input");
		Thread.sleep(5000);
		uiDriver.JScriptClick("//span[text()='Stop Future Opportunity Reason']/parent::span/following-sibling::div/div/div/div/a");
		Thread.sleep(5000);
		//uiDriver.JScriptClick("//a[text()='No Exposure']");
		
		//21. click on Save
		uiDriver.click("NewCBRenewal.btnSave");
		//uiDriver.click("NewOppCBNewBusiness.btnSave");
		Thread.sleep(4000);
		
	}
	
	public void NewOppCBNewBusiness(DataRow input, DataRow output) {
		//1. Type $data in BrokerLocation field
		uiDriver.setValue("NewOppCBNewBusiness.txtBrokerLocation", input.get("Type@BrokerLocation"));
		
		//2. Click on Calendar button
		uiDriver.click("NewOppCBNewBusiness.btnCalendar");
		
		//3. Type $data in DateTerm field
		uiDriver.setValue("NewOppCBNewBusiness.txtDateTerm", input.get("Type@DateTerm"));
		
		//4. Type $data in AccountName field
		uiDriver.setValue("NewOppCBNewBusiness.txtAccountName", input.get("Type@AccountName"));
		Thread.sleep(3000);
		uiDriver.click("NewCBRenewal.actName");
		
		
		//5. Type $data in OpportunityName field
		uiDriver.setValue("NewOppCBNewBusiness.txtOpportunityName", input.get("Type@OpportunityName"));
		
		//6. Type $data in OpportunityNumber field
		uiDriver.setValue("NewOppCBNewBusiness.txtOpportunityNumber", input.get("Type@OpportunityNumber"));
		
		//7. Click on RenewalDate button
		uiDriver.click("NewOppCBNewBusiness.btnRenewalDate");
		
		//8. Type $data in RenewalDate field
		uiDriver.setValue("NewOppCBNewBusiness.txtRenewalDate", input.get("Type@RenewalDate"));
		
		//9. NonRenewable
		uiDriver.click("NewOppCBNewBusiness.chkNonRenewable");
		
		//10. SaleType
		uiDriver.click("NewOppCBNewBusiness.lstSaleType");
		
		//11. Select $data from the SaleType list
		uiDriver.setValue("NewOppCBNewBusiness.lstSaleType", input.get("Select@SaleType"));
		
		//12. ReasonforOpportunity
		uiDriver.click("NewOppCBNewBusiness.lstReasonforOpportunity");
		
		//13. Select $data from the ReasonforOpportunity list
		uiDriver.setValue("NewOppCBNewBusiness.lstReasonforOpportunity", input.get("Select@ReasonforOpportunity"));
		
		//14. Stage
		uiDriver.click("NewOppCBNewBusiness.lstStage");
		
		//15. Select $data from the Stage list
		uiDriver.setValue("NewOppCBNewBusiness.lstStage", input.get("Select@Stage"));
		
		//16. BusinessUnit
		uiDriver.click("NewOppCBNewBusiness.lstBusinessUnit");
		
		//17. Select $data from the BusinessUnit list
		uiDriver.setValue("NewOppCBNewBusiness.lstBusinessUnit", input.get("Select@BusinessUnit"));
		
		//18. Type $data in Product field
		//uiDriver.setValue("NewOppCBNewBusiness.txtProduct", input.get("Type@Product"));
		uiDriver.JScriptClick("//span[(text()='Product')]/parent::label/following-sibling::div/div/div[1]/div/input");
		
		
		//19. Click on ClosedDate button
		uiDriver.click("NewOppCBNewBusiness.btnClosedDate");
		
		//20. Type $data in ClosedDate field
		uiDriver.setValue("NewOppCBNewBusiness.txtClosedDate", input.get("Type@ClosedDate"));
		
		//21. Click on Save button
		uiDriver.click("NewOppCBNewBusiness.btnSave");
		Thread.sleep(4000);
		
	}
	

	/**
	*Overriding toString() method of object class to print OppRecordType
	*format string
	**/
	public String toString(){
		return "OppRecordTypeDriver()";
	}
}
