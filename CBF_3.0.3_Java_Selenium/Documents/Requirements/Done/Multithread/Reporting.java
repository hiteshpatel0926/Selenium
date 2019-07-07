/*
Requirements
------------
1. Reporting is not threadsafe. Make it threadsafe akin to the tracker.
* Tracker/logger and reporting are intimately related
2. Provide for summarized reporting where possible.
3. Provide control to the Runner in these matters.
4. Cleanup ScreenDumpManager
* It is not part of core Reporting; it is coupled to cbfx.selenium
* Screen dump is specific to the thread

*/
/*
Specifications
--------------
1. Harness
* make Reporting non-static
Now, each thread is to create its own instance of Harness.
*/
class Harness {
  Harness(...) {
	  initializeReporters();    // call from constructor
  }

	private void initializeReporters() {
    ...
  }

  private ReportingManager reportingManager; // Make ReportingManager non-static
}
/*
2. Summary reports
The summary reports will need data to be accumulated from
multiple threads and reported in a central way.
So, the reporter at a thread level will only be responsible
for providing its own specific data.
  There has to be separate provision for summarizing.

3. Screen Dump
* Screen dump is a parameter of TestResultLogger. Hence it is a core cbf.*** functionality. So, define it in model.
*/
package cbf.model;
public interface ScreenDumper {
  public void dumpScreen(String filePath);  // dump screen at file path
}
/*
* Screen dump must be supported by AppDriver. So, if it implements it, then, that is good enough. No separate param needed.
*/
package cbfx.selenium;
public class BaseWebAppDriver extends BaseAppDriver implements ScreenDumper {
  public void dumpScreen(String filePath) { // dump screen at file path
    use uiDriver as needed
  }
}

/*
Provide access to AppDriver to TestResult objects!
There is no present way, where the reporters have access to
which application they are testing.
CHECKME: is this a good idea?
*/
package cbf.engine;
class TestCaseRunner {
 	public TestResult runTestCase(final TCMaker tcMaker, String name) {
		return oResultsTracker.track(new Trackable() {
			public void run(TestResult result) throws Exception {
        result.miscInfo.put("AppDriver", appDriver);  // <-- new line
        ... rest of logic


/*
Provide convenience method to return AppDriver
*/
class TestResult {
  public AppDriver getAppDriver() {
    AppDriver d = (AppDriver)miscInfo.get("AppDriver");
    if (d != null)
      return d;
    if (parent != null)
      return parent.getAppDriver();
    return null;
  }
}

/*
Have ScreenDumpManager access it as usual.
*/
class ScreenDumpManager {
  private ScreenDumper getScreenDumper(TestResult testResult) {
    AppDriver d = testResult.getAppDriver();
    if (d == null)
      logger.handleError("bug");
    try {
      return (ScreenDumper)d;
    } catch(ClassCastException e) {
      return null;
    }
  }

  // change rest of code to use this
}

/*
9. Cleanups:
9.1 Harness
* Remove browserName (argument/member). It is noise.

9.2 ReportingManager
* Constructor(List<String, Object> reporterPlugins, Configuration config)
  - Dont take a generic Object. Take a list
  - rename CONFIG --> config
* reporter member: List<ResultReporter> not List<Observer>. Why cast everytime?
* open(Headers) should be called explicitly by caller.
   Not implicitly from within ReportingManager
*/
