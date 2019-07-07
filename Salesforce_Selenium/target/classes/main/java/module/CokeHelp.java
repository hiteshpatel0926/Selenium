package main.java.module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.xml.XmlInclude;

import main.java.framework.BrowserDriver;
import main.java.framework.CSVReporter;
import main.java.framework.Framework;
import main.java.framework.UIDriver;
import main.java.framework.Utility;


@Listeners(main.java.listener.Listener.class)
public class CokeHelp extends BaseTest {
	public static boolean articleFound=false;
	public static String DataRowID;
	public static String componentName;
	public static String userType="";
	public static ITestContext context;

	static String orderNumber = null;

	public CokeHelp() {
		

	}

	@DataProvider(name="input")
	public static  Object[][] dataProvider(ITestContext context) {
		
		List<XmlInclude> oMethods = context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods();
		XmlInclude oInclude=context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods().get(intM);
		Map<String, String> oParam = context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods().get(intM).getAllParameters();
		
		String sComponent=context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods().get(intM).getName();
		Map<String, String> sDataRowID=oParam;
		CokeHelp.componentName=sComponent;
		CokeHelp.DataRowID=sDataRowID.get("DataRowID");
		
		System.out.println("Inside Data Provider:  "+componentName+"|"+DataRowID);
		
		CokeHelp.context=context;
		HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);
		CokeHelp oCoke=new CokeHelp();
		
		Object[][] dataArray = {{input}};
		return dataArray;
	
	}	
	
	
	public void print(String sValue){
		System.out.println(sValue);
	}

	public void print(boolean sValue){
		System.out.println(sValue);
	}

	public static void print(int sValue){
		System.out.println(sValue);
	}


	@BeforeMethod
	public void getMethodName(){
		String sComponent=CokeHelp.context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods().get(intM).getName();
		String sComponentInclude=CokeHelp.context.getCurrentXmlTest().getClasses().get(0).getIncludedMethods().get(intM).toString();
		
		System.out.println("-----Executing Component:"+sComponent+":"+sComponentInclude);
	}
		
	
	@AfterMethod
	public void getMethodName1(){
		intM++;
	}


//======================Component starts from here======================================
	
	@Test(dataProvider="input")
	public static void openURL(HashMap<String, String> input){
		
//		//System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
		
		String strURL=input.get("URL");
	
		UIDriver.launchURL(strURL);

		int iExit=1;
		while(UIDriver.checkElementpresent("connectionError", 2)){
			if(iExit>10){
				break;
			}
			System.out.println("Connection Error, Trying again:"+iExit);
			BrowserDriver.driver.close();
			BrowserDriver.driver.quit();
			BrowserDriver.getDriver(BrowserDriver.strBrowser);
			UIDriver.launchURL(strURL);
			iExit++;
		}

		
		boolean bLoginPage=UIDriver.checkElementpresent("userName", 15) || UIDriver.checkElementpresent("globalHeaderSwitcher",5);
		
		CSVReporter.reportPassFail(bLoginPage, "Validation of Login screen or Backend Page", "Screen should navigate to Login", "Navigation Successful", "Navigation Failed");
		
	}

	/**
	 * @name
	 * @author
	 * @description
	 * @preCondition
	 * @lastChanged
	 * @TODO
	 */

	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void cokeHelp_Logout(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);
		//System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
		
//		if(true){
//			return;
//		}
		boolean bLoginPage;
		if(UIDriver.checkElementpresent("profileLink_new",2)==true){
			UIDriver.clickElement("profileLink_new");
			UIDriver.wait(2);
			UIDriver.clickElementDynamic("profileMenu", "Logout");
	
			bLoginPage=UIDriver.checkElementpresent("userName", 8) ;
		}

		if(UIDriver.checkElementpresent("profileName_backend",2)==true){
				UIDriver.checkElementpresent("profileName_backend",10);
				UIDriver.clickElement("profileName_backend");
				UIDriver.checkElementpresentDynamic("linkdynamictext", "Log Out", 10);
				UIDriver.clickElementDynamic("linkdynamictext", "Log Out");
		}		
		
		
		bLoginPage=UIDriver.checkElementpresent("userName", 60) ;
		if(bLoginPage==false){
			String sStop="";
			sStop="Stop";
		}
		CSVReporter.reportPassFail(bLoginPage, "Logout Validation", "Screen should navigate to Login", "Navigation Successful", "Navigation Failed");

		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void bookmarkArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		UIDriver.clickElement("bookmark");
		
		boolean bBookmark=UIDriver.checkElementpresent("removeBookmark", 5);
		CSVReporter.reportPassFail(bBookmark, "Validation for Article Bookmark", "Article should be bookmarked", "Bookmarked successfully", "Bookmarked Failed");
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateBookmarkedArticle(@Optional HashMap<String, String> input){
		System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
		
		UIDriver.clickElement("bookMarks_Profile");
		UIDriver.wait(5);
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);
		
		String sArticleTitle=input.get("articleTitle");

		UIDriver.validateElementwithText(sArticleTitle);
		
	}


	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void verifyFooter(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
 		boolean bFooterImage=UIDriver.checkElementpresent("footerImage", 5);
		UIDriver.scrollToElement("footerImage");
		CSVReporter.reportPassFail(bFooterImage, "Footer Image Validation", "Footer Image should display", "Footer Image displayed", "Footer Image NOT displayed");
		
		UIDriver.validateElementwithText("© 2018 The Coca-Cola Company. All Rights Reserved.");
		UIDriver.validateElementwithText("As an employee, you have the responsibility to protect non-public Company Information against unauthorized disclosure and use.");
		UIDriver.validateElementwithText("Information labeled as Internal Use is generally available to all Company employees and authorized Third Parties.");
		UIDriver.validateElementwithText("For more information, please review the ");
		UIDriver.clickElementDynamic("linkdynamictext", "Company Information Protection Policy.");
		UIDriver.wait(4);
		UIDriver.switchToTab(1);
		
		CSVReporter.reportPassFail(UIDriver.checkElementpresent("userotheraccount", 10), 
				"Policy and Guideline Page Validation", "Policy and Guideline Page should display", "Policy and Guidline Page visible", "Policy and Guideline page not visible");
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);
		

		
	}
	

	
	@Test(dataProvider="input")
	
	public static void cokeHelp_Login(@Optional HashMap<String, String> input){
		System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
//		
//		if(true){
//			return;
//		}
//		
//		HashMap<String, String> input=null;

		String strNavigateToCH_Home=input.get("navigateToCH_Home");
		String strUserName=input.get("userName");
		String strPassword=input.get("passWord");
		String strLoginAs=input.get("loginAs");
		String sUserType = "";
		if(strNavigateToCH_Home==null){
			strNavigateToCH_Home="";
		}
		
		if(strLoginAs==null){
			strLoginAs="";
		}
		
		
		if(UIDriver.checkElementpresent("userName", 5)==true){

			UIDriver.setValue("userName", strUserName);
			UIDriver.setValue("passWord", strPassword);
			UIDriver.clickElement("Login");
			UIDriver.wait(5);

			boolean bError=UIDriver.checkElementpresent("loginError", 5);
			
			if(input.get("DataRowID").equalsIgnoreCase("invalid")){
				CSVReporter.reportPassFail(bError==true, "Validation for Login", "Login Should Fail", "Login Failed as Expected", "Login Passed unexpectedly");
				return;
			}
			else{
				CSVReporter.reportPassFail(bError==false, "Validation for Login", "Login Should be Succesful", "Login Successful", "Login Failed");
			}
			
			if (strLoginAs.equalsIgnoreCase("")==false){
				 
				UIDriver.checkElementpresent("frame", 30);
				BrowserDriver.driver.switchTo().frame(0);
				
				sUserType=UIDriver.getElementText("userType",strLoginAs).toLowerCase();
				userType=sUserType;
				
				UIDriver.wait(5);
				UIDriver.checkElementpresentDynamic("LoginAs", strLoginAs, 10);
				UIDriver.clickElementDynamic("LoginAs", strLoginAs);
				UIDriver.wait(4);
				
				UIDriver.closeTab(0);
				UIDriver.wait(2);
				UIDriver.switchToTab(0);
				
			}
			
		}
		
		
		
		if(strNavigateToCH_Home.equalsIgnoreCase("no")==false){
			
			
			if(sUserType.contains("associate")){
				UIDriver.checkElementpresent("globalHeaderSwitcher1", 20);
				UIDriver.clickElement("globalHeaderSwitcher1");
				UIDriver.clickElement("cokeHelp1");
			}
			else {
				UIDriver.checkElementpresent("globalHeaderSwitcher", 20);
				UIDriver.clickElement("globalHeaderSwitcher");
				UIDriver.wait(10);
				int intExit=0;
				while (UIDriver.checkElementpresent("cokeHelp", 20)==false){
					System.out.println("App List not Opened: trying again:"+intExit+1);
					UIDriver.clickElement("globalHeaderSwitcher");
					intExit++;
					if(intExit>4){
						break;
					}
				}
				UIDriver.clickElement("cokeHelp");
				UIDriver.wait(4);
				UIDriver.closeTab(0);
				UIDriver.switchToTab(0);
				
			}	
			
			if (UIDriver.checkElementpresentDynamic("poupfooterbutton", "Next", 5)){
				UIDriver.clickElementDynamic("poupfooterbutton", "Next");
			}
			
			boolean bCokeHome=UIDriver.checkElementpresent("cokeLogo_New", 15);
			CSVReporter.reportPassFail(bCokeHome, "Validation of Coke Home Screen", "Screen should navigate to Coke Home Page", "Navigation Successful", "Navigation Failed");
			
		}
		else{
			UIDriver.clickElement("globalHeaderSwitcher");
			UIDriver.wait(10);
			UIDriver.checkElementpresent("cokeHelp", 20);
			UIDriver.clickElement("cokeHelpBackend");
			
		}
				
	}

	
	@Test(dataProvider="input")
	public static void navigatetoCHHome(HashMap<String, String> input){
	
		UIDriver.checkElementpresent("globalHeaderSwitcher", 20);
		UIDriver.clickElement("globalHeaderSwitcher");
		UIDriver.wait(10);
		int intExit=0;
		while (UIDriver.checkElementpresent("cokeHelp", 20)==false){
			System.out.println("App List not Opened: trying again:"+intExit+1);
			UIDriver.clickElement("globalHeaderSwitcher");
			intExit++;
			if(intExit>4){
				break;
			}
		}
		UIDriver.clickElement("cokeHelp");
		UIDriver.wait(4);
		UIDriver.closeTab(0);
		UIDriver.switchToTab(0);
		
		
	
	if (UIDriver.checkElementpresentDynamic("poupfooterbutton", "Next", 5)){
		UIDriver.clickElementDynamic("poupfooterbutton", "Next");
	}
	
	boolean bCokeHome=UIDriver.checkElementpresent("cokeLogo_New", 15);
	CSVReporter.reportPassFail(bCokeHome, "Validation of Coke Home Screen", "Screen should navigate to Coke Home Page", "Navigation Successful", "Navigation Failed");

	}
	
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateArticleAndDiscussion(@Optional HashMap<String, String> input){
		boolean bArticles=UIDriver.checkElementpresentDynamic("linkdynamictitle", "Articles", 10);
		CSVReporter.reportPassFail(bArticles, "Articles section validation", "Articles section should be present", "Articles section present", "Article section NOT present");
		
		boolean bDiscussion=UIDriver.checkElementpresentDynamic("linkdynamictitle", "Discussions", 5);
		CSVReporter.reportPassFail(bDiscussion, "Discussions section validation", "Discussions section should be present", "Discussions section present", "Discussions section NOT present");
		
		

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void runReports(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sReport=input.get("reportText");
		String sReportColumns=input.get("reportColumns");

		UIDriver.setValue("searchType", "Reports");
		UIDriver.clickElement("filterOption_Main");
		UIDriver.wait(2);
		
		UIDriver.setValueAndEnter("searchEdit_Backend", sReport);
		
 		boolean bReport=UIDriver.checkElementpresentDynamic("linkdynamictitle", sReport, 20);
		CSVReporter.reportPassFail(bReport, "Validation for Report Link", "Report should be displayed:"+sReport, sReport + "Report displayed as expected", "Report Not displayed");
		
		UIDriver.clickElementDynamic("linkdynamictitle", sReport);
		
		boolean bReport1=UIDriver.checkElementpresent("reportsMetricsHeader", 20);
		CSVReporter.reportPassFail(bReport1, "Validation for Report Link", "Report should be displayed:"+sReport, sReport + "Report displayed as expected", "Report Not displayed");
		
		String[] aReportColumns=sReportColumns.split(";");
		
		for (String sReportColumn:aReportColumns){
			boolean bReportColumn=UIDriver.checkElementpresentDynamic("reportHeaderColumn", sReportColumn,1);
			CSVReporter.reportPassFail(bReportColumn, "Validation for Report Column", "Report should be displayed:"+sReport, sReport + "Report displayed as expected", "Report Not displayed");
		}

	}
	
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void navigateToProfileMenu(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sMenuItem=input.get("menuItem");
		
		UIDriver.clickElement("profileName");
		UIDriver.wait(2);
//		UIDriver.getElementDynamic("profileMenu", sMenuItem).click();
		
		boolean bMenuPage=UIDriver.checkElementpresentDynamic("linkdynamictitle", sMenuItem, 15);
		if(bMenuPage==true){
			UIDriver.clickElementDynamic("linkdynamictitle", sMenuItem);
		}
			
		
		CSVReporter.reportPassFail(bMenuPage, "Validation for Profile Menu Page", sMenuItem+" should be displayed", "Page is Displayed", "Page is NOT displayed");
		
		if(UIDriver.checkElementpresent("popupClose", 5)){
			UIDriver.clickElement("popupClose");
		}
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void updateDataPoints(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		HashMap<String,String> dataPoints=getDataPointsLookup();
		input.remove("DataRowID");
		String strLabel;
		
		Set<String> key=input.keySet();
		
		WebElement oCheckBox1=UIDriver.getElementDynamic("notificationCheckbox", dataPoints.get("email"));
		if(oCheckBox1.isSelected()==false){
			oCheckBox1.click();
		}
		
		for (String sKey : key) {
			if(dataPoints.containsKey(sKey)){
				strLabel=dataPoints.get((sKey));
				if(input.get(sKey).equalsIgnoreCase("yes")){
					WebElement oCheckBox=UIDriver.getElementDynamic("notificationCheckbox", strLabel);
					if(oCheckBox.isSelected()==false){
						UIDriver.clickElementDynamic("notificationLabel", strLabel);
					}
				}
			}
		}
		
		UIDriver.clickElement("saveButton");
		BrowserDriver.driver.navigate().refresh();
		UIDriver.checkElementpresentDynamic("notificationCheckbox", dataPoints.get("email"),20);
		for (String sKey : key) {
			strLabel=dataPoints.get((sKey));
			if(input.get(sKey).equalsIgnoreCase("yes")){
				WebElement oCheckBox=UIDriver.getElementDynamic("notificationCheckbox", strLabel);
				boolean bCheck=(oCheckBox.isSelected()==true);
				CSVReporter.reportPassFail(bCheck, "Validation for DataPoints Checkbox Selection",strLabel+ " should be selected", "Checkbox is selected", "Checkbox is Not Selected");	
				
			}
		}

}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	
	public static void selectAsBest_UserAccess(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sQuestion=input.get("questionText");
		
		String sSection=input.get("section");

		UIDriver.clickElementDynamic("viewAllDynamic",sSection);
		
		if(UIDriver.checkElementpresent("discussionsTab",20)){
			CSVReporter.reportPass("Validation for Discussions tab", "Discussion Tab should display", "Discussion Tab Visible");
		}
		
		UIDriver.clickElement("discussionsTab");
		UIDriver.wait(2);
		
		

		if(UIDriver.checkElementpresentDynamic("questionLink",sQuestion,15)) {
			CSVReporter.reportPass("Validation for Question ", sQuestion, "Question  Visible");
			UIDriver.clickElementDynamic("questionLink", sQuestion);
		}
		
		
		if(UIDriver.checkElementpresent("action_topRanked",20)){
			CSVReporter.reportPass("Validation for Action icon for Question ", sQuestion, "Action icon Visible");
			UIDriver.clickElement("action_topRanked");
			boolean bRemoveVerify=UIDriver.checkElementpresent("removeVerification",5);
			CSVReporter.reportPassFail(bRemoveVerify==false, "Validation for Remove Company Verify option", "Option should not display for used", "Option NOT displayed", "Option visible");
		}
		
		if(UIDriver.checkElementpresent("action_topRanked",20)){
			UIDriver.clickElement("action_topRanked");
			boolean bRemoveBest=UIDriver.checkElementpresent("removeAsBest",5);
			CSVReporter.reportPassFail(bRemoveBest==false, "Validation for Remove Best Answer option", "Option should not display for used", "Option NOT displayed", "Option visible");

		}
		
		

		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void selectAsBestAnswerandVerified(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sQuestion=input.get("questionText");
		String sAnswer=input.get("answer");
		String sSection=input.get("section");
		
		
		UIDriver.clickElementDynamic("viewAllDynamic",sSection);
		
		if(UIDriver.checkElementpresent("discussionsTab",20)){
			CSVReporter.reportPass("Validation for Discussions tab", "Discussion Tab should display", "Discussion Tab Visible");
		}
		
		UIDriver.clickElement("discussionsTab");
		UIDriver.wait(2);
		
		

		if(UIDriver.checkElementpresentDynamic("questionLink",sQuestion,15)) {
			CSVReporter.reportPass("Validation for Question ", sQuestion, "Question  Visible");
			UIDriver.clickElementDynamic("questionLink", sQuestion);
		}
		
		
		if(UIDriver.checkElementpresent("action_topRanked",20)){
			CSVReporter.reportPass("Validation for Action icon for Question ", sQuestion, "Action icon Visible");
			UIDriver.clickElement("action_topRanked");
			UIDriver.clickElement("removeVerification");
		}
		
		if(UIDriver.checkElementpresent("action_topRanked",20)){
			UIDriver.clickElement("action_topRanked");
			UIDriver.clickElement("removeAsBest");
		}
		
		
		BrowserDriver.driver.navigate().refresh();
		
		UIDriver.clickElementDynamic("selectAsBest",sAnswer);
		
		BrowserDriver.driver.navigate().refresh();
		boolean bBest=UIDriver.checkElementpresentDynamic("selectedAsBest",sAnswer,15);
		
		CSVReporter.reportPassFail(bBest, "Validation for Best Answer","'"+sAnswer + "' should be selected as Best", "Best Answer Selected", "Best Answer not selected");
		
		UIDriver.clickElementDynamic("answerActionTopRanked",sAnswer);
		
		UIDriver.clickElement("companyVerify");

		boolean bCompanyVerify=UIDriver.checkElementpresentDynamic("companyVerified",sAnswer,15);
		
		CSVReporter.reportPassFail(bCompanyVerify, "Validation for CompanyVerified","'"+sAnswer + "' should be selected as Company Verified", "Answer Company Verified", "Answer Company Verified");

		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void verifySelectAsBestForNonAdmin(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		
		String sQuestion=input.get("questionText");
		String sAnswer=input.get("answer");
		
		UIDriver.clickElement("discussionsTab");
		UIDriver.wait(2);
		
		UIDriver.clickElementDynamic("questionLink", sQuestion);
		
		UIDriver.clickElementDynamic("selectAsBest",sAnswer);
		
		
		boolean bBest=UIDriver.checkElementpresentDynamic("selectedAsBest",sAnswer,15);
		
		CSVReporter.reportPassFail(bBest==false, "Validation for Select As Best Answer option","Option should not be available", "Option NOt Avaialble", "Option Available");

		
		UIDriver.wait(2);
		
		UIDriver.clickElementDynamic("answerActionTopRanked",sAnswer);
		
		UIDriver.clickElement("companyVerify");

		boolean bCompanyVerify=UIDriver.checkElementpresentDynamic("companyVerified",sAnswer,15);
		
		CSVReporter.reportPassFail(bCompanyVerify==false, "Validation for Company Verify option","Option should not be available", "Option NOt Avaialble", "Option Available");

		
	}
	
	
	public static HashMap<String,String> getDataPointsLookup(){
		HashMap<String,String> dataPoints=new HashMap<>();
		dataPoints.put("email", "Enable email notifications for my community activity");
		dataPoints.put("followsMe", "Follows me");
		dataPoints.put("likesPostComment", "Likes a post or a comment I made");
		dataPoints.put("commeentsOnMyPost", "Comments on my posts");
		dataPoints.put("commentsOnProfilePost", "Comments on a post on my profile");
		dataPoints.put("commentsAfterMe", "Comments after me");
		dataPoints.put("commentsOnBookmarked", "Comments on an item I bookmarked");
		dataPoints.put("commentsOnLikes", "Comments on an item I like");
		dataPoints.put("mentionsInPost", "Mentions me in a post");
		dataPoints.put("mentionsInComment", "Mentions me in a comment");
		return dataPoints;

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateSearchFunctionality(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String strSearchValue=input.get("searchValue");
		
		if(UIDriver.checkElementpresent("homeLink", 5)==true){
			UIDriver.clickElement("homeLink");
		}

		
		UIDriver.setValue("searchEdit_New", strSearchValue);
		
		WebElement oElement=UIDriver.getElement("searchbutton_New");
		oElement.sendKeys(Keys.ENTER);
//		UIDriver.clickElement("searchbutton_New");
		
		boolean bSearchResults=UIDriver.checkElementpresent("searchResults", 15);
		
		CSVReporter.reportPassFail(bSearchResults, "Validation for Search Result Page", "Search Result Page Should be displayed", "Search Result page displayed", "Search result Page NOT displayed");
		
		String sValue=strSearchValue.split(" ")[0];
		
		boolean bSearchStringinResults=UIDriver.checkElementpresentDynamic("searchStringinResults",sValue, 30);
		
		CSVReporter.reportPassFail(bSearchStringinResults, "Validation for Search String in Results Page", "Search String should be present on Result Page", "Search String Found on Result page", "Search String NOT Found on Result page");
		
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void viewMoreinSearchResults(@Optional HashMap<String, String> input) throws ParseException{
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle","All", 20)==true){
			UIDriver.clickElementDynamic("linkdynamictitle","All");
		}
		
		boolean bViewMoreKnowledge=UIDriver.checkElementpresent("viewMoreKnowledge", 20);
		CSVReporter.reportPassFail(bViewMoreKnowledge, "Validation for View More Link in Knowledge", "View More Link should display", "View More present", "View More NOT visble");
		
		
		if(bViewMoreKnowledge==true){
			UIDriver.clickElement("viewMoreKnowledge");
			boolean bKnowledge=UIDriver.checkElementpresentDynamic("searchResultHeader", "Knowledge", 20);
			CSVReporter.reportPassFail(bKnowledge, "Validation for Navigation to Knowledge Results", "Knowledge Results should be displayed", "Knowledge Result displayed", "Knowledge Result NOT displayed");
			
		}
		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle","All", 20)==true){
			UIDriver.clickElementDynamic("linkdynamictitle","All");
		}
		
		
		boolean bViewMoreDiscussion=UIDriver.checkElementpresent("viewMoreDiscussion", 20);
		if(bViewMoreDiscussion){
			UIDriver.scrollToElement("viewMoreDiscussion");
			UIDriver.wait(4);
		}
		CSVReporter.reportPassFail(bViewMoreDiscussion, "Validation for View More Link in Discussions", "View More Link should display", "View More present", "View More NOT visble");
		
		
		if(bViewMoreDiscussion==true){
//			UIDriver.scrollToElement("viewMoreDiscussion");
			UIDriver.clickElement("viewMoreDiscussion");
			boolean bDiscussion=UIDriver.checkElementpresent("feedSortOption", 20);
			CSVReporter.reportPassFail(bDiscussion, "Validation for Navigation to Discussions Results", "Discussions Results should be displayed", "Discussions Result displayed", "Discussions Result NOT displayed");
			
		}

	
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateDiscussionsSearchResults(@Optional HashMap<String, String> input) throws ParseException{
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
//		
//		 String sDate1="May 15, 1998";  
//		    Date date1=new SimpleDateFormat("MMM dd, yyyy").parse(sDate1);  
//		    System.out.println(sDate1+"\t"+date1);  
		
		UIDriver.clickElement("discussionsTab");
		UIDriver.checkElementpresent("feedSortOption",20);
		
		
		selectElementinFilter("topQuestions");
		
		boolean bLatest=UIDriver.checkElementpresentDynamic("linkdynamictitle", "Top Questions",10);
		CSVReporter.reportPassFail(bLatest, "Validation for Sort By 'Top Questions'", "Option should be selected as expected", "Option is selected As expected", "Option Not Selected");
		
		boolean bQuestion=UIDriver.checkElementpresent("questionList", 20);
		CSVReporter.reportPassFail(bQuestion, "Validation for Top Questions in Discussions Tab", "Questions should be displayed as Expected", "Question displayed as expected", "Questions NOT displayed");
		
		
		selectElementinFilter("mostRecentActivity");
//		
		UIDriver.checkElementpresent("articletimestamp", 20);
		
		Utility.ValidateDateSort();	
		
	}
	
	
	

	public static void deselectElementinFilter(String sElement){
		UIDriver.clickElement("feedSortOption");
		UIDriver.wait(2);
		boolean bLatest=UIDriver.getElementAttribute(sElement, "aria-checked").equalsIgnoreCase("true");
		if(bLatest==true){
			UIDriver.clickElement(sElement);
		}
		else{
		UIDriver.clickElement("feedSortOption");
		}
	}

	public static void selectElementinFilter(String sElement){
		UIDriver.clickElement("feedSortOption");
		boolean bLatest=UIDriver.getElementAttribute(sElement, "aria-checked").equalsIgnoreCase("false");
		if(bLatest==true){
			UIDriver.clickElement(sElement);
		}
		else{
		UIDriver.clickElement("feedSortOption");
		}
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateInactivityLogout(@Optional HashMap<String, String> input){
		UIDriver.wait(29*60);
		
		
		boolean bLoginPage=UIDriver.checkElementpresent("userName", 15);
		CSVReporter.reportPassFail(bLoginPage, "Validation of Inactivity", "Screen should navigate to Login", "Auto-logout Successful", "Auto log-out Failed");

		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateInactivityContinue(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		UIDriver.wait(29*60);
		
		
		boolean bContinue=UIDriver.checkElementpresent("continue", 15);
		CSVReporter.reportPassFail(bContinue, "Validation of Inactivity", "Continue button should be displayed", "Continue Button Displayed", "Continue button NOT displayed");

		boolean bCokeHome=UIDriver.checkElementpresent("cokeLogo", 15);
		CSVReporter.reportPassFail(bCokeHome, "Validation of Coke Home Screen", "User should stay on Coke Home Page", "User stays on Coke Home Page", "Application is NOT on Coke Home Page");

		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateTiles(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String[] aTileName={"HR","IT","Travel","Procure to Pay","Workplace","Finance"};
		UIDriver.scrollToDynamicElement("tile", "HR");
		boolean bTiles=true;
		for(String sTileName:aTileName){
			bTiles=UIDriver.checkElementpresentDynamic("tile", sTileName, 15);
			if(bTiles==false){
				break;
			}
		}
		
		String sTileNames=aTileName.toString();
		CSVReporter.reportPassFail(bTiles,
		"Validation for "+sTileNames+" Tile on CokeHelp Home", sTileNames+" Tile should display as expected", sTileNames+" tile displayed", sTileNames+" Tile NOT displayed");

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void navigateToTile(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sMainTileText=input.get("mainTile");
		String sSubTileText1=input.get("subTile1");
		String sSubTileText2=Utility.getinputvalue(input,"subTile2");
		
		if(UIDriver.checkElementpresent("homeLink", 5)==true){
				UIDriver.clickElement("homeLink");
		}
		
		UIDriver.checkElementpresentDynamic("tile", sMainTileText, 15);
		
		WebElement oMainTile=UIDriver.getElementDynamic("tile", sMainTileText);
		
		oMainTile.click();
		UIDriver.wait(4);
		boolean bBreadCrumb=UIDriver.checkElementpresentDynamic("breadcrumb",sMainTileText, 15);
		CSVReporter.reportPassFail(bBreadCrumb, "Validation of Breadcrumb", "User Should Navigate to "+sMainTileText+"Page", "Navigation Succesful", "Navigation Failed");
		
		
		WebElement oSubTile=UIDriver.getElementDynamic("tile", sSubTileText1);
		oSubTile.click();
		UIDriver.wait(8);
		if(sSubTileText2.equals("")){
			boolean bHelpHeader=UIDriver.checkElementpresentDynamic("helpCategoryHeader",sSubTileText1, 15);
			CSVReporter.reportPassFail(bHelpHeader, "Validation of Help Category Header", sSubTileText1+" section should be displayed", sSubTileText1+" section is displayed", sSubTileText1+" section is NOT displayed");
		}
		else{
			WebElement oSubTile2=UIDriver.getElementDynamic("tile", sSubTileText2);
			oSubTile2.click();
			UIDriver.wait(4);
			boolean bHelpHeader=UIDriver.checkElementpresentDynamic("helpCategoryHeader",sSubTileText2, 15);
			CSVReporter.reportPassFail(bHelpHeader, "Validation of Help Category Header", sSubTileText2+" section should be displayed", sSubTileText2+" section is displayed", sSubTileText2+" section is NOT displayed");
		}
	}

	
//	public static void navigateToTab(String sTabName){
//		
//		WebElement oTab=UIDriver.getElementDynamic("linkdynamictitle", sTabName);
//		oTab.click();
//		
//	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void navigateToTab(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sTabName=input.get("tabName");
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle", sTabName, 10)){
			UIDriver.clickElementDynamic("linkdynamictitle", sTabName);
		}
		else{
			CSVReporter.reportFail("Validation for Tab:"+sTabName, "Tab should be present", "Tab Not Present");
		}
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateCriticalAlertAccess(@Optional HashMap<String, String> input){
//		navigateToTab("Knowledge");
		
		UIDriver.checkElementpresent("createArticle", 15);
		
		UIDriver.clickElement("createArticle");
		
		boolean bCritical=UIDriver.checkElementpresent("criticalAlert",10);
		
//		Reporter1.reportPassFail(bCritical==false, "Validation for Critical Aleart", strExpected, strActualPass, strActualFail);
		
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void createCriticalAlert(HashMap<String, String> input){

		String sArticleTitle=input.get("title");
		String sArticleURL=input.get("url");
		String sArticleSummary=input.get("summary");
		String sArticleBody=input.get("articleBody");
		
//		navigateToTab("Knowledge");

		UIDriver.clickElementDynamic("linkdynamictitle","Knowledge");
		
		UIDriver.checkElementpresentDynamic("linkdynamictitle","New",20);

		UIDriver.clickElementDynamic("linkdynamictitle","New");
		
		UIDriver.wait(4);
		if (UIDriver.checkElementpresentDynamic("elementDynamicText","Next", 15)==false){
			System.out.println("Next Button is Not Visible");
			return;
			
		}
		
		boolean boolStandard=UIDriver.checkElementpresentDynamic("articleType", "Standard Template", 5);
		CSVReporter.reportPassFail(boolStandard, "Article Type Option Validation", "Standard Template Option should be available", "Option Available", "Option Not Available");
		
		boolean boolCritical=UIDriver.checkElementpresentDynamic("articleType", "Critical Alert", 5);
		CSVReporter.reportPassFail(boolCritical, "Article Type Option Validation", "Critical Alert Option should be available", "Option Available", "Option Not Available");
		
		
		UIDriver.clickElementDynamic("articleType","Critical Alert");

		
		UIDriver.clickElementDynamic("elementDynamicText","Next");
		
		
		UIDriver.checkElementpresentDynamic("popupEdit","Title", 15);
		
		UIDriver.setValue("popupEdit","Title", sArticleTitle);
		
		sArticleURL=sArticleTitle+"URL";
		
		UIDriver.setValue("popupEdit","URL Name", sArticleURL);
		
		UIDriver.setValue("popupTextArea","Summary", sArticleSummary);
		
		
		UIDriver.clickElement("saveCriticalAlert");
		
		UIDriver.wait(5);
		
		if(UIDriver.checkElementpresent("articleError", 5)){
			CSVReporter.reportFail("Saving Article", "Article should be Saved", "Error while saving");
			Framework.exitTest=true;
			return;
		}
		
		UIDriver.clickElementDynamic("elementDynamicText","Publish");
		
		if(UIDriver.checkElementpresent("publishError", 10)){
			String sPublishError=UIDriver.getElementText("publishError");
			CSVReporter.reportFail("Publish Validation", "Error While Publishing", sPublishError);
			UIDriver.clickElement("cancelOK");
			return;
		}

		if(UIDriver.checkElementpresentDynamic("poupfooterbutton","Submit",3)){
			UIDriver.clickElementDynamic("poupfooterbutton","Submit");
		}
		else{
			UIDriver.clickElementDynamic("poupfooterbutton","Publish");
		}
		
		if (UIDriver.checkElementpresent("publishedStatus",10)==true){
			UIDriver.scrollToDynamicElement("elementDynamicText","Published");
			CSVReporter.reportPassFail(true, "Validation for Success Message for Publish Article", "Publish should be successful", "Publish Successful", "Publish Failed");
		}
		
//		alertBanner
//		bannerClose

			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void createStandardArticle(HashMap<String, String> input){

		
		String sArticleTitle=input.get("title");
		String sArticleURL=input.get("url");
		String sArticleSummary=input.get("summary");
		String sArticleBody=input.get("articleBody");
		
//		navigateToTab("Knowledge");

		UIDriver.clickElementDynamic("linkdynamictitle","Knowledge");
		
		UIDriver.checkElementpresentDynamic("linkdynamictitle","New",20);

		UIDriver.clickElementDynamic("linkdynamictitle","New");
		
		UIDriver.wait(4);
		while (UIDriver.checkElementpresentDynamic("elementDynamicText","Next", 15)==false){
			System.out.println("Next Button is Not Visible");
			BrowserDriver.driver.navigate().refresh();
			UIDriver.checkElementpresentDynamic("linkdynamictitle","New",20);
			UIDriver.clickElementDynamic("linkdynamictitle","New");
		}
		
		boolean boolStandard=UIDriver.checkElementpresentDynamic("articleType", "Standard Template", 5);
		CSVReporter.reportPassFail(boolStandard, "Article Type Option Validation", "Standard Template Option should be available", "Option Available", "Option Not Available");
		
		boolean boolCritical=UIDriver.checkElementpresentDynamic("articleType", "Critical Alert", 5);
		CSVReporter.reportPassFail(boolCritical, "Article Type Option Validation", "Critical Alert Option should be available", "Option Available", "Option Not Available");
		
		
		UIDriver.clickElementDynamic("articleType","Standard Template");
		
		UIDriver.clickElementDynamic("elementDynamicText","Next");
		
		
		UIDriver.checkElementpresentDynamic("popupEdit","Title", 15);
		
		UIDriver.setValue("popupEdit","Title", sArticleTitle);
		
		UIDriver.setValue("popupEdit","URL Name", sArticleTitle.replace(" ","-")+"-URL");
		
		
		UIDriver.setValue("popupTextArea","Summary", sArticleSummary);
		
		
		UIDriver.clickElementDynamic("poupfooterbutton", "Save");
		
		UIDriver.wait(5);
		
		if(UIDriver.checkElementpresent("articleError", 5)){
			CSVReporter.reportFail("Saving Article", "Article should be Saved", "Error while saving");
			return;
		}
		
		
		selectArticleCategory(input);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Draft Articles");

		boolean bDraft=UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,10);
		
		CSVReporter.reportPassFail(bDraft, "Article validation in Draft", sArticleTitle, "Article saved in Draft", "Article not saved in draft");

		
		UIDriver.clickElementDynamic("linkdynamictitle", sArticleTitle);
		
		
//		UIDriver.checkElementpresentDynamic("linkdynamictitle","Submit for Approval",20);
//
//		UIDriver.clickElementDynamic("linkdynamictitle","Submit for Approval");
//		
//		if(UIDriver.checkElementpresent("publishError", 5)){
//			String sPublishError=UIDriver.getElementText("publishError");
//			CSVReporter.reportFail("Publish Validation", "Error While Publishing", sPublishError);
//			UIDriver.clickElement("cancelOK");
//			return;
//		}
//		
//		
//		UIDriver.setValue("popupTextArea","Comments", "Please approve");
//		
//		
//		UIDriver.clickElementDynamic("poupfooterbutton", "Submit");
//		
//		if (UIDriver.checkElementpresent("publishedStatus",10)==true){
//			UIDriver.scrollToDynamicElement("elementDynamicText","Published");
//			CSVReporter.reportPassFail(true, "Validation for Success Message for Publish Article", "Publish should be successful", "Publish Successful", "Publish Failed");
//		}
		
//		alertBanner
//		bannerClose
			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void submitForApproval(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		String sArticleTitle=input.get("title");
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Draft Articles");

		
		boolean bDraft=UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,10);
		
		CSVReporter.reportPassFail(bDraft, "Article validation in Draft", sArticleTitle, "Article saved in Draft", "Article not saved in draft");

		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle",sArticleTitle,5)){
			UIDriver.clickElementDynamic("linkdynamictitle",sArticleTitle);
		}
		else{
			CSVReporter.reportFail("Article Submit for Approval", sArticleTitle, "Article NOT Found");
			return;
		}
		
		
		UIDriver.checkElementpresentDynamic("linkdynamictitle","Submit for Approval",10);

		UIDriver.clickElementDynamic("linkdynamictitle","Submit for Approval");
		
		if(UIDriver.checkElementpresent("publishError", 5)){
			String sPublishError=UIDriver.getElementText("publishError");
			CSVReporter.reportFail("Publish Validation", "Error While Publishing", sPublishError);
			UIDriver.clickElement("cancelOK");
			return;
		}
		
		
		UIDriver.setValue("popupTextArea","Comments", "Please approve");
		
		
		UIDriver.clickElementDynamic("poupfooterbutton", "Submit");
		
		if (UIDriver.checkElementpresent("publishedStatus",10)==true){
			UIDriver.scrollToDynamicElement("elementDynamicText","Published");
			CSVReporter.reportPassFail(true, "Validation for Success Message for Publish Article", "Publish should be successful", "Publish Successful", "Publish Failed");
		}
		

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void selectArticleCategory(HashMap<String, String> input){

		String sCokeHelp=Utility.getinputvalue(input, "category_CH");
		String sGeography=Utility.getinputvalue(input, "category_Geography");
		String sEmployeeType=Utility.getinputvalue(input, "category_EmployeeType");
		
		
		UIDriver.clickElement("categorySelection");
		UIDriver.clickElement("categoryEdit");

		
		UIDriver.checkElementpresentDynamic("categoryExpand", "Coca-Cola Help", 10);
		
		UIDriver.clickElementDynamic("categoryExpand", "Coca-Cola Help");
		
		String[] aCokeHelp=sCokeHelp.split(";");
		
		
		for(String sSection:aCokeHelp){
			String[] aValue=sSection.split(",");
			for(String sValue:aValue){
				if (UIDriver.checkElementpresentDynamic("categoryCheckbox", aValue[aValue.length-1],10)==false){
					UIDriver.clickElementDynamic("categoryExpand", sValue);
				}
				else{
					UIDriver.clickElementDynamic("categoryCheckbox", sValue);
				}
			}
		}
		
		if(UIDriver.getElementAttribute("categoryExpand", "Coca-Cola Help", "class").toLowerCase().contains("expanded")){
			UIDriver.clickElementDynamic("categoryExpand", "Coca-Cola Help");
		}
		
		

		UIDriver.clickElement("saveCriticalAlert");
		UIDriver.wait(4);
		
		for(String sSection:aCokeHelp){
			String[] aValue=sSection.split(",");
			boolean bFlag=UIDriver.checkElementpresentDOM("selectedCategory", aValue[aValue.length-1],10);
			CSVReporter.reportPassFail(bFlag, "Category Selection Validation", aValue[aValue.length-1], "Category Selected as expected", "Category NOT Selected");
			
		}
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void approveArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sArticleTitle=input.get("title");
		
		if (UIDriver.checkElementpresentDynamic("approveReject", sArticleTitle, 20)==false){
			CSVReporter.reportFail("Validation for Title in Approve/Reject Section", sArticleTitle, "Article NOT Found");
			return;
		}
		
		UIDriver.clickElementDynamic("backendTab", "Home");
		
		UIDriver.clickElementDynamic("approveReject", sArticleTitle);
		UIDriver.checkElementpresentDynamic("linkdynamictitle","Approve",10);
		
		UIDriver.clickElementDynamic("linkdynamictitle","Approve");
		
		UIDriver.setValue("popupTextArea","Comments", "Approved");
		
		UIDriver.clickElementDynamic("poupfooterbutton", "Approve");
		
		String sArticleStatus=UIDriver.getElementText("articleField", "Article Status");
		
		boolean bStatus=sArticleStatus.equalsIgnoreCase("Pending Editor Updates");
		
		CSVReporter.reportPassFail(bStatus, "Article Status", "Status should be updated", "Pending Editor Updates", sArticleStatus);
		

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void rejectArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sArticleTitle=input.get("title");
		
		if (UIDriver.checkElementpresentDynamic("approveReject", sArticleTitle, 20)==false){
			CSVReporter.reportFail("Validation for Title in Approve/Reject Section", sArticleTitle, "Article NOT Found");
			return;
		}
		
		UIDriver.clickElementDynamic("approveReject", sArticleTitle);
		UIDriver.checkElementpresentDynamic("linkdynamictitle","Reject",10);
		
		UIDriver.clickElementDynamic("linkdynamictitle","Reject");
		
		UIDriver.setValue("popupTextArea","Comments", "Rejected");
		
		UIDriver.clickElementDynamic("poupfooterbutton", "Reject");
		
		String sArticleStatus=UIDriver.getElementText("articleField", "Article Status");
		
		boolean bStatus=sArticleStatus.equalsIgnoreCase("Draft");
		
		CSVReporter.reportPassFail(bStatus, "Article Status", "Status should be updated", "Draft", sArticleStatus);
		

	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void publishArticle(HashMap<String, String> input){

		String sArticleTitle=input.get("title");
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Draft Articles");
		
		UIDriver.clickElementDynamic("tableColumn", "Article Number");
		UIDriver.wait(4);
		
//		
		String sSordOrder=UIDriver.getElementText("columnSortOrder", "Article Number");
		if(!sSordOrder.contains("descending")){
			UIDriver.clickElementDynamic("tableColumn", "Article Number");
			UIDriver.wait(4);
		}

		
		if (UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,20)==false){
			CSVReporter.reportWarning("Validation for Published Article", "Article should be Found: "+sArticleTitle, "Article NOT Found: "+sArticleTitle);
			return;
		}
		
		UIDriver.scrollToDynamicElement("alertAction", sArticleTitle);
		UIDriver.clickElementDynamic("alertAction", sArticleTitle);
		UIDriver.wait(4);

		UIDriver.clickElementDynamic("linkdynamictitle", "Publish");
		UIDriver.checkElementpresentDynamic("poupfooterbutton", "Publish",10);
		UIDriver.clickElementDynamic("poupfooterbutton", "Publish");
		UIDriver.wait(5);
		
		sortColumn("Article Number");
		
		boolean bDraft=UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,5);
		CSVReporter.reportPassFail(bDraft==false, "Draft Article", "Article should be removed", "Article removed from Draft", "Article NOT removed from Draft");

		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.wait(5);
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");

		sortColumn("Article Number");
		
		boolean bPublish=UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,5);
		CSVReporter.reportPassFail(bPublish, "Publish Article", "Article should be published", "Article Published", "Article NOT Published");

		
	}
	
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void reassignArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		UIDriver.checkElementpresentDynamic("linkdynamictitle","Assign",20);

		UIDriver.clickElementDynamic("linkdynamictitle","Assign");

		
		
	
		UIDriver.checkElementpresentDynamic("deleteiconEdit", "Assign to", 5);
		UIDriver.clickElementDynamic("deleteiconEdit", "Assign to");
		
		UIDriver.setValue("popupEdit","Assign to", "cch Editor");
	
		UIDriver.clickElement("lookupmenuitem");
		UIDriver.setValue("popupEdit","Instructions", "Please review and approve");

		UIDriver.clickElementDynamic("poupfooterbutton", "Save");
		

		
		
	}

	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateArticleEditable_Frontend(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

			String sEditText="Edit";
			boolean bEdit=UIDriver.checkElementpresentDynamic("linkdynamictitle", sEditText,8);
			
			
			CSVReporter.reportPassFail(bEdit==false, "Validation for Edit Option", "Edit Option should NOT display", "Edit Option NOT Displayed", "Edit Option Display");
			
			

			
	}
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateArticleEditable(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

			String sArticleTitle=input.get("title");
			
			
			UIDriver.checkElementpresent("globalHeaderSwitcher",20);
			
			UIDriver.clickElementDynamic("backendTab", "Knowledge");
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 10);
			UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
			UIDriver.clickElementDynamic("listviewOption", "Draft Articles");
			sortColumn("Article Number");
			String sEditText="Edit";
		
			if (UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,10)==false){
				System.out.println("Article Not Found in Draft"+sArticleTitle);
				return;
			}
		
			UIDriver.clickElementDynamic("alertAction", sArticleTitle);
			UIDriver.wait(4);
			
			boolean bEdit=UIDriver.checkElementpresentDynamic("linkdynamictitle", sEditText,8);
			
			
			if(userType.contains("associate")){
				CSVReporter.reportPassFail(bEdit==false, "Validation for Edit Option", "Edit Option should display", "Edit Option Displayed", "Edit Option Not Display");
			}
			else{
				CSVReporter.reportPassFail(bEdit, "Validation for Edit Option", "Edit Option should display", "Edit Option Displayed", "Edit Option Not Display");
			}
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void sharedCommentOnArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.wait(2);
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 10);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");
		UIDriver.wait(4);
	
		UIDriver.clickElement("articleLink_Backend");
		UIDriver.wait(4);
		
		boolean bEdit=UIDriver.checkElementpresentDynamic("buttondynamictitle", "Share an update...",15);
		
		CSVReporter.reportPassFail(bEdit, "Validation for Feeds on Article", "Feeds should display", "Feeds Displayed", "Feeds Not Display");

		UIDriver.clickElementDynamic("buttondynamictitle", "Share an update...");
		
		if (UIDriver.checkElementpresent("shareAnUpdateEdit", 5)){
			CSVReporter.reportPass("Validation for share an update Editor", "Editor should display", "Editor displayed");
			UIDriver.setTextToElement("shareAnUpdateEdit", "New Post");
			UIDriver.wait(2);
			UIDriver.clickElement("shareFeedButton");
		}
		else{
			CSVReporter.reportFail("Validation for share an update Editor", "Editor should display", "Editor NOT displayed");
		}
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void editStandardArticle(HashMap<String, String> input){

		String sArticleTitle=input.get("title");
		String sArticleSummary=input.get("newSummary");
//		String sArticleTitle="New Test Article";
		
		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");

		sortColumn("Article Number");
		
		UIDriver.wait(4);
		String sEditText="Edit as Draft";
		if (UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,10)==false){
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 10);
			UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
			UIDriver.clickElementDynamic("listviewOption", "Draft Articles");
			sortColumn("Article Number");
			sEditText="Edit";
		}
		
		if (UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,10)==false){
			System.out.println("Article Not Found in Draft"+sArticleTitle);
			return;
		}
		
		UIDriver.clickElementDynamic("alertAction", sArticleTitle);
		UIDriver.wait(4);
		
		if (UIDriver.checkElementpresentDynamic("linkdynamictitle", sEditText,8))
		{
			UIDriver.clickElementDynamic("linkdynamictitle", sEditText);
			
			if(UIDriver.checkElementpresentDynamic("popupEdit","Title", 15)==false){
				UIDriver.clickElementDynamic("poupfooterbutton", sEditText);
			}
		}
		else{
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 10);
			UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
			UIDriver.clickElementDynamic("listviewOption", "Draft Articles");
			sortColumn("Article Number");
			UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,10);
			UIDriver.clickElementDynamic("alertAction", sArticleTitle);
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Edit",8);
			UIDriver.clickElementDynamic("linkdynamictitle", "Edit");
			
		}
		
		UIDriver.setValue("popupTextArea","Summary", sArticleSummary);

		UIDriver.clickElementDynamic("poupfooterbutton", "Save");
		UIDriver.wait(5);
		
//		WebElement oCheckBox1=UIDriver.getElement("flagAsNew");
//		if(oCheckBox1.isSelected()==true){
//			oCheckBox1.click();
//			System.out.println("flag as New checkbox deselected");
//		}
		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle", sArticleTitle,2)){
			UIDriver.clickElementDynamic("linkdynamictitle", sArticleTitle);
		}
		
		if(sArticleSummary.equals("")==false && sArticleSummary!=null){
			if(UIDriver.checkElementpresentDynamic("articleinformation", "Summary", 20)){
				String sSummary=UIDriver.getElementText("articleinformation","Summary");
				boolean bSummary=sSummary.toLowerCase().contains(sArticleSummary.toLowerCase());
				CSVReporter.reportPassFail(bSummary, "Validation for Change in Article", "Summary should be displayed as:"+sArticleSummary, "Summary displayed as expected", "Summary NOT displayed:"+sSummary);
			}
			else{
				CSVReporter.reportFail("Validation For Change in Article", "Updated Summary should be displayed", "articleSummaryElement  not displayed");
			}
			
		}
		
		
		

//		submitForApproval(input);
	}

//	public static void searchArticle(@Optional HashMap<String, String> input){
//		
//		String sArticleNotes=input.get("articleNotes");
//		String sArticleTitle=input.get("title");
//
//		
//	}
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void addArticleNotes(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sArticleNotes=input.get("articleNotes");
		String sArticleTitle=input.get("title");
//		String sArticleSummary=input.get("newSummary");
//		String sArticleTitle="New Test Article";
		
		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");

		sortColumn("Article Number");
		
		UIDriver.wait(4);
		String sEditText="Edit as Draft";
		if (UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,4)==false){
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 10);
			UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
			UIDriver.clickElementDynamic("listviewOption", "Draft Articles");
			sortColumn("Article Number");
			sEditText="Edit";
		}
		
		if (UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,10)==false){
			System.out.println("Article Not Found in Draft"+sArticleTitle);
			return;
		}
		
		UIDriver.clickElementDynamic("alertAction", sArticleTitle);
		UIDriver.wait(4);
		
		UIDriver.checkElementpresentDynamic("linkdynamictitle", sEditText,15);
		
		UIDriver.clickElementDynamic("linkdynamictitle", sEditText);
		
		if(UIDriver.checkElementpresentDynamic("popupEdit","Title", 15)==false){
			UIDriver.clickElementDynamic("linkdynamictitle", "Edit");
		}
		
		UIDriver.scrollToElement("articleBodyEdit");
		UIDriver.clickElement("articleBodyEdit");
		
		UIDriver.wait(2);

		
		UIDriver.switchToFrame("title", "CK Editor Container");
		UIDriver.switchToFrame("title", "Rich Text Editor, editor");
		UIDriver.wait(5);
		//
		
		WebElement oTextArea=UIDriver.getElementByProperty("//body");
		UIDriver.scrollToElement(oTextArea);
		UIDriver.setTextToElement(oTextArea, sArticleNotes);
		
		UIDriver.switchToTab(0);
		
		UIDriver.clickElementDynamic("poupfooterbutton", "Save");
		
//		if(UIDriver.checkElementpresentDynamic("articleinformation", "Article Body", 20)){
//			String sSummary=UIDriver.getElementText("articleinformation","Summary");
//			boolean bSummary=sSummary.toLowerCase().contains(sArticleNotes);
//			Reporter1.reportPassFail(bSummary, "Validation for Change in Article", "Article Notes should be displayed as:"+sArticleNotes, "Notes displayed as expected", "Notes NOT displayed:"+sSummary);
//		}
//		else{
//			Reporter1.reportFail("Validation For Change in Article", "Updated Notes should be displayed", "Notes Element  not displayed");
//		}
		

//		submitForApproval(input);
	}

	private static void sortColumn(String sColumn){

		if(UIDriver.checkElementpresentDynamic("columnSortOrder", sColumn,2)){
			String sSordOrder=UIDriver.getElementText("columnSortOrder", sColumn).toLowerCase();
			if(sSordOrder.contains("descending")==true){
				System.out.println("Column "+ sColumn + "is already in descending order");
				return;
			}
		}
		
		UIDriver.clickElementDynamic("tableColumn", sColumn);
		UIDriver.wait(4);
		
		String sSordOrder=UIDriver.getElementText("columnSortOrder", sColumn).toLowerCase();
		if(!sSordOrder.contains("descending")){
			UIDriver.clickElementDynamic("tableColumn", sColumn);
			UIDriver.wait(4);
		}
		
		System.out.println("Column "+ sColumn + "sorted in descending order");

	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void criticalAlertE2EFlow(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		CSVReporter.reportAll("Done", "removeAndValidateCriticalAlert", "Function starts", "", "");
		removeAndValidateCriticalAlert(input);
		CSVReporter.reportAll("Done", "removeAndValidateCriticalAlert", "Function ends", "", "");
		
		CSVReporter.reportAll("Done", "createCriticalAlert", "Function starts", "", "");
		createCriticalAlert(input);
		CSVReporter.reportAll("Done", "createCriticalAlert", "Function ends", "", "");
		if (Framework.exitTest==true){
			return;
		}
		CSVReporter.reportAll("Done", "validateAlertBanner", "Function starts", "", "");
		validateAlertBanner(input);
		CSVReporter.reportAll("Done", "validateAlertBanner", "Function ends", "", "");
		
		CSVReporter.reportAll("Done", "editCriticalAlert", "Function starts", "", "");
		editCriticalAlert(input);
		CSVReporter.reportAll("Done", "editCriticalAlert", "Function ends", "", "");
		
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateAlertBanner(HashMap<String, String> input){

//		if(UIDriver.checkElementpresent("publishError", 10)){
//			String sPublishError=UIDriver.getElementText("publishError");
//			Reporter1.reportFail("Exiting Banner Validation", "Alert Not Created", sPublishError);
//			return;
//		}

		String sArticleTitle=input.get("title");
		
		UIDriver.clickElement("globalHeaderSwitcher");
		UIDriver.wait(10);
		int intExit=0;
		while (UIDriver.checkElementpresent("cokeHelp", 20)==false){
			System.out.println("App List not Opened: trying again:"+intExit+1);
			UIDriver.clickElement("globalHeaderSwitcher");
			UIDriver.wait(3);
			intExit++;
			if(intExit>4){
				break;
			}
		}
		UIDriver.clickElement("cokeHelp");
		UIDriver.wait(4);
//		UIDriver.closeTab(0);
		UIDriver.switchToTab(1);
		
		boolean bCokeHome=UIDriver.checkElementpresent("alertBanner", 15);
		CSVReporter.reportPassFail(bCokeHome, "Validation of Coke Home Screen", "Screen should navigate to Coke Home Page", "Navigation Successful", "Navigation Failed");
		
		String sBannerText=UIDriver.getElementText("alertBanner").toLowerCase();
		boolean bBanner=sBannerText.contains(sArticleTitle.toLowerCase());
		
		while(bBanner==false){
			if(UIDriver.checkElementpresent("bannerClose", 2)){
				UIDriver.clickElement("bannerClose");
			}
			else{
				break;
			}
			 bBanner=sBannerText.contains(sArticleTitle);

		}
		
		
		CSVReporter.reportPassFail(bBanner, "Validation for Banner", "Banner Title Should be:"+sArticleTitle, "Actual Banner Title:"+sBannerText, "Actual Banner Title:"+sBannerText);
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);
		
		
		UIDriver.clickElementDynamic("buttondynamictitle", "Close this window");
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void editCriticalAlert(HashMap<String, String> input){

		String sArticleTitle=input.get("title");
		String sArticleSummary=input.get("newSummary");
		
		
		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");

		
		UIDriver.clickElementDynamic("tableColumn", "Article Number");
		UIDriver.wait(4);
		
		String sSordOrder=UIDriver.getElementText("columnSortOrder", "Article Number");
		if(!sSordOrder.contains("descending")){
			UIDriver.clickElementDynamic("tableColumn", "Article Number");
			UIDriver.wait(4);
		}
		
		UIDriver.wait(4);
		
		UIDriver.checkElementpresentDOM("alertAction", sArticleTitle,30);
		
		UIDriver.clickElementDynamic("alertAction", sArticleTitle);
		UIDriver.wait(4);
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Edit as Draft",15);
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Edit as Draft");
		UIDriver.clickElementDynamic("poupfooterbutton", "Edit as Draft");
		
		if(UIDriver.checkElementpresentDynamic("popupEdit","Title", 15)==false){
			UIDriver.clickElementDynamic("linkdynamictitle", "Edit");
		}
		
		UIDriver.checkElementpresentDynamic("popupEdit","Title", 15);
		UIDriver.setValue("popupTextArea","Summary", sArticleSummary);

		UIDriver.clickElementDynamic("poupfooterbutton", "Save");
		UIDriver.wait(5);
		UIDriver.checkElementpresentDynamic("elementDynamicText", "Publish", 20);
		UIDriver.clickElementDynamic("elementDynamicText","Publish");
		
//		WebElement oCheckBox1=UIDriver.getElement("flagAsNew");
//		if(oCheckBox1.isSelected()==true){
//			oCheckBox1.click();
//			System.out.println("flag as New checkbox deselected");
//		}
		
		if(UIDriver.checkElementpresent("publishError", 10)){
			String sPublishError=UIDriver.getElementText("publishError");
			CSVReporter.reportFail("Publish Validation", "Error While Publishing", sPublishError);
			UIDriver.clickElement("cancelOK");
			return;
		}
		
		UIDriver.clickElement("publishOK");
		
		if (UIDriver.checkElementpresent("publishedStatus",10)==true){
			UIDriver.scrollToElement("publishedStatus");
			CSVReporter.reportPassFail(true, "Validation for Success Message for Publish Article", "Publish should be successful", "Publish Successful", "Publish Failed");
		}

		
		if(UIDriver.checkElementpresentDynamic("articleinformation", "Summary", 20)){
			String sSummary=UIDriver.getElementText("articleinformation","Summary");
			boolean bSummary=sSummary.toLowerCase().contains(sArticleSummary);
			CSVReporter.reportPassFail(bSummary, "Validation for Change in Article", "Summary should be displayed as:"+sArticleSummary, "Summary displayed as expected", "Summary NOT displayed:"+sSummary);
		}
		else{
			CSVReporter.reportFail("Validation For Change in Article", "Updated Summary should be displayed", "articleSummaryElement  not displayed");
		}
		

	}
	
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateTrendingTopics(@Optional HashMap<String, String> input){
		UIDriver.validateElementwithText("Trending Topics");
	}
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void postNewAnswer(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sQuestion=input.get("questionText");
		String sAnswer=input.get("answer");
		String sAttachmentFilePath=input.get("attachmentPath");
		String sSection=Utility.getinputvalue(input, "section");
		
		UIDriver.clickElementDynamic("viewAllDynamic",sSection);
		
//		if(UIDriver.checkElementpresent("writeAnAnswerEdit", 20)==false){
			UIDriver.checkElementpresent("discussionsTab",20);
			UIDriver.clickElementJS("discussionsTab");
			UIDriver.wait(2);
			
			UIDriver.clickElementDynamic("questionLink", sQuestion);
			UIDriver.checkElementpresent("writeAnAnswerEdit", 20);
//		}
		
		UIDriver.scrollToElement("writeAnAnswerEdit");
		UIDriver.setTextToElement("writeAnAnswerEdit", sAnswer);
		
		
		
		attachFileAnswer("uploadFiles",sAttachmentFilePath);
		
		boolean bAttach;
			
		if (sAttachmentFilePath!="" && sAttachmentFilePath!=null){
			
			if(sAttachmentFilePath.toLowerCase().contains(".png")){
				String sDisabled=UIDriver.getElementAttribute("answerButton","disabled");
				if(sDisabled==null){
					sDisabled="false";
				}
				 bAttach=(sDisabled.toString().equalsIgnoreCase("false"));
				 
				CSVReporter.reportPassFail(bAttach, "Validation for File Attachment", "File attachment Should be successful", "File attachment Successful", "File attachment Failed");
				
				if (bAttach==false){
					return;
				}
				UIDriver.clickElement("answerButton");
				
				boolean bAnswer=UIDriver.checkElementpresentDynamic("answer", sAnswer, 10);
				
				CSVReporter.reportPassFail(bAnswer, "Validation for New Answer", "Answer '"+ sAnswer + "' should be posted", "Answer is posted", "Answer NOT posted");

			}
			else{
				bAttach=(UIDriver.getElementAttribute("answerButton","disabled").toString().equalsIgnoreCase("true"));
				CSVReporter.reportPassFail(bAttach, "Validation for File Warning", "File attachment Should give Warning", "File attachment gives warning", "No Warning For file");
			}
			
		}

		

	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static boolean attachFileQuestion(String sElement, String sAttachmentFilePath){
		
//		UIDriver.clickElementJS("attachFile");
		UIDriver.clickElementJS("buttondynamictitle", "Attach up to 10 files");
		
		UIDriver.wait(4);
		UIDriver.checkElementpresent(sElement, 10);
		
	    File file = new File(sAttachmentFilePath);
	    
	    String sFilePath=file.getPath();
	    
	    UIDriver.clickElement(sElement);
	    
	    UIDriver.wait(10);
	    
		try {
			  StringSelection s = new StringSelection((char) 34 + sFilePath + (char) 34);
		        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		      
		        Robot robot1 = new Robot();

		        robot1.keyPress(KeyEvent.VK_ENTER);
		        robot1.keyRelease(KeyEvent.VK_ENTER);
		        robot1.keyPress(KeyEvent.VK_CONTROL);
		        robot1.keyPress(KeyEvent.VK_V);
		        robot1.keyRelease(KeyEvent.VK_V);
		        robot1.keyRelease(KeyEvent.VK_CONTROL);
		        robot1.keyPress(KeyEvent.VK_ENTER);
		        robot1.keyRelease(KeyEvent.VK_ENTER);
		        
		        UIDriver.wait(2);
		        

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean bAttach;
		if(sAttachmentFilePath.toLowerCase().contains(".png")){
			 bAttach=(UIDriver.checkElementpresent("fileWarning", 10));
			 
//			Reporter1.reportPassFail(bAttach==false, "Validation for File Warning", "File attachment Should be successful", "File attachment Successful", "File attachment Failed");
			if(bAttach==true){
					UIDriver.clickElement("removefile_question");
					attachFileQuestion("uploadFiles",sAttachmentFilePath);
				}
		}
		else{
			bAttach=(UIDriver.checkElementpresent("fileWarning", 15));
//			Reporter1.reportPassFail(bAttach==true, "Validation for File Warning", "File attachment Should give Warning", "File attachment gives warning", "No Warning For file");
		}
		return bAttach;
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void attachFileAnswer(String sElement, String sAttachmentFilePath){
			
//			UIDriver.clickElementJS("attachFile");
			UIDriver.clickElementJS("buttondynamictitle", "Attach file");
			UIDriver.wait(4);
			UIDriver.checkElementpresent(sElement, 10);
			
		    File file = new File(sAttachmentFilePath);
		    
		    String sFilePath=file.getPath();
		    
		    UIDriver.clickElement(sElement);
		    
		    UIDriver.wait(10);
		    
			try {
				  StringSelection s = new StringSelection((char) 34 + sFilePath + (char) 34);
			        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
			      
			        Robot robot1 = new Robot();

			        robot1.keyPress(KeyEvent.VK_ENTER);
			        robot1.keyRelease(KeyEvent.VK_ENTER);
			        robot1.keyPress(KeyEvent.VK_CONTROL);
			        robot1.keyPress(KeyEvent.VK_V);
			        robot1.keyRelease(KeyEvent.VK_V);
			        robot1.keyRelease(KeyEvent.VK_CONTROL);
			        robot1.keyPress(KeyEvent.VK_ENTER);
			        robot1.keyRelease(KeyEvent.VK_ENTER);
			        
			        UIDriver.wait(10);
			        

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(UIDriver.getElementText("previewFile").trim()!=""){
				return;
			}
			else{
				if(sAttachmentFilePath.toLowerCase().contains(".png")==false){
					return;
				}
				UIDriver.clickElementJS("removeFile");
				UIDriver.wait(3);
				attachFileAnswer(sElement,sAttachmentFilePath);
				
			}
		
		UIDriver.wait(5);

		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void removeAndValidateCriticalAlert(HashMap<String, String> input){

		String sArticleTitle=input.get("title");
		
//		if(UIDriver.checkElementpresent("cokeHelp",10)==true){
//			BrowserDriver.driver.navigate().back();
//		}
		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20)==false){
			UIDriver.clickElementDynamic("backendTab", "Knowledge");
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		}
		
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Draft Articles");

		UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,10);
		
		sortColumn("Article Number");
		if (UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,10)==true){

			List<WebElement> oElements=UIDriver.getElements("alertAction",sArticleTitle);
			
			for(WebElement oElement:oElements){
				UIDriver.scrollToElement(oElement);
				oElement.click();
				UIDriver.checkElementpresentDynamic("linkdynamictitle", "Delete",10);
				UIDriver.clickElementDynamic("linkdynamictitle", "Delete");
				UIDriver.checkElementpresentDynamic("poupfooterbutton", "Delete",10);
				UIDriver.clickElementDynamic("poupfooterbutton", "Delete");
			}
			
			
			
			boolean bArticleDelte=(UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,5)==false);
			CSVReporter.reportPassFail(bArticleDelte, "Delete Article", "Article should be deleted", "Article deleted as expected", "Article NOT deleted");	
			
		}
		
//		UIDriver.clickElementDynamic("backendTab", "Knowledge");
		UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "Published Articles");

		if (UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,20)==false){
			CSVReporter.reportWarning("Validation for Published Article", "Article should be Found: "+sArticleTitle, "Article NOT Found: "+sArticleTitle);
			return;
		}
		
		UIDriver.scrollToDynamicElement("alertAction", sArticleTitle);
		UIDriver.clickElementDynamic("alertAction", sArticleTitle);
		UIDriver.wait(4);

		UIDriver.clickElementDynamic("linkdynamictitle", "Archive");
		UIDriver.checkElementpresentDynamic("poupfooterbutton", "Archive",10);
		UIDriver.clickElementDynamic("poupfooterbutton", "Archive");
		UIDriver.wait(5);
		
		boolean bArchive=UIDriver.checkElementpresentDynamic("alertAction", sArticleTitle,5);
		CSVReporter.reportPassFail(bArchive==false, "Archive Article", "Article should be archived", "Article Archived", "Article NOT Archived");

		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void deleteFeedback(@Optional HashMap<String, String> input){

		//System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
		String sFeedbackText=input.get("feedbackText");
		
		
//		if(true){
//			return;
//		}

		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Feedback");
		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20)==false){
			UIDriver.clickElementDynamic("backendTab", "Feedback");
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		}
		
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "General Feedback");

		
		sortColumn("Feedback ID");
		if (UIDriver.checkElementpresentDynamic("feedbackAction", sFeedbackText,10)==true){

			List<WebElement> oElements=UIDriver.getElements("feedbackAction",sFeedbackText);
			
			for(WebElement oElement:oElements){
				UIDriver.scrollToElement(oElement);
				oElement.click();
				UIDriver.checkElementpresentDynamic("linkdynamictitle", "Delete",10);
				UIDriver.clickElementDynamic("linkdynamictitle", "Delete");
				UIDriver.checkElementpresentDynamic("poupfooterbutton", "Delete",10);
				UIDriver.clickElementDynamic("poupfooterbutton", "Delete");
			}
			
			
			
			boolean bArticleDelte=(UIDriver.checkElementpresentDynamic("feedbackAction", sFeedbackText,5)==false);
			CSVReporter.reportPassFail(bArticleDelte, "Delete Feedback", "Feedback should be deleted", "Feedback deleted as expected", "Feedback NOT deleted");	
			
		}
		
	}

	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void feedbackValidation(@Optional HashMap<String, String> input){

		//Loggingout front end and login into backend
		cokeHelp_Logout(input);
		
		HashMap<String, String> input1=Framework.getDataRecord("backend", "cokeHelp_Login");
		cokeHelp_Login(input1);
		
		
		
		
		String sFeedbackType=input.get("feedbackType");
		String sFeedbackText=input.get("feedbackText");
		
		UIDriver.checkElementpresent("globalHeaderSwitcher",20);
		
		UIDriver.clickElementDynamic("backendTab", "Feedback");
		
		if(UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20)==false){
			UIDriver.checkElementpresentDynamic("linkdynamictitle", "Select List View", 20);
		}
		
		
		UIDriver.clickElementDynamic("linkdynamictitle", "Select List View");
		UIDriver.clickElementDynamic("listviewOption", "General Feedback");

		UIDriver.checkElementpresentDynamic("feedbackAction", sFeedbackText,10);
		
		sortColumn("Feedback ID");
		if (UIDriver.checkElementpresentDynamic("feedbackAction", sFeedbackText,10)==true){
			UIDriver.clickElementDynamic("feedbackLink",sFeedbackText);
			
			boolean bFeedback=(UIDriver.checkElementpresentDynamic("articleinformation", "What can we do better?", 5));
			CSVReporter.reportPassFail(bFeedback, "Feedback Information", "Feedback information should displayed as:"+sFeedbackText, "Feedback information is displayed", "Feedback is NOT displayed");	
			
		}
		else{
			CSVReporter.reportFail("Feedback Validation", "Feedback should be available:"+sFeedbackText, "Feedback NOT found");
		}
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void editExistingQuesiton(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sPostTo=Utility.getinputvalue(input, "postTo");
		String sQuestion=Utility.getinputvalue(input, "question");
		String sDetails=Utility.getinputvalue(input, "details");
		String sBlockedKeyword=Utility.getinputvalue(input, "blockedKeywork");
		String sAttachmentPath=Utility.getinputvalue(input, "attachmentPath");

		UIDriver.clickElement("questionActionMenu");
		
		UIDriver.clickElementDynamic("linkdynamictitle","Edit");
		UIDriver.wait(10);
		
		UIDriver.validateElementwithText("Edit Question");
		
		fillQuesitonDetails(input);
		
		UIDriver.clickElement("ask");
		
		if  (sBlockedKeyword.equalsIgnoreCase("yes")==false){
			boolean bQuestion=UIDriver.checkElementpresentDynamic("questionTitle", sQuestion, 20);
			boolean bDetails=UIDriver.checkElementpresentDynamic("questionBody", sDetails, 20);
			CSVReporter.reportPassFail(bQuestion, "Validation for Question Title:"+sQuestion, "Question Title should be displayed:"+sQuestion, "Question Title is  displayed", "Question Title is NOT displayed");
			CSVReporter.reportPassFail(bDetails, "Validation for Question Body:"+sDetails, "Question Body should be displayed:"+sDetails, "Question Body is  displayed", "Question Body is NOT displayed");
		}
		else{
			boolean bQuestionError=UIDriver.checkElementpresent("questionError",  20);
			CSVReporter.reportPassFail(bQuestionError, "Validation for Error for Blocked Keyword", "Error should be displayed for question title"+ sQuestion +"with body:"+sDetails, "Error is  displayed", "Error is NOT displayed");
			UIDriver.clickElement("cancelquestion");
		}
		
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void askTheCommunity(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sPostTo=Utility.getinputvalue(input, "postTo");
		String sQuestion=Utility.getinputvalue(input, "question");
		String sDetails=Utility.getinputvalue(input, "details");
		String sBlockedKeyword=Utility.getinputvalue(input, "blockedKeywork");
//		String sAttachmentPath=Utility.getinputvalue(input, "attachmentPath");

		//Deleting Existing Questions
		
		UIDriver.clickElementDynamic("viewAllDynamic",sPostTo);
		
//		if(UIDriver.checkElementpresent("writeAnAnswerEdit", 20)==false){
			UIDriver.checkElementpresent("discussionsTab",20);
			UIDriver.clickElementJS("discussionsTab");
			UIDriver.wait(10);
			
			List<WebElement> objQuestionsAction=null;
			List<WebElement> objQuestionsParent=null;
		
		try {
			objQuestionsAction=UIDriver.getElements("deleteQuestions",sQuestion);
			if(objQuestionsAction!=null){
				for (WebElement oAction:objQuestionsAction){
					int iY = oAction.getLocation().y-100;
					((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+ iY +")");
					oAction.click();
					UIDriver.wait(5);
					UIDriver.clickElementDynamic("linkdynamictitle", "Delete");
					UIDriver.clickElementDynamic("buttondynamictitle", "Delete");
					UIDriver.wait(5);
//					objQuestionsAction=UIDriver.getElements("deleteQuestions",sQuestion);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error While Deleting Question");
		}
		
		UIDriver.clickElement("askTheCommunity");
		UIDriver.wait(5);
		
		boolean bWarning=fillQuesitonDetails(input);
		
		if(bWarning==false){
			UIDriver.clickElementJS("ask");
		}
		else{
			UIDriver.clickElementJS("cancelquestion");
			return;
		}
		
		
		
		if  (sBlockedKeyword.equalsIgnoreCase("yes")==false){
			boolean bQuestion=UIDriver.checkElementpresentDynamic("questionTitle", sQuestion, 20);
			boolean bDetails=UIDriver.checkElementpresentDynamic("questionBody", sDetails, 20);
			CSVReporter.reportPassFail(bQuestion, "Validation for Question Title:"+sQuestion, "Question Title should be displayed:"+sQuestion, "Question Title is  displayed", "Question Title is NOT displayed");
			CSVReporter.reportPassFail(bDetails, "Validation for Question Body:"+sDetails, "Question Body should be displayed:"+sDetails, "Question Body is  displayed", "Question Body is NOT displayed");
		}
		else{
			boolean bQuestionError=UIDriver.checkElementpresent("questionError",  20);
			CSVReporter.reportPassFail(bQuestionError, "Validation for Error for Blocked Keyword", "Error should be displayed for question title"+ sQuestion +"with body:"+sDetails, "Error is  displayed", "Error is NOT displayed");
			UIDriver.clickElement("cancelquestion");
		}
		
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static boolean fillQuesitonDetails(HashMap<String, String> input){

		String sPostTo=Utility.getinputvalue(input, "postTo");
		String sQuestion=Utility.getinputvalue(input, "question");
		String sDetails=Utility.getinputvalue(input, "details");
		String sAttachmentPath=Utility.getinputvalue(input, "attachmentPath");
		

		
		boolean bAttach = false;

		if(UIDriver.checkElementpresent("postTo", 10)==false){
			UIDriver.clickElement("askTheCommunity");
		}

		boolean bAsk=UIDriver.checkElementpresent("ask", 15);
		CSVReporter.reportPassFail(bAsk, "Validation of Ask The Community Pop-up", "Ask The Community section should be displayed", "Ask The Community section is displayed", "Ask The Community section is NOT  displayed");
		
		
		UIDriver.selectValue("postTo", sPostTo, "text");
		
		UIDriver.setValue("question", sQuestion);
		
		if(UIDriver.getElementAttribute("showMoreDetails", "aria-expanded").equalsIgnoreCase("false")){
			UIDriver.clickElement("showMoreDetails");
		}
		
		
		UIDriver.wait(5);

		JavascriptExecutor executor = (JavascriptExecutor)BrowserDriver.driver;
		executor.executeScript("arguments[0].innerHTML = arguments[1]", UIDriver.getElement("questiondetails"), sDetails);

		if (sAttachmentPath.equals("")==false){
			bAttach=attachFileQuestion("uploadFiles", sAttachmentPath);
			UIDriver.wait(5);

			
		}
		return bAttach;

	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateSortFunctionalityinMyFeeds(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		UIDriver.checkElementpresent("myFeed",40);
		
		UIDriver.clickElement("myFeed");
		UIDriver.wait(4);
		
		
		selectElementinFilter("mostRecentActivity");
		UIDriver.wait(2);
		
		
		boolean bRecent=UIDriver.getElementAttribute("mostRecentActivity", "aria-checked").equalsIgnoreCase("true");
		CSVReporter.reportPassFail(bRecent, "Validation for Sort By 'Most Recent Activity'", "Option should be selected as expected", "Option is selected As expected", "Option Not Selected");
//		Utility.ValidateDateSort();
		
		selectElementinFilter("latestPosts");
		
		UIDriver.checkElementpresent("latestPosts", 20);
		boolean bLatest=UIDriver.getElementAttribute("latestPosts", "aria-checked").equalsIgnoreCase("true");
		CSVReporter.reportPassFail(bLatest, "Validation for Sort By 'Latest Posts'", "Option should be selected as expected", "Option is selected As expected", "Option Not Selected");
		Utility.ValidateDateSort();
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateGreetingMessage(@Optional HashMap<String, String> input){
		int timeHour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));

		if(UIDriver.checkElementpresent("homeLink", 5)==true){
			UIDriver.clickElement("homeLink");
		}

//		String sGreetingMsg="";
		String sGreetingMsg=UIDriver.getElementText("greetingMsg").toLowerCase();
		boolean bGreeting;
		if(timeHour>=1 && timeHour<=11){
			bGreeting=sGreetingMsg.contains("good morning");
			CSVReporter.reportPassFail(bGreeting, "Validation of Greeting on Home Page", "Greeting should be passed","Expected Greeting: Good Morning", "Actual Greeting:"+sGreetingMsg);
		}
		else if(timeHour>=12 && timeHour<=16){
			bGreeting=sGreetingMsg.contains("good afternoon");
			CSVReporter.reportPassFail(bGreeting, "Validation of Greeting on Home Page", "Greeting should be passed","Expected Greeting: Good Afternoon", "Actual Greeting:"+sGreetingMsg);
		}
		else if(timeHour>=16) {
			bGreeting=sGreetingMsg.contains("good evening");
			CSVReporter.reportPassFail(bGreeting, "Validation of Greeting on Home Page", "Greeting should be passed","Expected Greeting: Good Evening", "Actual Greeting:"+sGreetingMsg);
		}
		
//		boolean bGreeting=UIDriver.checkElementpresent("welcomeMsg", 10);
//		Reporter1.reportPassFail(bGreeting, "Validation of Welcome Message on Home Page", "Greeting should display","Welcome Message is displayed as expected","Welcome Message Not displayed");

		
	}

	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void submitNewCase(HashMap<String, String> input){

		UIDriver.checkElementpresent("serviceNow",30);
		
		((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+UIDriver.getElement("serviceNow").getLocation().y+")");
		
		UIDriver.clickElement("serviceNow");
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);

		
		boolean bNewCase=UIDriver.checkElementpresent("SignInserviceNow1", 15);
		
		if(bNewCase==false){
			bNewCase=UIDriver.checkElementpresent("userotheraccount", 5);
		}
		
		CSVReporter.reportPassFail(bNewCase, "Validation for New Case Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);

	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateChatFunctionality(HashMap<String, String> input){

		UIDriver.checkElementpresent("chat",30);
		
		((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+UIDriver.getElement("chat").getLocation().y+")");
		UIDriver.clickElement("chat");
		UIDriver.wait(5);
		ArrayList<String> newTab = new ArrayList<String>(BrowserDriver.driver.getWindowHandles());
		
//		System.out.println("Number Of Tabs:"+newTab.size());
		
		BrowserDriver.driver.switchTo().window(newTab.get(1));
		
		UIDriver.checkElementpresent("chatName", 15);
		
		UIDriver.setValue("chatName", "User");
		UIDriver.clickElement("submitChatName");
		boolean bChat=UIDriver.checkElementpresent("chatMsg", 15);
		
		CSVReporter.reportPassFail(bChat, "Validation for Chat Window", "Chat connection should establish", "Chat Successful", "Chat Failed");
		
		
		if(UIDriver.checkElementpresent("chatOK", 30)){
			UIDriver.clickElement("chatOK");
		}
		
		BrowserDriver.driver.switchTo().window(newTab.get(1)).close();
		UIDriver.wait(4);
		BrowserDriver.driver.switchTo().window(newTab.get(0));
		if(UIDriver.checkElementpresent("supportsession", 30)){
			BrowserDriver.driver.navigate().back();
			UIDriver.checkElementpresent("searchEdit", 20);
		}
		
		BrowserDriver.driver.switchTo().window(newTab.get(0));
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateNewCaseMessage(@Optional HashMap<String, String> input){
		String sMessage="Only Coca-Cola Associates have access to HR Help If you think this is a mistake, contact your manager";
		
		String sActual=UIDriver.getElementText("newCaseMessage");
		
		boolean bMsg=sMessage.equalsIgnoreCase(sActual);
		
		CSVReporter.reportPassFail(bMsg, "Validation for Message in HR section", "Message should be displayed","Expected:"+sMessage, "Actual:"+sActual );
		
	}


//Sprint 2 Components starts here
	public static void selectHelpDesk(@Optional HashMap<String, String> input){
	
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void existingArticleCheck(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		searchArticle(input);
		if(articleFound=true){
			editStandardArticle(input);
		}
		else{
			createStandardArticle(input);
			publishArticle(input);
		}
		
		
	}
	
	
	
	public static void selectOption(String strLabel,String strOption){
		UIDriver.clickElementDynamic("selectOptionLink", strLabel);
		UIDriver.clickElementDynamic("selectOptionMenu", strOption);
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void searchArticle(HashMap<String, String> input){

		String sTitle=Utility.getinputvalue(input, "title");
		
//		UIDriver.setValue("searchArticleEdit", sTitle);
//		WebElement oElement=UIDriver.getElement("searchArticleEdit");
//		oElement.sendKeys(Keys.ENTER);

		UIDriver.setValue("searchType", "Knowledge");
		UIDriver.clickElement("filterOption_Main");
		UIDriver.wait(2);
		
		UIDriver.setValueAndEnter("searchEdit_Backend", sTitle);

		
		UIDriver.clickElementDynamic("searchNavigation", "Knowledge");
		
		if(UIDriver.checkElementpresent("searchArticleResult", 20)){
			String sArticleText=UIDriver.getElementText("searchArticleResult");
			UIDriver.clickElement("searchArticleResult");
			articleFound=true;
		}
		else{
			CSVReporter.reportWarning("Validation for Search Article", "Article should display", "No Search Results");
		}
		//articleSummary
		
		CSVReporter.reportPassFail(UIDriver.checkElementpresentDynamic("articleHeader",sTitle, 20),"Validation for Article Page",
				"Article Page should display","Navigation successful","Navigation failed");
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void viewLastModifiedDate(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bLastModified=UIDriver.checkElementpresent("lastModifiedDate",5);
		CSVReporter.reportPassFail(bLastModified, "Validation for Last Modified Date", "Last Modified Date should be present", "Last Modified Date is present", "Last Modified Date is NOT present");
		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void FeebackOnArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		UIDriver.clickElement("likeLink");
		
		if(UIDriver.checkElementpresent("voteCount", 20)==true){
			String sVoteCount=UIDriver.getElementText("voteCount");
			CSVReporter.reportPassFail(sVoteCount!="0","Validation for Feed Back","Article should be liked","Article Liked","Article NOT Liked");
		}
		else{
			CSVReporter.reportFail("Validation for Article Page", "Article Page should display", "Article Page NOT displayed");
		}

	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	
	public static void validateChatorCase(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sChatORCase=Utility.getinputvalue(input, "chat_OR_Case");

		if(sChatORCase.contains("case")){
			submitNewCase(input);
		}
		else if(sChatORCase.contains("chat")){
			validateChatFunctionality(input);
		}
		else{
			CSVReporter.reportFail("Validation for Chat/Case", "Value not given in datasheet for chat_Or_Case", "");
		}


		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void selectCaseOption(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sHelpOption=Utility.getinputvalue(input, "helpOption");
		UIDriver.scrollToElement("helpOption");
		UIDriver.selectValue("helpOption", sHelpOption, "text");
			
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void navigateToArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sArticleText=Utility.getinputvalue(input, "articleTitle");
		
		if(UIDriver.checkElementpresentDynamic("articleLink", sArticleText, 20)==true){
			UIDriver.scrollToDynamicElement("articleLink", sArticleText);
			CSVReporter.reportPass("Article Validation", sArticleText, "Article Found");
			UIDriver.clickElementDynamic("articleLink",sArticleText);
		}
		else{
			CSVReporter.reportFail("Article Validation", sArticleText, "Article NOT Found");
		}
			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void navigateToArticleFrontEnd(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		
		UIDriver.clickElementDynamic("searchNavigation", "Knowledge");
		String sArticleText="";
		if(UIDriver.checkElementpresent("searchArticleResult", 20)==true){
			sArticleText=UIDriver.getElementText("searchArticleResult");
			CSVReporter.reportPass("Article Validation", "Article Should be present:"+sArticleText, "Article Found");
			UIDriver.clickElement("searchArticleResult");
		}
		else{
			CSVReporter.reportFail("Article Validation", "Search Result should be Found", "Article NOT Found");
		}
			UIDriver.validateElementwithText(sArticleText);
			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void provideFeedbackonArticle(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sFeedBack=input.get("Feedback");
		
		if(sFeedBack.equalsIgnoreCase("like")){
			sFeedBack="Upvote";
		}
		else{
			sFeedBack="Downvote";
		}
		
		UIDriver.scrollToElement("relatedArticles");
		
		UIDriver.clickElementDynamic("buttondynamictitle", sFeedBack);
		
		if(sFeedBack.equalsIgnoreCase("downvote")){
			if(UIDriver.checkElementpresentDynamic("popupDropDown", "Reason", 10)==false){
				CSVReporter.reportFail("Feedback Details Popup", "Feedback details popup should open", "Feedback details not opened");
				return;
			}
			else{
				CSVReporter.reportPass("Feedback Details Popup", "Feedback details popup should open", "Feedback details opened");
				UIDriver.selectValue("popupDropDown", "Reason", "Information is not accurate", "text");
				UIDriver.setValue("popupTextArea1", "Details", "Please Review Information");
				UIDriver.selectCheckbox("requestFollowup");
				UIDriver.clickElementDynamic("poupfooterbutton1", "Submit");
				if (UIDriver.checkElementpresentDynamic("poupfooterbutton1", "Close",4)){
					UIDriver.clickElementDynamic("poupfooterbutton1", "Close");
				}
				
				
			}
		}
		
		String sProperty=UIDriver.getElementProperty("buttondynamictitle").replace("#param#", sFeedBack)+"/following-sibling::span";
		WebElement oVotes=UIDriver.getElementByProperty(sProperty);
		String sVoteCount=oVotes.getText().trim();
		CSVReporter.reportPassFail(sVoteCount.equals("0")==false,"Validation for Feed Back","Article should be liked","Article Liked","Article NOT Liked");

		
	}
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void observeRelatedArticles(@Optional HashMap<String, String> input){
		UIDriver.validateElementwithText("Related Articles");
		
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void financeServiceAssist(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", "Financial Services Assist",20);
		
		if(bFinacialAssist==false){
			CSVReporter.reportFail("Validation of Financial Service Assist", "Link should be present", "Financial Service Assist Link Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", "Financial Services Assist");
			CSVReporter.reportPass("Validation of Financial Service Assist", "Link should be present", "Financial Service Assist Link Found");
		}
		UIDriver.clickElementDynamic("linkdynamictext", "Financial Services Assist");
		
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);

		
		boolean bNewCase=UIDriver.checkElementpresent("SignInserviceNow", 15);
		
		CSVReporter.reportPassFail(bNewCase, "Validation for Financial Services Assist Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void validateHelpOptions(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		String sHelp=input.get("HelpType");
		
		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", sHelp,20);
		
		if(bFinacialAssist==false){
			CSVReporter.reportFail("Validation of "+sHelp, "Link should be present", sHelp+" Link Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", sHelp);
			CSVReporter.reportPass("Validation of "+sHelp, "Link should be present", sHelp+" Link Found");
		}
		UIDriver.clickElementDynamic("linkdynamictext", sHelp);
		
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);
		
		boolean bNewCase=false;
		boolean bDown=false;

		bNewCase=UIDriver.checkElementpresent("loginUsing", 15);
		if(bNewCase=false){
			BrowserDriver.driver.navigate().refresh();
			bNewCase=UIDriver.checkElementpresent("loginUsing", 4);
		}
		else{
			bNewCase=UIDriver.checkElementpresent("userotheraccount", 4);
		}
		
		

		
		if(bNewCase==false){
			bDown=UIDriver.checkElementpresent("downForMaintenance", 15);
		}
		
		if(bDown==false){
			CSVReporter.reportPassFail(bNewCase, "Validation for "+sHelp+" Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		}
		else{
		CSVReporter.reportWarning(sHelp+" window", "Down for maintenance", "");	
		}

		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void ITHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

//		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", "Financial Services Assist",20);
//		
//		if(bFinacialAssist==false){
//			Reporter1.reportFail("Validation of Financial Service Assist", "Link should be present", "Financial Service Assist Link Not Found");
//			return;
//		}
//		else{
//			UIDriver.scrollToDynamicElement("linkdynamictext", "Financial Services Assist");
//			Reporter1.reportPass("Validation of Financial Service Assist", "Link should be present", "Financial Service Assist Link Found");
//		}
//		UIDriver.clickElementDynamic("linkdynamictext", "Financial Services Assist");
//		
//		UIDriver.wait(5);
//		
//		UIDriver.switchToTab(1);
//
//		
//		boolean bNewCase=UIDriver.checkElementpresent("SignInserviceNow", 15);
//		
//		Reporter1.reportPassFail(bNewCase, "Validation for Financial Services Assist Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
//		
//		UIDriver.closeTab(1);
//		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void procureToPayHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", "Procure to Pay Contacts",20);
		
		if(bFinacialAssist==false){
			CSVReporter.reportFail("Validation of Procure to Pay Contacts", "Link should be present", "Procure to Pay Contacts Link Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", "Procure to Pay Contacts");
			CSVReporter.reportPass("Validation of Financial Service Assist", "Link should be present", "Financial Service Assist Link Found");
		}
		UIDriver.clickElementDynamic("linkdynamictext", "Procure to Pay Contacts");
		
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);
		
		boolean bNewCase=false;
		
		if (UIDriver.checkElementpresent("signInserviceNow1", 15)==false){
			BrowserDriver.driver.navigate().refresh();
			bNewCase=UIDriver.checkElementpresent("signInserviceNow1", 5) || UIDriver.checkElementpresent("userotheraccount", 5);
		}
		
		CSVReporter.reportPassFail(bNewCase, "Validation for Procure to Pay Contacts Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void workplaceHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", " Workplace Contacts",20);
		
		if(bFinacialAssist==false){
			CSVReporter.reportFail("Validation of  Workplace Contacts", "Link should be present", "Workplace Contacts Link Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", " Workplace Contacts");
			CSVReporter.reportPass("Validation of Workplace Contacts Link", "Link should be present", "Workplace Link Found");
		}
		UIDriver.clickElementDynamic("linkdynamictext", " Workplace Contacts");
		
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);

		
		boolean bNewCase=UIDriver.checkElementpresent("SignInserviceNow", 15);
		boolean bDown=false;
		if(bNewCase==false){
			bDown=UIDriver.checkElementpresent("downForMaintenance", 15);
		}
		
		if(bDown==false){
		CSVReporter.reportPassFail(bNewCase, "Validation for Workplace Contacts Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		}
		else{
		CSVReporter.reportWarning("Workplace contacts window", "Down for maintenance", "");	
		}
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void HRHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bFinacialAssist=UIDriver.checkElementpresentDynamic("linkdynamictext", "HR Help",20);
		
		if(bFinacialAssist==false){
			CSVReporter.reportFail("Validation of  HRHelp", "Link should be present", "Workplace Contacts Link Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", "HR Help");
			CSVReporter.reportPass("Validation of HRHelp Link", "Link should be present", "Workplace Link Found");
		}
		
		UIDriver.clickElementDynamic("linkdynamictext", "HR Help");
		
		UIDriver.wait(5);
		
		UIDriver.switchToTab(1);

		
		boolean bNewCase=UIDriver.checkElementpresent("userotheraccount", 15);
		
		if(bNewCase==false){
				bNewCase= UIDriver.checkElementpresent("loginUsing", 5);
		}
		CSVReporter.reportPassFail(bNewCase, "Validation for HRHelp Window", "Window should display as Expected", "Window displayed", "Window NOT Displayed");
		
		UIDriver.closeTab(1);
		UIDriver.switchToTab(0);


			
	}

	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void feedbackonCokeHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);
		//System.out.println("Executing Component:"+new Object(){}.getClass().getEnclosingMethod().getName());
		
//		if(true){
//			return;
//		}

		UIDriver.clickElementDynamic("buttondynamictext", "Feedback");
		UIDriver.wait(2);
		
		UIDriver.selectValue("feedbackOption", "Other", "text");
		UIDriver.setValue("feedbackText", "Test Feedback");
		UIDriver.clickElementDynamic("buttondynamictext", "Submit");
		
		boolean bFlag=UIDriver.checkElementpresent("feedbackMsg", 5);
		CSVReporter.reportPassFail(bFlag, "Feedback submission validation", "Feedback should be submitted", "Feebback is submitted successfully", "Feedback not submitted");

		UIDriver.clickElement("dialogClose");
	}	
	
	
	
	@Test(dataProvider="input")
	//@Parameters("DataRowID")
	public static void travelHelp(@Optional HashMap<String, String> input){
		//String componentName = new Object(){}.getClass().getEnclosingMethod().getName();
		//HashMap<String, String> input=Framework.getDataRecord(DataRowID,componentName);

		boolean bHelp1=UIDriver.checkElementpresentDynamic("linkdynamictext", "EMEA",10);
		boolean bHelp2=UIDriver.checkElementpresentDynamic("linkdynamictext", "Asia Pacific",2);
		boolean bHelp3=UIDriver.checkElementpresentDynamic("linkdynamictext", "North America",2);
		boolean bHelp4=UIDriver.checkElementpresentDynamic("linkdynamictext", " Latin America",2);
		
		if ((bHelp1 && bHelp2 && bHelp4 && bHelp3)==false){
			CSVReporter.reportFail("Validation of Travel Help Contacts", "EMEA, Asia Pacific, North America and Latin America Links should be present", "Links Not Found");
			return;
		}
		else{
			UIDriver.scrollToDynamicElement("linkdynamictext", "EMEA");
			CSVReporter.reportPass("Validation of Travel Help Contacts", "EMEA, Asia Pacific, North America and Latin America Links should be present", "Links Found");
		}
		
		
		String sHref=UIDriver.getElementAttribute("linkdynamictext","EMEA" ,"href");
		
		boolean bNewCase=sHref.contains("mailto");
		
		CSVReporter.reportPassFail(bNewCase, "Validation for Travel Help E-Mail Link", "Travel Help Email link should displayed", "Link leads to Email Window", "Link doesnot lead to Email Window");
		


			
	}
	
}