/*
Requirements
------------
To give support for singleSuite of test cases. We have to incorporate all the individual testCases into one single suite.
Change the logic of respective class libraries where we are reading different xls sheets.So,that we can read the testCases from single suite.

Considerations
--------------
Testcase file has multiple sheets like steps,Inline data sheet,variables,Iterations and References.
 to create single Testcase suite we need to know how Iterations,References and variables to place in these sheets for individual testCases. 

We have to create one workbook under plan folder instead of TestCases. In that we have to create separate sheets for individual TestCases.
In order to implement single suite we have to do some modifications in classes shown below.
1. ExcelTestCaseFactory
2. ExcelDeserializer


Specification
--------------
1. Test case file has different types of data like steps,Iterations,References,Inline data and variables.

2.DesignerDeserializer has to be modified for Attributes,Iterations,stepcount ,etc because in individual testCases there is separate sheet for Iterations.
 For single Suite we have to know how these things has to be placed.
3.In ExcelTestCaseFactory we have to change the path of the folder for TestCases as we are incorporating in to single suite.

Eg:1. testFile = Harness.resourcePaths().getSuiteResource("Plan/TestCases", testName + ".xls"); 
   2. private TestCase deserializeTest(String testName, String serializedFileName) {
      } these both has to be changed.


*/
