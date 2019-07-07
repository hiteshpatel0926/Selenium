/******************************************************************************
$Id : AppiumWebDriver.java 12/23/2016 4:08:47 PM
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cbf.harness.Harness;
import cbf.utils.LogUtils;
import cbfx.ui.objectmap.ObjectMap;

/**
 * 
 * Supporting mobile applications like keyEvents,clear,click,
 * select,verifyText,checkPage etc.
 *
 */

public class AppiumWebDriver {
	
	/**
	 * Overloaded Constructor to initialize AppiumWebdriver and desired capabilities
   * @param params
	 */

	public AppiumWebDriver(Map params) {

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

	private RemoteWebDriver getDriver(Map params,
			DesiredCapabilities capabilities) {

			try {
				driver = new RemoteWebDriver(new URL(
						"http://127.0.0.1:4723/wd/hub"), capabilities);
				logger.trace("Driver is assigned for Web - Android : - Successfull");
			} catch (Exception e) {
				handleError("Failed in RemoteWebdriver Instantantion",e);
			}
		return driver;
	}

	private DesiredCapabilities getcapabilities(Map params) {
		DesiredCapabilities caps = new DesiredCapabilities();
		if ("android".equalsIgnoreCase((String) params.get(PLATFORM_NAME))) {
			caps.setCapability(PLATFORM_NAME, params.get(PLATFORM_NAME));
			caps.setCapability(VERSION, params.get(VERSION));
			caps.setCapability(DEVICE_NAME, params.get(DEVICE_NAME));
			caps.setCapability(BROWSER_NAME, params.get(BROWSER_NAME));
			logger.trace("Capabilities are assigned for Web - Android : - Successfull");

		} else if ("ios".equalsIgnoreCase((String) params.get(PLATFORM_NAME))) {
			caps.setCapability(PLATFORM_NAME, params.get(PLATFORM_NAME));
			caps.setCapability("platformVersion", params.get(VERSION));
			caps.setCapability(DEVICE_NAME, params.get(DEVICE_NAME));
			caps.setCapability(BROWSER_NAME, params.get(BROWSER_NAME));
			logger.trace("Capabilities are assigned for Web - ios : - Successfull");
		}
		return caps;
	}

	

	/**
	 * Launches the Application in the Browser
	 * 
	 * @param url
	 *            URL of the page to be opened.
	 */
	public boolean launchApplication(String url) {
		try {
			driver.get(url);
			webWait(time);
		} catch (Exception e) {
			handleError("Launch Application url: " + url, e);
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
	public boolean setValue( String elementName, String value) {
		try {
			WebElement webEle = getControl(elementName);
			webEle.sendKeys(value);
			webWait(time);
			logger.trace("Typed text '" + value
					+ "' in the input element '" + elementName + "'");
		} catch (Exception e) {
			handleError("Set Value: " + value + " for element: " + elementName, e);
		}
		return true;
	}
	
	/**
	 * Actions performed on this keyEvents
	 * @param ctrlName
	 * @param actionType
	 * @return
	 */


	public boolean keyEvents(String ctrlName,  String actionType) {
		try {
			WebElement webEle = getControl(ctrlName);
			switch (actionType.toUpperCase()) {
			case "ENTER":
				webEle.sendKeys(Keys.ENTER);
				break;
			case "CLEAR":
				webEle.sendKeys(Keys.CLEAR);
				break;
			case "HOME":
				webEle.sendKeys(Keys.HOME);
				break;
			case "TAB":
				webEle.sendKeys(Keys.TAB);
				break;
			default:
				break;
			}
			logger.trace("keyEvents - " + actionType + ": Successfull");
			webWait(time);
		
		} catch (Exception e) {
			handleError("Key Events: Property does not Displayed"+ ctrlName, e);
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
			WebElement webEle = getControl(elementName);
			webEle.clear();
			webWait(time);
		} catch (Exception e) {
			handleError("clear: Failed to clear  " + elementName, e);
		}
		return true;
	}

	/**
	 * Clicks on the specified element
	 * 
	 * @param elementName
	 *            element to be clicked
	 */
	public boolean click( String elementName) {
		try {
			WebElement webEle = getControl(elementName);
			webEle.click();
			webWait(time);
		} catch (Exception e) {
			handleError("Click: Failed to click on the element: "+ elementName, e);
		}
		return true;
	}
	
	/**
	 * selects the dropdown options
	 * @param ctrlName
	 * @param seleitem
	 * @return
	 */


	// select
	public boolean select(String ctrlName, String seleitem) {
		try {
			Select dropdown = new Select(getControl(ctrlName));
			dropdown.selectByVisibleText(seleitem);
			logger.trace("Select - " + ctrlName + " SelectItem " + seleitem
					+ "- Successfull");
			webWait(time);
		} catch (Exception e) {
			handleError("Select: Property does not Displayed"+ ctrlName, e);
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
			WebElement webEle = getControl(elementName);
			check(webEle.getText().trim(), expectedText);
			webWait(time);
		} catch (Exception e) {
			handleError("Verify Text: Property does not Displayed", e);
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
			handleError("Check Page: Error in checking the page title: " + pageTitle, e);
		}
		return false;
	}
	
	/**
	 * It  waits for certain amount of time for particular elements
	 * @param time
	 */

	
	public void webWait(long time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS) ;
	}
	
	/**
	 * It  waits for certain amount of time for particular elements
	 * @throws Exception
	 */
	

	public void waitimplicit(){
		try {
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			handleError("Wait Implicit: Implicit Wait Error : "+ time, e);
		}
	}
	
	/**
	 * It waits for text to be displayed in particular web element
	 * @param ctrlName
	 * @param text
	 * @return
	 */

	// Wait For Text
	public boolean waitForText( String ctrlName, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.textToBePresentInElement(
					getControl(ctrlName), text));
			logger.trace("WaitForText - " + text + " in property " + ctrlName
					+ " - is found");
		} catch (Exception e) {
			logger.handleError("Wait For Text: Text is not Displayed: "+ text, e);
		}
		return true;
	}
	
	/**
	 * Checks the presence of element till timeout
	 * 
	 * @param elementName
	 *            name of the element
	 *
	 * @return 
	 */


	// Wait For Property
		public boolean checkElementPresent( String elementName) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOf(getControl(elementName)));
				logger.trace("WaitForText - Property " + elementName + " - is found");

			} catch (Exception e) {
				logger.handleError("Check Element Present: Element is not Displayed: "+ elementName, e);
			}
			return true;
		}
		
		/**
		 * Shut downs the Browsers
		 * 
		 */

		public void closeBrowsers() {
			try {
				driver.quit();
				logger.trace("Driver is released Succesfully");
			} catch (Exception e) {
				handleError("Close Browsers: Failed to release Driver", e);
			}
		}
		
		/**
		 * Checking the element
		 * @param getTxtVal
		 * @param txtCheck
		 */

		// Check Message
		public void check( String getTxtVal,String txtCheck) {
			try {
				Assert.assertEquals(getTxtVal, txtCheck);
				logger.trace("Check - " + getTxtVal + " : " + txtCheck
						+ " - Successfull");
			} catch (Exception e) {
				handleError("Check: Message is not displayed Properly "+ getTxtVal, e);
			}
		}

		/**
		 * To move back to the previous screen
		 * @return
		 */

		// goBack
		public boolean goBack() {
			try {
				driver.navigate().back();
				logger.trace("goBack - Action Performed - Successfull");
				webWait(time);
			} catch (Exception e) {
				handleError("Go Back: Unable to moving back to previous screen", e);
			}
			return true;
		}
		
		/**
		 * Identifies the locator as needed by webDriver object
		 * 
		 *  @param childName
     * @return
		 */
		
	// Get Control of the Properties form Mapping CSV.
	public WebElement getControl(String childName) {
		String locator = (String) objMap.properties(childName).get("locator");
		WebElement element = null;
		int index = 1;
		final By[] byCollection = { By.id(locator), By.name(locator),
				By.xpath(locator), By.className(locator),
				By.cssSelector(locator), By.tagName(locator),
				By.linkText(locator), By.partialLinkText(locator) };

		for (By by : byCollection) {
			try {
				element = driver.findElement(by);
				if (!element.equals(null)) {
					break;
				}
			} catch (Exception e) {
				if (index == byCollection.length) {
					handleError("Get Control: Unable to find element: "+ childName, e);
				} else {
					index++;
					continue;
				}
			}
		}
		return element;
	}
	
	/**
	 * Takes screenshot and embedded in to logs
	 */
	

	public File takescreenshot(String name) {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File srcfile = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);
			logger.trace("DumpScreens - " + name + " - Successfull");
			return srcfile;

		} catch (Exception e) {
			handleError("Take screenshot: DumpScreens:" + name + " - Failed", e);
		}
		return null;
	}
	
	private static final String BROWSER_NAME="browserName";
	private static final String DEVICE_NAME="deviceName";
	private static final String VERSION="version";
	private static final String PLATFORM_NAME="platformName";
	private RemoteWebDriver driver;
	private ObjectMap objMap;
	private long time = 2;
	private LogUtils logger = new LogUtils(this);

}
