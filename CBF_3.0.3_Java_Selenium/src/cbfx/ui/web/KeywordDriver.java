/******************************************************************************
$Id : KeywordDriver.java 12/23/2016 4:09:02 PM
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
package cbfx.ui.web;

import static cbf.engine.TestResultLogger.done;
import static cbf.engine.TestResultLogger.failed;
import static cbf.engine.TestResultLogger.passed;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cbf.engine.TestResultLogger;
import cbf.harness.Harness;
import cbf.model.FailureCause;
import cbf.utils.FrameworkException;
import cbf.utils.GlobalData;
import cbf.utils.LogUtils;
import cbf.utils.TypeUtils;
import cbf.utils.StringUtils;

import cbfx.ui.selenium.WebUIDriver;
import cbfx.ui.selenium.WebUIDriver.AlertHandler;
import cbfx.ui.web.KeywordDriver.Action;
import cbfx.utils.AWTUtils;
import cbfx.utils.ProcessUtils;
import cbfx.utils.SleepUtils;

public class KeywordDriver {

	final public WebUIDriver webUIDriver;
	/*
	 * String added to check and verify the Lightening view of SFDC
	 */
	String lightningMode = (String) Harness.configuration().get("LightningView");
	
	/**
	 * Constructor initializes the WebUIDriver.
	 *
	 * TODO: Accept WebUIDriver and Map params as parameter. params are
	 * configuration parameters such as timeouts etc. Thus, Sleep timeouts etc. can
	 * now can be made local parameters
	 * 
	 * @param webUIDriver
	 *            the web UI driver
	 */
	public KeywordDriver(WebUIDriver webUIDriver) {
		this.webUIDriver = webUIDriver;
	}

	/**
	 * launchApplication.
	 *
	 * @param url
	 *            the url
	 */
	public void launchApplication(String url) {
		act("Launch Application", f("Launched %s", url), (Action) () -> webUIDriver.launch(url, true));
	}
	public void launchApplicationAppium(String url) {
		act("Launch Application", f("Launched %s", url), (Action) () -> webUIDriver.launchApplicationAppium(url));
	} 
	/**
	 * Shut downs the webdriver Keyword equivalent of close().
	 */
	public void closeApplication() {
		act("Close Application", "Closed", (Action) () -> webUIDriver.close());
	}

	/**
	 * Check title.
	 *
	 * @param expectedTitle
	 *            the expected title
	 */
	public boolean checkTitle(String expectedTitle) {
		return check("Check Title", f("Title should be %s", expectedTitle), (CheckAction) ((OnCheck onChk) -> {
			String actualTitle = webUIDriver.getTitle();
			return onChk.apply(match(actualTitle, expectedTitle), f("Title was %s", actualTitle));
		}));
	}

	/**
	 * 
	 * checkElementPresent vs checkPresent
	 *
	 * @param elementName
	 *            the element name
	 * @return true, if successful
	 */
	public boolean checkPresent(String elementName) {
		return check(f("Check %s", elementName), "Element present", (CheckAction) ((OnCheck onChk) -> onChk
				.apply(webUIDriver.isPresent(elementName), f("Element was %s", elementName))));

	}
	public boolean checkElementPresent(String elementName, int timeout) {		
		return check(
				f("Check %s", getElement(elementName)),
				"Element present",
				(CheckAction) ((OnCheck onChk) -> {
					return onChk.apply(webUIDriver.isPresent(elementName),
							f("Element was %s",getElement(elementName) ));
				}));
	}
	/**
	 * Sets the value.
	 *
	 * @param elementName
	 *            the element name
	 * @param value
	 *            the value
	 */
	public void setValue(String elementName, String value) {
		act(f("Set value %s to %s", value, elementName), f("Sets %s into %s", value, elementName),
				(Action) () -> webUIDriver.setValue(elementName, value));
	}

	/**
	 * Sets the values.
	 *
	 * @param elementName
	 *            the element name
	 * @param value
	 *            the value
	 */
	public void setValues(String elementName, String value) {
		act(f("Set value %s for %s", value, elementName), f("sets %s for %s", value, elementName),
				(Action) () -> webUIDriver.setValues(elementName.split("\\|"), value.split("\\|")));
	}

	
	/**
	 * execute javascripts 
	 * @param script
	 * @param varargs
	 */
	
	public void executeJavaScript(String script, Object... varargs) {
		act(f("Execute script %s", script), f("Executes script %s", script),
				(Action) () -> webUIDriver.executeJavaScript(script, varargs));
	}
	
	public void executeJavaScript(String script, String elementName) {
		act(f("Execute script %s", script), f("Executes script %s", script),
				(Action) () -> webUIDriver.executeJavaScript(script, elementName));
	}

	
	/**
	 * Sets the values.
	 *
	 * @param pageName
	 *            the page name
	 * @param data
	 *            the data
	 */
	public void setValues(String pageName, Map data) {
		act(f("Set all fields on %s page", pageName), f("Sets all fields on %s", pageName),
				(Action) () -> webUIDriver.setValues(pageName, data));
	}

	/**
	 * Clear.
	 *
	 * @param elementName
	 *            the element name
	 */
	public void clear(String elementName) {
		act(f("Clear %s", elementName), "Cleared field", (Action) () -> webUIDriver.clear(elementName));
	}

	/**
	 * Navigate menu.
	 *
	 * @param menuList
	 *            the menu list
	 */
	public void navigateMenu(String menuList) {
		act(f("Navigate to %s", menuList), f("Navigated to %s", menuList),
				(Action) () -> webUIDriver.navigateMenu(menuList.split(",")));
	}

	/**
	 * Check text on page.
	 *
	 * @param text
	 *            the text
	 * @param timeout
	 *            the timeout
	 * @return true, if successful
	 */
	public boolean checkTextOnPage(String text, String timeout) {
		return check(f("Check %s", text), f("value %s", text), (CheckAction) ((OnCheck onChk) -> {
			long timeOut = toInterval(timeout);
			return onChk.apply(webUIDriver.waitTilltextOnPage(text, timeOut), f("Value was %s", text));
		}));
	}

	/**
	 * Mouse over and click.
	 *
	 * @param menu
	 *            the menu
	 */
	public void mouseOverAndClick(String menu) {
		act(f("Mouse over on %s", menu), f("clicked %s", menu), (Action) () -> webUIDriver.mouseOverAndClick(menu));
	}

	/**
	 * Click.
	 *
	 * @param elementName
	 *            the element name
	 */
	public void click(String elementName) {
		act(f("Click on %s", elementName), f("clicked %s", elementName), (Action) () -> webUIDriver.click(elementName));
	}

	public void rightClick(String elementName, int option) {
		act(f("Right click on '%s'", elementName), f("Right clicked '%s'", elementName),
				(Action) () -> webUIDriver.rightClick(elementName, option));
	}

	/**
	 * Drag and drop.
	 *
	 * @param fromLocator
	 *            the from locator
	 * @param toLocator
	 *            the to locator
	 */
	public void dragAndDrop(String fromLocator, String toLocator) {
		act("Drag and Drop", f("Drag and Dropped the %s to %s", fromLocator, toLocator),
				(Action) () -> webUIDriver.dragAndDrop(fromLocator, toLocator));
	}

	public void setAttribute(String elementName, String attribute, String value) {
		act(f("Set '%s' for '%s'", attribute, elementName), f("Sets '%s' for '%s'", value, attribute),
				(Action) () -> webUIDriver.setAttribute(elementName, attribute, value));
	}

	/**
	 * Check text.
	 *
	 * @param elementName
	 *            the element name
	 * @param expectedText
	 *            the expected text
	 * @return true, if successful
	 */
	public boolean checkText(String elementName, String expectedText) {
		return check(f("Check text '%s'", elementName), f("Text should be '%s'", expectedText),
				(CheckAction) (OnCheck check) -> {
					String text = webUIDriver.getText(elementName);
					return check.apply(match(text, expectedText), f("Text was '%s'", expectedText));
				});
	}

	/**
	 * Check tool tip.
	 *
	 * @param elementName
	 *            the element name
	 * @param expectedText
	 *            the expected text
	 * @return true, if successful
	 */
	public boolean checkToolTip(String elementName, String expectedText) {
		return check(f("Check Tool Tip %s", elementName), f("Should get Attribute '%s'", expectedText),
				(CheckAction) (OnCheck check) -> {
					String value = webUIDriver.getToolTip(elementName, expectedText);
					return check.apply(match(value, expectedText), f("'%s' value was '%s'", expectedText, value));
				});
	}

	/**
	 * Draw highlight.
	 *
	 * @param elementName
	 *            the element name
	 */
	public void drawHighlight(String elementName) {
		act(f("Draw Highlight %s", elementName), f("%s Highlighted", elementName), (Action) () -> webUIDriver
				.executeJavaScript(elementName, "arguments[0].style.border='2px groove red'"));
	}

	/**
	 * Send key.
	 *
	 * @param keyEvent
	 *            the key event
	 */
	public void sendKey(String keyEvent) {
		act(f("Send Key %s", keyEvent), f("Sent %s", keyEvent), (Action) () -> AWTUtils.sendKey(keyEvent));
	}

	/**
	 * File upload.
	 *
	 * @param filePath
	 *            the file path
	 * @param browse
	 *            the browse
	 * @param upload
	 *            the upload
	 */
	public void fileUpload(String filePath, String browse, String upload) {
		act(f("Upload %s", filePath), f("%s Uploaded", filePath),
				(Action) () -> webUIDriver.fileUpload(filePath, browse, upload));
	}

	/**
	 * Handle alert.
	 *
	 * @param text
	 *            the text
	 * @param button
	 *            the button
	 */
	public boolean checkAlert(String text, String button) {
		return check("Check Alert", f("Alert with '%s' should popup", text), (CheckAction) (OnCheck check) -> {
			class Temp { // work around for final matched
				boolean matched = false;
			}
			;
			final Temp temp = new Temp();

			webUIDriver.handleAlert((WebUIDriver.AlertHandler) (String alerttext) -> {
				temp.matched = alerttext.matches(text);
				check.apply(temp.matched,
						temp.matched ? f("Alert '%s' matched", alerttext) : f("Alert '%s' did not match", alerttext));
				return TypeUtils.string2Bool(button);
			}, toInterval("Low"));
			return temp.matched;
		});
	}

	
	public boolean checkAlert(String text) {
		return check("Check Alert", f("Alert with '%s' should popup", text), (CheckAction) (OnCheck check) -> {
			class CheckAlert implements AlertHandler {
				@Override
				public boolean handle(String alertText) {
					if (alertText.matches(text))
						return true;
					else
						return false;
				}
			}
			;
			CheckAlert ckAlert = new CheckAlert();
			return check.apply(webUIDriver.handleAlert(ckAlert, toInterval("Low")),
					f("Alert with '%s' was present and handled", text));
		});
	}

	public void checkPrompt(String value) {
		act("Check Prompt", "Prompt should popup", (Action) () -> webUIDriver.handlePrompt(value));
	}

	/**
	 * Doubleclick.
	 *
	 * @param elementName
	 *            the element name
	 */
	public void doubleclick(String elementName) {
		act(f("Double click on %s", elementName), f("Double clicked on %s", elementName),
				(Action) () -> webUIDriver.doubleclick(elementName));
	}

	/**
	 * Reset frame.
	 */
	public void resetFrame() {
		act("Switch to parent Frame", "Switched to parent frame", (Action) () -> webUIDriver.resetFrame());

	}

	/**
	 * Key events.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param actionType
	 *            the action type
	 */
	public void keyEvents(String ctrlName, String actionType) {
		act(f("Key events in %s", ctrlName), f("Keyed '%s'", actionType),
				(Action) () -> webUIDriver.keyEvents(ctrlName, actionType));
	}

	/**
	 * Select.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param seleitem
	 *            the seleitem
	 */
	public void select(String ctrlName, String seleitem) {
		act(f("Select %s on dropdown list", seleitem), f("Selected %s on dropdown list", seleitem),
				(Action) () -> webUIDriver.select(ctrlName, seleitem));
	}

	/**
	 * Wait for text.
	 *
	 * @param ctrlName
	 *            the ctrl name
	 * @param text
	 *            the text
	 */
	public boolean waitForText(String ctrlName, String text) {
		return check(f("Wait for text on %s", ctrlName), f("Should wait for text %s", text),
				(CheckAction) (OnCheck oncheck) -> {
					long timeInSec = 15;
					return oncheck.apply(webUIDriver.waitForText(ctrlName, text, timeInSec),
							f("Waited for text %s", text));
				});
	}

	/**
	 * Switch to window.
	 *
	 * @param title
	 *            the title
	 * @return true, if successful
	 */
	public boolean switchToWindow(String title) {
		act("Switch to window", f("switched to window %s", title), (Action) () -> webUIDriver.switchToWindow(title));
		return true;
	}

	/**
	 * Check the running process and kill it.
	 *
	 * @param serviceName
	 *            Give name of the process that you want to kill
	 * @return Boolean
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void killProcess(String serviceName) throws IOException {
		act(f("Kill Process '%s'", serviceName), f("'%s' killed", serviceName),
				(Action) () -> ProcessUtils.killProcess(serviceName));
	}

	/**
	 * Convert a timeout value in String form - slab name: YIELD/HIGH/LOW/MEDIUM:
	 * recommended - actual value encoded in string?.
	 *
	 * @param svalue
	 *            timeout specification in string form
	 * @return timeout value in seconds
	 */
	public long toInterval(String svalue) {
		if (svalue == null)
			return 0;

		SleepUtils.TimeSlab slab = SleepUtils.slabByName(svalue);
		if (slab != null)
			return webUIDriver.toInterval(slab);

		try {
			return Long.parseLong(svalue);
		} catch (NumberFormatException e) {
			logger.handleError("Invalid timeout value", svalue, e);
		}
		return 0;
	}

	/**
	 * The Interface Action.
	 */
	public interface Action {

		/**
		 * Act.
		 */
		public void act();
	}

	/**
	 * Act.
	 *
	 * @param name
	 *            the name
	 * @param msg
	 *            the msg
	 * @param action
	 *            the action
	 */
	private void act(String name, String msg, Action action) {
		try {
			action.act();
			done(name, "", msg);
		} catch (Exception e) {
			handleError(name, msg, e);
		}
	}

	/**
	 * handle the error and logs error message
	 * 
	 * @param name
	 * @param context
	 * @param e
	 * @param varargs
	 */
	private void handleError(String name, String context, Exception e, Object... varargs) {
		if (e instanceof FrameworkException) {
			TestResultLogger.handleError(name, f("Error while '%s': %s", context, e), varargs);
		} else {
			TestResultLogger.handleError(name, f("Unexpected error while '%s':'%s'", context, e), e, varargs);
		}
	}

	/**
	 * checks the presence of specified options in the dropdown.
	 *
	 * @param elementName
	 *            name of element
	 * @param optionsStr
	 *            options to be checked with comma separated values
	 * @return true if successfully matched
	 */
	public boolean checkDropDownOptions(String elementName, String optionsString) {
		return check(f("Check '%s' options for '%s'", optionsString, elementName),
				f("Options '%s' should match for dropdownlist", optionsString), (CheckAction) (OnCheck onChk) -> {
					String[] expectedOptions = optionsString.split(",");
					String firstUnmatched = matchOptions(elementName, expectedOptions);
					String actualMsg = (firstUnmatched == null) ? f("Options '%s' matched", optionsString)
							: f("Option '%s' did not match '%s'", firstUnmatched, optionsString);
					return onChk.apply(firstUnmatched == null, actualMsg);
				});
	}

	/**
	 * match a list of options against a dropdown element.
	 *
	 * @param elementName
	 *            name of element
	 * @param expectedOptions
	 *            array of expected options
	 * @return the first unmatched option; null if all matched
	 */
	protected String matchOptions(String elementName, String[] expectedOptions) {
		logger.debug("matching", expectedOptions);

		String[] actualList = webUIDriver.getDropDownOptions(elementName);
		List<String> actuals = Arrays.asList(actualList);
		for (String actual : actuals) {
			if (match(actual, expectedOptions) < 0) {
				logger.trace("one item didnt match", actual, expectedOptions);
				return actual;
			}

			logger.debug("matched", actual);
		}

		return null;
	}

	public interface OnCheck {
		public boolean apply(boolean result, String actualMsg, Object... details);
	}

	public interface CheckAction {
		public boolean check(OnCheck onChk);
	}

	public boolean check(String name, String expectedMsg, CheckAction ckAction) {
		try {
			return ckAction.check((boolean result, String actualMsg, Object... details) -> {
				if (result) {
					passed(name, expectedMsg, actualMsg);
				} else {
					try {
						failed(name, expectedMsg, actualMsg, (FailureCause) details[0]);
					} catch (Exception e) {
						failed(name, expectedMsg, actualMsg, FailureCause.NonSpecific);
					}
				}
				return result;
			});
		} catch (Exception e) {
			handleError(name, f("expecting %s", expectedMsg), e);
			return false;
		}
	}

	/**
	 * 
	 * @param format
	 * @param args
	 * @return
	 */
	protected static String f(String format, Object... args) {
		return String.format(format, args);
	}

	protected int match(String item, String[] array) {
		int i = 0;
		for (String s : array) {
			if (match(item, s))
				return i;
			i++;
		}
		return -1;
	}
	
	public void setGlobalValue(String key, Object variable){
		gData.put(key,variable);
	}

	
	public List getGlobalList(String key){
		List dataList = (List<?>) gData.get(key);
		if(!dataList.isEmpty()){
				return dataList;
		}
		return null;
	}
	
	public String getGlobalValue(String key){
		String value = (String) gData.get(key);
		if(value != null){
		return  value;
		}
		return null;
	}
	
	
		
	public void SwitchToFrame(String elementName) {
		act("Switch to frame", "Switched to frame", (Action) () -> webUIDriver.SwitchToFrame(elementName));

	}

	public void JScriptClick(String elementName) {
		
		act(f("Javascript on %s", elementName), f("Javascript %s", elementName),
				(Action) () -> webUIDriver.JScriptClick(elementName));

	}
	
	
	public void SFDCListSelect(String elementName, String seleitem) {
		//If application mode is Lightning
		if (lightningMode.equalsIgnoreCase("Yes")) {
			
			JScriptClick(elementName);			
		}
		else {
			select(elementName, seleitem);
		}	
	}
	public String getElement(String element) {
		String elementName = element.split("\\.")[1];
		return elementName;
	}

	public Map getGlobalMap(String key){
	return (Map) gData.get(key);
	}

	/**
	 * Assumptions: the text on a page (such as title, select-visible-text) might
	 * not always be exact match; This method provides a uniform match for such
	 * checks.
	 */
	protected boolean match(String actualText, String expected) {
		return StringUtils.match(actualText, expected, false, true);
	}

	private LogUtils logger = new LogUtils(this);
	protected GlobalData gData = new GlobalData();
}
