/******************************************************************************
$Id : BaseWebModuleDriver.java 12/23/2016 4:08:49 PM
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


import cbf.model.BaseModuleDriver;
import cbfx.api.RestAPIDriver;
import cbfx.ui.mainframe.EmulatorStart;
import cbfx.ui.mainframe.MainFrameUIDriver;
import cbfx.utils.DataBaseOperations;


/**
 * 
 * Manages tools specific instance variables of wrapper
 *
 */
public class BaseWebModuleDriver extends BaseModuleDriver {
	protected KeywordDriver uiDriver;
	protected RestAPIDriver raDriver;
	protected DataBaseOperations dbo;
	protected MainFrameUIDriver mfuiDriver; 
	protected EmulatorStart mfLogin;

	


	public BaseWebModuleDriver(KeywordDriver kwDriver) {
		uiDriver = kwDriver;
		dbo = new DataBaseOperations();
		mfuiDriver = BaseWebAppDriver.mainframeUIDriver;
		mfLogin = new EmulatorStart();
	}

}
