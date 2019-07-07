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

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cbf.utils.LogUtils;
import cbf.utils.StringUtils;
import cbfx.ui.browser.Browser;
import cbfx.ui.objectmap.ObjectMapFactory;
import cbfx.utils.SleepUtils;
import cbfx.utils.SleepUtils.TimeSlab;

/**
 * 
 * Extends BaseWebAppDriver and handles all the common functionalities for
 * webControls like setting the TextBox,Selecting an option in Dropdown ,etc..
 * 
 */

public class WebUIDriver {

	/**
	 * Overloaded Constructor to initialize webdriver and selenium.
	 *
	 * @param objMap
	 *            the obj map
	 * @param browser
	 *            the browser
	 */
	public WebUIDriver(ObjectMapFactory objMapFactory, Browser browser) {
		this.browser = browser;
		this.th = new TableHandler(this);
		this.objectFinder = new ObjectFinder(objMapFactory);
		this.controlDriverFactory = new ControlDriverFactory(objectFinder);
	}

	/**
	 * Open.
	 */
	public void open() {
		webDr = browser.openDriver();
		logger.trace(browser + "driver instance created");
	}

	/**
	 * Close.
	 */
	public void close() {
		if (webDr != null) {
			webDr = browser.closeDriver(webDr);
			logger.trace(browser + "driver closed");
			if (webDr != null)
				logger.warning("Error in closing " + browser + "driver");
		}
	}

	public WebDriver webDriver() {
		if (webDr == null)
			logger.handleError("webDriver is not open");
		return webDr;
	}

	/**
	 * Launches the Application in the Browser.
	 *
	 * @param url
	 *            URL of the page to be opened.
	 * @param maximize
	 *            the maximize
	 */
	public void launch(String url, boolean maximize) {
		logger.trace("launch", url, maximize);
		webDr.get(url);
		if (maximize)
			maximizeWindow();
	}

	/**
	 * Maximize Window.
	 */
	public void maximizeWindow() {
		logger.trace("maximizeWindow");
		webDr.manage().window().maximize(); // CHECKME: what happens in
											// devices/appium?
	}
	public void launchApplicationAppium(String url) {

		try {
			webDr.get(url);
			
		} catch (Exception e) {
		
			logger.handleError("Error while navigating to url "+url, e.getMessage());
		}
	}
	/**
	 * Checks whether the page title matches or not.
	 *
	 * @return true or false result
	 */
	public String getTitle() {
		return webDr.getTitle();
	}

	/**
	 * Identifies the locator as needed by webDriver object.
	 *
	 * @param elementName
	 *            Name of the element whose locator is required
	 * @return Identified locator as Web Element
	 */
	public WebElement findElement(String elementName) {
		return objectFinder.find(elementName, webDr, (WebElement e, By by, Map properties) -> {
			webDr.findElement(by);
		});

	}

	/**
	 * getControlDriver.
	 *
	 * @param elementName
	 *            the element name
	 * @return the control driver
	 */
	private ControlDriver getControlDriver(String elementName) {
		return controlDriverFactory.makeDriver(elementName, webDr);
	}

	/**
	 * Checks the presence of element at the instant.
	 *
	 * @param elementName
	 *            name of the element
	 * @return boolean result TODO reimplement it as ExpectedCondition<WebElement>
	 */
	public boolean isPresent(String elementName) {
		return findElement(elementName) != null;
	}

	/**
	 * new reusable function to be shared by all sync waits
	 *
	 * @param elementName
	 *            the element name
	 * @param value
	 *            the value
	 */
	public <T> T wait(ExpectedCondition<T> condition, long timeoutInSecs) {
		WebDriverWait wait = new WebDriverWait(webDr, timeoutInSecs);
		T result = null;
		try {
			result = wait.until(condition);
			logger.trace("wait successful", result, condition, timeoutInSecs);

		} catch (TimeoutException e) {
			logger.trace("wait timed out", condition, e, timeoutInSecs);
		}
		return result;
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
	 * To handle angular DOM related setValue functionalities. The multiple element
	 * names and the corresponding values(filterText or the value to be set) are
	 * provided with "|" as delimiter Ex. In case of value to be set after
	 * traversing through multiple menu/dropdowns etc., each element to be traversed
	 * along with the value acting as filterText(in case of ng-repeat directives)
	 * has to be provided with the syntax in script as:
	 * uIDriver.setValues(elementName1|elementName2|elementName3....,
	 * value1|value2|value3....)
	 * 
	 *
	 * @param elementName
	 *            elements to be located..(elementName1|elementName2|elementName3..)
	 * @param value
	 *            value to act as filterText or to set(value1|value2|value3....)
	 *            FIXME: change this to String[] elementNames, String[] values. Move
	 *            split to the caller. Compare elementNames.length == values.length
	 *            before proceeding
	 */
	public void setValues(String[] elementNames, String[] values) {
		if (elementNames.length != values.length)
			logger.handleError("number of names and values dont match", elementNames.length, values.length);

		for (int i = 0; i < elementNames.length; i++) {
			getControlDriver(elementNames[i]).setValue(values[i]);
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
		List<Map> resultMap = objectFinder.getPageMap(pageName);// TODO: objMap should be Removed. replace with
																// ObjectMapFactory
		if (resultMap == null)
			logger.handleError("objects not mapped for page", pageName);
		setValues(data, resultMap);
	}

	/**
	 * Sets a given uiElements list with values from data
	 *
	 * @param data
	 *            the data
	 * @param uiElements
	 *            the ui elements
	 */
	private void setValues(Map data, List<Map> uiElements) {
		for (Map uiElement : uiElements) {
			String elementName = (String) uiElement.get("elementName");

			if (data.containsKey(elementName)) {
				setValue(elementName, (String) data.get(elementName));
			}
		}
	}

	/**
	 * Gets the value of the specified element.
	 *
	 * @param elementName
	 *            whose value has to be obtained
	 * @return the value
	 */
	public String getValue(String elementName) {
		return getControlDriver(elementName).getValue();

	}

	/**
	 * Clears the value of specified element.
	 *
	 * @param elementName
	 *            element to be cleared
	 */
	public void clear(String elementName) {
		getControlDriver(elementName).clear();

	}

	/**
	 * Navigates to the Menu.
	 *
	 * @param menuList
	 *            list of menu items
	 */
	public void navigateMenu(String[] menuList) {
		for (String item : menuList) {
			if ("".matches(item))
				continue;

			findElement(item).click();
			logger.trace("Clicked on menu", item);

			waitForBrowserStability();
		}
	}

	/**
	 * Check for the presence of the specified text on page till timeout.
	 *
	 * @param text
	 *            text to be verified
	 * @param timeOut
	 *            the time out
	 * @return boolean Result
	 */
	public boolean waitTilltextOnPage(String text, long timeOut) {
		return wait(ExpectedConditions.textToBePresentInElementValue(By.tagName("body"), text), timeOut);
	}

	/**
	 * Clicks on the specified element.
	 *
	 * @param elementName
	 *            element to be clicked
	 */
	public void click(String elementName) {
		getControlDriver(elementName).click();
	}

	/**
	 * Mouse overs the menu and the submenus delimited by '|' and clicks on the last
	 * submenu.
	 *
	 * @param menu
	 *            menu to be mouse overed. To be given as:
	 *            menu|submenu1|submenu2|submenu3|....
	 * @param subMenu
	 *            the sub menu
	 * @return
	 */
	public void mouseOverAndClick(String menu) {
		Actions actions = new Actions(webDr);
		if (menu.contains("|")) {
			clickFromData(menu.split("\\|"));
		} else {
			actions.moveToElement(findElement(menu)).build().perform();
		}
	}

	private void clickFromData(String[] subMenu) {
		String valueForMouseHOver;
		String valueForClick = subMenu[subMenu.length - 1];
		for (int i = 0; i < subMenu.length; i++) {
			valueForMouseHOver = subMenu[i];
			if (valueForMouseHOver.matches(valueForClick) || valueForMouseHOver.equals(valueForClick)) {
				click(valueForClick);
			} else {
				mouseOverAndClick(valueForMouseHOver);
				SleepUtils.sleep(TimeSlab.YIELD);
			}
		}
	}

	/**
	 * Performs the drag and drop operation.
	 *
	 * @param fromLocator
	 *            Contains the locator of source element
	 * @param toLocator
	 *            Contains the locator of destination element
	 */
	public void dragAndDrop(String fromLocator, String toLocator) {
		try {
			WebElement from = findElement(fromLocator);
			WebElement to = findElement(toLocator);
			Actions builder = new Actions(webDr);
			Action dragAndDrop = builder.clickAndHold(from).moveToElement(to).release(to).build();
			dragAndDrop.perform();
		} catch (StaleElementReferenceException e) {
			logger.handleError("Failed to drag drop elements ", fromLocator, " , ", toLocator, " ", e);
		}
	}

	/**
	 * Switch to another window.
	 *
	 * @param title
	 *            Contains title of the new window
	 * @return true or false
	 */
	public boolean switchToWindow(String title) {
		String currentWindow = webDr.getWindowHandle();

		Set<String> availableWindows = webDr.getWindowHandles();

		for (String windowId : availableWindows) {
			try {
				WebDriver w = webDr.switchTo().window(windowId);

				if (w.getTitle().equals(title))
					return true;
			} catch (NoSuchWindowException e) {
				logger.warning("No child window is available to switch", windowId, e, title);
			}
		}

		try {
			webDr.switchTo().window(currentWindow);
		} catch (NoSuchWindowException e) {
			logger.warning("Cannot switch back to current window", currentWindow, e, title);
		}

		return false;
	}
	
	
	
	public WebElement SwitchToFrame(String elementName) {

		try {
			WebElement element = null;
			// By executing a java script
			JavascriptExecutor js = (JavascriptExecutor) webDr;
			Integer numberOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());
			System.out.println("Number of iframes on the page are " + numberOfFrames);

			// By finding all the web elements using iframe tag
			List<WebElement> iframeElements = webDr.findElements(By.cssSelector(elementName));
			System.out.println("The total number of iframes are " + iframeElements.size());

			for (WebElement webElement : iframeElements) {

				webDr.switchTo().frame(webElement);

			}

			return element;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warning("Cannot switch to frame",e);
		}
			
		
		return null;

	}

	

	/**
	 * Scroll page
	 */

	public void scrollPage(){
		executeJavaScript("window.scrollBy(0,1000)");
	}
	/**
	 * JavaScript method added to click on JavaScript web element.
	 * 
	 * @param elementName
	 */
	public void JScriptClick(String elementName) {

		WebElement element = null;
		try {
			element = (WebElement) getControlDriver(elementName);
			JavascriptExecutor js = (JavascriptExecutor) webDr;
			
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			logger.handleError("Failed to click element", e);
		}

	}

	
	/**
	 * Uploads a file.
	 *
	 * @param filePath
	 *            Contains path of the file to be uploaded
	 * @param browse
	 *            Contains locator of the browse button
	 * @param upload
	 *            locator of the upload button
	 */
	public void fileUpload(String filePath, String browse, String upload) {
		getControlDriver(browse).setValue(filePath);
		if (upload != null) {
			click(upload);
		}
	}

	/**
	 * Compares actual and expected text from the application.
	 *
	 * @param elementName
	 *            element for which text is to be checked
	 * @return boolean result
	 */
	public String getText(String elementName) {
		return getControlDriver(elementName).getValue();

	}

	/**
	 * Verify Tooltip's text.
	 *
	 * @param elementName
	 *            Give element for which tooltip is visible
	 * @param expectedText
	 *            the expected text
	 * @return the tool tip
	 */
	public String getToolTip(String elementName, String attributeName) {
		try {
			return getAttribute(elementName, attributeName); // CHECKME: expectedText as attrbuteName
		} catch (Exception e) {
			logger.handleError("Check Tool Tip: " + attributeName, e);
		}
		return null;
	}

	/**
	 * Interface for handleAlert
	 */
	@FunctionalInterface
	public interface AlertHandler {

		/**
		 * Handle.
		 *
		 * @param text
		 *            the alert text received
		 * @return if true, alert will be accepted; if false, dismissed
		 */
		public boolean handle(String text);
	}

	/**
	 * Handle alert.
	 *
	 * @param handler
	 *            the handler
	 * @param timeout
	 *            the timeout
	 * @return true, if successful
	 * @throws NoAlertPresentException
	 *             the no alert present exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public boolean handleAlert(AlertHandler handler, long timeout) {
		boolean rc = false; // default is dismiss
		Alert alert = null;
		try {
			alert = waitForAlert(timeout);

			String text = alert.getText();
			logger.trace("Got alert", text);
			rc = handler.handle(text);
		} catch (NullPointerException e) {

			logger.handleError("NullPointerException caught", e);
		} catch (Exception e) {

			logger.handleError("Alert is not present", e);
		} finally {
			if (alert != null) {
				if (rc)
					alert.accept();
				else
					alert.dismiss();
			}

		}

		return rc;
	}

	/**
	 * Wait for alert.
	 *
	 * @param timeout
	 *            the timeout
	 * @return the alert
	 * @throws NoAlertPresentException
	 *             the no alert present exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private Alert waitForAlert(long timeout) throws NoAlertPresentException, InterruptedException {
		ExpectedCondition<Alert> condition = ExpectedConditions.alertIsPresent();
		return wait(condition, timeout);
	}

	/**
	 * Interface for handlePrompt
	 */
	public interface PromptHandler {

		public String handle(String text);
	}

	/**
	 * Handle prompt
	 *
	 * @param text
	 *            the alert text received
	 * @return if non-null, value will be sent and alert accepted; if false,
	 *         dismissed
	 */
	public String handlePrompt(PromptHandler handler, long timeout) {
		String value = null;
		Alert alert = null;
		try {
			alert = waitForAlert(timeout);
			String text = alert.getText();
			logger.trace("Got alert", text);
			value = handler.handle(text);

		} catch (NullPointerException e) {

			logger.handleError("NullPointerException caught", e);
		} catch (Exception e) {

			logger.handleError("Alert Prompt is not present", e);
		} finally {
			if (alert != null) {
				if (value != null) {
					alert.sendKeys(value);
					alert.accept();
				} else {
					alert.dismiss();
				}
			}
		}
		return value;
	}

	/**
	 * Handle prompts
	 *
	 * @param value
	 *            text value to be set in alert box
	 */
	public void handlePrompt(final String value) {
		handlePrompt(promptHandler -> value, toInterval(SleepUtils.TimeSlab.MEDIUM));
	}

	/**
	 * It Executes the given Javascript.
	 *
	 * @param script
	 *            script to be executed
	 * @return the object
	 */
	public Object executeJavaScript(String script, Object... varargs) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) webDr;
			logger.debug("executeJavaScript", script, varargs);
			return js.executeScript(script, varargs);
		} catch (Exception e) {
			logger.handleError("Executing script", script, e, varargs);
		}
		return null;
	}
	
	
	public Object executeJavaScript(String script, String elementName) {
		WebElement ele = null;
		try {
			if(elementName != null)
			ele = (WebElement) getControlDriver(elementName);
			JavascriptExecutor js = (JavascriptExecutor) webDr;
			logger.debug("executeJavaScript", script, ele);
			return js.executeScript(script, ele);
		} catch (Exception e) {
			logger.handleError("Executing script", script, e, ele);
		}
		return null;

	}


	/**
	 * Retrieve drop down options.
	 *
	 * @param elementName
	 *            name of dropdown
	 * @return the drop down options
	 */
	public String[] getDropDownOptions(String elementName) {
		return getControlDriver(elementName).getOptions();
	}

	/**
	 * Checks the attribute value.
	 *
	 * @param elementName
	 *            name of element
	 * @param attribute
	 *            attribute to be set
	 * @param value
	 *            value of the attribute
	 */
	public void setAttribute(String elementName, String attribute, String value) {
		try {
			executeJavaScript("arguments[0].setAttribute(arguments[1], arguments[2])", elementName, attribute, value);
		} catch (Exception e) {
			logger.handleError("Failed to set ", value, " for attribute ", attribute, " for the element ", elementName,
					" ", e);
		}
	}

	/**
	 * Gets the attribute value.
	 *
	 * @param elementName
	 *            name of element
	 * @param attribute
	 *            attribute name
	 * @return the attribute
	 */
	public String getAttribute(String elementName, String attribute) {
		try {
			WebElement element = findElement(elementName);
			return element.getAttribute(attribute);
		} catch (WebDriverException e) {
			logger.handleError("Failed to get value for attribute ", attribute, " for the element ", elementName, " ",
					e);
		}
		return null;
	}

	/**
	 * For Double clicking element identified by locator.
	 *
	 * @param elementName
	 *            name of element
	 */

	public void doubleclick(String elementName) {
		Actions axn = new Actions(webDr);

		axn.doubleClick(findElement(elementName)).build().perform();
	}

	/**
	 * For right clicking on any element.
	 *
	 * @param elementName
	 *            name of element
	 * @param option
	 *            the option
	 */
	public void rightClick(String elementName, int option) {
		Actions actions = new Actions(webDr);

		actions.contextClick(findElement(elementName));

		for (int i = 0; i < option - 1; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * If some thing generate from web server to web browser form that is cookies
	 * here it will clear all cookies in the form.
	 */
	public void clearCookies() {
		webDr.manage().deleteAllCookies();
	}

	/**
	 * Checks whether the page is loaded or not.
	 *
	 * @return boolean result
	 */
	public boolean waitForBrowserStability() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				return new Boolean(isBrowserLoaded());
			}
		};

		return wait(expectation, toInterval(SleepUtils.TimeSlab.MEDIUM));
	}

	/**
	 * Checks if body of the page is loaded or not.
	 *
	 * @return Boolean Result
	 */
	public boolean isBrowserLoaded() {
		return "complete".equals(executeJavaScript("return document.readyState"));
	}

	/**
	 * It verifies the presence of element in frame.
	 *
	 * @param frame
	 *            the new frame
	 */
	public boolean setFrame(String frame) {
		WebElement element = null;
		try {
			if (isVisible(frame))
				element = findElement(frame);
		} catch (NoSuchElementException e) {
			logger.handleError("Unable to locate frame with element name " + frame, e);
		}
		if (element == null)
			return false;
		else {
			webDr.switchTo().frame(element);
			return true;
		}
	}

	/**
	 * Return the focus to the parent frame.
	 */

	public void resetFrame() {
		webDr.switchTo().defaultContent();
		logger.trace("Navigated back to webpage from frame");
	}

	/**
	 * It verifies the presence of Element.
	 *
	 * @param elementName
	 *            the element name
	 * @return true, if is visible
	 * @throws NoSuchElementException
	 *             the no such element exception
	 */
	public boolean isVisible(String elementName) throws NoSuchElementException {
		try {
			return findElement(elementName).isDisplayed();
		} catch (ElementNotVisibleException e) {
			logger.handleError("Error: Caused while Verifying the Presence of Element \" " + elementName + " \"", e);
			return false;
		}
	}

	/**
	 * Events which can be performed on Mobile web application or on mobile device.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param actionType
	 *            the action type
	 */
	public void keyEvents(String ctrlName, String actionType) {
		WebElement webEle = null;
		try {
			webEle = findElement(ctrlName);
		} catch (NoSuchElementException e) {
			logger.handleError("Cannot find element: " + ctrlName, e);
		}

		if (webEle != null) {

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
				logger.handleError("Invalid keyEvents:" + actionType);
			}

			logger.trace("keyEvents", ctrlName, actionType);
		}
	}

	/**
	 * selecting a value from a dropdown.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param seleitem
	 *            the seleitem
	 */
	public void select(String ctrlName, String seleitem) {
		Select dropdown = new Select(findElement(ctrlName));
		dropdown.selectByVisibleText(seleitem);

		logger.trace("select", ctrlName, seleitem);
	}

	/**
	 * It waits for text to be diplayed in particular web element.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean waitForText(String ctrlName, String text, long timeInSec) {
		boolean result = wait(ExpectedConditions.textToBePresentInElement((By) findElement(ctrlName), text), timeInSec);

		logger.trace("WaitForText", ctrlName, text, timeInSec, result);

		return result;
	}

	/**
	 * Takes screenshot and embedded in to logs.
	 *
	 * @return the file
	 */
	public File takescreenshot() {
		try {
			return ((TakesScreenshot) webDr).getScreenshotAs(OutputType.FILE);
		} catch (WebDriverException e) {
			logger.handleError("take screenshot", e);
		} catch (java.lang.UnsupportedOperationException ue) {
			// TODO: return null; and handle null's in the caller
			logger.handleError("take screenshot not supported", ue);
		}
		return null;
	}

	public void setCalendar(WebElement element, String value) {
		element.click();
		webDr.findElement(By.linkText(value.substring(0, value.indexOf('/')))).click();
	}

	/**
	 * Convert a Time slab to integer value in seconds
	 *
	 * @param svalue
	 *            the svalue
	 * @return timeout value in seconds
	 */
	public long toInterval(SleepUtils.TimeSlab slab) {
		return SleepUtils.interval(slab.level) / 1000; // FIXME: Round up to 1 when 0
	}

	/**
	 * To print reference variable in java the we are going for tostring() method
	 * tostring() belongs to object class Overriding toString() method to return
	 * WebUIDriver format string
	 */
	@Override
	public String toString() {
		return StringUtils.mapString(this);
	}

	private ObjectFinder objectFinder;
	private ControlDriverFactory controlDriverFactory;
	private WebDriver webDr;
	private final Browser browser;
	public final TableHandler th;
	private final LogUtils logger = new LogUtils(this);

}
