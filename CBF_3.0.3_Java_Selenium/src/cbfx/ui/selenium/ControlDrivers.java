/******************************************************************************
$Id : ControlDrivers.java 10/02/2017 4:09:02 PM
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

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cbf.utils.LogUtils;

public class ControlDrivers {
private ControlDrivers() { } // just a namespace

static class Basic implements ControlDriver {
  public Basic(WebElement e) {
    this.element = e;
  }

  public String getValue() {
	  if ("".equals(element.getText()))
			return element.getAttribute("value");
		return element.getText();
  }

  public void setValue(String value) {
	  element.clear();
		element.sendKeys(value);
  }

  public void clear() {
	  element.clear();
  }

  public String[] getOptions() {
	  List<String> optionsStr = new ArrayList<>();
		try {
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (WebElement option : options) {
				optionsStr.add(option.getText());
			}
		} catch (Exception e) {
			logger.handleError("Failed to get dropdown list for: ", element, e);
		}
		return (String[]) optionsStr.toArray();
  }
  
  public void click() {
	  element.click();		  
  }
  
  protected final WebElement element;
  protected final LogUtils logger = new LogUtils(this);
}

static public class Text extends Basic {
	public Text(WebElement e) {
		super(e);
	}
	@Override
	public String getValue() {
		String v = element.getText();
		if (v == null || "".equals(v))
			return element.getAttribute("value");
		return v;
	}
	@Override
	public void setValue(String value) {
		element.clear();
		element.sendKeys(value);
	}
	@Override
	public void clear() {
    element.clear();
	}
  
}

public static class Selection extends Basic{

	public Selection(WebElement e) {
		super(e);
	}
	@Override
	public void setValue(String value){
		Select selectOption = new Select(element);
		selectOption.selectByVisibleText(value);
	}
	@Override
	public String getValue(){
		Select selectOption = new Select(element);
		return selectOption.getFirstSelectedOption().getText();
	}
	@Override
	public String[] getOptions(){
		List<String> optionsStr = new ArrayList<>();
		try {
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (WebElement option : options) {
				optionsStr.add(option.getText());
			}
		} catch (Exception e) {
			logger.handleError("Failed to get dropdown list for: ", element, e);
		}
		return (String[]) optionsStr.toArray();
	}
	
}

public static class Checkbox extends Basic{
	public Checkbox(WebElement e) {
		super(e);
	}
	@Override
	public String getValue(){
		if (element.isSelected()) {
			return "Yes";
		} else {
			return "No";
		}
	}
}

public static class Radio extends Basic{

	public Radio(WebElement e) {
		super(e);
	}
}

public static class Calendar extends Basic{

	public Calendar(WebElement e) {
		super(e);
	}
	@Override
	public void setValue(String value) {
		element.click();
		element.findElement(By.linkText(value.substring(0, value.indexOf('/')))).click();
	}
	
}

}