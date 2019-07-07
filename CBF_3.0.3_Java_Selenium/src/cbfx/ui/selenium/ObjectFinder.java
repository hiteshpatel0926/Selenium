/******************************************************************************
$Id : ObjectFinder.java 12/23/2016 4:09:02 PM
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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.ByAngular;

import cbf.engine.TestResultLogger;
import cbf.utils.FrameworkException;
import cbf.utils.LogUtils;
import cbf.utils.StringUtils;
import cbfx.ui.objectmap.ObjectMap;
import cbfx.ui.objectmap.ObjectMapFactory;

public class ObjectFinder {

	/**
	 * Constructor TODO: params for caching etc.
	 * 
	 * @param objMap
	 *            the obj map
	 */
	public ObjectFinder(ObjectMapFactory objMapFactory) {
		this.objectMapFactory = objMapFactory;
	}

	/**
	 * What to do on find
	 */
	public interface OnFind {
		public void apply(WebElement element, By by, Map properties);
	}

	/**
	 * Identifies an object by the properties defined in the object map.
	 * 
	 * @Note: for Angular with repeaters, returns the first item matching the
	 *        locator
	 *
	 * @param elementName
	 *            Name of the element whose locator is required
	 * @return Identified locator as Web Element
	 */
	/**
	 * Identifies an object by the properties defined in the object map.
	 * 
	 * @Note: for Angular with repeaters, returns the first item matching the
	 *        locator
	 *
	 * @param elementName
	 *            Name of the element whose locator is required
	 * @return Identified locator as Web Element
	 */
	public WebElement find(String elementName, WebDriver webDr, OnFind onFind) {
		Map map = getProperties(elementName);
		if (map != null){
		final By[] byCollection = determineBy(getLocatorName(map),
				"Angular".equalsIgnoreCase((String) map.get("controlType")));
		logger.debug("By", elementName, byCollection);

		for (By by : byCollection) {
			try {
				WebElement element = webDr.findElement(by);
				if (!element.equals(null)) {
					logger.trace("found element", elementName, element, by);
					if (onFind != null)
						onFind.apply(element, by, map);
					return element;
				}
			} catch (NoSuchElementException e) {
				logger.warning("no such element", elementName, by, e);
			}
			logger.debug("tried in vain", elementName, by);
		}
		}
		logger.trace("didnt find element", elementName);
		return null;
	}

	public String getLocatorName(Map<String, String> locatorMap) {

		for (Entry<String, String> entry : locatorMap.entrySet()) {
			if (!entry.getValue().isEmpty())
				switch (entry.getKey()) {
				case "locator":
				case "id":
				case "name":
				case "relativeXpath":
				case "className":
				case "tagName":
				case "cssSelector":
				case "linkText":
				case "css":
				case "absoluteXpath":	
				case "partialLinkText":
				case "controlType":
				case "relativeXpathClassic":
				case "relativeXpathLightening":
				return entry.getValue();
				}
		}
		return null;
	} 
	

	/**
	 * get ObjectMap from ObjectMapFactory
	 * 
	 * @return ObjectMap
	 */
	public ObjectMap objectMap() {
		String moduleCode = TestResultLogger.getCurrentModuleCode();
		return objectMapFactory.getObjectMap(moduleCode);
	}

	/**
	 * get the locators in the specified page
	 * 
	 * @param pageName
	 *            name of the page to get locators
	 */
	public List<Map> getPageMap(String pageName) {
		return objectMap().ObjectMaps(pageName);
	}

	/**
	 * standard tostring() method
	 */
	public String toString() {
		return StringUtils.mapString(this, objMap);
	}

	/**
	 * get properties.
	 *
	 * @param elementName
	 *            the element name
	 * @return the properties
	 */
	private Map getProperties(String elementName) {
		String locator=getElement(elementName);
		Map m = objectMap().properties(locator);
		try{
		if (m == null)
			logger.handleError("element " + elementName + " is not mapped");

		return m;
		}catch(Exception e){
			
			logger.handleError("element " + elementName + " is not present",e);	
		}
		return null;
		}
	

	
	public String getElement(String element) {

	
		String locator="";
		Map<String, String> locatorMap = null;
		if(element.contains(".")){
			component=element.split("\\.")[0];
			locator =element.split("\\.")[1];
		}else{
			locator=element;
		}
		return locator; 
	}

	private By[] determineBy(String locator, boolean tryAngular) {
		By[] basic = { By.id(locator), By.name(locator), By.xpath(locator), By.className(locator),
				By.cssSelector(locator), By.tagName(locator), By.linkText(locator), By.partialLinkText(locator) };

		if (!(tryAngular && (byAngular != null))) // Angular is not being used
			return basic;

		By[] angular = makeByAngular(locator);

		return (By[]) ArrayUtils.addAll(basic, angular);
	}

	private By[] makeByAngular(String actualLocator) {
		// FIXME: do the reflection magic to call the static method of byAngular member
		// below
		By[] angular = { ByAngular.binding(actualLocator), ByAngular.model(actualLocator),
				ByAngular.repeater(actualLocator), ByAngular.exactBinding(actualLocator),
				ByAngular.exactRepeater(actualLocator), };
		return angular;
	}

	/**
	 * check and load the angular selector
	 * 
	 * @return
	 */
	private static Class getAngular() {
		try {
			return Class.forName("com.paulhammant.ngwebdriver.ByAngular");
		} catch (ClassNotFoundException ce) {
			logger.debug("Error in getting Angular class", ce);
			return null;
		} catch (Exception e) {
			throw new FrameworkException("Configuration error in checking angular:", e);
		}
	}

	private static final Class byAngular = getAngular();

	private static ObjectMap objMap;
	private ObjectMapFactory objectMapFactory;
	private static LogUtils logger = new LogUtils();
	private String  component;
}
