package main.java.framework;

import static org.testng.Assert.assertTrue;
import main.java.framework.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.script.ScriptException;

import org.testng.Reporter;

/*import main.java.module.ModuleDriver;*/
//import ModuleDrivers.*;

class MethodDriver {
	Method oMethod;
	Object oDriver;

}

public class Framework {

	public static List<Method[]> allMethods = new ArrayList<Method[]>();
	public static List<Object> allDrivers = new ArrayList<Object>();
	public static HashMap<Method[], Object> objModuleDriver;
	public static int iMethod = 0;
	public static int iDriver = 0;
	public static String strTestCaseName;
	public static String result = "Pass";
	public static boolean exitTest = false;

	public static void runTest() throws IOException, ScriptException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		// HashMap<String, testcaseData>
		// excelData=TestDriver.readExcelAllData(TestDriver.strFilePath,
		// TestDriver.strSheetName);
		// List<HashMap<String, String>> arrTestcase=
		// TestDriver.readExcelAllDataSheet1(TestDriver.strFilePath,
		// TestDriver.strSheetName);
		List<HashMap<String, String>> arrTestcase = CSV_IO.readCSVFile(TestDriver.strFilePath);

		// System.out.println(excelData);
		// Framework.availableEngine();
		String strYN = "";
		String strComponentName = "";
		String strDataRowID = "";
		for (HashMap<String, String> aComponent : arrTestcase) {
			// HashMap<String,String> aComponent1=aComponent;
			// for (testcaseData td: excelData.values() ) {
			strYN = Utility.getinputvalue(aComponent, "YN");
			strComponentName = Utility.getinputvalue(aComponent, "componentName");
			strDataRowID = Utility.getinputvalue(aComponent, "dataRowID");

			if (strYN == null) {
				strYN = "Y";
			}
			if (strYN.equalsIgnoreCase("Y") == true) {
				System.out.println("----Executing Component '" + strComponentName + "' for Data Row ID : '"
						+ strDataRowID + "'----");

				// Method sActualMethod=getMethod(arrMethods,td.componentCode);

				MethodDriver objMethodDriver = getMethodAndDriver(strComponentName);

				Method sActualMethod = objMethodDriver.oMethod;
				Object oActualDriver = objMethodDriver.oDriver;

				// HashMap<String, String> objInput=getParameters(td);
				int intExecute = 0;
				if (sActualMethod != null) {
					TestDriver.strDataRowID = strDataRowID;
					if (strDataRowID == null || strDataRowID == "") {
						// System.out.println("Execute Component without Data Row
						// ID:"+strComponentName);
						HashMap<String, String> objInput = null;
						intExecute = intExecute + 1;
						try {
							sActualMethod.invoke(oActualDriver, objInput);
						} catch (Exception e) {
							System.out.println("Error in Component:" + strComponentName + ":" + e.toString());
							e.printStackTrace();
							Framework.result = "Fail";
						}

					} else {
						// List<HashMap<String, String>>
						// objInputs=TestDriver.readExcelAllDataSheet1(TestDriver.strDataSheetName,strComponentName);
						List<HashMap<String, String>> objInputs = CSV_IO.readCSVFile(TestDriver.strDataSheetName);
						for (HashMap<String, String> objInput : objInputs) {
							if (strDataRowID.equalsIgnoreCase(objInput.get("DataRowID"))
									&& strComponentName.equalsIgnoreCase(objInput.get("componentName"))) {
								intExecute = intExecute + 1;
								// System.out.println("Execute Component without Data Row ID:"+strDataRowID);
								try {
									sActualMethod.invoke(oActualDriver, objInput);
									break;
								} catch (Exception e) {
									System.out.println("Error in Component:" + strComponentName + ":" + e.toString());
									Framework.result = "Fail";
									e.printStackTrace();
								}
								// System.out.println("Output of "+td.componentCode +" is : " +
								// objResults.toString());

							}
						}
					}
				} else {
					System.out.println("Component Not Found in ModuleDriver " + strComponentName);
					return;
				}

				if (intExecute == 0) {
					System.out.println(
							"Data Row ID '" + strDataRowID + "'not given for component : '" + strComponentName + "'");
					Reporter.log(
							"Data Row ID '" + strDataRowID + "'not given for component : '" + strComponentName + "'");
					// Reporter1.reportAll("Warning", "Data Row ID '"+ strDataRowID +"'not found","
					// Component : '" +strComponentName +"'", "", "");
				}

			}

		}
		System.out.println("Execution completed...");
		Reporter.log("Execution completed...");
	}

	public static HashMap<String, String> getDataRecord(String strDataRowID, String strComponentName) {
		int intExecute = 0;
		List<HashMap<String, String>> objInputs = null;
		try {
			objInputs = CSV_IO.readCSVFile(TestDriver.strDataSheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (HashMap<String, String> objInput : objInputs) {
			if (strDataRowID.equalsIgnoreCase(objInput.get("DataRowID"))
					&& strComponentName.equalsIgnoreCase(objInput.get("componentName"))) {
				intExecute = intExecute + 1;
				return removeKeysIfEmpty(objInput);
//				return objInput;
			}
		}
		if (intExecute == 0) {
			System.out
					.println("Data Row ID '" + strDataRowID + "'not given for component : '" + strComponentName + "'");
			Reporter.log("Data Row ID '" + strDataRowID + "'not given for component : '" + strComponentName + "'");
			// Reporter1.reportAll("Warning", "Data Row ID '"+ strDataRowID +"'not found","
			// Component : '" +strComponentName +"'", "", "");
			return null;
		}
		return null;

	}

	public static List<HashMap<String, String>> removeRecordIfNo(List<HashMap<String, String>> arrTestSet) {
		for (Iterator<HashMap<String, String>> iterator = arrTestSet.iterator(); iterator.hasNext();) {
			HashMap<String, String> oTestSet = iterator.next();
			if (oTestSet.get("YN").contains("N")) {
				iterator.remove();
			}
		}
		return arrTestSet;
	}
	public static HashMap<String, String> removeKeysIfEmpty(HashMap<String, String> input){
		HashMap<String, String> input1= (HashMap<String, String>) input.clone();
		for(String key:input1.keySet()){
			if(input1.get(key).equals("") || (input1.get(key)==null)){
				input.remove(key);
			}
		}
		return input;
	}

	public static void runTestAll() throws IOException, ScriptException, NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		//new CSVReporter();

		// List<HashMap<String, String>> arrTestSet=
		// TestDriver.readExcelAllDataSheet1(TestDriver.testSetPath, "TestCases");
		List<HashMap<String, String>> arrTestSet = CSV_IO.readCSVFile(TestDriver.testSetPath);
		String[] aHeaders = { "Test Case Name", "Status" };
		/*CSVPrinter csvSummaryReport = CSV_IO.createNewCSVFile(TestDriver.executionSummary, aHeaders);*/

		for (HashMap<String, String> sTest : arrTestSet) {
			String strYN = sTest.get("YN");
			strTestCaseName = sTest.get("TestCase");
			result = "Pass";
			if (strYN.equalsIgnoreCase("Y")) {
				// BrowserDriver.getDriver(BrowserDriver.strBrowser);
				// XSSFWorkbook
				// objWorkbook=TestDriver.createWorkBookObject(TestDriver.reportPath);
				// TestDriver.writeDatainExcel(objWorkbook,"Summary", intRowS, 1,
				// strTestCaseName);
				// TestDriver.writeDatainExcel(objWorkbook,"Summary", intRowS, 2, "In
				// Progress");
				System.out.println("==============Executing Testcase " + strTestCaseName + "============");
				Reporter.log("==============Executing Testcase " + strTestCaseName + "============");
				String strTestCaseFile = TestDriver.folderpath + "//Testcases//" + strTestCaseName + ".csv";
				TestDriver.strFilePath = strTestCaseFile;
				TestDriver.strSheetName = "TestCase";
				try {
					Framework.runTest();
				} catch (Exception e) {
					System.out.println("Error in Test:" + strTestCaseName + ":" + e.toString());
					Reporter.log("Error in Test: " + strTestCaseName + ", Error Detail: " + e.toString());
				}
				/*String[] arrSummaryRecord = { strTestCaseName, result };*/
				
				/*CSV_IO.writeinCSV(csvSummaryReport, arrSummaryRecord);*/

			}

		}
		if (result.equalsIgnoreCase("fail")) {
			try {
				assertTrue(false);
			} catch (Exception e) {
				System.out.println("Test case Failed");
			}

		}

	}

	private static Method getMethod(Method[] arrMethods, String strMethod) {
		Method sActualMethod = null;
		for (Method sMethod : arrMethods) {
			if (sMethod.toString().contains(strMethod + "(")) {
				sActualMethod = sMethod;
				break;
			}
		}

		return sActualMethod;
	}

	private static MethodDriver getMethodAndDriver(String strMethod) {
		// Framework.allDrivers;
		/*CompositeDriver compositeDriver = new CompositeDriver();

		compositeDriver.toString();*/

		// HashMap<Method,ModuleDriver> objMethodDriver = null;
		MethodDriver objMethodDriver = new MethodDriver();
		List<Method[]> aMethodList = Framework.allMethods;
		int intCounter = 0;
		for (Method[] aMethod : aMethodList) {
			Method objActuaMethod = getMethod(aMethod, strMethod);
			if (objActuaMethod != null) {
				objMethodDriver.oMethod = objActuaMethod;
				objMethodDriver.oDriver = main.java.framework.Framework.allDrivers.get(intCounter);
			}
			intCounter++;
		}

		return objMethodDriver;
	}
}