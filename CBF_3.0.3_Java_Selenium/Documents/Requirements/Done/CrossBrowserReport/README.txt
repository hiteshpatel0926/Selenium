Requirements
------------
1. Implement reports as in XBT style.
See XBTSample folder.

Background
----------
XBT reports are generated from some base data.
See code in ttp://gnrsvn/svn/AutomationCOE_SVN/Solutions/Cross%20Browser/branches/Cross%20Browser%20Testing%20-%202.3.3/Framework/xtam/crossbrowser/report
Framework/xtam/crossbrowser/report folder/package
Framework/xtam/crossbrowser/report/reporter/HTMLReport.java ? Quadrant Reports
xtam/crossbrowser/report/reporter/helper/HTMLReportHelper.java

Specifications
--------------
1. The report will consist of the following:
* THe base html, and auxiliary js/css/img files
* The data.js files (alternately, csv files might be also alright?)

2. The reporter will do the following:
2.1 create the base html/js/css/img files from the reference template files <-- replace the data.js file name if needed
* If the report file is created in its own folder, then this is not needed
* There may be multiple report files using same data file
  - index.html (cover page), summary.html (), detail.html
2.2 open data.js file. keep appending a line addData(<parameters>) for every event

3. The reporter may be configured for both XBT and Single-browser-functional testing.
* Just select the right html file, for e.g.

Sample
------
NewSample folder is a WIP template for the same.
XBTReporter.java is the reporter.
THis is for further discussions.
