/*
Requirements
------------
Drive testNG test cases written in text files such as TestNGDriver.yaml file

Synoposis
---------
Test suite is a collection of such yaml files.
A generic driver file will drive the suite of yaml files.
* Read from yaml file into a data structure
* Convert to a generic TestNG testcase
  - code generation vs interpretation

Multiple instances are put into a generic factory
*/
package cbf.helper;

import TestNGHelper;

class TestNGDriver {
  public static Step {
    module/component/args/descrition
  }

  public interface Test {
    public Step[] before;
    public Step[] after;
    public Step[][] tests;
  }

  public class TestNGDriver(Test test) {
    this.test  = test;
  }

  @before
  public void doBefore() {
    runSteps(test.before);
  }

  @after
  public void doAfter() {
    runSteps(test.after);
  }

  @test
  public void doTest() {
    // how to split to multiple test?
  }


  private void runSteps(Step[] steps) {
    if (steps == null)
      return;
    steps.forEach(step) {
      TestNGHelper.perform(step.module/action/args);
    }
  }
}
