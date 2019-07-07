/******************************************************************************
$Id : WebUIDriver.java 12/23/2016 4:09:02 PM
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

package cbfx.ui.selenium;

import static cbf.engine.TestResultLogger.failed;
import static cbf.engine.TestResultLogger.handleError;
import static cbf.engine.TestResultLogger.passed;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cbf.model.FailureCause;
import cbf.utils.LogUtils;
import cbf.utils.SleepUtils;
import cbf.utils.SleepUtils.TimeSlab;
import cbf.utils.StringUtils;

import cbfx.ui.browser.Browser;
import cbfx.ui.objectmap.ObjectMap;
import cbfx.utils.AWTUtils;
import cbfx.utils.ProcessUtils;

/**
 * 
 * Extends BaseWebAppDriver and handles all the common functionalities for
 * webControls like setting the TextBox,Selecting an option in Dropdown ,etc..
 * 
 */

public class WebUIDriver {

	/**
	 * Overloaded Constructor to initialize webdriver and selenium
	 * 
	 * @param parameters
	 *            webDriver: object of WebDriver selenium: object of
	 *            WebDriverBackedSelenium
	 */
	public WebUIDriver(ObjectMap objMap, Browser browser) {
		this.objMap = objMap;
		this.browser = browser;
		this.th = new TableHandler(this);
	}

	public void open(){
		webDr = browser.openDriver();
	}

	public void close() {
	  if (webDr != null) {
	  	browser.closeDriver(webDr);
	  	webDr = null;
	  }
	}
	
	/**
	 * Launches the Application in the Browser
	 * 
	 * @param url
	 *            URL of the page to be opened.
	 */
	public void launch(String url, boolean maximize) {
    logger.trace("launch", url, maximize);

		webDr.get(url);

    if (maximize)
      maximizeWindow();
	}

  public void maximizeWindow() {
    logger.trace("maximizeWindow");

		webDr.manage().window().maximize(); // CHECKME: what happens in devices/appium?
  }

	/**
	 * Checks whether the page title matches or not
	 * 
	 * @param pageTitle
	 *            title of page opened
	 * @return true or false result
	 */
	public String getTitle() {
    return webDr.getTitle();
	}

	/**
	 * Identifies the locator as needed by webDriver object
	 * 
	 * @param elementName
	 *            Name of the element whose locator is required
	 * @return Identified locator as Web Element
	 */

	public WebElement getControl(String elementName) {
		String actualLocator = null;
    
    try {
      actualLocator = (String) objMap.properties(elementName).get("locator");
    } catch(NullPointerException ne) {
      logger.handleError("Unmapped element", elementName, ne);
      return null;
    }
 
		WebElement element = null;
		final By[] byCollection = { By.id(actualLocator), By.name(actualLocator),By.xpath(actualLocator),
				By.className(actualLocator), By.cssSelector(actualLocator), By.tagName(actualLocator),
				By.linkText(actualLocator), By.partialLinkText(actualLocator) };

		for (By by : byCollection) {
			try {
				element = webDr.findElement(by);
				if (!element.equals(null)) {
          logger.debug("found element", elementName, element, by);
					return element;
				}
			} catch (NoSuchElementException e) {
        logger.warn("no such element", elementName, by, e);
			}
		}
		return element;
	}
  
	private ControlDriver getControlDriver(String elementName) {
		Map map = objMap.properties(elementName);
		
		if (map.get("controlType")!=null){
			if (map.get("controlType").toString().equalsIgnoreCase("Angular")) {
				return new NgWebUIDriver(map, webDr); // TBD
			} 
			
		
		else {
			return new WebElementDriver(map, webDr);
		}
		}

		return new WebElementDriver(map, webDr);	}

	/**
	 * Checks the presence of element at the instant
	 * 
	 * @param elementName
	 *            name of the element
	 * @return boolean result
	 */
	public boolean isPresent(String elementName) {
		return getControlDriver(elementName).isPresent();
	}

  /**
   * new reusable function
   **/
  public boolean wait(ExpectedCondition<Boolean> condition, long timeoutInMsecs) {
			Wait<WebDriver> wait = new WebDriverWait(webDr, timeoutInMsecs/1000, timeoutInMsecs%1000);
			try {
				boolean result = wait.until(condition);
        logger.trace("wait successful", result, condition, timeoutInMsecs);
        return result;
			} catch (TimeoutException e) {
        logger.trace("wait timed out", condition, e, timeoutInMsecs);
        return false;
			}
  }

	/**
	 * Sets the value to the specified UI Element
	 * 
	 * @param elementName
	 *            name of element
	 * @param value
	 *            value to be set on element
	 */
	public void setValue(String elementName, String value) {
		getControlDriver(elementName).setValue(value);
	}

	/**
	 * To handle angular DOM related setValue functionalities. The multiple
	 * element names and the corresponding values(filterText or the value to be
	 * set) are provided with "|" as delimiter Ex. In case of value to be set
	 * after traversing through multiple menu/dropdowns etc., each element to be
	 * traversed along with the value acting as filterText(in case of ng-repeat
	 * directives) has to be provided with the syntax in script as:
	 * uIDriver.setValues(elementName1|elementName2|elementName3....,
	 * value1|value2|value3....)
	 * 
	 *
	 * @param elementName
	 *            elements to be
	 *            located..(elementName1|elementName2|elementName3..)
	 * @param value
	 *            value to act as filterText or to set(value1|value2|value3....)
	 */
	public void setValues(String elementName, String value) {

		for (int i = 0; i < elementName.split("\\|").length; i++) {
			try {
				getControlDriver(elementName.split("\\|")[i]).setValue(value.split("\\|")[i]);
			} catch (Exception e) {
				failed("setting the value for" + " " + elementName + " ", "value"
						+ " " + value + " " + "should set properly",
						"Failed to set value" + " " + value,FailureCause.DataIdentification);
			}
		}
	}

	/**
	 * Sets the value to all the locators on the specified page.
	 * 
	 * @param pageName
	 *            name of the page
	 * @param data
	 *            value to be set on page
	 */
	public void setValues(String pageName, Map data) {
		List<Map> resultMap = new ArrayList();
		resultMap = objMap.ObjectMaps(pageName);
		setValues(data, resultMap);
	}

	private void setValues(Map data, List<Map> uiElements) {
		String elementName = "";
		for (Map uiElement : uiElements) {
			elementName = ((String) uiElement.get("elementName"));

			if (data.containsKey(elementName)) {
				setValue(elementName, (String) data.get(elementName));
			}
		}
	}

	/**
	 * Gets the value of the specified element
	 * 
	 * @param elementName
	 *            whose value has to be obtained
	 */
	public String getValue(String elementName) {
		return getControlDriver(elementName).getValue();

	}

	/**
	 * Clears the value of specified element
	 * 
	 * @param elementName
	 *            element to be cleared
	 */
	public void clear(String elementName) {
		getControlDriver(elementName).clear();

	}

	/**
	 * Navigates to the Menu
	 * 
	 * @param menuList
	 *            list of menu items
	 */
	public void navigateMenu(String menuList) {
		String[] aMenu;
		aMenu = menuList.split(",");
		try {
			for (int i = 0; i < aMenu.length; i++) {
				if (!(aMenu[i].matches(""))) {
					getControl(aMenu[i]).click();
					logger.trace("Clicked on menu: " + aMenu[i] + ".");
					waitForBrowserStability("1000");
				}
			}
		} catch (Exception e) {
			if (menuList.contains(","))
				menuList = menuList.replaceAll(",", "-->");
			failed("Navigate to menu" + menuList,
					"Should navigate " + menuList, "Failed to navigate to "
							+ menuList,FailureCause.Synchronization);
			logger.handleError("Failed while Navigating to  " + menuList);
		}
	}

	/**
	 * Check for the presence of the specified text on page till timeout
	 * 
	 * @param text
	 *            text to be verified
	 * @param timeInSec
	 *            time-limit(in seconds)
	 * @return boolean Result
	 */
	public boolean checkTextOnPage(String text, int timeInSec) {
		boolean result = false;
		WebDriverWait waitForPage = new WebDriverWait(webDr, TimeUnit.MILLISECONDS.toSeconds(500));

		try {
			for (int second = 0;; second++) {
				if (second >= timeInSec * 10) {
					logger.trace("TimeOut for " + second);
					break;
				}
				if (webDr.findElement(By.tagName("body")).getText().contains(text)) {
					result = true;
				}
				waitForPage.until(ExpectedConditions.visibilityOf(webDr.findElement(By.tagName("body"))));
			}
		} catch (Exception e) {
			failed("Check " + text + " on page", text
					+ " Should be present on page ", text
					+ " not present on page ",FailureCause.Synchronization);
			logger.handleError("Error: Caused while Verifying the Presence of Text \" " + text + " \"", e.getMessage());
		
		}
		return result;
	}

	/**
	 * Clicks on the specified element
	 * 
	 * @param elementName
	 *            element to be clicked
	 */

	public void click(String elementName) {
		getControlDriver(elementName).click();
	}

	/**
	 * Mouse overs the menu and the submenus delimited by '|' and clicks on the
	 * last submenu
	 * 
	 * @param menu
	 *            menu to be mouse overed. To be given as:
	 *            menu|submenu1|submenu2|submenu3|....
	 * @param text
	 *            filtertext to identify element in case of angular DOM. To be
	 *            given as: text1|text2|text3....
	 * @return
	 */
	public void mouseOverAndClick(String menu) {
		try {
			Robot r = new Robot();
			r.mouseMove(1, 1);
			SleepUtils.sleep(TimeSlab.YIELD);
			if (menu.contains("|")) {
				clickFromData(menu);
			} else {
				getControlDriver(menu).mouseOver();
			}
		} catch (StaleElementReferenceException e) {
			failed("Mouse over menu " + menu + " and click subMenu " ,
					"Should mouse over menu " + menu + " and click subMenu "
							, "failed to mouse over menu " + menu
							+ " and click subMenu ",FailureCause.ObjectIdentification ); 
			logger.handleError("Falied to click ", " on", menu, " ", e.getMessage());
		}
		catch(Exception e){
			failed("Mouse over menu " + menu + " and click subMenu " ,
					"Should mouse over menu " + menu + " and click subMenu "
							, "failed to mouse over menu " + menu
							+ " and click subMenu "); 
			logger.handleError("Falied to click ", " on", menu, " ", e.getMessage());
		}

	}

	private void clickFromData(String sValue) {
		String[] parts = null;
		String valueForMouseHOver = null, valueForClick = null;
		// Checks for the | in the sValue String.
		if (sValue.contains("|")) {
			parts = sValue.split("\\|");
			valueForClick = parts[parts.length - 1];
			for (int i = 0; i < parts.length; i++) {
				valueForMouseHOver = parts[i];
					if (valueForMouseHOver.matches(valueForClick) || valueForMouseHOver.equals(valueForClick)) {
						click(valueForClick);
					} else {
						mouseOverAndClick(valueForMouseHOver);
						SleepUtils.sleep(TimeSlab.YIELD);
					}
			}
		}

	}

	/**
	 * Performs the drag and drop operation
	 * 
	 * @param fromLocator
	 *            Contains the locator of source element
	 * @param toLocator
	 *            Contains the locator of destination element
	 */
	public void dragAndDrop(String fromLocator, String toLocator) {
		try {
			WebElement from = getControl(fromLocator);
			WebElement to = getControl(toLocator);
			Actions builder = new Actions(webDr);
			Action dragAndDrop = builder.clickAndHold(from).moveToElement(to).release(to).build();
			dragAndDrop.perform();
		} catch (StaleElementReferenceException e) {
			failed("Drag and drop from " + fromLocator + " to " + toLocator,
					"Drag and drop from " + fromLocator + " to " + toLocator,
					"Failed to drag and drop from " + fromLocator + " to "
							+ toLocator);
			logger.handleError("Failed to drag drop elements ", fromLocator, " , ", toLocator, " ", e.getMessage());
		}
	}

	/**
	 * Switch to another window
	 * 
	 * @param title
	 *            Contains title of the new window
	 * @return true or false
	 */
	public boolean switchToWindow(String title) {
		Set<String> availableWindows = webDr.getWindowHandles();
		if (availableWindows.size() > 1) {
			try {
				for (String windowId : availableWindows) {
					if ((webDr.switchTo().window(windowId)).getTitle().equals(title))
						return true;
				}
			} catch (NoSuchWindowException e) {
				
				failed("Switch to window with title: " + title,
						"Should switch to window with title: " + title,
						"Failed to Switch to window with title: " + title,FailureCause.ObjectIdentification);
				logger.handleError("No child window is available to switch ", e.getMessage());
			}
		}
		return false;
	}

	/**
	 * Uploads a file
	 * 
	 * @param filePath
	 *            Contains path of the file to be uploaded
	 * @param browse
	 *            Contains locator of the browse button
	 * @param upload
	 *            locator of the upload button
	 */
	public void fileUpload(String filePath, String browse, String upload) {

		try {
			getControlDriver(browse).setValue(filePath);
			if (upload != null) {
				click(upload);
			}
		} catch (Exception e) {
			failed("Upload file " + browse + " on filepath " + filePath,
					"Should upload file " + browse + " on filepath " + filePath,
					"Failed to upload file " + browse + " on filepath "
							+ filePath,FailureCause.DataIdentification);
			logger.handleError("Invalid File Path: ", filePath, e.getMessage());
			
		}

	}

	/**
	 * Invokes enter/tab/F5/home/delete key
	 * 
	 * @param keyEvent
	 *            key to be invoked
	 * 
	 */
	// TODO:Check for other useful key events
	public void sendKey(String keyEvent) {
		AWTUtils.sendKey(keyEvent);
	}

	/**
	 * Send string as keyboard keystrokes
	 * 
	 * @param text
	 *            String to be entered
	 */
	public void setText(String text) {
		AWTUtils.sendKeys(text);
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
	public boolean checkText(String elementName, String expectedText) {
		String actualText = getControlDriver(elementName).getValue();
		if (actualText.equalsIgnoreCase(expectedText)) {
			return true;
		}
		return false;
	}

	/**
	 * Verify Tooltip's text
	 * 
	 * @param elementName
	 *            Give element for which tooltip is visible
	 * @param expected
	 *            expected tooltip text
	 */

	public void checkToolTip(String elementName, String expectedText) {
		try {
			String value = getAttribute(elementName, expectedText);
			if (!value.isEmpty()) {
				if (value.contains(expectedText)) {
					passed("verify tooltip: " + value + "for " + elementName,
							"Tooltip's text should match with " + value + "for " + elementName,
							"Tooltip's text matches with " + value + "for " + elementName);
				} else {
					failed("verify tooltip: " + expectedText, "Tooltip's text should match with " + expectedText,
							"Tooltip's text doesn't match with " + expectedText);
				}
			} else {
				failed("verify tooltip: " + value + "for " + elementName, value + " not Visible",
						"Tooltip" + value + " is not visible for " + elementName);
			}
		} catch (Exception e) {
			handleError("Check Tool Tip: "+ expectedText, e.getMessage());
		}

	}

	/**
	 * Verifies text in alert and clicks on either OK or cancel.
	 * 
	 * @param text
	 *            expected text
	 * @param button
	 *            "OK" or "Cancel" button
	 */

	public void handleAlert(String text, String button) {
		try {

			Alert alert = webDr.switchTo().alert();
			if (!text.equals("")) {
				String alerttext = alert.getText();
				if (alerttext.matches(text)) {
					passed("verify " + text + " in alert", text + " should present in alert",
							text + " is present in  alert");
				} else {
					failed("verify " + text + " in alert", text + " should present in alert",
							text + " is not present in  alert");
				}
			}
			if (button.equalsIgnoreCase("yes") || button.equalsIgnoreCase("ok")) {
				alert.accept();
			} else {
				alert.dismiss();
			}
		}

		catch (NoAlertPresentException e) {
			handleError("Alert not present: \"" + text + "\" with button: \""+ button+"\"", e);
			failed("verify " + text + " in alert", text
					+ " should present in alert", text
					+ " is not present in  alert",FailureCause.ObjectIdentification);
			logger.handleError("Error while verifying text:" + text + " in alert with button", button, e.getMessage());
		} catch (Exception e) {
		
			failed("verify " + text + " in alert", text
					+ " should present in alert", text
					+ " is not present in  alert");
			logger.handleError("Error while verifying text:" + text + " in alert with button", button, e.getMessage());
		}

	}

	/**
	 * Sets the text value in the alert box
	 * 
	 * @param value
	 *            text value  to be set in alert box
	 */

	public void handlePrompt(String value) {
		try {
			Alert alt = webDr.switchTo().alert();
			alt.sendKeys(value);
			alt.accept();
		} catch (NoAlertPresentException e) {
		
			failed("Send values to alert " + value, "Should send values to alert "
					+ value, "Alert not present to set value " + value,FailureCause.ObjectIdentification);
			
		} catch (Exception e) {
	
			failed("Send values to alert " + value, "Should send values to alert "
					+ value, "Alert not present to set value " + value,FailureCause.NonSpecific);
			
		}

	}

	/**
	 * Gets the text from the alert box
	 * 
	 * @return String
	 */

	public String getAlertText() {
		try {
			Alert alt = webDr.switchTo().alert();
			return alt.getText();
		} catch (NoAlertPresentException e) {
			failed("Get text from alert ", "Should get text from alert ",
					"Failed to get value from alert",FailureCause.ObjectIdentification);
			logger.handleError("Failed to get text from alert box", e.getMessage());
		} catch (Exception e) {
			failed("Get text from alert ", "Should get text from alert ",
					"Failed to get value from alert");
			logger.handleError("Failed to get text from alert box", e.getMessage());
		}
		return null;
	}

	/**
	 * Verify the presence of alert till timeout
	 * 
	 * @param TimeOutinSeconds
	 *            Give max time limit
	 * @return boolean
	 */

	public boolean isAlertPresent(int TimeOutinSeconds) {
		for (int i = 0; i < TimeOutinSeconds; i++) {
			try {
				Thread.sleep(500);
				webDr.switchTo().alert();
				return true;
			} catch (InterruptedException | NoAlertPresentException e) {
				failed("Check the presence of Alert",
						"Alert should be present ", "Alert is not present",FailureCause.ObjectIdentification);
				logger.handleError("Failed to verify presence of alert", e.getMessage());
			}
		}
		return false;
	}

	/**
	 * For highlighting an element
	 * 
	 * @param elementName
	 *            Locator
	 */

	public void drawHighlight(String elementName) {
		WebElement element = null;
		try{
			element = getControl(elementName);
			JavascriptExecutor js = ((JavascriptExecutor) webDr);
			js.executeScript("arguments[0].style.border='2px groove red'", element);
		} catch (Exception e) {
			failed("Draw highlight on " + elementName, elementName
					+ " should be highlighted", elementName
					+ " is not highlighted");
			logger.handleError("Failed to highlight element", elementName, " ", e.getMessage());
			
		}
	}

	/**
	 * Retrieve drop down options
	 * 
	 * @param elementName
	 *            name of dropdown
	 * 
	 */
	public List<String> getDropDownOptions(String elementName) {
		return ((WebElementDriver) getControlDriver(elementName)).getDropDownOptions();
	}

	/**
	 * checks the presence of specified options in the dropdown
	 * 
	 * @param elementName
	 *            name of element
	 * @param optionsStr
	 *            options to be checked with comma separated values
	 * 
	 * 
	 */
	public boolean checkDropDownOptions(String elementName, String optionsStr) {
		List<Object> flag = new ArrayList<Object>();
		List<String> dropDownOptions = getDropDownOptions(elementName);
		List<String> dropDownOptionsLowerCase = new ArrayList<String>();
		for (String temp : dropDownOptions) {
			dropDownOptionsLowerCase.add(temp.trim().toLowerCase());
		}
		String[] dropDownOptionsActList = optionsStr.split(",");
		try {
			for (int i = 0; i < dropDownOptionsActList.length; i++) {
				if (dropDownOptionsLowerCase.contains(dropDownOptionsActList[i].trim().toLowerCase())) {
					flag.add(true);
				} else {
					flag.add(false);
				}
			}
		} catch (Exception e) {
			failed("Check options " + optionsStr + " in drop down "
					+ elementName, "Options " + optionsStr + " in drop down "
					+ elementName + " should be present",
					"Failed to verify option: " + optionsStr + " for "
							+ elementName,FailureCause.ObjectIdentification);
			
			logger.handleError("Failed to verify option: ", optionsStr, "for ", elementName, " ", e.getMessage());
		}
		if (flag.contains(false)) {
			return false;
		} else {

			return true;
		}
	}

	/**
	 * Checks the attribute value
	 * 
	 * @param elementName
	 *            name of element
	 * 
	 * @param attribute
	 *            attribute to be set
	 * @param value
	 *            value of the attribute
	 

	public boolean checkAtttribute(String elementName, String attribute, String value) {
		String actualValue = getAttribute(elementName, attribute);
		if (value.equals(actualValue))
			return true;
		return false;
	}

	/**
	 * Sets the attribute value
	 * 
	 * @param elementName
	 *            name of element
	 * 
	 * @param attribute
	 *            attribute to be set
	 * @param value
	 *            value of the attribute
	 */
	public void setAttribute(String elementName, String attribute, String value) {
		getControlDriver(elementName).setAttribute(attribute, value);
	}

	/**
	 * Gets the attribute value
	 * 
	 * @param elementName
	 *            name of element
	 * 
	 * @param attribute
	 *            attribute name
	 */
	public String getAttribute(String elementName, String attribute) {
		return getControlDriver(elementName).getAttribute(attribute);
	}
	/**
	 * It Executes  the given Javascript
	 * 
	 * @param script
	 *            script to be executed
	 **/
	public void executeJavaScript(String script) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDr;
			js.executeScript(script);
		}catch (Exception e) {
			failed("Execute javascript " + script, script
					+ " should be executed", "Falied to execute " + script,FailureCause.NonSpecific);
			logger.handleError("Falied to execute ", script);
		}
	}

	/**
	 * For Double clicking element identified by locator
	 * 
	 * @param elementName
	 *            name of element
	 */

	public void doubleclick(String elementName) {
		Actions axn = new Actions(webDr);
		try {
			axn.doubleClick(getControl(elementName)).build().perform();
		} catch (Exception e) {
			failed("double click on element", " " + elementName + " "
					+ "element" + " " + elementName + " "
					+ "should be double clicked",
					"Failed to double click on element" + " " + elementName,FailureCause.ObjectIdentification);
			logger.handleError("Failed to double click on " + elementName, e.getMessage());
		}
	}

	/**
	 * For right clicking on any element
	 * 
	 * @param elementName
	 *            name of element
	 */

	public void rightClick(String elementName, int option) {
		Actions action = new Actions(webDr);
		Actions act = null;
		for (int i = 0; i < option - 1; i++) {
			act = action.contextClick(getControl(elementName)).sendKeys(Keys.ARROW_DOWN);
			act.build().perform();
		}
		act.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Shut downs the webdriver
   * Keyword equivalent of close()
	 */
	public void closeBrowsers() {
    close();
	}

	/**
	 * Takes screenshot and embedded in to logs
	 */
	public File takescreenshot() {
		try {
			return ((TakesScreenshot) webDr).getScreenshotAs(OutputType.FILE);
		} catch (Exception e) {
			failed("To take Screen shots", "Takes ScreenShots",
					"Failed to Take ScreenShots");
			logger.handleError("Failed to take screenshots", e.getMessage());
		}
		return null;
	}

	/**
	 *  If some thing generate from web server to web browser form that is cookies here it will clear all cookies in the form 
	 */
	public void clearCookies() {
		try {
			webDr.manage().deleteAllCookies();
		} catch (Exception e) {
			
			failed("To Clear cookies", "Cookies are cleared",
					"Failed to clear cookies");
			logger.handleError("Failed to clear cookies", e.getMessage());
		}
	}

	/**
	 * Check the running process and kill it
	 * 
	 * @param serviceName
	 *            Give name of the process that you want to kill
	 * @return Boolean
	 * @throws IOException
	 */

	public void killProcess(String serviceName) throws IOException {
		ProcessUtils.killProcess(serviceName);
	}

	/**
	 * Checks whether the page is loaded or not
	 * 
	 * @param maxTimeInSec
	 *            time to wait(In seconds)
	 * @return boolean result
	 */
	public boolean waitForBrowserStability(String maxTimeInSec) {
		int maxWait = Integer.parseInt(maxTimeInSec);
		int secsWaited = 0;
		try {
			do {
				Thread.sleep(100);
				secsWaited++;
				if (isBrowserLoaded()) {
					return true;
				}
			} while (secsWaited < (maxWait * 10));
			Thread.sleep(100);
		}catch (Exception e) {
			failed("Browser to load", "check for page to load",
					"Failed to check for page to load",FailureCause.Synchronization);
			logger.handleError("Error while waiting for the page to load",e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if body of the page is loaded or not
	 * 
	 * @return Boolean Result
	 */
	private boolean isBrowserLoaded() {
		try {
			long timeOut = 5000;
			long end = System.currentTimeMillis() + timeOut;
			while (System.currentTimeMillis() < end) {

				if (String.valueOf(((JavascriptExecutor) webDr).executeScript("return document.readyState"))
						.equals("complete")) {
					return true;
				}
			}
		} catch (Exception e) {
			failed("Browser to load", "check for browser to load",
					"Failed to check for browser to load",FailureCause.Synchronization);
			logger.handleError("Failed to check for the browser to load", e.getMessage());
		}
		return false;
	}
/**It verifies the presence of element in frame
 * 
 * @param frame
 */
	public void setFrame(String frame) {
		try {
			if (checkElementVisibilty(frame)) {
				webDr.switchTo().frame(getControl(frame));
				logger.trace("Navigated to frame with element name" + frame);
			}
		} catch (NoSuchFrameException e) {
			
			failed("Switch to  frame", "Should switch to frame",
					"Failed to Switch to frame ",FailureCause.ObjectIdentification);
			logger.handleError("Unable to locate frame with element name " + frame,e.getMessage());
		}
	}

	/**
	 * Return the focus to the parent frame
	 * 
	 */

	public void resetFrame() {
		try {
			webDr.switchTo().defaultContent();
			logger.trace("Navigated back to webpage from frame");
		}catch (Exception e) {
			failed("Switch to parent frame", "Should switch to parent frame",
					"Failed to Switch to parent frame ",FailureCause.NonSpecific);
			logger.handleError("Unable to reset frame ",e.getMessage());
		}
	}
/**
 * It verifies the presence of Element
 * @param elementName
 * @return
 */
	public boolean checkElementVisibilty(String elementName) {
		try {
			return getControl(elementName).isDisplayed();
		}catch (ElementNotVisibleException e) {
			failed("CheckElementVisibility for " + elementName,
					"Should check Element Visisbility for " + elementName,
					"Failed to check element " + elementName,FailureCause.ObjectIdentification);
			logger.handleError("Error: Caused while Verifying the Presence of Element \" " + elementName + " \"",e.getMessage());
		}
		return false;
	}
	/**
	 *  
  Waits until the given condition is true or timeout expires.
 
	 * @param time
	 */
	public void Wait(long time) {
		WebDriverWait wait = new WebDriverWait(webDr, time);
	}
/**
 * Events which can be performed on Mobile web application or on mobile device
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
			logger.trace("keyEvents - " + actionType + " - Successfull");
			Wait(time);
		
		} catch (Exception e) {
			handleError("Key Events: Property does not Displayed"+ ctrlName, e.getMessage());
		}
		return true;
	}

	/**
	 * selecting a value from a dropdown
	 * @param ctrlName
	 * @param seleitem
	 * @return
	 */
		public boolean select(String ctrlName, String seleitem) {
			try {
				Select dropdown = new Select(getControl(ctrlName));
				dropdown.selectByVisibleText(seleitem);
				logger.trace("Select - " + ctrlName + " SelectItem " + seleitem
						+ "- Successfull");
				Wait(time);
			} catch (Exception e) {
				handleError("Select: Property does not Displayed"+ ctrlName, e.getMessage());
			}
			return true;
		}
		
		
		/**
		 * It  waits for certain amount of time for particular elements
		 * @throws Exception
		 */
		
		public void waitimplicit() throws Exception {
			try {
				webDr.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			} catch (Exception e) {
				handleError("Wait Implicit: Implicit Wait Error : "+ time, e.getMessage());
			}
		}
/**
 * It waits for text to be diplayed in particular web element
 * @param ctrlName
 * @param text
 * @return
 */
		public boolean waitForText( String ctrlName, String text) {
			try {
				WebDriverWait wait = new WebDriverWait(webDr, 15);
				wait.until(ExpectedConditions.textToBePresentInElement(
						getControl(ctrlName), text));
				logger.trace("WaitForText - " + text + " in property " + ctrlName
						+ " - is found");
			} catch (Exception e) {
				logger.handleError("Wait For Text: Text is not Displayed: "+ text, e.getLocalizedMessage());
			}
			return true;
		}
		
		/**
		 * It takescreen shot of particular file and dump in to logs file
		 * @param name
		 * @return
		 */
		public File takescreenshot(String name) {
			try {
				WebDriver augmentedDriver = new Augmenter().augment(webDr);
				File srcfile = ((TakesScreenshot) augmentedDriver)
						.getScreenshotAs(OutputType.FILE);
				logger.trace("DumpScreens - " + name + " - Successfull");
				return srcfile;

			} catch (Exception e) {
				handleError("Take screenshot: DumpScreens:" + name + " - Failed", e.getMessage());
			}
			return null;
		}
		
		/**
		 * To move back to the previous screen
		 * @return
		 */
				public boolean goBack() {
					try {
						webDr.navigate().back();
						logger.trace("goBack - Action Performed - Successfull");
						Wait(time);
					} catch (Exception e) {
						handleError("Go Back: Unable to moving back to previous screen", e.getMessage());
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
						Wait(time);
					} catch (Exception e) {
						handleError("Verify Text: Property does not Displayed", e.getMessage());
					}
					return true;
				}

			/**
			 * It just compares the gettextval and txtcheck values in the given condition 
			 * @param getTxtVal
			 * @param txtCheck
			 */
				public void check( String getTxtVal,String txtCheck) {
					try {
						Assert.assertEquals(getTxtVal, txtCheck);
						logger.trace("Check - " + getTxtVal + " : " + txtCheck
								+ " - Successfull");
					} catch (Exception e) {
						handleError("Check: Message is not displayed Properly "+ getTxtVal, e.getMessage());
					}
				}
			
	
	/**
	 * To print reference variable  in java the we are going for tostring() method
	 * tostring() belongs to object class
	 * Overriding toString() method to return WebUIDriver format string
	 */
	public String toString() {
		return StringUtils.mapString(this);
	}

	private WebDriver webDr;
	private ObjectMap objMap;
	private Browser browser;
	public TableHandler th;
	private LogUtils logger = new LogUtils(this);
	private long time = 2;
	
	
}
