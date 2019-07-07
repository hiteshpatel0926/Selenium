/******************************************************************************
$Id : AppiumNativeDriver.java 12/23/2016 4:08:45 PM
Copyright © 2016 Capgemini Group of companies. All rights reserved
(Subject to Limited Distribution and Restricted Disclosure Only.)
THIS SOURCE FILE MAY CONTAIN INFORMATION WHICH IS THE PROPRIETARY
INFORMATION OF CAPGEMINI GROUP OF COMPANIES AND IS INTENDED FOR USE
ONLY BY THE ENTITY WHO IS ENTITLED TO AND MAY CONTAIN
INFORMATION THAT IS PRIVILEGED, CONFIDENTIAL, OR EXEMPT FROM
DISCLOSURE UNDER APPLICABLE LAW.
YOUR ACCESS TO THIS SOURCE FILE IS GOVERNED BY THE TERMS AND
CONDITIONS OF AN AGREEMENT BETWEEN YOU AND CAPGEMINI GROUP OF COMPANIES.
The USE, DISCLOSURE REPRODUCTION OR TRANSFER OF THIS PROGRAM IS
RESTRICTED AS SET FORTH THEREIN.
 ******************************************************************************/

package cbfx.ui.appium;

import static cbf.engine.TestResultLogger.handleError;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cbf.harness.Harness;
import cbf.utils.LogUtils;
import cbfx.ui.objectmap.ObjectMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import junit.framework.Assert;

/**
 * 
 * Supporting mobile applications like keyEvents,clear,click,
 * select,verifyText,checkPage etc.
 *
 */


public class AppiumNativeDriver {
	
	/**
	 * Overloaded Constructor to initialize AppiumNativedriver and desired capabilities
      * @param params
	 */

	public AppiumNativeDriver(Map params) {

		Map obj = (Map<String, Object>) Harness.configuration().get("ObjectMap");
		objMap = (ObjectMap) Harness.pluginManager().getPlugin(
				(String) obj.get("plugin"), null);
		try {
			DesiredCapabilities capabilities = getcapabilities(params);
			this.driver = getDriver(params, capabilities);
		} catch (Exception e) {
			handleError("Failed to Setup device", e);
		}
	}

	private DesiredCapabilities getcapabilities(Map params) {
		DesiredCapabilities caps = new DesiredCapabilities();
		if ("ANDROID".equalsIgnoreCase((String) params.get(PLATFORM_NAME))) {
			caps.setCapability(PLATFORM_NAME, params.get(PLATFORM_NAME));
			caps.setCapability(VERSION, params.get(VERSION));
			caps.setCapability(DEVICE_NAME, params.get(DEVICE_NAME));
			caps.setCapability("androidPackage", params.get("appPackage"));
			caps.setCapability("androidActivity", params.get("appActivity"));
			caps.setCapability("app-wait-activity",
					"com.yulong.android.launcher3.Launcher");
			logger.trace("Capabilities are assigned for Native - Android : - Successfull");
			
		} else {
			caps.setCapability(PLATFORM_NAME, params.get(PLATFORM_NAME));
			caps.setCapability("platformVersion", params.get(VERSION));
			caps.setCapability(DEVICE_NAME, params.get(DEVICE_NAME));
			caps.setCapability("app", params.get("appPath"));
			logger.trace("Capabilities are assigned for Natives - ios : - Successfull");

		}
		return caps;

	}
	
	/**
	 * 
	 * @param params
	 *             parameters
	 * @param capabilities
	 * @return getDriver
	 */

	public AppiumDriver getDriver(Map params, DesiredCapabilities capabilities) {

		if ("android".equalsIgnoreCase((String) params.get(PLATFORM_NAME))) {
			try {
				driver = new AndroidDriver<WebElement>(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				logger.trace("Driver is assigned for Native - Android : - Successfull");
			} catch (Exception e) {
				handleError("Failed to Setup Android device", e);
			}
		} else {
			try {
				driver = new IOSDriver<IOSElement>(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				logger.trace("Driver is assigned for Native - ios : - Successfull");
			} catch (Exception e) {
				handleError("Failed to Setup ios device", e);
			}
		}
		return driver;
	}

	/**
	 * Launches the Application
	 * 
	 * Application to be opened.
	 */
	public boolean launchApplication() {
		try {
			driver.launchApp();
			logger.trace("LaunchApp - Successfull");
		} catch (Exception e) {
			handleError("Launch Application: - Invalid control type: %s", e);
		}
		return true;
	}

	/**
	 * Sets the value to the specified UI Element
	 * 
	 * @param elementName
	 *            name of element
	 * @param value
	 *            value to be set on element
	 */
	public boolean setValue(String elementName, String value) {
		try {
			WebElement element = getControl(elementName);
			webWait(time);
			element.sendKeys(value);
			logger.trace("enterText - in: " + elementName + " value: " + value
					+ " - Successfull");
		} catch (Exception e) {
			handleError("Set Value: "+ value +" for element: "+elementName, e);
		}
		return true;
	}

	/**
	 * Clears the value of specified element
	 * 
	 * @param elementName
	 *            element to be cleared
	 */
	public boolean clear(String elementName) {
		try {
			getControl(elementName);
			webWait(time);
			logger.trace("ClearText - " + elementName + " -Successfull");
		} catch (Exception e) {
			handleError("clear: Invalid control type: " + elementName, e);
		}
		return true;
	}

	/**
	 * Clicks on the specified element
	 * 
	 * @param elementName
	 *            element to be clicked
	 */
	public boolean click(String elementName) {
		try {
			WebElement element = getControl(elementName);
			webWait(time);
			element.click();
			logger.trace("touch - " + elementName + "-Successfull");
		} catch (Exception e) {
			handleError("click: touch - Invalid control type: " + elementName,	e);
		}
		return true;
	}

	/**
	 * Compares actual and expected text from the application
	 * 
	 * @param elementName
	 *            element for which text is to be checked
	 * @param expectedText
	 *            expected text
	 * @return boolean result
	 */
	public boolean verifyText(String elementName, String expectedText) {
		try {
			WebElement element = getControl(elementName);
			check(element.getText().trim(), expectedText);
			webWait(time);
		} catch (Exception e) {
			handleError("verifyText: Property does not Displayed", e);
		}
		return true;
	}

	/**
	 * Checks whether page title matches or not
	 * 
	 * @param pageTitle
	 *            title of page opened
	 * @return true or false result
	 */
	public boolean checkPage(String pageTitle) {
		try {
			if (pageTitle.equals(driver.getTitle())) {
				return true;
			}
		} catch (Exception e) {
			handleError("Check Page: Checking the page title: " + pageTitle, e);
		}
		return false;
	}

	
/**
 Checks Whether the checkbox is checked or not

 * 
 * @param elementName
 * @return
 * @throws Exception
 */
	public boolean checkbox(String elementName){
		try {
			WebElement element = getControl(elementName);
			if (!element.isSelected()) {
				element.click();
				logger.trace("Checkbox is checked Successfully");
			}
			logger.trace("Checkbox is checked - Already");
			webWait(time);
		} catch (Exception e) {
			handleError("Check Box: Invalid control type: "+	elementName, e);
		}
		return true;
	}
	
	/**
	 * Close the Application
	 * 
	 * Close the application which was provided in the capabilities at session creation.
	 */
	
	// Close App
	public boolean closeApp() {
		try {
			driver.closeApp();
			logger.trace("CloseApp Successfull");
			webWait(time);
		} catch (Exception e) {
			logger.handleError("Close App: Error while Closing Application", e);
		}
		return true;
	}
	
	/**
	 * Close the Application
	 * 
	 * Closes the keyboard by using driver.hideKeyboard()
	 */

	// close Keyboard
	public boolean closeKeyboard() {

		try {
			driver.hideKeyboard();
			logger.trace("CloseApp Successfull");
			webWait(time);
		} catch (Exception e) {
			logger.handleError("Close Key Board: Error while Closing Keyboard", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It waits dynamically for specified condition
	 */


	public void dynamicWait(String elementName) {
		Map pageMap = (Map) info.get(elementName);
		String getObj = (String) pageMap.get("ID");
		int getVal = 0;

		if (getObj.contains(":id")) {
			getVal = 1;
		} else if (getObj.contains("class")) {
			getVal = 2;
		}
		switch (getVal) {
		case 1:
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.id(getObj)));
			break;
		case 2:
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.ignoring(NoSuchElementException.class);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By
					.className(getObj.split("/")[1])));
			break;
		default:
			break;
		}
	}
	
	/**
	 * 
	 * It waits for WebElement for specified time
	 */


	public boolean waitForWebElement(String elementName) {
		try {
			for (int ix = 0; ix < 30; ix++) {
				getControl(elementName);
				break;
			}
			logger.trace("WaitForWebElement : " + elementName
					+ " : -Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Wait For WebElement: Invalid control type: "+elementName, e);
		}
		return true;
	}
	
	/**
	 * To move back to the previous screen
	 * @return
	 */


	// goBack
	public boolean goBack() {

		try {
			driver.navigate().back();
			logger.trace("goBack - Action Performed Successfully");
			webWait(time);
		} catch (Exception e) {
			handleError("Go Back: Failed to Move Back to Previous Screen", e);
		}
		return true;
	}
	
	/**
	 * Checking the element
	 * @param getTxtVal
	 * @param txtCheck
	 */


	// Check Message
	public void check(String getTxtVal, String txtCheck) {
		try {
			Assert.assertEquals(getTxtVal, txtCheck);
			logger.trace("Check - " + getTxtVal + " : " + txtCheck
					+ " : Successfull");
		} catch (Exception e) {
			handleError("Check: Message is not displayed Properly "+ getTxtVal, e);
		}
	}

	/**
	 * Identifies the locator as needed by webDriver object
	 * 
	 * @param elementName
	 *            Name of the element whose locator is required
	 * @return Identified locator as Web Element
	 */

	
	public WebElement getControl(String elementName) {
		Map<String, String> locator = new HashMap<>();
		Map<String, String> locators = getLocator(elementName);
		String className = locator.get("Class");
		return getProperty(locators, className);
	}
/**
 * Gets the locators
 * @param element
 * @return locatorMap
 */
	public Map getLocator(String element) {

		Map<String, String> locatorMap = null;
		List<Map> pageLocators = objMap.ObjectMaps(element.split("\\.")[0]);
		for (Map locator : pageLocators) {
			if (locator.get("elementName").equals(element.split("\\.")[1])) {
				locatorMap = locator;
				break;
			}
		}
		return locatorMap;
	}
	
	/**
	 * 
	 * @param locator
	 * @param ClassName
	 * @return
	 */

	public WebElement getProperty(Map locator, String className) {

		WebElement element;
		if (locator.get("xpath") != null) {
			webWait(time);
			element = driver.findElementByXPath((String) locator.get("xpath"));
			return element;
		}
		return null;
	}
	
/**
 * It waits for a specified time
 * @param time
 */

	public void webWait(long time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS) ;
	}
	
	/**
	 * 
	 * It releases the driver by using driver.quit()

	 */


	public void releaseDriver()  {
		try {
			driver.quit();
			logger.trace("releaseDriver Successfull");
		} catch (Exception e) {
			handleError("Release Driver: Failed to release driver", e);
		}
	}
	
	/**
	 * 
	 * It takes a screenshots of that file
	 */


	public File takescreenshot() {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			return ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);

		} catch (Exception e) {
			handleError("Take screens hot: DumpScreens - Failed", e);
		}
		return null;
	}

	/**
	 * 
	 * It Zoom the given element by using  driver.zoom(element)
	 * @param elementName
	 *            Name of the element whose locator is required
	 */

	// zoomElement
	public boolean zoomElement(String elementName) {
		try {
			WebElement element = getControl(elementName);
			driver.zoom(element);
			logger.trace("zoomElement - in:" + elementName + " Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Zoom Element: Invalid control type %s "+ elementName, e);
		}
		return true;
	}

	/**
	 * 
	 * It Zoom the given element by giving the coordinates x and y
	 */

	// zoomXY
	public boolean zoomXY(int x, int y) {
		try {
			driver.zoom(x, y);
			logger.trace("zoomXY - Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("zoomXY: Invalid control type "+x+","+y, e );
		}
		return true;
	}
	
	/**
	 * 
	 * @param elementName
	 * @return
	 * @throws Exception
	 */

	// pinchElement
	public boolean pinchElement(String elementName){
		try {
			WebElement element = getControl(elementName);
			driver.pinch(element);
			logger.trace("pinchElement - in:" + elementName + " -Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Pinch Element: Invalid control type: "+ elementName, e);
		}
		return true;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */

	// pinchXY
	public boolean pinchXY(int x, int y){

		try {
			driver.pinch(x, y);
			logger.trace("pinchXY -  - Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("PinchXY: Invalid control type ", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It lock the screen by giving specified time
	 */


	// lockScreen
	public boolean lockScreen(int sec) {
		try {
			driver.lockScreen(sec);
			logger.trace("lockScreen -  - Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Lock Screen: Invalid control type ", e);
		}
		return true;
	}

	/**
	 * 
	 * to tap on a specific location by giving the coordinates.(X and Y).
	 */

	// tap
	public boolean tap(int x, int y) {
		final TouchAction action = new TouchAction(driver);
		try {
			action.tap(x, y).perform();
			logger.trace("tap -  - Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Tap: Invalid control type ", e);
		}
		return true;
	}

	/**
	 * 
	 * performs an action longPressElement by action 
	 * @param elementName
	 *            Name of the element 
	 *            
	 */

	// LongPressElement
	public boolean longPressElement(String elementName) {
		final TouchAction action = new TouchAction(driver);
		try {
			WebElement element = getControl(elementName);
			action.longPress(element).perform();
			logger.trace("LongPressElement - " + elementName
					+ " :Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Long Press Element: Invalid control type "+ elementName, e);
		}
		return true;
	}
	
	/**
	 * 
	 * It resets the application by closing and reopening  the application
	 */


	public boolean resetApp() {
		try {
			driver.resetApp();
			webWait(time);
			logger.trace("resetApp - Successfull");
		} catch (Exception e) {
			handleError("ResetApp: Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 *It rotates the screen orientation to Landscape in Native application
	 */


	public boolean switchToLandscape() {
		try {
			// HasNetworkConnection.setConnection(Connection)
			driver.rotate(ScreenOrientation.LANDSCAPE);
			webWait(time);
			logger.trace("switchToLandscape - Successfull");
		} catch (Exception e) {
			handleError("Switch To Landscape - Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It rotates the screen orientation to potrait in Native application
	 */


	public boolean switchToPortrait() {

		try {
			driver.rotate(ScreenOrientation.PORTRAIT);
			webWait(time);
			logger.trace("switchToPortrait - Successfull");
		} catch (Exception e) {
			handleError("switchToPortrait - Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It gives the text from the element
	 * @param elementName
	 *            Name of the element 
	 */


	public String getValue(String elementName) {

		try {
			WebElement element = getControl(elementName);
			return element.getText();
		} catch (Exception e) {
			handleError("Get Value: " + elementName + "  Failed.", e);
		}
		return null;
	}
	
	/**
	 * 
	 * It gives the all elements from the Dropdown
	 * @param elementName
	 *            Name of the element 
	 * 
	 */


	public List<WebElement> getAllElementsfromDropdown(String elementName) {

		try {
			WebElement element = getControl(elementName);
			Select select = new Select(element);
			return select.getOptions();
		} catch (Exception e) {
			handleError("Get All Elements from Dropdown  : " + elementName+ " Failed.", e);
		}
		return Collections.emptyList();  
	}
	
	/**
	 * 
	 * Runs the current application as a background application for the time requested. 
	 * @param Seconds
	 * 
	 */

 public void runAppInBackground(int seconds) {
		try {
			driver.runAppInBackground(seconds);
			logger.trace("runAppInBackground - " + seconds + " : Successfull");
		} catch (Exception e) {
			handleError("Run App In Background - " + seconds + "  Failed", e);
		}
	}
	
	/**
	 * 
	 * Performs horizontal swipe operation from left to right or right to left in given time  
	 * @param Time
	 * 
	 */


	public boolean swipeHorizontal(int time) {
		try {
			Dimension size = driver.manage().window().getSize();
			int xStart = (int) (size.width * 0.80);
			int xEnd = (int) (size.width * 0.20);
			int yStart = size.height / 2;
			driver.swipe(xStart, yStart, xEnd, yStart, time);
			logger.trace("swipeHorizontal - - Successfull");
			
		} catch (Exception e) {
			handleError("Swipe Horizontal: Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It swipe down the screen from required position
   * @param Time
	 * 
	 */


	public boolean swipeDown(int time) {

		try {
			Dimension size = driver.manage().window().getSize();
			// Get the size of the screen
			// Find swipe start and End points from Screen's
			// Width
			// and
			// Height
			// Find Startx point which is at bottom side of the
			// screen
			int yStart = (int) (size.height * 0.80);
			// Find endx point which is at left side of the
			// screen
			int yEnd = (int) (size.height * 0.20);
			// Find vertical point where you want to swipe.It is
			// in
			// the
			// middle of the screen height
			int xStart = size.height / 2;
			driver.swipe(xStart, yStart, xStart, yEnd, time);
			logger.trace("swipeDown - - Successfull");
		} catch (Exception e) {
			handleError("Swipe Down: Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It swipe up the screen from required position
   * @param Time
	 * 
	 */


	// swipe---------------------------------------swipeUp-Vasu
	public boolean swipeUp(int time) {

		try {
			Dimension size = driver.manage().window().getSize();
			// Get the size of the screen
			// Find swipe start and End points from Screen's Width
			// and
			// Height
			// Find Startx point which is at bottom side of the
			// screen
			int yStart = (int) (size.height * 0.20);
			// Find endx point which is at left side of the screen
			int yEnd = (int) (size.height * 0.80);
			// Find vertical point where you want to swipe.It is in
			// the
			// middle of the screen height
			int xStart = size.height / 2;
			driver.swipe(xStart, yStart, xStart, yEnd, time);
			logger.trace("swipeUp - - Successfull");

		} catch (Exception e) {
			handleError("Swipe Up: Failed", e);
		}
		return true;
	}
	
	/**
	 * 
	 * It  checks whether application is already installed in device 
   * @param BundleId
   * @return
   * 
	 */


	public boolean isAppInstalled(String bundleId) {
		boolean result = false;

		try {
			result = driver.isAppInstalled(bundleId);
			logger.trace("IsAppInstalled - Successfull");
		} catch (Exception e) {
			handleError("Is App Installed: Failed", e);
		}
		return result;
	}
	private static final String DEVICE_NAME="deviceName";
	private static final String VERSION="version";
	private static final String PLATFORM_NAME="platformName";
	private AppiumDriver driver;
	private long time = 500;
	private Map info;
	private ObjectMap objMap;
	private LogUtils logger = new LogUtils(this);

}
