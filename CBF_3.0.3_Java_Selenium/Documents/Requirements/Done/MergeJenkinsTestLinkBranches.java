/*
Requirements
------------
There are 3 branches for Jenkins, Testlink and basic runner.
The code base needs to be merged into a single code base.

The branches are largely the same. The variances are:
* Harness.java is different
* There are a few specific classes (TestSet related, for e.g.
* There might be a few minor customizations scattered around: TBD

Approach
--------
1. Convert Harness.java to a pure harness.
* It will hold everything togehter
* It provides methods for running test cases
  - loads the configuration
  - initilizes plugin manager
  - initializes reporting, tracker, engine etc.
  - loads app driver
  - run a test case <-- can be called multiple times
  - close all at the end

2. There will be separate runners which calls the framework.
* ExcelTestSetRunner
* TestlinkTestRunner or STAFTCRunner?
* JenkinsTestRunner

FUTURE: To be specified later.
There will be variants of JUnit and TestNG tests which can use this.
* startup: initialize harness object
* tearDown: harness.close
* testMethod: deserialize something and call harness

Specifications
--------------
To be detailed separately...
*/
