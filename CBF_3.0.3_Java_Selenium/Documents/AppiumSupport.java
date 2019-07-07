/*
Requirements
------------
1. Currently, cbfx.appium classes combines both WebUIDriver and Browser
functionalities. This has to be separated as:
* An browser.Appium class which implements browser.Browser
* Rest of the function to be merged with WebUIDriver 

Specifications
--------------
TODO
*/

A. cbfx.ui.appium.AppiumWebDriver
--------------------------------------------------
1.currently the Overloaded Constructor to initialize appiumWebdriver and appium is constructed with the parameter Map param.
  as per overloaded constructor of new webUiDriver constructor shoud accept object map and browser instace. so this change should be done.

B.cbfx.ui.browser.appium
----------------------------------------------------
1. This new class which implements AbstractBrowser shold be added. This class should take all the capabilities given in config file and should 
initialize the remotedriver with respect to perticular device.

C. MasterConfig.xml
----------------------------------------------------------------
1. new plugin of appium browser should be added in masterconfig.

D.cbfx.ui.web.BaseWebAppDriver and BaseWebModuleDriver
--------------------------------------------------------------
1.need some discussion on this