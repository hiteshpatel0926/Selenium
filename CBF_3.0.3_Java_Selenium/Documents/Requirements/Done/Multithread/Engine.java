/*
Changes
-------
Engine is currently dummy. Take it a bit more responsibilities from Harness.
A bit more robust etc.
* Remove runName. Pass appDriver as part of parameter.
* RESULT will be recreated for every run.
*/
	public Engine(TestResultTracker resultTracker) {
		this.resultTracker = resultTracker;

    TestResultLogger.setTracker(this.resultTracker);  // set tracker

		CHECKME: why this? appDriver.initialize();
	}

	public TestResult runTestCase(TCMaker tcMaker, String TCName, AppDriver appDriver) {
		TestCaseRunner oTestCaseRunner = new TestCaseRunner(appDriver, resultTracker);

		return oTestCaseRunner.runTestCase(tcMaker, TCName);
	}
	
	/**
	 * Returns Engine format string
	 */
	public String toString() {
		return StringUtils.mapString(this);
	}

	private TestResultTracker resultTracker;
	private LogUtils logger;
}
