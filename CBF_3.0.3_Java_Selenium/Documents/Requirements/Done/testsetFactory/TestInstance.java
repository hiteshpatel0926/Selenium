public interface TestInstance {
	/**
	 * Returns name of TestInstance
	 * 
	 * @return TestInstance's name
	 */
	public String instanceName();

	// public TestIteration[] iterations(); REMOVE ITERATIONS. get from testCase
  // DOUBLE-CHECK before making this change

	// public String description(); REMOVE THIS
	
	public String folderPath(); REMOVE THIS

	/**
   * Custom properties might include description, folderPath etc.
   *
	 * @return custom properties specific to the implementation of test instance
	 */
	public Map properties();
}
