/*
Requirements
------------
TCMaker & TSMaker model gets into issues with XBT and re-run, since each testcase will need to be run multiple times.
In the case of an E2E SIT cases also, where multiple testcases are stitched together to create E2E scenarios, this is a problem too.
Present design adds complexity, but doesnt address problem.

Options:
1. Directly use Testcase
2. Instead, use TestInstance interface. This is best.
   TestInstance should lazily load TestCase, so only in TestCaseRunner it should call it
2.1 BaseTestInstance in test set factories caches loaded test case.
    So, for multiple runs(for XBT), there wont be multiple loads.
2.2 In these cases, there has to be a facility to avoid spurious reruns
    when there is initialization errors.

3. Remove TSMaker. Let TestSet initiali be handled outside of engine.

Specifications
--------------
1. Remove TCMaker (and TSMaker). Pass TestInstance (and TestSet) directly to
Engine and TestCaseRunner.

2. Have explicit method in Tracker and Engine for reporting initialization errors.
Thus Engine.java will be:
*/
public class Engine {
	// <--- Note signature change
	public TestResult runTestCase(TestInstance testInstance, AppDriver appDriver) {
		TestCaseRunner oTestCaseRunner = new TestCaseRunner(appDriver, resultTracker);
		TestResultLogger.setTracker(resultTracker); // Direct the result to result tracker
		return oTestCaseRunner.runTestInstance(testInstance);
	}
}

// TestCaseRunner will be simplified. No TCMaker, TSMaker etc.
public class TestCaseRunner {
  ...

  /* Remove TCMaker interface
	***** ------------- public interface TCMaker
  */

	/**
	 * Executes TestCase
	 * 
	 * @param testcase
	 *            testcase object
	 * @return result of the test
	 */
	public TestResult runTestInstance(TestInstance oTestInstance) { <--- signature change
		return oResultsTracker.track(new Trackable() {
			
			public void run(TestResult result) throws Exception {
				result.miscInfo.put("AppDriver", appDriver);

				TestCase oTestCase;
				try {
					oTestCase = testInstance.getTestCase();
				} catch (Exception e) {
					error("RunTestCase():", e.toString(), "" + e);
          result.miscInfo.put("InitializationError", e);  <-- use this to check initialization error
					return;
				}

				// fire an artificial log event with the deserialized testCase
				oResultsTracker.log(ResultType.DONE, oTestCase);

        // <-- move these lines to Engine.runTestCase
        // <-- scope of this is wrong: TODO discuss
				paramsAccess = new ParameterAccess();
				biComponentsDriver = new BuiltinComponentsDriver(paramsAccess);
				paramsAccess.declareVariables(oTestCase);
				oResultsTracker.addReporter(paramsAccess);
/*

3. Now the event model will change for reporters.
There is a TestInstance object instance of TestCase as entityDetails.
To discuss.
*/
