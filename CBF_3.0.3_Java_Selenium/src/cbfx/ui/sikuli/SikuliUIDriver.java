/******************************************************************************
$Id : SikuliUIDriver.java 12/23/2016 4:09:04 PM
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

package cbfx.ui.sikuli;

import static cbf.engine.TestResultLogger.handleError;
import java.util.Map;
import org.sikuli.script.Screen;
import cbf.harness.Harness;
import cbf.utils.StringUtils;
import cbfx.ui.objectmap.ObjectMap;

/**
 * Supporting UI operations like get locator,click,double click,right click etc.
 *
 */
public class SikuliUIDriver {

	/**
	 * 
	 * Initializing the ObjectMap .
	 *
	 */
	public SikuliUIDriver(Map params) {
		objMap = (ObjectMap) Harness.pluginManager().getPlugin(params);
	}

	/**
	 * 
	 * Gets the actual locator
	 * @param element name
	 * @return locator
	 */
	public String getLocator(String elementName) {
		return (String) objMap.properties(elementName).get(
				"locator");
	}

	/**
	 * 
	 * Clicks on the specified element
	 * @param element name
	 */
	public void click(String elementName) {

		try {

			String locator = getLocator(elementName);

			sikuli.click(locator);

		} catch (Exception e) {
			handleError("Error caused while clicking on image ", e);
		}
	}

	/**
	 * Double clicks on the specified element
	 * @param element name
	 */
	public void doubleClick(String elementName) {
		try {

			String locator = getLocator(elementName);

			sikuli.doubleClick(locator);

		} catch (Exception e) {
			handleError("Error caused while double clicking on image ", e);
		}
	}

	/**
	 * 
	 * Right clicks on the specified element
	 * @param element name
	 */
	public void rightClick(String elementName) {
		try {

			String locator = getLocator(elementName);

			sikuli.rightClick(locator);

		} catch (Exception e) {
			handleError("Error caused while right clicking on image ", e);
		}
	}

	/**
	 * 
	 * Sets value to the element
	 * @param elementName
	 * @param  value
	 *
	 */
	public void type(String elementName, String value) {
		try {

			String locator = getLocator(elementName);

			sikuli.type(locator, value);

		} catch (Exception e) {
			handleError("Error caused By typing on image ", e);
		}
	}

	/**
	 * 
	 * 
	 *
	 */
	@Override
	public String toString() {
		return StringUtils.mapString(this, objMap);

	}

	private ObjectMap objMap;
	private Screen sikuli;
}
