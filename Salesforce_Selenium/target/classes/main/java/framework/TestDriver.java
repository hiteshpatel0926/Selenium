package main.java.framework;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestDriver {
	public static int intRowNum;
	public static int intColNum;
	public static int intColCount;
	public static int intRowCount;
	public static int[] iCellPos;
	public static boolean boolFLag;
	// public static XSSFWorkbook objWorkbook;
	// public static XSSFSheet objSheet;
	// public static XSSFRow objRow;
	// public static XSSFCell objCell;
	public static String strFilePath;
	public static String strSheetName;
	public static String strDataSheetName;
	public static String strDataRowID;
	public static String reportPath;
	public static String reportTemplate;
	public static String testSetPath;
	public static String folderpath;
	public static int reporterRow = 2;
	public static String reportFolder;
	public static String testNGReportFolder;
	public static String testName;
	public static String screenshotFolder;
	public static String executionSummary;
	public static HashMap<String, String> input;

	// Method for Initializing All Variables
	public TestDriver(String strFolderPath) throws IOException, ScriptException {
		
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		String strDataSheetPath = strFolderPath + "//Data//TestData.csv";
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String strTimeStamp = timeStamp.replace(".", "_");
		String strReportFolder = /*strFolderPath +*/ "target\\ExecutionReport_" + strTimeStamp;
		File objFile = new File(strReportFolder);
		objFile.mkdir();
		String strReportPath = strReportFolder + "\\\\ExecutionReport.csv";
		String strSummaryReport=strReportFolder + "\\\\ExecutionSummary.csv";

		String strScreenShotFolder = System.getProperty("screenshotDirectory");
		
		//ENABLE THE BELOW CODE IF DEBUGGING/RUNNING FROM MAIN METHOD
		
		if (strScreenShotFolder == null) {
			strScreenShotFolder = "target\\surefire-reports" + "\\ScreenShots";
		}
		
		File objFile1 = new File(strScreenShotFolder);
		objFile1.mkdir();
		String strReportTemplate = strFolderPath + "////Report Template////ReportTemplate.csv";
		String strTestSetPath = strFolderPath + "//TestSet.csv";

		TestDriver.strDataSheetName = strDataSheetPath;
		TestDriver.reportPath = strReportPath;
		TestDriver.reportTemplate = strReportTemplate;
		TestDriver.folderpath = strFolderPath;
		TestDriver.testSetPath = strTestSetPath;
		TestDriver.reportFolder = strReportFolder;
		TestDriver.executionSummary = strSummaryReport;
		TestDriver.screenshotFolder = strScreenShotFolder;
		// TestDriver.createWorkBookObject();
		// TestDriver.getSheetObject();

		UIDriver.readOR();

	}

	/*public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (args.length == 0) {
			System.out.println("Arguments Not Set, Please set the arguments and re-execute");
		}
		
		String strFolderPath = args[0];
		String strBrowser = args[1];
		new TestDriver(strFolderPath);
		XMLDriver.createTestNGXML();
		new Reporter();
		BrowserDriver.strBrowser = strBrowser;
		BrowserDriver.getDriver(strBrowser);
		// Create object of TestNG Class
		TestNG runner = new TestNG();
		// Create a list of String
		List<String> suitefiles = new ArrayList<String>();
		// Add xml file which you have to execute
		suitefiles.add("testng.xml");
		new CSVReporter();
		// now set xml file for execution
		runner.setTestSuites(suitefiles);
		// finally execute the runner using run method
		runner.run();
		//Framework.runTestAll();
		// BrowserDriver.driver.close();
		// BrowserDriver.driver.quit();
		//File srcDir = new File("test-output");
		//File destDir = new File(testNGReportFolder);
		//FileUtils.copyDirectory(srcDir, destDir);
	}*/
}