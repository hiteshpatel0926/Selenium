/******************************************************************************
$Id : BaseWebAppDriver.java 12/23/2016 4:09:05 PM
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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import cbf.model.AppDriver;
import cbf.model.ScreenDumper;
import cbf.utils.LogUtils;
import cbf.utils.StringUtils;
import cbfx.api.RestAPIDriver;
import cbfx.ui.browser.Browser;
import cbfx.ui.mainframe.MainFrameUIDriver;
import cbfx.ui.objectmap.ObjectMapFactory;
import cbfx.ui.selenium.WebUIDriver;

public abstract class BaseWebAppDriver  implements AppDriver, ScreenDumper{
	
	public BaseWebAppDriver(ObjectMapFactory objMapFactory, Browser browser) {
      webUIDriver = new WebUIDriver(objMapFactory, browser);
      restAPIDriver = new RestAPIDriver();
      mainframeUIDriver = new MainFrameUIDriver(objMapFactory);
	}

	public void open(){
		webUIDriver.open();
	}
	
	public void close() {
		webUIDriver.close();
	}
	
	public void recover() {
	  close();
	  open();
	}

	  public void dumpScreen(String filePath) {
		logger.trace("DumpScreen : " + filePath);
		
		File screenShot = webUIDriver.takescreenshot();
		try {
			FileUtils.copyFile(screenShot, new File(filePath));
		} catch (IOException e) {
		
			logger.handleError("Exception caught while creating screen dump",
					filePath, e);
		}
	  }
	@Override
  public String toString() {
    return StringUtils.mapString(this, webUIDriver);
  }

    protected final WebUIDriver webUIDriver;
    protected static RestAPIDriver restAPIDriver;
    protected static MainFrameUIDriver mainframeUIDriver;
    protected static LogUtils logger = new LogUtils(BaseWebAppDriver.class);
}

