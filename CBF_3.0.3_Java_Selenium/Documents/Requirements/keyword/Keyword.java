/*
TODO:
1. java.text usage for text templates'
2. Expand value (see TODO below)
3. Non-standard keywords
*/
class KeywordDriver {
  final public WebUIDriver webUIDriver;

  public KeywordDriver(WebUIDriver webUIDriver) {
    this.webUIDriver  = webUIDriver;
  }

	public void launchApplication(String url) {
		act("Launch Application", "Launched {url}", () -> webUIDriver.launch(url, true));
	}

  public boolean checkPage(String expectedTitle) {
    return check("Check page title", expectedTitle, () -> webUIDriver.getTitle(), (expected, actual) -> expected.equals(actual));
  }

  /**
   * CHECKME
   * checkElementPresent vs checkPresent
   * Timeout: some has timeout in parameters; why is this implicit?
   **/
  public boolean checkPresent(String elementName) {
    return check("Check Element Present", true, () -> webUIDriver.isPresent(elementName)), (expected, actual) -> (expected == actual));
    /*
    TODO: check doesnt do a proper messaging
    TODO: timeout
		long timeout = toTimeout((String) Harness.configuration().get("TimeOutInSec"));
    wait(timeout)
    */
  }
  
  /**
    * svalue can be either:
    * - slab name: YIELD/HIGH/LOW/MEDIUM: recommended
    * - actual value encoded in string?
    **/
  public long toTimeout(String svalue) {
    if (svalue == null)
      return 0;

    SleepUtils.TimeSlab slab = SleepUtils.slabByName(svalue);
    if (slab != null)
      return SleepUtils.interval(slab);

    try {
      return Long.parseLong(svalue);
    } catch(NumberFormatException e) {
      logger.handleError("Invalid timeout value", svalue, e);
    }
  }

  private interface Action {
    public void act();
  }
  private void act(String name, String msg, Action action) {
    try {
      action.act();
      done(name, "", msg);
    } catch(FrameworkException e) {
      error(name, "", "Error while '{msg}': {e} caused by {e.getCause()}");
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{msg}': {e}");
    }
  }

  private interface Getter {
    public Object get();
  }
  private interface Checker {
    public boolean check();
  }
  private boolean check(String name, Object expected, Getter getter, Checker check) {
    Object actual = null;
    try {
      actual = getter.get();
    } catch(FrameworkException e) {
      error(name, "", "Error while '{name}': {e} caused by {e.getCause()}");
      return false;
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{name}': {e}");
      return false;
    }

    try {
      boolean rc = checker.check(expected, actual);
      if (rc)
        passed(name, "Expected is {expected}", "Actual was {actual}");
      else
        passed(name, "Expected is {expected}", "Actual was {actual}", FailureCause.???);
      return rc;
    } catch(FrameworkException e) {
      error(name, "", "Error while '{name}': {e} caused by {e.getCause()}");
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{name}': {e}");
    }

    return false;
  }

  private interface ElementAction {
    public void act() throws NoSuchElementException;
  }
  private void act(String name, String msg, ElementAction action) {
    try {
      action.act();
      done(name, "", msg);
    } catch(NoSuchElementException e) {
      failed(name, "", "Could not find targeted element", failureCause.???); // <-- if needed NoSuchElementException() should contain targeted element name
    } catch(FrameworkException e) {
      error(name, "", "Error while '{msg}': {e} caused by {e.getCause()}");
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{msg}': {e}");
    }
  }

  private interface ElementGetter {
    public Object get() throws NoSuchElementException;
  }
  private boolean check(String name, Object expected, ElementGetter getter, Checker check) {
    Object actual = null;
    try {
      actual = getter.get();
    } catch(NoSuchElementException e) {
      failed(name, "", "Could not find targeted element", failureCause.???);
      return false;
    } catch(FrameworkException e) {
      error(name, "", "Error while '{name}': {e} caused by {e.getCause()}");
      return false;
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{name}': {e}");
      return false;
    }

    try {
      boolean rc = checker.check(expected, actual);
      if (rc)
        passed(name, "Expected is {expected}", "Actual was {actual}");
      else
        passed(name, "Expected is {expected}", "Actual was {actual}", FailureCause.???);
      return rc;
    } catch(FrameworkException e) {
      error(name, "", "Error while '{name}': {e} caused by {e.getCause()}");
    } catch(Exception e) {
      error(name, "", "Unchecked error while '{name}': {e}");
    }

    return false;
  }

  private LogUtils logger = new LogUtils(this);
}
