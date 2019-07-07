/******************************************************************************
$Id : ControlDriverFactory.java 12/23/2016 4:09:02 PM
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

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cbf.utils.LogUtils;
import cbf.utils.StringUtils;

public class ControlDriverFactory {
	/**
	 * Constructor TODO: params for caching etc.
	 * 
	 * @param objectFinder
	 */
	public ControlDriverFactory(ObjectFinder objectFinder) {
		this.objectFinder = objectFinder;
	}

	/**
	 * get ControlDriver by element type and webdriver
	 * 
	 * @param elementName
	 *            name of the element to get Control
	 * @param webDr
	 *            WebDriver instance
	 * @return ControlDriver instance
	 */
	public ControlDriver makeDriver(String elementName, WebDriver webDr) {
		class Temp {
			ControlDriver d = null;
		}
		final Temp t = new Temp();

		if (objectFinder.find(elementName, webDr,
				(WebElement e, By by, Map properties) -> t.d = makeControlDriver(e, by, properties, webDr)) == null) {
			logger.trace("couldnt find element", elementName);
			return null;
		}

		logger.trace("found element", elementName, t.d);
		return t.d;
	}

	/**
	 * get Control Driver by Using locator and web driver
	 * 
	 * @param element
	 *            name of the element
	 * @param by
	 *            locator type(ex. id,class,xpath,etc.)
	 * @param properties
	 *            locator properties in uiMap
	 * @param webDr
	 *            web driver instance
	 * @return ControlDriver instance
	 */
	private ControlDriver makeControlDriver(WebElement element, By by, Map properties, WebDriver webDr) {
		// TODO: custom control if properties have custom-control-type

		String type = element.getAttribute("type");

		if ("text".equals(type) || "password".equals(type)) {
			return new ControlDrivers.Text(element);
		}
		if ("select-one".equals(type)) {
			return new ControlDrivers.Selection(element);
		}
		if ("checkbox".equals(type)) {
			return new ControlDrivers.Checkbox(element);
		}
		if ("radio".equals(type)) {

		}
		if ("calendar".equalsIgnoreCase(type)) {
			// return new ControlDriverFactory.Calendar(element, by,
			// webUIDriver); // multiple elements
		}

		if (isRepeater(by)) {
			return new NgControlDrivers.Repeater(webDr, by); // multiple
																// elements
		}

		return new ControlDrivers.Basic(element);
	}

	/**
	 * checks the element is repeater type or not
	 * 
	 * @param by
	 *            locator type(ex. id,class,xpath,etc.)
	 * @return true, if successful
	 */
	private static boolean isRepeater(By by) {
		return by.toString().contains("repeater");
	}

	/**
	 * standard tostring() method
	 */
	@Override
	public String toString() {
		return "";
		
	}

	private final ObjectFinder objectFinder;
	private final LogUtils logger = new LogUtils(this);
}
