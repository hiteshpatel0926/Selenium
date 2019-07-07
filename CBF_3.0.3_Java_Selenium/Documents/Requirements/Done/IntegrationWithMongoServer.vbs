'Requirement
'-----------
'Customizations in framework to work with Mongo DB for accessing Auto_TC.xls file for execution
'Create test in designer
'Execute test by just fetching the test case object from mongo server exposed via web server layer which eliminates unnecessary maintenance of TC and data excel files.


'Specifications
'--------------
'1. Configurations -> 
'1.1 Url for accessing web server and TC folder paths are kept in config properties file
'1.2 Testset file -> Contains list of TCs to be executed.

'2. Serializer class without CDTAccess input param.
  
    public TestCase deserialize(){

      'Parse Json string to Json object using JSONParser
      'Return TestCase object
      
    }

'3. TestCaseAccess class - should be customized to handle std as well as mongo integrated scenarios.
'   Switch between existing serializer and the serializer version for mongo access with the help of a config param

'4. Method which sets up connection with webserver should be placed in Utils.

'Other areas
'-----------
' With this approach, the test instances mentioned in TestSet.xls present in the folder path defined in config will give positive results
'What if testcases mentioned in TestSet.xls are from different folder paths? Should there be a separate column in TestSet.xlsto provide the folder path against TC?



