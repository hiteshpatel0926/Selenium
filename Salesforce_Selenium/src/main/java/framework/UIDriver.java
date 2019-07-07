package main.java.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;




public class UIDriver {

	public static List<HashMap<String, String>> OR;
	
	public static void readOR() throws IOException, ScriptException
	{
		String strORFilePath=TestDriver.folderpath + "//OR//Repository.csv";
		
		List<HashMap<String, String>> objOR=CSV_IO.readCSVFile(strORFilePath);
		UIDriver.OR=objOR;
	}
	
	
	public static boolean checkElementpresentDOM(String strElementName,int iTimeOut){
		boolean bElementPresent=false;
		int intCount=0;
		while(intCount<iTimeOut){
			try {
				bElementPresent=(UIDriver.getElements(strElementName).size()>0);
			} catch (Exception e) {
				bElementPresent=false;	
			}
			intCount++;
			if (bElementPresent==false){
				UIDriver.wait(1);
			}
			else{
				break;
			}
		}	
		if(bElementPresent==false){
			System.out.println("Element Not Found:"+strElementName);
		}

		return bElementPresent;

	}
	
	public static boolean checkElementpresentDOM(String strElementName,String sParam,int iTimeOut){
		boolean bElementPresent=false;
		int intCount=0;
		while(intCount<iTimeOut){
			try {
				bElementPresent=(UIDriver.getElements(strElementName,sParam).size()>0);
			} catch (Exception e) {
				bElementPresent=false;	
			}
			intCount++;
			if (bElementPresent==false){
				UIDriver.wait(1);
			}
			else{
				break;
			}
		}	
		if(bElementPresent==false){
			System.out.println("Element Not Found:"+strElementName);
		}

		return bElementPresent;

	}
	
	public static boolean checkElementpresent(String strElementName,int iTimeOut){
		boolean bElementPresent=false;
		int intCount=0;
		while(intCount<iTimeOut){
			try {
				bElementPresent=UIDriver.getElement(strElementName).isDisplayed();
			} catch (Exception e) {
				bElementPresent=false;	
			}
			intCount++;
			if (bElementPresent==false){
				UIDriver.wait(1);
			}
			else{
				break;
			}
		}	
		if(bElementPresent==false){
			System.out.println("Element Not Found:"+strElementName + ":"+UIDriver.getElementProperty(strElementName));
		}

		return bElementPresent;

	}
	
	
	
	public static boolean checkElementpresentDynamic(String strElementName,String sParamValue,int iTimeOut){
		boolean bElementPresent=false;
		int intCount=0;
		while(intCount<iTimeOut){
			try {
				bElementPresent=UIDriver.getElementDynamic(strElementName,sParamValue).isDisplayed();
			} catch (Exception e) {
				bElementPresent=false;	
			}
			intCount++;
			if (bElementPresent==false){
				UIDriver.wait(1);
			}
			else{
				break;
			}
		}	
		if(bElementPresent==false){
			System.out.println("Element Not Found:"+strElementName + ":" + UIDriver.getElementProperty(strElementName).replace("#param#", sParamValue));
		}
		return bElementPresent;

	}

	public static boolean checkElementpresentDynamic(String strElementName,String sParam1,String sParam2, int iTimeOut){
		boolean bElementPresent=false;
		int intCount=0;
		while(intCount<iTimeOut){
			try {
				bElementPresent=UIDriver.getElementDynamic(strElementName,sParam1,sParam2).isDisplayed();
			} catch (Exception e) {
				bElementPresent=false;	
			}
			intCount++;
			if (bElementPresent==false){
				UIDriver.wait(1);
			}
			else{
				break;
			}
		}	
		if(bElementPresent==false){
			System.out.println("Element Not Found:"+strElementName + ":" + UIDriver.getElementProperty(strElementName).replace("#param1#", sParam1).replace("#param2#", sParam2));
		}
		return bElementPresent;

	}
	
	public static void wait(int iTimeinSeconds){
		try {
			Thread.sleep(iTimeinSeconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getElementProperty(String strElementName){
		
		String strElementProperty="";
		for (HashMap<String,String> objORRow:UIDriver.OR){
			String strElementNameOR=objORRow.get("elementName");
			if (strElementNameOR.equalsIgnoreCase(strElementName)){
				strElementProperty=objORRow.get("Property");
				break;
			}
			
		}
		if(strElementProperty.equals("")){
		System.out.println("Element Name Not Found in Reporitory : " + strElementName);
		}
		return strElementProperty;
		
	}
	
	public static void launchURL(String strURL){
		BrowserDriver.driver.get(strURL);
		
		
	}
	
	public static List<WebElement> getElementsByProperty(String strProperty)
	{
		//String strProperty=UIDriver.getElementProperty(strElementName);
		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		}
	
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}

		return objElements;
	}

	
	public static WebElement getElementByProperty(String strProperty)
	{
		//String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return null;
		}
		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0);}
		else {
			return null;
		}
	}

	
	public static WebElement getElementDynamic(String strElementName, String sParamValue)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		strProperty=strProperty.replace("#param#", sParamValue);
		 
		if(strProperty==""){
			return null;
		}
		
//		if (UIDriver.checkElementpresent(strElementName, 5)==false){
//			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
//			Reporter1.reportFail("Validation for Element", strElementName, "Element Not Present");
//		}

		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0);}
		else {
//			System.out.println("Element NOT Found:"+strElementName +":" +strProperty);
			return null;
		}
	}
	
	


	public static WebElement getElementDynamic(String strElementName, String sParam1, String sParam2)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		strProperty=strProperty.replace("#param1#", sParam1);
		strProperty=strProperty.replace("#param2#", sParam2);
		 
		if(strProperty==""){
			return null;
		}
		
//		if (UIDriver.checkElementpresent(strElementName, 5)==false){
//			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
//			Reporter1.reportFail("Validation for Element", strElementName, "Element Not Present");
//		}

		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0);}
		else {
//			System.out.println("Element NOT Found:"+strElementName +":" +strProperty);
			return null;
		}
	}

	public static WebElement getElement(String strElementName)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return null;
		}
		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0);}
		else {
			return null;
		}
	}

	public static List<WebElement> getElements(String strElementName,String sParam)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		strProperty=strProperty.replace("#param#", sParam);
		
		if(strProperty==""){
			return null;
		}
		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements;
			}
		else {
			return null;
		}
	}

	
	public static List<WebElement> getElements(String strElementName)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return null;
		}
		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements;
			}
		else {
			return null;
		}
	}
	
	public static void scrollToElement(String strElement){
		((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+UIDriver.getElement(strElement).getLocation().y+")");
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Scroll to Element", "Screen scolled to "+strElement, "", "");
		}
	}

	public static void scrollToElement(WebElement oElement){
		((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+oElement.getLocation().y+")");
		if(CSVReporter.bDetail==true){
//			Reporter1.reportAll("Done", "Scroll to Element", "Screen scolled to "+oElement.getText(), "", "");
		}
	}

	
	public static void setTextToElement(String strElement,String sText){
		JavascriptExecutor executor = (JavascriptExecutor)BrowserDriver.driver;
		executor.executeScript("arguments[0].innerHTML = arguments[1]", UIDriver.getElement(strElement), sText);
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Set Element Text", strElement+" element text is set  "+sText, "", "");
		}

	}

	public static void setTextToElement(WebElement oElement,String sText){
		JavascriptExecutor executor = (JavascriptExecutor)BrowserDriver.driver;
		executor.executeScript("arguments[0].innerHTML = arguments[1]", oElement, sText);
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Set Element Text", " element text is set  "+sText, "", "");
		}

	}

	public static void clickElementJS(String strElement){
		JavascriptExecutor executor = (JavascriptExecutor)BrowserDriver.driver;
		executor.executeScript("arguments[0].click();", UIDriver.getElement(strElement));
		UIDriver.wait(2);
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Element clicked", strElement, "", "");
		}

	}

	public static void clickElementJS(String strElement,String sParam){
		JavascriptExecutor executor = (JavascriptExecutor)BrowserDriver.driver;
		executor.executeScript("arguments[0].click();", UIDriver.getElementDynamic(strElement,sParam));
		UIDriver.wait(2);
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Element clicked", strElement, "", "");
		}

	}

	
	public static void scrollToDynamicElement(String strElementName,String sParamValue){
		((JavascriptExecutor)BrowserDriver.driver).executeScript("window.scrollTo(0,"+UIDriver.getElementDynamic(strElementName, sParamValue).getLocation().y+")");
	}

	public static String getElementAttribute(String strElementName,String strAttribute)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return "";
		}
		
		if (UIDriver.checkElementpresentDOM(strElementName, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
			return "" ;
		}

		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0).getAttribute(strAttribute);}
		else {
			return "";
		}
	}

	public static String getElementAttribute(String strElementName,String sParam,String strAttribute)
	{
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return "";
		}
		strProperty=strProperty.replace("#param#", sParam);
		
		if (UIDriver.checkElementpresentDOM(strElementName, sParam, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
			return "" ;
		}

		
		List<WebElement> objElements;
		
		objElements=BrowserDriver.driver.findElements(By.id(strProperty));
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.name(strProperty));
		}
	
		
		if(objElements.size()==0){
			objElements=BrowserDriver.driver.findElements(By.xpath(strProperty));
		}
	
//		if(objElements.size()==0){
//			objElements=BrowserDriver.driver.findElements(By.cssSelector(strProperty));
//			//objElements=BrowserDriver.driver.findElements(By.cssSelector("." + strProperty));
//		}
		
		if (objElements.size()>0){
			return objElements.get(0).getAttribute(strAttribute);}
		else {
			return "";
		}
	}

	public static void clickElementDynamic(String strElementName,String sParamValue){
		
		if(UIDriver.checkElementpresentDynamic(strElementName, sParamValue, 15)){
			WebElement oElement=UIDriver.getElementDynamic(strElementName, sParamValue);
			try {
				oElement.click();
			} catch (Exception e) {
				System.out.println("Clicking Element through JS:"+ strElementName +":"+ getElementProperty(strElementName));
				clickElementJS(strElementName,sParamValue);
			}
			
			wait(4);
		}
		else{
			
		}
		if(CSVReporter.bDetail==true){
		CSVReporter.reportAll("Done", "Element clicked", strElementName, "", "");
		}
	}
	
	public static void switchToTab(int iWindow){
		ArrayList<String> allTabs = new ArrayList<String>(BrowserDriver.driver.getWindowHandles());
		if(allTabs.size()>=iWindow){
			BrowserDriver.driver.switchTo().window(allTabs.get(iWindow));
	
		}
		else{
			System.out.println("Window not Found:"+iWindow);
		}
		if(CSVReporter.bDetail==true){
			CSVReporter.reportAll("Done", "Switch To Tab:"+iWindow, "Window Switched", "", "");
		}
	}
	
	
	public static void switchToFrame(String sAttribute,String sAttributeValue){
		List<WebElement> oFrames=UIDriver.getElementsByProperty("//iframe");
		
		int iFrame=0;
	
		for (WebElement oFrame:oFrames){
			String sTitle=oFrame.getAttribute(sAttribute);
			if(sTitle.contains(sAttributeValue)){
				BrowserDriver.driver.switchTo().frame(oFrame);
				iFrame++;
				break;
			}
			
		}
		if(iFrame==0){
			System.out.println("Frame NOT Found:"+sAttribute+"="+sAttributeValue);
		}
	
	}
	
	public static void closeTab(int iWindow){
		ArrayList<String> allTabs = new ArrayList<String>(BrowserDriver.driver.getWindowHandles());
		if(allTabs.size()>=iWindow){
			BrowserDriver.driver.switchTo().window(allTabs.get(iWindow)).close();;
		}
		else{
			System.out.println("Window not Found:"+iWindow);
		}
	}
	
	
	
	public static void clickElement(String strElementName){
		String strProperty=UIDriver.getElementProperty(strElementName);
		
		if(strProperty==""){
			return;
		}
		
		if (UIDriver.checkElementpresent(strElementName, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
//			Reporter1.reportFail("Validation for Element", strElementName, "Element Not Present");
			return;
		}
		
		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		
		
//	
//		WebDriverWait wait = new WebDriverWait(BrowserDriver.driver, 20);
//		wait.until(ExpectedConditions.elementToBeClickable(objElement));
		
		if(objElement.isEnabled()){
			
			try{
				try {
					objElement.click();
				} catch (Exception e) {
					System.out.println("Clicking Element through JS:"+ strElementName +":"+ getElementProperty(strElementName));
					clickElementJS(strElementName);	
				}
				
				if(CSVReporter.bDetail==true){
					CSVReporter.reportAll("Done", "Click Element", strElementName, "", "");
				}
				wait(3);
				}
			catch (Exception e) {
				System.out.println("Element Not Clickable:"+e.toString());
			}
			}
		else {
			System.out.println("Failed to Click Element : '" + strElementName + "' : Element is disabled");
			}	
		}
	
	
	
	public static void setValue(String strElementName,String strValue){
		String strProperty=UIDriver.getElementProperty(strElementName);
		if(strProperty==""){
			System.out.println("verify Element in OR " + strElementName);
			return;
		}
		
		if(strValue.equals("") || strValue==null){
			System.out.println("No Value to set in "+strElementName+":"+strProperty);
			return;
		}
		
		if(strValue.equalsIgnoreCase("blank")){
			strValue="";
		}

		
		if (UIDriver.checkElementpresent(strElementName, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
		}

		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		
		if(objElement.isEnabled()){
			objElement.clear();
			objElement.sendKeys(strValue);
			if(strElementName.equalsIgnoreCase("password")){
					strValue="";	
			}
			if(CSVReporter.bDetail==true){
				CSVReporter.reportAll("Done", "Set value in Element:"+strElementName, "Value set:"+strValue, "", "");
			}
			}
		else {
			System.out.println("Failed to Set value in Element : '" + strElementName + "' : Element is disabled");
			}	
		}

	public static void setValueAndEnter(String strElementName,String strValue){
		String strProperty=UIDriver.getElementProperty(strElementName);
		if(strProperty==""){
			System.out.println("verify Element in OR " + strElementName);
			return;
		}
		
		if(strValue.equals("") || strValue==null){
			System.out.println("No Value to set in "+strElementName+":"+strProperty);
			return;
		}
		
		if(strValue.equalsIgnoreCase("blank")){
			strValue="";
		}

		
		if (UIDriver.checkElementpresent(strElementName, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
		}

		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		
		if(objElement.isEnabled()){
			objElement.clear();
			objElement.sendKeys(strValue);
			UIDriver.wait(2);
			objElement.sendKeys(Keys.ENTER);
			if(strElementName.equalsIgnoreCase("password")){
					strValue="";	
			}
			if(CSVReporter.bDetail==true){
				CSVReporter.reportAll("Done", "Set value in Element:"+strElementName, "Value set:"+strValue, "", "");
			}
			}
		else {
			System.out.println("Failed to Set value in Element : '" + strElementName + "' : Element is disabled");
			}	
		}

	public static void setValue(String strElementName,String sParam,String strValue){
		String strProperty=UIDriver.getElementProperty(strElementName);
		strProperty=strProperty.replace("#param#", sParam);
		
		if(strValue.equals("") || strValue==null){
			System.out.println("No Value to set in "+strElementName+":"+strProperty);
			return;
		}
		
		if(strValue.equalsIgnoreCase("blank")){
			strValue="";
		}
		
		if(strProperty==""){
			System.out.println("verify Element in OR " + strElementName);
			return;
		}
		
		if (UIDriver.checkElementpresentDynamic(strElementName,sParam, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
			return;
		}

		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		
		if(objElement.isEnabled()){
			objElement.clear();
			objElement.sendKeys(strValue);
			if(strElementName.equalsIgnoreCase("password")){
					strValue="";	
			}
			if(CSVReporter.bDetail==true){
				CSVReporter.reportAll("Done", "Set value in Element:"+strElementName, "Value set:"+strValue, "", "");
			}
			}
		else {
			System.out.println("Failed to Set value in Element : '" + strElementName + "' : Element is disabled");
			}	
		}
	

	
	public static String getElementText(String strElementName){
		if(UIDriver.checkElementpresent(strElementName, 5)==false){
			System.out.println("Element NOT Found:"+strElementName);
			return "";
		}
		WebElement objElement=UIDriver.getElement(strElementName);
		return objElement.getText();
	}

	public static String getElementText(String strElementName,String sParam){
		if(UIDriver.checkElementpresentDynamic(strElementName, sParam,5)==false){
			System.out.println("Element NOT Found:"+strElementName);
			return "";
		}
		WebElement objElement=UIDriver.getElementDynamic(strElementName,sParam);
		return objElement.getText();
	}


	
	public static void validateElementwithText(String sText){
		String sXPath="//*[text()='"+ sText +"']";
		
		if (sText.equals("") || sText==null){
			System.out.println("Text Not Given for Element:");
			return;
		}
		
		try {
			List<WebElement> objElements=getElementsByProperty(sXPath);
			boolean bEle=false;
			if (objElements==null){
				bEle=false;
			}
			else{
				bEle=(objElements.size()>0);
			}
			
			CSVReporter.reportPassFail(bEle,"Validation for Element with Text:"+sText, "Element should be present", "Element is Present","Element is NOT Present");
		} catch (Exception e) {
			CSVReporter.reportFail("Validation for Element with Text:"+sText, "Element should be present", "Element is NOT Present");
			System.out.println("Element with Text '"+sText +"' is NOT present");
		}
		
		
		
		
		
	}

	public static void selectValue(String strElementName,String strValue,String strBy){
		String strProperty=UIDriver.getElementProperty(strElementName);
		if(strProperty==""){
			System.out.println("verify Element in OR " + strElementName);
			return;
		}
		
		if (UIDriver.checkElementpresent(strElementName, 5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
		}

		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		Select objSelect=new Select(objElement);
		
		
		if(objElement.isEnabled()){
			if(strBy.equalsIgnoreCase("index")){
				objSelect.selectByIndex(Integer.parseInt(strValue));
			}
			else if(strBy.equalsIgnoreCase("value")){
				objSelect.selectByValue(strValue);
			}
			else if(strBy.equalsIgnoreCase("text")){
				objSelect.selectByVisibleText(strValue);
			}
			if(CSVReporter.bDetail==true){
				CSVReporter.reportAll("Done", "Select value in Element:"+strElementName, "Value set:"+strValue, "", "");
			}

			}
		else {
			System.out.println("Failed to Selected value in Element : '" + strElementName + "' : Element is disabled");
			}	
		}

	
	public static void selectCheckbox(String strElementName){
		WebElement oCheck=UIDriver.getElement(strElementName);
		if(oCheck!=null){
			if(oCheck.isSelected()==false){
				oCheck.click();
			}
		}
		
		
	}
	public static void selectValue(String strElementName,String sParam,String strValue,String strBy){
		String strProperty=UIDriver.getElementProperty(strElementName);
		strProperty=strProperty.replace("#param#", sParam);
		if(strProperty==""){
			System.out.println("verify Element in OR " + strElementName);
			return;
		}
		
		if (UIDriver.checkElementpresentDynamic(strElementName, sParam,5)==false){
			System.out.println("Element Not Found:"+strElementName +" : '"+ strProperty +"'");
			CSVReporter.reportFail("Validation for Element", strElementName, "Element Not Present");
		}

		
		WebElement objElement=UIDriver.getElementByProperty(strProperty);
		Select objSelect=new Select(objElement);
		
		
		if(objElement.isEnabled()){
			if(strBy.equalsIgnoreCase("index")){
				objSelect.selectByIndex(Integer.parseInt(strValue));
			}
			else if(strBy.equalsIgnoreCase("value")){
				objSelect.selectByValue(strValue);
			}
			else if(strBy.equalsIgnoreCase("text")){
				objSelect.selectByVisibleText(strValue);
			}
			if(CSVReporter.bDetail==true){
				CSVReporter.reportAll("Done", "Select value in Element:"+strElementName, "Value set:"+strValue, "", "");
			}

			}
		else {
			System.out.println("Failed to Selected value in Element : '" + strElementName + "' : Element is disabled");
			}	
		}


	
//Class Closing brace
}
