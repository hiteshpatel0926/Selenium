package cbf.testaccess;

import cbf.model.TestSetFactory;

abstract class BaseTestSetFactory implements TestSetFactory {
  /*
  params: factory parameters.
          for e.g. it could be ALM connection string, project
          This makes it a plugin
  */
	protected BaseTestSetFactory(Map params) {
		this.params = params;

    labFolder = ResourcePaths.getInstance().getSuiteResource("Lab", "");

    testCaseFactory = makeTestCaseFactory();
  }

  /*
  non-static inner class which uses testCaseFactory to return testInstances
  */
  abstract class BaseTestInstance implements TestInstance {
    public BaseTestInstance(Map info) {
      this.info = info;
    }

    public Map properties() {
      return info;
    }

    public TestCase testCase() {
      if (cachedTestCase != null)
        return cachedTestCase;

      cachedTestCase = testCaseFactory.getTestCase(info);
      if (cachedTestCase == null)
        logger.handleError("Invalid testinstance info", info);

      return cachedTestCase;
    }

    protected Map info;
    protected TestCase cachedTestCase;
  }

  /*
  Returns null if not found.
  info: specifications of what test set is as per factory
  */
  abstract public TestSet getTestSet(Map info);

// a few convenience methods
/*
Many a time, factory params contain simply default for the test set.
This method combines the two, with testSetParams overriding the factory's.
*/
  protected Map mergeWithParams(Map testSetParams) {
    if (testSetParams == null)
      return params;
    if (params == null)
      return testSetParams();

    Map out = new HashMap(this.params);
    out.putAll(testSetParams);
    return out;
  }

  private TestCaseFactory makeTestCaseFactory() {
    Map config = getTestCaseFactoryConfig();

    try {
      return (TestCaseFactory)PluginManger.getPlugin(config);
    } catch(Exception e) {
      logger.handleError("Loading testcasefactory", config, e);
    }
  }

  /*
  Factory might be configured specifically for the testset
  Or, globally configured at the Harness.Config
  */
  private Map getTestCaseFactoryConfig() {
    final String KEY = "TestCaseFactory";
    Map config;
    try {
      config = (Map)params.get(KEY);
    } (ClassCastException ce) {
      logger.handleError("Testcase configuration for the test set", params, ce);
    }
    if (config != null)
      return config;

    try {
      config = (Map)Harness.config().get(KEY);
    } (ClassCastException ce) {
      logger.handleError("Testcase configuration", Harness.config(), ce);
    }
    if (config != null)
      return config;

    logger.handleError("Testcases not configured");
    return null;
  }

  protected String labFolder; // for convenience, since many factories are lab-based

  protected Map params;
  protected LogUtils logger = new LogUtils(this);
}
