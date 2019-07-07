/*
Requirements
------------
1. TestNG parallel execution requires the engine to run multiple tests/threads
within the same JVM
2. Crossbrowser test requires the same test to be run with different browsers

Approach
--------
1. Remove global items.
1.1 Do **not** use summary reports. Or if used, be able to synchronizely handle when the events get generated in parallelized manner

1.2 Remove static variables. Pass local variables as much as possible.
Replace static variables with ThreadLocal variables.
Refer: https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html

2. Provide for instantiation of AppDriver for each TestCase
* as opposed to 'recover' etc. not quite relevant here

3. Provide for passing of browser as 'run time argument'

General Concerns
------- --------
1. Utils/Others
* Logger will be shared by multiple threads
* getInstance() methods should be synchronized

2. Lots of avoidable static members
2.1 EmailAlerter
	isFailed, tsMap: these static members are not needed. Need not be static.
  Shouldnt be even members.
  msDetails of each TestResult will provide details...
2.2 testaccess/*TestSetAccess
The test set access model is wrong. That itself seems to require static member.
./testaccess/AlmTestSetAccess.java
./testaccess/ExcelTestSetAccess.java
./testaccess/JsonTestSetAccess.java
./testaccess/XmlTestSetAccess.java
2.3 plugin/PluginManager.java
	public static String masterConfigFileName; // make it private non-static
2.4 harness/ParameterAccess.java
	entityType2Scope: Mark it as final. Mark it private
2.5 harness/ResourcePaths.java
	autoHome, workHome, runHome: Mark them as final. Mark them as private
  there are two getInstance. TODO: clean that design.
2.6 harness/Harness.java
	public static ResourcePaths resourcePaths;  // remove this; unused
	public static AppDriver appDriver;  // Make it non-static
	public static Configuration GCONFIG; // Harness is not a singleton. So, this cannot be static here. TODO: clean that design
2.7 engine/TestResultLogger.java <-- see new file.
  Since this has only STATIC methods, getInstance() class, private TestResultLogger(constructor) and RESULT object doesnt make sense.
	private static TestResultLogger RESULT; // check if this can be avoided
	private TestResultLogger(TestResultTracker tracker) { // <-- this tracker parameter is not needed.

	private static TestResultTracker tracker; TODO: MUST fix this.
  Tracker is actually instantiated in Harness.

Specifications
--------------
0. Global Setup
We will continue to assume below as shared read-only global variables.
* Configuration: as in earlier Harness.GCONFIG; might need change
* Setup of logs, ReportManager:

1. ThreadLocal Variables
Use the following as ThreadLocal so that the overall model remains close to the single threaded model.
* TestResultTracker: so, you can use static import passed/failed etc. <-- see TestResultLogger.java

2. The following will require flexibility to be managed at run-level or thread-level or at test-case level
* Harness: AppDriver, Engine etc
2.1 Essentially, Harness will provide for repeated instantiation of itself, and these.
    Caller/runner can be programmed to manage it in different ways.
2.2 loadConfiguration() will move out to a different harness.Config class to take care of #0 above <-- see Config.java
2.3 Cleanup constructor and other interface <-- see Harness.java

3. AppDriver
What is AppDriver.Initialize()? Method is not clearly defined in interface.

9. Reporting
9.1 Testcase level outputs will require to be also ThreadLocal so that they
don't clash with each other. The exception is, when no state need to be
maintained across START/FINISH events.
e.g.?
9.2 Cross-testcase summary will require a shared DB or something.
*/
