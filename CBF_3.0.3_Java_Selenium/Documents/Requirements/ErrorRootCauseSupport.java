/*
Requirements
------------
When tests fail, the root cause categories should also be logged, wherever possible/appropriate.
1. Within WebUIDriver, the causes could be known. Hence should be logged in those cases.
2. In general, there has to be a provision for the cause

Specifications
--------------
1. Define common rootcause categories
*/
package model;
enum FailureCause {
  ObjectIdentification="Object Identification",
  DataIdentification="Data identification",
  Synchronization="Synchronization",
  NonSpecific // <-- not-a-clear-category
};

class TestResultLogger {
  public static void failed(existingParameters, FailureCause cause) {
  }
  public static void failed(existingParameters) {
    failed(existingParameters, NonSpecific);
  }
}

/*
2. In existing wrapper code, determine if failed calls should use the categories
cbfx.ui.* classes

3. In reporters, how to enhance to report these categories.
cbfx.factory.report.* classes
*/
