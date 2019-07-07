/*
Requirements
------------
1. At present, 1 instance of ReportingManager is created for each Harness instance.  This means one for each thread. So, global reporting will make it difficult to manage.
1.1 This is proposed only. Not implemented. But, will be needed.

2. Lifecycle of reporting will need to be clear.
Required so that reporting resources (Excel files/DB connections etc.) are properly managed.

Discussion
----------
1. It is possible for the XXXReporter class to use static variables to share
summary data. However, this is not right since, it will create problems
when we want >1 variations of same XXXReporter class loaded as different plugins.

Specifications
--------------
1. Change Reporter interface to return a Tracker.Observer, rather than
be an observer itself.
*/
package cbf.model;
interface Reporter {
  public Observer open(Map headers);  // start tracking
  public void close(Observer o);  // end tracking
}

/*
2. ReporterManager will be a harness helper which manages reporters.
It will be static to Harness.
*/
public ReporterManager {
  private Reporter[] reporters; // list of configured reporters

  public ReporterManager(Map reporterConfiguration, Configuration config) {
    initialize reporters;
  }
  ...

