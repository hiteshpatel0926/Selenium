package cbfx.selenium;
/*
BaseWebAppDriver and BaseWebModuleDriver has essential responsibilities
* WebDriver management
*/
import org.openqa.selenium.WebDriver;
abstract class BaseWebAppDriver extends BaseAppDriver {
  public BaseWebAppDriver(Map browserArgs) {
    try {
      BaseWebAppDriver((Browser)PluginManager.getPlugin(browserArgs))
    } catch(Exception e) {
      logger.handleError("Loading browser", browserArgs, e);
    }
  }

  public BaseWebAppDriver(Browser browser) {
    this.browser = browser;

    webDriver = browser.openDriver();
  }

  public recover() {
    // closeDriver(); CHECKME
  }

  public finalize() {
    closeDriver();
  }

  private closeDriver() {
    if (webDriver) {
      browser.closeDriver(webDriver);
      webDriver = null;
    }
  }

  public String toString() {
    return StringUtils.mapToString(this, browser);
  }

  private Map browserArgs;
  protected Browser browser;
  protected WebDriver webdriver;
  protected Logger logger = new Logger(this);
}

import org.openqa.selenium.WebDriver;
abstract class BaseWebModuleDriver extends BaseModuleDriver {
  public BaseWebModuleDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }
  protected WebDriver webdriver;
}

/*
New class to act as base class for all simple web applications.
Handles the following: <-- can be overridden
* wrapper layers
* UIDriver handling, Module driver loading as per configuration parameters: new functionality
* Plugin config will be:
<plugin>
<classname>cbfx.selenium.WebAppDriver</classname>
<moduledrivers>
  <moduledriver>
    <modulecode>Main</modulecode>
    <classname>banking.MainDriver</classname>
  </moduledriver>
  <moduledriver>
    <modulecode>Account</modulecode>
    <classname>banking.AccountDriver</classname>
  </moduledriver>
</moduledrivers>
<--- alternately
<moduledrivers>
  <Main>banking.MainDriver</Main>
  <Account>banking.AccountDriver</Account>
</moduledrivers>
</plugin>
*/

import cbfx.selenium.BaseWebAppDriver;

class WebAppDriver extends BaseWebAppDriver {
  public WebAppDriver(Map params) {
    super(getBrowserConfig(params));

    this.params = params;

    webUIDriver = setupWrapper(webDriver);

    loadModuleDrivers(webUIDriver);
  }

  protected WebUIDriver setupWrapper(WebDriver webDriver) {
    logger.handleError("TODO: initialize WebUIDriver");
    return null;
  }

  protected void loadModuleDrivers() {
    List moduleDrivers;
    try {
      moduleDrivers = (List)params.get("moduledrivers"); // CHECKME: list in Config.xml
      if (moduleDrivers == null)  // will probably be overridden manually
        return;
    } catch(Exception e) {
      logger.handleError("Invalid Module Drivers Configuration", e);
    }

    for (Object moduleDriver: moduleDrivers) {
      try {
        Map info = (Map)moduleDriver;
        loadModuleDriver(
          (String)info.get("modulecode"),
          (String)info.get("classname"));
      } catch(ClassCastException ce) {
        logger.handleError("Invalid Module Driver Configuration", moduleDriver, ce);
      } catch(Exception e) {
        logger.handleError("Loading Module Driver", moduleDriver, ce);
      }
    }
  }

  /*
  * As a default, argument is a classname, and takes a wrapper WebUIDriver
  * instance as constructor
  */
  protected void loadModuleDriver(String modulecode, String classname) {
		try {
			ModuleDriver moduleDriver = (ModuleDriver)Class.forName(classname).getDeclaredConstructor(webUIDriver.class)
					.newInstance(webUIDriver);
		} catch (ClassNotFoundException e) {
			logger.handleError("Class not found", classname, e);
		} catch (NoSuchMethodException e) {
			logger.handleError("No valid constructor found for ", classname, "It should take a constructor with a WebUIDriver object.");
		} catch (Exception e) {
			logger.handleError("Class instantiation error", classname, e);
		}
  }

  private Map getBrowserConfig(Map params) {
    Object args = params.get("args");
    if (args == null) { // get default from CONFIG
      args = Harness.config().get("Browser");
      logger.trace("Browser from global config", args, params)
    }
    if (args == null)
      logger.handleError("Browser not configured");
    
    try {
      return (Map)args;
    } catch(ClassCastException ce) {
      logger.handleError("Browser configuration error", args, ce);
    }
  }

  private String toString() {
    return StringUtils.mapToString(this, params);
  }

  protected WebUIDriver webUIDriver;
  protected Browser browser;
  private params;
}

package cbfx.selenium;
import org.openqa.selenium.WebDriver;
abstract class WebModuleDriver extends BaseWebModuleDriver {
  public WebModuleDriver(WebUIDriver webUIDriver) {
    super(webUIDriver.webDriver);  // CHECKME
    this.webUIDriver = webUIDriver;
  }
}
