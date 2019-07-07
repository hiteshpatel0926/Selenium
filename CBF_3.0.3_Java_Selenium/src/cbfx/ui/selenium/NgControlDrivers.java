/******************************************************************************
$Id : NgwebUIDriver.java 12/23/2016 4:09:05 PM
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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NgControlDrivers {
private NgControlDrivers() { } // just a namespace

public static class Repeater implements ControlDriver {
  public Repeater(WebDriver webDr, By by) {
    this.webDr = webDr;
    this.by = by;
  }

  public String getValue() {
    return null;  // no way to determine current?
  }

  public void setValue(String s) {
    WebElement el = select(s);
    if (el == null) {
      // handleError()
      return;
    }

    el.click();
  }

  public WebElement select(String s) {
    for (WebElement el: webDr.findElements(by)) {
				if (el.getText().contains(s)) {
					return el;
				}
				for (WebElement cEl:el.findElements(By.tagName("*"))) {
					if (cEl.getText().equalsIgnoreCase(s)) {
						return cEl;
					}
				}
    }
		return null;
  }

  public void clear() {
	  webDr.findElement(by).clear();
  }
  
  public String[] getOptions(){
	return null;
	}
  
  public void click() {
		webDr.findElement(by).click();
		
  }
  private final By by;
  private final WebDriver webDr;

  
}
}