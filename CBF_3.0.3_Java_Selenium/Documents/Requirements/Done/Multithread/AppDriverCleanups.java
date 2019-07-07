/*
Requirements
------------
CBF package is generic. So, there must be **no** inclusion of OPENQA modules in CBF packages.

Specifically:
1. import org.openqa.selenium.WebDriver;
The extra perform() with WebDriver should be removed from below classes:
./engine/BaseAppDriver.java
./engine/BaseModuleDriver.java
./model/AppDriver.java
./model/ModuleDriver.java

Instead, try the following:
1.1 Revert BaseAppDriver and BaseModuleDriver back to old state
* They should be in cbf.model not in cbf.engine package.
1.2 Add and use cbfx.selenium.BaseWebAppDriver and cbfx.BaseWebModuleDriver as follows
* To be discussed <== These are now further detailed in WebAppDriver.java
  - Merging with cbfx.basedrivers
  - Use of WebUIWrapper
  - Use of BrowserFactory
*/
import org.openqa.selenium.WebDriver;
abstract class BaseWebAppDriver extends BaseAppDriver {
  public BaseWebAppDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void recover() {
    close browser;
  }

  protected WebDriver webdriver;
}

import org.openqa.selenium.WebDriver;
abstract class BaseWebModuleDriver extends BaseWebModuleDriver {
  public BaseWebModuleDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }
  protected WebDriver webdriver;
}

/*
2. Remove further references in the below areas:
./engine/Engine.java: Revert back by removing the new extra method
./engine/TestCaseRunner.java: Revert back by removing the new extra method
./runner/TestSetRunner.java: Revert back by removing the new extra method
./harness/Harness.java: Revert back by removing the new extra method

3. There are further cleanups in cbfx:
* browsers: rename as browser; create a facade "Factory". add Remote also.
  Discuss how appium etc. falls into this.
* objectmaps: rename as objectmap; interface needs cleanup
* All UIDriver: instead of null constructors, should get Map() as parameters; caller should get from GCONFIG.
*/
*/
