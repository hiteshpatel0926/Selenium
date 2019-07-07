/*
Goals
-----
1. Merging TestNGRunner and MainRunner
* TestNGRunner: Supports XBT, Parallel exection
* MainRunner: Supports TestSet, Reruns

Considerations & Approach
-------------- - --------
1. Use TestNGRunner since it supports parallelism

2. Model incompatibilities will restrict combinations
* XBT Reruns: a single (TC, OS, Browser) combo might be rerun
* TestSet vs XBT: if properties of TestInstance contain browsers: [], then it can be used by the XBT runner

3. XBT test execution and Functional test execution are two use cases. They need not share the same runners.
But, they should not be entirely different either.

4. TCMaker & TSMaker model gets into issues, since each testcase will need to be run multiple times.
In the case of an E2E SIT cases also, where multiple testcases are stitched together to create E2E scenarios, this is a probem.
4.1 Fix this by a special method in tracker purely for reporting purposes. See Engine-FixingTCMaker.java 

5. TestNGRunner factory requires the list of tests to be known in the beginning.
So, rerun-on-failure support can be handled either by:
* having one TestNG runner instance for each rerun <-- failed tests are rerun as a separate batch 
* faking a TestCase as itself + its reruns <-- case where the same test needs to be rerun immediately on failure

6. Harness access provides events at testcase level.
There is no advantage of providing TestSetRunner within engine since it forces test to run in series.
However, a generic BatchRunner can be provided for specific cases including TestSet, TestCase-rerun.

6.1 When there are parallel runs, the parent TestResult object can be synchronously updated to summarize.
Unfortunately, this is not supported since tracker-engine-harness is at each thread level.
So, this has to be now explicitly summarized by each report.

7. There are limits of parallel runs: TestNG vs #grid node availability. This needs checking.

Requirements
------------

Specifications
--------------
1. MainRunner will be the functional runner. CLI will take the following:
* Config file
* TestSetFactory
* Rerun: {count | immediately or at end }

2. XBTRunner will be the XBT runner. CLI will take the following:
* Config file
* TestSetFactory: <browsers> might be a property of the test instance, runMap (comma separated)
* Grid specs?
* Rerun: {count | immediately or at end }

3. engine.TestSetRunner will be purified and generalized as a MultiTestCaseRunner
Tracking of failed ones are needed if failed ones are to be rerun at end.
It is expected that that is explicitly added as observer to engine by caller.

ALL RUNS BY THIS ARE SEQUENTIAL.
*/
package cbf.engine;
class MultiTestCaseRunner {
  public MultiTestCaseRunner(AppDriver, TestResultTracker) {
    testcaseRunner = new TestCaseRunner(appDriver, resultTracker);
  }

  /*
  rerun same test case multiple times in case of failure
  */
  public TestResult void runTestcase(final TCMaker tcMaker, String tcName, int trials) {
    final TestResult lastResult[] = new TestResult[1];  // single element; final issue

		TestResult result = resultTracker.track(new Trackable() {
			public void run(TestResult result) throws Exception {
        // CHECKME: this will remake TCMaker multiple times
        for (int i=0; i<=trials; ++i) {
          TestResult result = testcaseRunner.runTestCase(tcMaker, tcName);
          lastResult[0] = result;
          if (result.isPassed())
            break;
          logger.trace("Rerunning the test", i);
        }
      }
		}, TestResult.EntityType.RERUN, tcName, tcMaker);

    result.msResult = ... = lastResult[0];  // overwrite by last trial

    return result;
  }
  
  public interface Handler {
    public void handle(TCMaker tc, TestResult result);
  }

  /*
  trials: if <=0: each test case has a single try without a RERUN result node (the default)
          if >=1: each test case has 1 or more try; with a RERUN result node
  */
  public TestResult runTestcases(final TCMaker[] tcMakers, enum parentEntityType, String entityName, Object entityDetails, final Handler handler, int trials) {
		return resultTracker.track(new Trackable() {
			public void run(TestResult result) throws Exception {
        for (TCMaker tcMaker: tcMakers) {
          if (trials <= 0)
            TestResult result = testcaseRunner.runTestCase(tcMaker, "NoName");  // CHECKME; this will get fixed if TCMaker is removed
          else
            TestResult result = runTestCase(tcMaker, "NoName", trials);

          if (handler != null)
            handler.handle(tc, result);
        }
      }
		}, parentEntityType, entityName, entityDetails);
  }

  /*
  short hand of runTestCases(trials=0)
  */
  public TestResult runTestcases(TCMaker[] tcMakers, enum parentEntityType, String entityName, Object entityDetails, Handler handler) {
    return runTestcases(tcMakers, parentEntityType, entityName, entityDetails, handler, 0);
  }

  /*
  batchTrials: #batch trials; if >1, subsequent batches are with the failed tcMakers only. 
          if <=0, a single batch is run without RERUN node
          if >=1, at most as many batches run with failed tests till all pass
  */
  public TestResult runTestcases(final TCMaker[] tcMakers, enum parentEntityType, String entityName, Object entityDetails, int trials, int batchTrials) {
    if (batchTrials <= 0) { // simply run
      return runTestCases(tcMakers, parentEntityType, entityName, entityDetails, trials, null);
    }

    final TestResult lastResult[] = new TestResult[1];  // single element; final issue

		TestResult result = resultTracker.track(new Trackable() {
			public void run(TestResult result) throws Exception {
        TCMaker[] nextOnes = tcMakers;

        for (int i=0; i<=batchTrials; ++i) {
          logger.trace("batch run", i, "tests", nextOnes.length());

          final List<TCMaker> failed = new ArrayList<TCMaker>();

          TestResult result = runTestCases(nextOnes, parentEntityType, entityName, entityDetails, trials,
            new Handler() {
              public void handle(TCMaker tc, TestResult result) {
                if (!result.isPassed)
                  failed.put(tc);
              }
            });

          lastResult[0] = result;

          if (result.isPassed()) { // all has passed
            logger.trace("All tests passed, not checking for rerun ix", i, "#failed", failed.size());
            break;
          }

          nextOnes = failed.toArray();
        }
      }
		}, parentEntityType, entityName, entityDetails);

    result.msResult = ... = lastResult[0];  // overwrite by last trial

    return result;
  }

  private final testcaseRunner;
}

/*
4.MainRunner can simply call MultiTestCaseRunner
Engine can act as facade:
* getTestCaseRunnner()
* getMultiTestCaseRunner()
*/
package cbf.runner;
class MainRunner {
  public static void main(String[] args) {
    Map runMap = Utils.parse(args);

    int trials = runMap.get("runTrials");
    TestSet ts = getTestSet(...);

    Harness.initialize(runMap);

    Harness h = new Harness();
    TestResult result = h.getEngine().getMultiTestCaseRunner().runTestCase(
                            testset2TCMaker(ts), TestResult.Type.TESTSET, ts.getName(), ts, trials, batchTrials);

    h.finalize();

    Harness.finalize();
  }

  private static TCMaker[] testset2TCMaker(TestSet ts) {
    TCMaker[] out = new TCMaker[ts.getInstanceCount()];
    for each instance in ts {
      out[ix] = new TCMaker() {
        public TestCase getTestCase() {
          return instance.getTestCase();
        }
      }
    }
  }

  private static TestSet getTestSet() {
  }
}

/*
5. XBT/TestNGRunner will require a parallel approach
It should be generalized beyond XBT.
5.1 Rename XBTBridgeTestCase as BridgeTestCase
* It has no logic specific to XBT
* Change it to use TestInstance instead of TCMaker+instanceName
* It can accept trialCount and call MultiTestCaseRunner() to support reruns

5.2 Create a BridgeTestCaseFactory for common logic
This will allow for using TestNGRunner in different ways
*/
package cbfx.runner;

class BridgeTestCaseFactory {
  public BridgeTestCaseFactory(TestSet testSet, Map runArgs) {
    this.testSet = testSet;
    this.runArgs = runArgs;
  }

  /*
  as per default configuration
  */
  public BridgeTestCase[] make() {
    return make(this.runArgs);
  }

  /*
  overrride default runArgs
  */
  public BridgeTestCase[] make(Map runArgs) {
    BridgeTestCase[] out = new BridgeTestCase[testSet.instanceCount());

    for (int i=0; i<testSet.instanceCount(); ++i) {
      out[i] = new BridgeTestCase(testSet.instance(i), runArgs);
    }

    return out;
  }

  /*
  for a single browser; for web app drivers only
  */
  public BridgeTestCase[] make(String browser) {
    Map args = makeArgs(browser);
    return make(args);
  }

  /*
  across multiple browsers
  */
  public BridgeTestCase[] make(TestSet testSet, String[] browsers) {
    List<BridgeTestCase> out = ArrayList<BridgeTestCase>();
    for (String b: browsers) {
      tmp = make(b);
      out.append(b);
    }
    return out.toArray();
  }

  /*
  across specific matrix of (instance x browsers)
  Here, TestInstance.properties("browsers") contain list of browsers
  */
  public BridgeTestCase[] makeFromMatrix(TestSet testSet) {
    List<BridgeTestCase> out = ArrayList<BridgeTestCase>();
    for each instance {
        String bsel = instance.properties().get("browsers");
        if (bsel == null)
          continue;
        String[] browsers = bsel.split(",");
        
        for (String browser: browsers) {
          out.add(new BridgeTestCase(instance, makeArgs(browser));
        }
    }

    return out.toArray();
  }

  private static Map makeArgs(String browser) {
    Map runArgs = this.runArgs.clone(); overwrite ["browser"]
    return runArgs;
  }
}

/*
Streamline other files as below:
5.3 Runner: will be aligned to MainRunner
* runArgs
* testSet object creation from factory
* trial/batchTrial counts

5.4 XBTExcelTestCaseFactory: will now be just a bridge class
* take testSet, runArgs as parameters
* call BridgeTestCaseFactory() and return its values

5.5 XBTSuiteFactory: make it generic. rename as XmlSuiteFactory
* Its variable parameters can be configured as a plugin
*/
class XmlSuiteFactory {
  public XmlSuiteFactory(Map params) {
    this.params = params;
  }

	public XmlSuite makeSuite(TestSet testSet, Map runArgs, int threadCount) {
			
		Map<String,String> parameters = new HashMap<String, String>();
		parameters.put("testSet", testSet);
		parameters.put("runArgs", runArgs);
		
		List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		myClasses.add(new XmlClass(params.get("factoryClass")) // "cbfx.testng.XBTExcelTestCaseFactory"
		
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName(testSet.getName());
		mySuite.setThreadCount(threadCount);
		
		XmlTest myTest = new XmlTest(mySuite);
			myTest.setName(testSet.getName());
			myTest.setParallel("methods");										 
			myTest.setParameters(parameters);	
			myTest.setXmlClasses(myClasses);
		
		List<XmlTest> myTests = new ArrayList<XmlTest>();								 
		myTests.add(myTest);
					 
		mySuite.setTests(myTests);
		
		return mySuite;
  }
}

/*
5.6 TestNGRunner: Make it generic. Align it MainRunner.
* Rename cbf.runner as cbfx.cli. Rename cbf.runner.MainRunner as cbfx.cli.Simple
* Rename cbfx.testng.TestNGRunner as cbfx.cli.TestNG
*/

package cbfx.cl;
class  TestNG {
  public static void main(String[] args) {
    runArgs = ParseCommandLine(args)

    TestSet ts = getTestSet(...); // same as Simple

    call Runner() parameters
  }
}

/*
5.7 Clean up
* Remove TestNGHelper. Noise.
* Runner: might need a better name.
/*
