package main.java.framework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.eventbus.AllowConcurrentEvents;


public class CSVReporter {
	
	public static boolean bDetail=false;
	public static CSVPrinter csvReport;
	public static CSVPrinter csvSummaryReport=null;
	
	public CSVReporter() {
		String[] aHeaders={"S.No.", "Test Case Name","stepName", "Expected Result", "Actual Result","Pass/Fail","Timestamp"};
		CSVPrinter csvReport=CSV_IO.createNewCSVFile(TestDriver.reportPath, aHeaders);
		CSVReporter.csvReport=csvReport;
		
		String[] aHeaders1={"Test Case Name","Status"};
		CSVPrinter csvSummaryReport=CSV_IO.createNewCSVFile(TestDriver.executionSummary, aHeaders1);
		CSVReporter.csvSummaryReport=csvSummaryReport;
	}
	
	
	
	public static void reportPassFail(boolean bPassFail,String strStep,String strExpected, String strActualPass,String strActualFail){
		if(bPassFail==true){
			reportAll("Pass", strStep, strExpected, strActualPass, strActualFail);
			Reporter.log("Passed:"+strActualPass);
		}
		else{
			reportAll("Fail", strStep, strExpected, strActualPass, strActualFail);
			Reporter.log("Failed:"+strActualFail);
			try {
//				Assert.fail(strActualFail);
			} catch (Exception e) {
				System.out.println("TestNG:"+strActualFail);
			}
			
		}
		
		
	}
	
	public static void reportPass(String strStep,String strExpected, String strActual){
			reportAll("Pass", strStep, strExpected, strActual, "");
	}

	public static void reportFail(String strStep,String strExpected, String strActual){
		reportAll("Fail", strStep, strExpected, "", strActual);
	}
	
	public static void reportWarning(String strStep,String strExpected, String strActual){
		reportAll("Warning", strStep, strExpected, strActual, "");
	}
	
	public static void reportDone(String strStep,String strExpected, String strActual){
		reportAll("Done", strStep, strExpected, strActual, "");
	}


	public static void reportAll(String strPassFail,String strStep,String strExpected, String strActualPass,String strActualFail) {
		
		if (Framework.result.equalsIgnoreCase("fail")==false){
			if(strPassFail.equalsIgnoreCase("fail")){
				Framework.result="Fail";
			}
		}	
		//========Capture ScreenShot====================================
		String strScreenShotFile = null;
		String timeStamp = null,strTimeStamp;
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		strTimeStamp=timeStamp.replace(".", "_");
		 strScreenShotFile=TestDriver.screenshotFolder+"\\Screenshot_"+strTimeStamp + "_" + TestDriver.reporterRow + ".jpg";
//		 strScreenShotFile="ScreenShots"+"\\Screenshot_"+strTimeStamp + "_" + excelconnect.reporterRow + ".jpg";
		// BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		 //ImageIO.write(screenFullImage, "jpg", new File(strScreenShotFile));
		 File scrFile = ((TakesScreenshot) BrowserDriver.driver).getScreenshotAs(OutputType.FILE);
		 
			try {
				FileUtils.copyFile(scrFile, new File(strScreenShotFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//=================================================================
		
			try {
				URL url = new URL("file:ScreenShots/"+"Screenshot_"+strTimeStamp + "_" + TestDriver.reporterRow + ".jpg");
				strScreenShotFile=url.toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		int intSNo=TestDriver.reporterRow-1;
		System.out.println("***"+strPassFail+"*** - "+strStep+"|"+strExpected+"|"+strActualPass);
		if (strPassFail.equalsIgnoreCase("Pass")){
			
			String[] arrReportRecord={Integer.toString(intSNo),TestDriver.testName,strStep,strExpected,strActualPass,"Pass",timeStamp};
			try {
				CSV_IO.writeinCSV(CSVReporter.csvReport, arrReportRecord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (strPassFail.equalsIgnoreCase("Fail")){
			String[] arrReportRecord={Integer.toString(intSNo),TestDriver.testName,strStep,strExpected,strActualFail,"Fail",timeStamp};
			try {
				CSV_IO.writeinCSV(CSVReporter.csvReport, arrReportRecord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		else if (strPassFail.equalsIgnoreCase("Done")){
			String[] arrReportRecord={Integer.toString(intSNo),TestDriver.testName,strStep,strExpected,strActualPass,"Done",timeStamp};
			try {
				CSV_IO.writeinCSV(CSVReporter.csvReport, arrReportRecord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (strPassFail.equalsIgnoreCase("warning")){
			String[] arrReportRecord={Integer.toString(intSNo),TestDriver.testName,strStep,strExpected,strActualPass,"Warning"};
			try {
				CSV_IO.writeinCSV(CSVReporter.csvReport, arrReportRecord);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		TestDriver.reporterRow++;
		intSNo++;
		
	
	
	}
	
	



//Class Closing brace
}
