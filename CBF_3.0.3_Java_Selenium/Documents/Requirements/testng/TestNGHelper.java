/*
Requirements
------------
Ability to call CBF components directly from free-style TESTNG scripts

TestNGHelper provides certain methods to do the same.

Synoposis
---------
static import cbf.helper.TestNGHelper;

class MyTest {
  @before
  void xxxx() {
    setup("./config.xml");
  }

  @after
  void yyyy() {
    teardown()
  }

  @test
  void someTest(...) {
    Map output = perform("Main", "Login", { });  <-- figure out improving data mgmt
  }
}
*/
package cbf.helper;

class TestNGHelper {
  static class Reporter {
    // redirect pass/fail call to asserts()
  }

  public static void setup(String configFilePath) {
    harness = instantiate harness with config from config file
    harness.tracker.add(new Reporter());
  }

  public static void teardown() {
    harness.finalize();
    harness = null;
  }

  /*
  CHECKME: Tracker testresult: might not be returnable
  */
  public static TestResult perform(String moduleCode, String componentCode, Map input, Map output) {
    harness.engine.tracker.tracker... {
      harness.appDriver.perform(moduleCode, componentCode, input, output);
    }
    return testresult;
  }

  public static TestResult perform(String moduleCode, String componentCode, Map input) {
    Map output = new DataRow(...);
    return perform(moduleCode, componentCode, input, output);
  }

  public static void recover() {
    harness.appDriver.recover();
  }

  private static Harness harness = null;
}
