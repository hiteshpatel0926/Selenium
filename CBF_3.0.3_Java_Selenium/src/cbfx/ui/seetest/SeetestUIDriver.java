/******************************************************************************
$Id : SeetestUIDriver.java 12/23/2016 4:08:55 PM
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

package cbfx.ui.seetest;

import static cbf.engine.TestResultLogger.handleError;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.testng.Assert;
import com.experitest.client.Client;
import cbf.harness.Harness;
import cbf.utils.LogUtils;
import cbfx.ui.objectmap.ObjectMap;

public class SeetestUIDriver {

	public SeetestUIDriver(Map parameters) {
		this.client = new Client(host, port, true);
		Map obj = (Map<String, Object>) Harness.configuration().get("ObjectMap");
		objMap = (ObjectMap) Harness.pluginManager().getPlugin((String) obj.get("plugin"), null);
	}

	/**
	 * Clears the catche and returns true
	 * 
	 */
	public boolean doiOSNativeClearCache() {
		try {
			client.launch("safari:http://google.com", true, false);
			client.click(NATIVE, "xpath=//*[@text='Bookmarks']", 0, 1);
			client.click(NATIVE, "xpath=//*[@text='Clear']", 0, 1);
			client.click(NATIVE, "xpath=//*[@t.ext='Clear History']", 0, 1);
			client.click(NATIVE, "xpath=//*[@text='Done']", 0, 1);
			wait(time);
		} catch (Exception e) {
			logger.handleError("Unable to Clear cache",e);
		}
		return true;
	}

	/**
	 * Sends text to an element
	 * 
	 * @param ctrlName
	 *            Name of the element whose locator is required
	 * @param value
	 *            Value of the text to be sent
	 * @return true or false result
	 */
	public boolean enterText(String ctrlName, final String value) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.elementSendText((String) getProperty.get("zone"), element, idx, value);
		} catch (Exception e) {
			handleError("enterText - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	// getElement
	/**
	 * gets the property of an element
	 * 
	 * @param getProperty
	 *            gets the property of the element from the locator
	 * @return element
	 */
	public String getElement(Map getProperty) {
		String element = "";
		if (getProperty.get("id") != "") {
			element = "id=" + getProperty.get("id").toString();
		} else if (getProperty.get("xpath") != "") {
			element = "xpath=" + getProperty.get("xpath").toString();
		} else if (getProperty.get("text") != "") {
			element = "text=" + getProperty.get("text").toString();
		} else if (getProperty.get("contentDescription") != "") {
			element = "contentDescription=" + getProperty.get("contentDescription").toString();
		} else if (getProperty.get("hint") != "") {
			element = "hint=" + getProperty.get("hint").toString();
		} else if (getProperty.get("class") != "") {
			element = "class=" + getProperty.get("class").toString();
		} else if (getProperty.get("css") != "") {
			element = "css=" + getProperty.get("css").toString();
		}

		return element;

	}

	// getControl
	public Map getControl(String childName) {
		return objMap.properties(childName);
	}

	/**
	 * Gets the element map.
	 *
	 * @param elementName
	 *            the element name
	 * @return the element map
	 */
	// getElementMap
	public Map getElementMap(String elementName) {
		for (Map map : childMap) {
			String element = (String) map.get("elementName");
			if (element.equals(elementName)) {
				return map;
			}
		}
		return null;
	}

	// touch
	/**
	 * Click an element
	 * 
	 * @param ctrlName
	 *            Name of the element whose locator is required
	 * @return true or false result
	 */
	public boolean touch(final String ctrlName) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.click(getProperty.get("zone").toString(), element, idx, 1);
		} catch (Exception e) {
			handleError("touch - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	// Image Touch Duplicate
	/**
	 * Click an Image
	 * 
	 * @param ctrlName
	 *            Name of the element whose locator is required
	 * @return true or false result
	 */
	public boolean touchImage(final String ctrlName) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.click(getProperty.get("zone").toString(), element, idx2, 1);
		} catch (Exception e) {
			handleError("touchImage - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	// Select
	/**
	 * Select an option from a Select element in a WebView
	 * 
	 * @param ctrlName
	 *            Name of the element whose locator is required
	 * @param value
	 *            Value to be selected
	 * @return true or false result
	 */

	public boolean select(final String ctrlName, final String value) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String objEle = (String) getProperty.get("zone");
			String method = objEle.split("=")[0];
			String element = objEle.split("=")[1];
			client.hybridSelect("", idx, method, element, value);
		} catch (Exception e) {
			handleError("select : %s" + ctrlName, e);
		}
		return true;
	}

	/**
	 * Swipe the screen
	 * 
	 * @param ctrlName
	 *            Swipe the screen for the element
	 * @param direction
	 *            Swipes the screen in particular direction
	 * @param offset
	 *            ,time The command will only perform the swipe within a given
	 *            container
	 * @return true or false result
	 */

	public boolean elementSwipe(final String ctrlName, final String direction, final int offset, final int time) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.elementSwipe(getProperty.get("zone").toString(), element, idx, direction, offset, time);
		} catch (Exception e) {
			handleError("elementSwipeDown - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	/**
	 * Check if a given element is found in the specified zone
	 * 
	 * @param ctrlName
	 *            verifyProperty for the element
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean verifyProperty(final String ctrlName) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			if (client.isElementFound(getProperty.get("zone").toString(), element, idx)) {
				logger.handleError("Text is not Displayed");
			}
		} catch (Exception e) {
			handleError("verifyProperty - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	// isElementFound Duplicate
	/**
	 * Check if a given text is found in the specified zone
	 * 
	 * @param ctrlName
	 *            verifytext for the element
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean verifyText(final String ctrlName) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			if (client.isElementFound(getProperty.get("zone").toString(), element, idx)) {
				logger.handleError("Text is not Displayed");
			}
		} catch (Exception e) {
			handleError("verifyText - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	/**
	 * Set the active device under test
	 * 
	 * @param deviceName
	 *            device name to be set
	 * @return if found returns TRUE,if not found returns FALSE
	 */

	public boolean setDevice(final String deviceName) {
		try {
			client.setDevice(deviceName);
		} catch (Exception e) {
			handleError("Device is not set in SeeTest", e);
		}
		return true;
	}

	/**
	 * Launch or navigate to given URL during runtime.
	 * 
	 * @param Url
	 *            The Url to be launched
	 * @return if found returns TRUE,if not found returns FALSE
	 */

	public boolean launchUrl(final String url) {
		try {
			client.launch(url, true, false);
		} catch (Exception e) {
			handleError("launchUrl not set: " + url, e);
		}
		return true;
	}

	/**
	 * Launch activity (application) during runtime.
	 * 
	 * @param appName
	 *            The applicationName to be launched
	 * @return if found returns TRUE,if not found returns FALSE
	 */

	public boolean launchApplication(String appName) {
		try {
			client.launch(appName, false, false);
		} catch (Exception e) {
			handleError("launchApplication - Invalid control type: " + appName, e);
		}
		return true;
	}

	/**
	 * Close activity (application) if opened.
	 * 
	 * @param appName
	 *            The applicationName to be closed
	 * @return if found returns TRUE,if not found returns FALSE
	 */
	public boolean closeApplication(String appName) {
		try {
			client.applicationClose(appName);
		} catch (Exception e) {
			handleError("closeApplication - Invalid control type: " + appName, e);
		}
		return true;

	}

	/**
	 * Close the soft-keypad if open on the device
	 * 
	 * @return if found returns TRUE,if not found returns FALSE
	 */

	public boolean closeKeyboard() {
		try {
			client.closeKeyboard();
		} catch (Exception e) {
			handleError("closeApplication - Invalid control type", e);
		}
		return true;

	}

	/**
	 * Go back
	 * 
	 * @return if found returns TRUE,if not found returns FALSE
	 */
	public boolean goBack() {
		try {
			client.sendText("{ESC}");
		} catch (Exception e) {
			handleError("goBack - Invalid control type", e);
		}
		return true;

	}

	/**
	 * The command gives the ability to choose device for testing
	 * dynamically,based on query of the devices properties.
	 * 
	 * @param query
	 * 
	 * @param timeout
	 * 
	 * @return if device is found returns TRUE,if not found returns FALSE
	 */

	public String waitForDevice(final String query, final int timeout) {
		try {
			return client.waitForDevice(query, timeout);

		} catch (Exception e) {
			handleError("wait for Device - Invalid Device Name", e);
		}
		return "";
	}

	/**
	 * Returns the text of an element
	 * 
	 * @param ctrlName
	 *            the text from the element to get
	 * @return Value of the element
	 */

	public String elementGetText(final String ctrlName) {
		String gettingValue;
		Map getProperty = getControl(ctrlName);
		int idx = Integer.parseInt((String) getProperty.get(INDEX));
		String element = getElement(getProperty);
		gettingValue = client.elementGetText(getProperty.get("zone").toString(), element, idx);
		return gettingValue;
	}

	/**
	 * Sets sleep until element is identified.
	 * 
	 * @param ctrlName
	 *            elementName to be waited
	 * @param timeOut
	 *            sleep time in milliseconds
	 * @return if element is found returns TRUE,if not found returns FALSE
	 */
	public boolean waitForElement(final String ctrlName, final int timeOut) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.waitForElement((String) getProperty.get("zone"), element, idx, timeOut);
		} catch (Exception e) {
			handleError("waitForElement - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * Setting the title to application
	 * 
	 * @return if element is found returns TRUE,if not found returns FALSE
	 */

	public boolean setApplicationTitle(final String title) {

		try {
			client.setApplicationTitle(title);
		} catch (Exception e) {
			handleError("Failed to setApplicationTitle: " + title, e);
		}
		return true;

	}

	/**
	 * Command to perform drag action based on start coordinates (X,Y) and end
	 * coordinates (X,Y) on the device reflection
	 * 
	 * @return if element is found returns TRUE,if not found returns FALSE
	 */

	public boolean dragCoordinates(int x1, int y1, int x2, int y2, int time) {
		try {
			client.dragCoordinates(x1, y1, x2, y2, time);
		} catch (Exception e) {
			handleError("Failed to dragCoordinates", e);
		}
		return true;
	}

	/**
	 * Send/Insert text to/into the application. Also supports key events.
	 * 
	 * @param Text
	 *            text to be sent to the application
	 * @return if is found returns TRUE,if not found returns FALSE
	 */

	public boolean sendText(final String text) {
		try {
			client.sendText(text);
		} catch (Exception e) {
			handleError("Send Text: " + text, e);
		}
		return true;
	}

	/**
	 * Return the content of the monitor values as string in CSV format.
	 * 
	 * @param csvFilePath
	 *            csvFilePath to store the data.
	 * @return if is found returns TRUE,if not found returns FALSE
	 */

	public boolean getMonitorsData(final String csvFilePath) {
		try {
			client.getMonitorsData(csvFilePath);
		} catch (Exception e) {
			handleError("getMonitorsData: get Monitors Data from: " + csvFilePath, e);
		}
		return true;
	}

	/**
	 * Swipes the screen in thegiven direction
	 * 
	 * @param direction
	 *            Direction in which the screen to be swiped like UP, DOWN,
	 *            LEFT, RIGHT
	 * @param offset
	 *            ,time The command will only perform the swipe within a given
	 *            container-time and offset
	 * @return if is found returns TRUE,if not found returns FALSE
	 */
	public boolean swipe(final String direction, final int offset, final int time) {
		try {
			client.swipe(direction, offset, time);
		} catch (Exception e) {
			handleError("Invalid control type: " + offset, e);
		}
		return true;

	}

	/**
	 * Click in window X,Y offset
	 * 
	 * @param ClickCount
	 *            Count of the clicks
	 * @return if is found returns TRUE,if not found returns FALSE
	 */

	public boolean clickCoordinate(final int x, final int y, final int clickCount) throws InterruptedException {
		try {
			client.clickCoordinate(x, y, clickCount);
		} catch (Exception e) {
			handleError("Click Co-Oridinate: Invalid control type: " + x + "," + y, e);
		}
		wait(time);
		return true;
	}

	/**
	 * Pinch In at specific location using a specific pinch radius.
	 *
	 */

	public void pinch(final int x, final int y, final int radius) {
		try {
			client.pinch(true, x, y, radius);
			wait(time);
		} catch (Exception e) {
			handleError("pinch: Invalid control type: " + x + "," + y, e);
		}

	}

	/**
	 * Clears the log file of the connected device
	 *
	 */

	public void clearDeviceLog() throws InterruptedException {
		try {
			client.clearDeviceLog();
			wait(time);
		} catch (Exception e) {
			handleError("clear Device Log", e);
		}
	}

	/**
	 * Install the application on the tested device during runtime
	 * 
	 * @param appPath
	 *            Application name to be installed.
	 *
	 */

	public void install(final String appPath, final boolean sign, final boolean upgrade) {
		try {
			client.install(appPath, sign, upgrade);
			wait(time);
		} catch (Exception e) {
			handleError("Install Application", e);
		}

	}

	/**
	 * uninstall-unInstall the application on the tested device during runtime
	 * 
	 * @param app
	 *            Application name to be installed.
	 *
	 */
	public void uninstall(final String app) {
		try {
			client.uninstall(app);
			wait(time);

		} catch (Exception e) {
			handleError("Uninstall Application", e);
		}

	}

	/**
	 * applicationClearData-Clear the entire application data and cache from the
	 * device it is installed on during runtime
	 * 
	 * @param app
	 *            Application name is to be cached.
	 *
	 */

	public void applicationClearData(final String app) {
		try {
			client.applicationClearData(app);
		} catch (Exception e) {
			handleError("Application Clear Data", e);
		}
	}

	/**
	 * startVideoRecord-Gives the ability to video record the test execution,
	 * and later view it as part of the generated report
	 *
	 */

	public void startVideoRecord() {
		try {
			client.startVideoRecord();
		} catch (Exception e) {
			handleError("start Video Record", e);
		}
	}

	/**
	 * stopVideoRecord-the ability to video record the test execution, and later
	 * view it as part of the generated report. The command is used after the
	 * command StartVideoRecord and stops the current recording.
	 *
	 */
	public void stopVideoRecord() {
		try {
			client.stopVideoRecord();
		} catch (Exception e) {
			handleError("stop Video Record", e);
		}
	}

	/**
	 * wait
	 *
	 */

	public void wait(String time) throws InterruptedException {
		Thread.sleep(s2time(time));
	}

	// Library functions //

	// Seconds to Milliseconds
	public int s2time(String s) {
		String lw = "2"; // LowWait
		String mw = "10"; // MediumWait
		String hw = "35"; // HighWait
		int wait = 0;

		if (isFloat(s)) {
			Float f = new Float(s);
			wait = (int) (f * 1000);
			return wait;
		} else if (isNumeric(s)) {
			wait = Integer.parseInt(s) * 1000;
			return wait;
		} else {
			if ("LW".equalsIgnoreCase(s)) {
				wait = Integer.parseInt(lw) * 1000;
			} else if ("MW".equalsIgnoreCase(s)) {
				wait = Integer.parseInt(mw) * 1000;
			} else if ("HW".equalsIgnoreCase(s)) {
				wait = Integer.parseInt(hw) * 1000;
			}
			return wait;
		}
	}

	private static final Pattern DOUBLE_PATTERN = Pattern
			.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
					+ "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
					+ "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
					+ "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

	/**
	 * isFloat-Check the given String is Float.
	 *
	 */
	public boolean isFloat(String s) {
		return DOUBLE_PATTERN.matcher(s).matches();
	}

	// Check the given String is Numeric.
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	/**
	 * isNumeric-Check the given String is Numeric.
	 *
	 */
	public void check(final String getTxtVal, final String txtCheck) {
		try {
			Assert.assertEquals(getTxtVal, txtCheck);
		} catch (Exception e) {
			handleError("Check - Message is not displayed Properly: " + getTxtVal, e);
		}
	}

	/**
	 * check-Check Message
	 *
	 */
	public void check(final boolean flag, final String msg) {
		try {
			Assert.assertTrue(flag, msg);
		} catch (Exception e) {
			handleError("Check - Message is not displayed Properly " + msg, e);
		}
	}

	@SuppressWarnings("unused")
	private String getFileName(String nameTest) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		return dateFormat.format(date) + "_" + nameTest + ".png";
	}

	/**
	 * getDeviceInfo
	 * 
	 * @param ctrlName
	 * 
	 */
	public String getDeviceInfo(String ctrlName) {
		String getDeviceInfo = client.getDevicesInformation();
		String[] getDeviceName = getDeviceInfo.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		String getDevInfo = null;
		int count = 0;
		for (String getdev : getDeviceName) {
			count = count + 1;
			if (getdev.toLowerCase().contains(ctrlName.toLowerCase())) {
				String getDInfo = getdev.split("=")[1];
				getDevInfo = getDInfo.substring(1, getDInfo.length() - 1);
				break;
			}
		}
		return getDevInfo;
	}

	/**
	 * releaseClient
	 * 
	 */

	public void releaseClient() {
		try {
			client.releaseClient();
		} catch (Exception e) {
			handleError("Release Client", e);
		}
	}

	// New APIs

	/**
	 * clickIn-Search for an element and click on another element in reference
	 * to it.
	 * 
	 * @param ctrlName
	 * 
	 * @param ctrlName1
	 * 
	 * @param direction
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean clickIn(final String ctrlName, final String direction, final String ctrlName1, final int width,
			final int height) {
		try {
			Map getProperty = getControl(ctrlName);
			Map getProperty1 = getControl(ctrlName1);
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			client.clickIn(getProperty.get("zone").toString(), element, idx2, direction,
					getProperty1.get("zone").toString(), element1, width, height);
		} catch (Exception e) {
			handleError("clickIn - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * getTextIn-Matching command to getText command. Get text in a certain area
	 * relative to an element . The direction can be UP, DOWN, LEFT or RIGHT..
	 * 
	 * @param ctrlName
	 * 
	 * @param ctrlName1
	 * 
	 * @param direction
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 */
	public String getTextIn(final String ctrlName, final String ctrlName1, final String direction, final int width,
			final int height) {
		String gettingValue;

		Map getProperty = getControl(ctrlName);
		Map getProperty1 = getControl(ctrlName1);
		int idx = Integer.parseInt((String) getProperty.get(INDEX));
		String element = getElement(getProperty);
		gettingValue = client.getTextIn(getProperty.get("zone").toString(), element, idx,
				getProperty1.get("zone").toString(), direction, width, height);
		return gettingValue;
	}

	/**
	 * drag-Drag an element or text in a specified zone
	 * 
	 * @param ctrlName
	 * 
	 * @param x1
	 * 
	 * @param y1
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean drag(final String ctrlName, final int x1, final int y1) {
		try {

			Map getProperty = getControl(ctrlName);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.drag(getProperty.get("zone").toString(), element, idx2, x1, y1);
		} catch (Exception e) {
			handleError("drag - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * verifyIn-Search for an element and verify that an element related to it
	 * exists. The direction can be UP, DOWN, LEFT or RIGHT.
	 * 
	 * @param ctrlName
	 * 
	 * @param ctrlName1
	 * 
	 * @param direction
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean verifyIn(final String ctrlName, final String direction, final String ctrlName1, final int width,
			final int height) {
		try {
			Map getProperty = getControl(ctrlName);
			Map getProperty1 = getControl(ctrlName1);
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			client.verifyIn(getProperty.get("zone").toString(), element, idx, direction,
					getProperty1.get("zone").toString(), element1, width, height);
		} catch (Exception e) {
			handleError("verifyIn - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * dragDrop-Drag an element or text in a specified zone and drop it on
	 * another element.
	 * 
	 * @param ctrlName
	 * 
	 * @param ctrlName1
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean dragDrop(final String ctrlName, final String ctrlName1) {
		try {
			Map getProperty = getControl(ctrlName);
			Map getProperty1 = getControl(ctrlName1);

			int idx1 = Integer.parseInt((String) getProperty.get(INDEX));
			int idx2 = Integer.parseInt((String) getProperty1.get(INDEX));
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			client.dragDrop(getProperty.get("zone").toString(), element, idx1, element1, idx2);
		} catch (Exception e) {
			handleError("dragDrop - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * setLocation-The command gives the ability to test geolocation based
	 * applications by allowing the user to set the location of the device as
	 * necessary for testing. It is assumed that the user has pre-configured the
	 * device for location testing.
	 * 
	 * @param latitude
	 * 
	 * @param longitude
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean setLocation(final String latitude, final String longitude) {
		try {
			client.setLocation(latitude, longitude);
		} catch (Exception e) {
			handleError("Location not set", e);
		}
		return true;

	}

	/**
	 * clearLocation-The command gives the ability to reset the device’s
	 * location services after the user executes the SetLocation command.
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean clearLocation() {
		try {
			client.clearLocation();
		} catch (Exception e) {
			handleError("Location not cleared", e);
		}
		return true;
	}

	/**
	 * addDevice-This command gives you the option to automate the add device
	 * process.
	 * 
	 * @param serialNumber
	 * 
	 * @param deviceName
	 * 
	 */
	public String addDevice(final String serialNumber, final String deviceName) {

		return client.addDevice(serialNumber, deviceName);

	}

	/**
	 * waitForElementToVanish-Wait for Element to disappear from the screen
	 * 
	 * @param ctrlName
	 * 
	 * @param timeout
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean waitForElementToVanish(final String ctrlName, final int timeout) {
		try {
			Map getProperty = getControl(ctrlName);

			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.waitForElementToVanish(getProperty.get("zone").toString(), element, idx2, timeout);
		} catch (Exception e) {
			handleError("WaitForElementToVanish - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * verifyElementFound-Verification command to check if an element exists.
	 * 
	 * @param ctrlName
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean verifyElementFound(final String ctrlName) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			if (client.isElementFound(getProperty.get("zone").toString(), element, idx)) {
				logger.handleError("Element is  Displayed");
			}
		} catch (Exception e) {
			handleError("verifyElementFound - Invalid control type: " + ctrlName, e);
		}
		return true;
	}

	/**
	 * elementGetProperty-Command to get a property of an element. Can retrieve
	 * all properties of the elements that are showing on the object spy as also
	 * controllers.
	 * 
	 * @param ctrlName
	 * 
	 * @param property
	 * 
	 */
	public String elementGetProperty(final String ctrlName, final String property) {

		String gettingValue;

		Map getProperty = getControl(ctrlName);
		int idx = Integer.parseInt((String) getProperty.get(INDEX));
		String element = getElement(getProperty);
		gettingValue = client.elementGetProperty(getProperty.get("zone").toString(), element, idx, property);
		return gettingValue;
	}

	/**
	 * elementSetProperty-Designed command to sets value of a controller such as
	 * date pickers, sliders, switches etc..
	 * 
	 * @param ctrlName
	 * 
	 * @param property
	 * 
	 * @param value
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean elementSetProperty(final String ctrlName, final String property, final String value) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.elementSetProperty(getProperty.get("zone").toString(), element, idx2, property, value);
		} catch (Exception e) {
			handleError("elementSetProperty not set", e);
		}
		return true;

	}

	/**
	 * getAllValues-Get all the values of a certain property from all the
	 * existing elements. Possible properties are all the methods available on
	 * the Object spy for the page.
	 * 
	 * @param ctrlName
	 * 
	 * @param property
	 * 
	 */
	public String[] getAllValues(final String ctrlName, final String property) {

		String[] gettingValue;

		Map getProperty = getControl(ctrlName);
		String element = getElement(getProperty);
		gettingValue = client.getAllValues(getProperty.get("zone").toString(), element, property);

		return gettingValue;
	}

	/**
	 * elementListSelect-Select an element within a list (first making the
	 * element visible)
	 * 
	 * @param listCtrlName
	 * 
	 * @param elementCtrlName
	 * 
	 * @param click
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean elementListSelect(final String listCtrlName, final String elementCtrlName, final boolean click) {
		try {
			Map getProperty = getControl(listCtrlName);
			Map getProperty1 = getControl(elementCtrlName);
			int idx1 = Integer.parseInt((String) getProperty1.get(INDEX));
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			client.elementListSelect(element, element1, idx1, click);
		} catch (Exception e) {
			handleError("elementListSelect - Invalid control type: " + listCtrlName, e);
		}
		return true;

	}

	// 14th December

	/**
	 * runNativeAPICall-Gives the ability to create and run scripts that are
	 * based on the Native API methods available for the class of the element.
	 * 
	 * @param ctrlName
	 * 
	 * @param script
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean runNativeAPICall(final String ctrlName, final String script) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.runNativeAPICall(getProperty.get("zone").toString(), element, idx2, script);
		} catch (Exception e) {
			handleError("RunNativeAPICall not DONE", e);
		}
		return true;

	}

	/**
	 * flick-Flick the screen in a given direction.
	 * 
	 * @param direction
	 * 
	 * @param offset
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean flick(final String direction, final int offset) {
		try {
			client.flick(direction, offset);
		} catch (Exception e) {
			handleError("flick not set", e);
		}
		return true;

	}

	/**
	 * reboot-Will reboot the device
	 * 
	 * @param timeout
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean reboot(final int timeout) {
		try {
			client.reboot(timeout);
		} catch (Exception e) {
			handleError("reboot not set", e);
		}
		return true;

	}

	/**
	 * getDeviceLog-The device log will be downloaded to the directory where the
	 * report is located.
	 * 
	 */
	public String getDeviceLog() {

		return client.getDeviceLog();
	}

	/**
	 * setNetworkConditions-Command to select the profile configured on the
	 * Network Virutlaizaiont server to test the device under the different
	 * network conditions.
	 * 
	 * @param profile
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean setNetworkConditions(final String profile) {
		try {
			client.setNetworkConditions(profile);
		} catch (Exception e) {
			handleError("Set Network Conditions not set", e);
		}
		return true;

	}

	/**
	 * getInstalledApplications-Return a list of applications installed on the
	 * device.
	 *
	 */
	public String getInstalledApplications() {

		return client.getInstalledApplications();

	}

	/**
	 * openDevice-The command will open the reflection of the active device
	 * under test during runtime
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean openDevice() {
		try {
			client.openDevice();
		} catch (Exception e) {
			handleError("openDevice not set", e);
		}
		return true;
	}

	/**
	 * releaseDevice-This command gives the user several options on how to
	 * automate several common scenarios when finished testing on a specific
	 * device.
	 *
	 * @param deviceName
	 * 
	 * @param releaseAgent
	 * 
	 * @param removeFromDeviceList
	 * 
	 * @param releaseFromCloud
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean releaseDevice(final String deviceName, final boolean releaseAgent,
			final boolean removeFromDeviceList, final boolean releaseFromCloud) {
		try {
			client.releaseDevice(deviceName, releaseAgent, removeFromDeviceList, releaseFromCloud);
		} catch (Exception e) {
			handleError("releaseDevice not set", e);
		}
		return true;

	}

	/**
	 * sendWhileNotFound-Send given text while an element is not found.
	 *
	 * @param toSend
	 * 
	 * @param ctrlNameToFind
	 * 
	 * @param timeout
	 * 
	 * @param delay
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean sendWhileNotFound(final String toSend, final String ctrlNameToFind, final int timeout,
			final int delay) {
		try {
			Map getProperty = getControl(ctrlNameToFind);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.sendWhileNotFound(toSend, getProperty.get("zone").toString(), element, idx2, timeout, delay);
		} catch (Exception e) {
			handleError("sendWhileNotFound not set", e);
		}
		return true;

	}

	/**
	 * longClick-Long click on or near an element or text (the proximity to the
	 * element/text is specified by an X-Y offset)
	 *
	 * @param ctrlNameToClick
	 * 
	 * @param clickCount
	 * 
	 * @param x1
	 * 
	 * @param y1
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean longClick(final String ctrlNameToClick, final int clickCount, final int x1, final int y1) {
		try {
			Map getProperty = getControl(ctrlNameToClick);
			int idx2 = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.longClick(getProperty.get("zone").toString(), element, idx2, clickCount, x1, y1);
		} catch (Exception e) {
			handleError("longClick not set", e);
		}
		return true;

	}

	/**
	 * isElementBlank-Boolean command to check if a given element contains an
	 * image or if it is blank.
	 *
	 * @param ctrlName
	 * 
	 * @param colorGroup
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean isElementBlank(final String ctrlName, final int colorGroup) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			if (client.isElementBlank(getProperty.get("zone").toString(), element, idx, colorGroup)) {
				logger.handleError("Element was Blank");
			}
		} catch (Exception e) {
			handleError("isElementBlank - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * getElementCount-Count the number of times an element or text is found.
	 *
	 * @param ctrlName
	 * 
	 */
	public int getElementCount(final String ctrlName) {

		int gettingValue;

		Map getProperty = getControl(ctrlName);
		String element = getElement(getProperty);
		gettingValue = client.getElementCount(getProperty.get("zone").toString(), element);

		return gettingValue;
	}

	/**
	 * getElementCountIn-Count the number of elements appearances in a
	 * container.
	 *
	 * @param searchElementCtrlName
	 * 
	 * @param direction
	 * 
	 * @param countElementCtrlName
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 */
	public int getElementCountIn(final String searchElementCtrlName, final String direction,
			final String countElementCtrlName, final int width, final int height) {

		int gettingValue;

		Map getProperty = getControl(searchElementCtrlName);
		Map getProperty1 = getControl(countElementCtrlName);
		String element = getElement(getProperty);
		String element1 = getElement(getProperty1);
		int idx = Integer.parseInt((String) getProperty.get(INDEX));
		gettingValue = client.getElementCountIn(getProperty.get("zone").toString(), element, idx, direction,
				getProperty1.get("zone").toString(), element1, width, height);

		return gettingValue;
	}

	/**
	 * getCoordinateColor-Get the integer representation in the RGB color model
	 * for coordinate (X,Y)
	 *
	 * @param x1
	 * 
	 * @param y1
	 * 
	 */
	public int getCoordinateColor(final int x1, final int y1) {

		return client.getCoordinateColor(x1, y1);

	}

	/**
	 * elementListVisible-Matching command to elementListSelect. Will make
	 * element on a list visible
	 *
	 * @param listCtrlName
	 * 
	 * @param elementCtrlName
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean elementListVisible(final String listCtrlName, final String elementCtrlName) {
		try {
			Map getProperty = getControl(listCtrlName);
			Map getProperty1 = getControl(elementCtrlName);
			int idx1 = Integer.parseInt((String) getProperty1.get(INDEX));
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			client.elementListVisible(element, element1, idx1);
		} catch (Exception e) {
			handleError("elementListVisible - Invalid control type: " + listCtrlName, e);
		}
		return true;

	}

	/**
	 * isFoundIn-Search for an element and check if an element related to it
	 * exists. The direction can be UP, DOWN, LEFT or RIGHT.
	 * 
	 *
	 * @param ctrlName
	 * 
	 * @param direction
	 * 
	 * @param ctrlName1
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean isFoundIn(final String ctrlName, final String direction, final String ctrlName1, final int width,
			final int height) {
		try {
			Map getProperty = getControl(ctrlName);
			Map getProperty1 = getControl(ctrlName1);
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			client.isFoundIn(getProperty.get("zone").toString(), element, idx, direction,
					getProperty1.get("zone").toString(), element1, width, height);
		} catch (Exception e) {
			handleError("isFoundIn - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * getVisualDump-This command will return a String which contains the XML
	 * with all the dump (object spy) properties of the screen.
	 *
	 * @param type
	 * 
	 */

	public String getVisualDump(final String type) {

		return client.getVisualDump(type);

	}

	/**
	 * shake-Shake command uses to simulate the shake operation on the device.
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 * 
	 */

	public boolean shake() {
		try {
			client.shake();
		} catch (Exception e) {
			handleError("shake not set", e);
			return false;
		}
		return true;

	}

	/**
	 * run-Run adb commands or other device shell commands.
	 *
	 * @param command
	 * 
	 */

	public String run(final String command) {

		return client.run(command);
	}

	/**
	 * p2cx-According to the given percentage, this command will return the
	 * active device's width pixel count (X Coordinate) as an Integer. With this
	 * command and the matching P2CY command, you can avoid relying on
	 * coordinates and to give relative size not based on resolution of device.
	 *
	 * @param percentage
	 * 
	 */

	public int p2cx(final int percentage) {

		return client.p2cx(percentage);

	}

	/**
	 * p2cy-According to the given percentage, this command will return the
	 * active device's width pixel count (Y Coordinate) as an Integer. With this
	 * command and the matching P2CY command, you can avoid relying on
	 * coordinates and to give relative size not based on resolution of device.
	 *
	 * @param percentage
	 * 
	 */

	public int p2cy(final int percentage) {

		return client.p2cy(percentage);

	}

	/**
	 * getConnectedDevices-This command will return a string of all devices
	 * currently available in the device list (on ready mode).
	 *
	 *
	 */

	public String getConnectedDevices() {

		return client.getConnectedDevices();

	}

	/**
	 * resetDeviceBridge-Resets the connection of devices connected to
	 * SeeTestAutomation.
	 *
	 * @param deviceType
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean resetDeviceBridge(final String deviceType) {
		try {
			client.resetDeviceBridge(deviceType);
		} catch (Exception e) {
			handleError("resetDeviceBridge not set", e);
		}
		return true;

	}

	/**
	 * getText-Get all text by a specific Zone from entire screen of the device
	 * reflection.
	 *
	 * @param zone
	 *
	 *
	 */

	public String getText(final String zone) {

		return client.getText(zone);

	}

	/**
	 * syncElements-Waits for the screen to become silent.
	 *
	 * @param silentTime
	 *
	 * @param timeout
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean syncElements(final int silentTime, final int timeout) {
		try {
			client.syncElements(silentTime, timeout);
		} catch (Exception e) {
			handleError("syncElements not set", e);
		}
		return true;

	}

	/**
	 * sync-Waits for the screen to become silent.
	 * 
	 *
	 * @param silentTime
	 *
	 * @param sensitivity
	 *
	 * @param timeout
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean sync(final int silentTime, final int sensitivity, final int timeout) {
		try {
			client.sync(silentTime, sensitivity, timeout);
		} catch (Exception e) {
			handleError("sync not set", e);
		}
		return true;

	}

	/**
	 * setProperty-This command can change configuration on the device during
	 * runtime. It will set the given property to the given value.
	 * 
	 *
	 * @param key
	 *
	 * @param value
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setProperty(final String key, final String value) {
		try {
			client.setProperty(key, value);
		} catch (Exception e) {
			handleError("setProperty not set", e);
		}
		return true;

	}

	/**
	 * textFilter-Designed command to improve TEXT (OCR) recognition abilities
	 * either dynamically or through the repository.
	 * 
	 *
	 * @param color
	 *
	 * @param sensitivity
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean textFilter(final String color, final int sensitivity) {
		try {
			client.textFilter(color, sensitivity);
		} catch (Exception e) {
			handleError("textFilter not set", e);
		}
		return true;

	}

	/**
	 * setLanguage-Set the OCR language
	 *
	 * @param language
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setLanguage(final String language) {
		try {
			client.setLanguage(language);
		} catch (Exception e) {
			handleError("setLanguage not set", e);
		}
		return true;

	}

	/**
	 * setLanguagePropertiesFile-Sets the Language properties file to be used
	 * during the script.
	 *
	 * @param filepath
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setLanguagePropertiesFile(final String filepath) {
		try {
			client.setLanguagePropertiesFile(filepath);
		} catch (Exception e) {
			handleError("setLanguagePropertiesFile not set", e);
		}
		return true;

	}

	/**
	 * setDefaultTimeOut-Set the default timeout for click commands
	 *
	 * @param newTimeout
	 *
	 */

	public String setDefaultTimeOut(final int newTimeout) {

		return client.setDefaultTimeout(newTimeout);

	}

	/**
	 * setDefaultClickDownTime-Set the default click down time (default is 100
	 * miliseconds)
	 *
	 * @param downTime
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setDefaultClickDownTime(final int downTime) {
		try {
			client.setDefaultClickDownTime(downTime);
		} catch (Exception e) {
			handleError("setDefaultClickDownTime not set", e);
		}
		return true;

	}

	/**
	 * setDragStartDelay-Set the drag start delay
	 *
	 * @param delay
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setDragStartDelay(final int delay) {
		try {
			client.setDragStartDelay(delay);
		} catch (Exception e) {
			handleError("setDragStartDelay not set", e);
		}
		return true;

	}

	/**
	 * setDefaultWebView-Set the WebView with which to work.
	 *
	 * @param webViewLocator
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setDefaultWebView(final String webViewLocator) {
		try {
			client.setDefaultWebView(webViewLocator);
		} catch (Exception e) {
			handleError("setDefaultWebView not set", e);
		}
		return true;

	}

	/**
	 * setWebAutoScroll-Command to determine behavior of scrolling to invisible
	 * elements on a webView.
	 *
	 * @param autoScroll
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean setWebAutoScroll(final boolean autoScroll) {
		try {
			client.setWebAutoScroll(autoScroll); // set to
		} catch (Exception e) {
			handleError("setWebAutoScroll not set", e);
		}
		return true;

	}

	/**
	 * pressWhileNotFound- Press on a certain element (ElementToClick) while
	 * another element (ElementToFind) is not found
	 *
	 * @param ctrlName
	 *
	 * @param ctrlName1
	 *
	 * @param timeout
	 *
	 * @param delay
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean pressWhileNotFound(final String ctrlName, final String ctrlName1, final int timeout,
			final int delay) {
		try {
			Map getProperty = getControl(ctrlName);
			Map getProperty1 = getControl(ctrlName1);

			int idx1 = Integer.parseInt((String) getProperty.get(INDEX));
			int idx2 = Integer.parseInt((String) getProperty1.get(INDEX));
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			client.pressWhileNotFound(getProperty.get("zone").toString(), element, idx1, element1, idx2, timeout,
					delay);
		} catch (Exception e) {
			handleError("pressWhileNotFound - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * listSelect- Used to navigate lists in non-touch devices
	 *
	 * @param sendRest
	 *
	 * @param sendNavigation
	 *
	 * @param delay
	 *
	 * @param textToIdentify
	 *
	 * @param color
	 *
	 * @param rounds
	 *
	 * @param sendonfind
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean listSelect(final String sendRest, final String sendNavigation, final int delay,
			final String textToIdentify, final String color, final int rounds, final String sendonfind) {
		try {
			client.listSelect(sendRest, sendNavigation, delay, textToIdentify, color, rounds, sendonfind);
		} catch (Exception e) {
			handleError("elementListSelect - Invalid control type: ", e);
		}
		return true;

	}

	/**
	 * swipeWhileNotFound- Swipe the screen to search for an element.
	 *
	 * @param direction
	 *
	 * @param offset
	 *
	 * @param swipeTime
	 *
	 * @param ctrlName
	 *
	 * @param delay
	 *
	 * @param rounds
	 *
	 * @param click
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean swipeWhileNotFound(final String direction, final int offset, final int swipeTime,
			final String ctrlName, final int delay, final int rounds, final boolean click) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.swipeWhileNotFound(direction, offset, swipeTime, getProperty.get("zone").toString(), element, idx,
					delay, rounds, click);
		} catch (Exception e) {
			handleError("swipeWhileNotFound - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * elementListPick-Select an element from the Object Repository in a list
	 * Equivalent command to elementListSelect command.
	 *
	 * @param listCtrlName
	 *
	 * @param elementCtrlName
	 *
	 * @param click
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean elementListPick(final String listCtrlName, final String elementCtrlName, final boolean click) {
		try {
			Map getProperty = getControl(listCtrlName);
			Map getProperty1 = getControl(elementCtrlName);
			int idx1 = Integer.parseInt((String) getProperty1.get(INDEX));
			String element = getElement(getProperty);
			String element1 = getElement(getProperty1);
			client.elementListPick(getProperty.get("zone").toString(), element, getProperty1.get("zone").toString(),
					element1, idx1, click);
		} catch (Exception e) {
			handleError("elementListPick - Invalid control type: " + listCtrlName, e);
		}
		return true;

	}

	/**
	 * elementGetTableRowsCount-This command allows you to extract the numbers
	 * of one or more rows of a given table, whether the table is entirely
	 * visible on the screen or not.
	 *
	 * @param CtrlName
	 *
	 * @param visible
	 *
	 */

	public int elementGetTableRowsCount(final String ctrlName, final boolean visible) {

		int gettingValue;

		Map getProperty = getControl(ctrlName);

		int idx = Integer.parseInt((String) getProperty.get(INDEX));
		String element = getElement(getProperty);
		gettingValue = client.elementGetTableRowsCount(getProperty.get("zone").toString(), element, idx, visible);

		return gettingValue;
	}

	/**
	 * elementScrollToTableRow-This command allows you to scroll a row in a
	 * table or list to the top of screen.
	 *
	 * @param CtrlName
	 *
	 * @param rowIndex
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */
	public boolean elementScrollToTableRow(final String ctrlName, final int rowIndex) {
		try {
			Map getProperty = getControl(ctrlName);
			int idx = Integer.parseInt((String) getProperty.get(INDEX));
			String element = getElement(getProperty);
			client.elementScrollToTableRow(getProperty.get("zone").toString(), element, idx, rowIndex);
		} catch (Exception e) {
			handleError("elementScrollToTableRow - Invalid control type: " + ctrlName, e);
		}
		return true;

	}

	/**
	 * extractLanguageFiles-Extract the language files from an application to a
	 * specified path during runtime.
	 *
	 * @param application
	 *
	 * @param directoryPath
	 *
	 * @param allowOverwrite
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean extractLanguageFiles(final String application, final String directoryPath,
			final boolean allowOverwrite) {
		try {
			client.extractLanguageFiles(application, directoryPath, allowOverwrite);
		} catch (Exception e) {
			handleError("extractLanguageFiles - Invalid control type", e);
		}
		return true;

	}

	/**
	 * simulateCapture-The command allow to test applications which uses the
	 * camera feature of a mobile device.
	 *
	 * @param picturePath
	 *
	 * @return if found returns TRUE if not found returns FALSE
	 */

	public boolean simulateCapture(final String picturePath) {
		try {
			client.simulateCapture(picturePath);
		} catch (Exception e) {
			handleError("simulateCapture - Invalid control type", e);
		}
		return true;

	}

	/**
	 * getLastCommandResultMap-This command can be used to retrieve information
	 * (of all parameters listed below) on the last command which was executed.
	 *
	 * if found returns TRUE if not found returns FALSE
	 */

	public boolean getLastCommandResultMap() {
		try {
			client.getLastCommandResultMap();
		} catch (Exception e) {
			handleError("getLastCommandResultMap not set", e);
		}
		return true;

	}

	/**
	 * Takes screenshot
	 */
	public File takescreenshot() {
		File source = null;
		try {
			String screenDumpPath = client.capture();
			source = new File(screenDumpPath);
			wait("MW");

		} catch (Exception e) {
			handleError("Failed to take screenshots", e);
		}
		return source;
	}
	
	private static final String NATIVE="NATIVE";
	private static final String INDEX = "index";
	private String time = "2";
	private Client client = null;
	private String host = "localhost";
	private int port = 8889;
	private ObjectMap objMap;
	private List<Map> childMap;
	private LogUtils logger = new LogUtils(this);

}
