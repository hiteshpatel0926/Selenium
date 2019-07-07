import cbf.utils.LogUtils;

// Thread-safe version of TestResultLogger
public class TestResultLogger {
  /*
  * Set thread safe tracker
  * This will be called from Engine
  */
  public static void setTracker(TestResultTracker tr) {
    tracker.set(tr);
  }

	/* Remove this
	public static TestResultLogger getInstance(TestResultTracker tracker) {
	 */

	/**
	 * Logs TestStep result as passed in report
	 * 
	 * @param name
	 *            name of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void passed(String name, String expected, String actual) {
		log(name, ResultType.PASSED, expected, actual, true);
	}

	/**
	 * Logs TestStep result as failed in report
	 * 
	 * @param name
	 *            name of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void failed(String name, String expected, String actual) {
		log(name, ResultType.FAILED, expected, actual, true);
	}

	/**
	 * Logs TestStep result as error in report
	 * 
	 * @param name
	 *            name of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void error(String name, String expected, String actual) {
		log(name, ResultType.ERROR, expected, actual, true);
	}

	/**
	 * Logs TestStep result as done in report
	 * 
	 * @param name
	 *            name of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void done(String name, String expected, String actual) {
		log(name, ResultType.DONE, expected, actual, true);
	}

	/**
	 * Logs TestStep result as warning in report
	 * 
	 * @param name
	 *            name of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void warning(String name, String expected, String actual) {
		log(name, ResultType.WARNING, expected, actual, true);
	}

	/**
	 * Logs the execution results in report as per the inputs
	 * 
	 * @param name
	 *            name of TestStep
	 * @param rsType
	 *            ResultType of TestStep
	 * @param expected
	 *            expected result of TestStep
	 * @param actual
	 *            actual result of TestStep
	 */
	public static void log(String name, ResultType rsType, String expected,
			String actual, boolean screenDump) {
    TestResultTracker tr = tracker.get();
    if (tr == null) {
      logger.handleError("Logging status before setting tracker");
    }

		tr.log(rsType, Utils.toMap(new Object[] { "name", name,
				"expected", expected, "actual", actual, "screenDump",
				new Boolean(screenDump) }));
	}

  private static final ThreadLocal<TestResultTracker> tracker = new ThreadLocal<TestResultTracker>();

	private static LogUtils logger = new LogUtils("TestResultLogger");
}
