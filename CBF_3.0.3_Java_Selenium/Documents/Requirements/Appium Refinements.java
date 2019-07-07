/*
Requirements
------------
1. Simplify handling of some of the browsers.
Make them more flexible. Remove hard-cordings of capabilities.

2. Appium driver is a 3-in-one.
Internally, it should be split into 3. Then, a single Appium facade?
* IOSNative
* AndroidNative
* RemoteWeb

Specifications
--------------
1. Read-in capabilities from the Resources rather than hard-code.
*/

package cbfx.ui.browser;

/**
 * Utility for building capabilities from a map
**/
class Capabilities {
  /**
   * Build capabilities corresponding to settings
   **/
  public static buildCapabilities(Map settings) {
		DesiredCapabilities caps = new DesiredCapabilities();

    if (settings != null) {
      for (Map.Entry entry: settings) { // each key in settings
        try {
			    caps.setCapability(entry.getKey(), entry.getValue());
        } catch(Exception e) {
          logger.warning("Capability ignored", entry, e);
        }
		  }
    }

    return caps;
  }
}

/*
2. Define capabilities from Resources files. So, as to insulate from code.
Below sample appium driver configurations in MasterConfig.xml
*/
	<variable>
		<name>APPIUM</name>
		<value>
			<classname>cbfx.ui.browser.Appium</classname>
			<parameters>
        <platform>IOS|Android</platform> <-- this comes from Config.xml or dynamic?
        <URL>http://127.0.0.1:4723/wd/hub</URL><-- will be overridden; escape /
        <IOS>
  <classname>io.appium.java_client.ios.IOSDriver</classname>
  <typeparameter>io.appium.java_client.ios.IOSElement</typeparameter>
  <timeout>10</timeout>
    <capabilities>
      <key1>value1</key1>
      <key2>value2</key2>
    </capabilities>
        </IOS>
        <Android>
  <classname>io.appium.java_client.android.AndroidDriver</classname>
  <typeparameter>org.openqa.selenium.WebElement</typeparameter>
  <timeout>10</timeout> <-- optional
    <capabilities>
      <key1>value1</key1>
      <key2>value2</key2>
    </capabilities>
        </Android>
			</parameters>
		</value>
	</variable>

/*
3. Rewrite Appium Browser to use the MasterConfig.
*/

import static cbf.engine.TestResultLogger.handleError;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class Appium extends AbstractBrowser{
  private Map platform;

	public Appium(Map parameters) {
		super(parameters);
		
		String platformName = params.get("platform");
    if ("IOS".equalsIgnoreCase(platform)) {
      platform = (Map)parameters.get("IOS");
    else if ("Android".equalsIgnoreCase(platform)) {
      platform = (Map)parameters.get("Android");
    } else {
      handleError("Invalid platform", platformName, parameters);
    }
	}

	
	
	/**
	 * Loads appium driver
	 * 
	 * @return appium driver instance
	 */
	public WebDriver openDriver() {
		try {
			DesiredCapabilities capabilities = Capabilities.buildCapabilities((Map)platform.get("capabilities"));

			driver = getDriver(params, capabilities);
		} catch (Exception e) {
			handleError("Failed to Setup device", e);
		}

    int timeout = -1;
    try {
      timeout = new Integer((String)params.get("timeout")).intValue();
    } catch(Exception e) {
			logger.warning("Driver timeout setting", e);
    }
    if (timeout > 0) {
			logger.trace("Driver wait with timeout start", timeout);
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			logger.trace("Driver wait ended");
    }
		
		return driver;
	}

	private RemoteWebDriver getDriver(DesiredCapabilities capabilities) {
    URL url = new URL((String)params.get("URL"));
		try {
				switch (params.get("appType").toString()) {
				case "Native":
            String cnm = params.get("classname");
            String typeparam = params.get("typeparameter");
            if (typeparam != null)
              cnm = cnm + "<" + typeparam + ">";

            return makeNativeDriver(cnm, url, capabilities);

					if (((String) params.get("platformName")).equalsIgnoreCase("android")) {
						return new AndroidDriver<WebElement>(url, capabilities);
					} else {
						return new IOSDriver<IOSElement>(url, capabilities);
					}
				case "Web":
					return new RemoteWebDriver(url, capabilities);
						
				default:
					logger.handleError("Invalid UI Driver plugin :appType");
					break;
				}
			} catch (Exception e) {
				handleError("Failed in RemoteWebdriver Instantantion",e);
			}
	}

  private RemoteWebDriver makeNativeDriver(String cnm, Url url, Capabilities cap) {
    // Use reflection with cnm to instantiate cnm and return 
  }
}
