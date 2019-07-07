Requirements
------------
Cleanup in the design of *TestSetFactory classes

1 TestInstance model is wrong:
  - has folderPath
  - has iterations[]: need to explain why
  - has description: just name will do; replace folderPath, description by a map of properties() which are for use by its impleme
2 testset\*TestSetFactory's have wrong interfaces
  - implements TestSet, instead of implementing method returning TestSet
  - return TestSets with TestInstances which return null TestCases.
    Runners have to separately get the TestCase using TestCaseFactory
3 there is a model\TestCaseFactory.
  TestSetFactory is not a model and is an implementation in harness package.

Specifications
--------------
A. cbf.harness.TestSetFactory
-- --------------------------
1. It is now an island (not called by harness; called only runner; calls other testaccess classes).
This should now be moved/merged to runners. See #C below.

B. cbf.testaccess.*TestSetFactory Classes
-- ------------------------------ -------
1. Each currently implements TestSet. Instead, it should return TestSet's. Then only it is a factory.
* will internally contain TestCaseFactory
  - TestCaseFactory might be configured as an internal plugin
  - TestCaseFactory plugin name can be passed as a parameter
* will return TestInstances which will use TestCaseFactory to create TestCases when testcase() is called
* Runners will be therefore simplified
* Thus, there can be easy configuration of TestSet/TestCase factories without coding of runners.

C. Runners
-- -------
1. cbf.runner.*: These will now be simplified.
They already are changed, by Vaishnavi for rerun-on-failure purposes.
THis will need re-check.
The logic for calling TestSetFactory's as in cbf.harness.TestSetFactory will
be merged here.

2. cbf.testng.Runner: Suitably the factory method can call
the appropriate factory as per runtime arguments including configuration.
