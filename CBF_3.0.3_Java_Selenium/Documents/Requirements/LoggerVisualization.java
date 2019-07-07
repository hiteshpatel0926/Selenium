/*
Requirements
------------
1. Improve logging to be able to further downstream processing.
* Currently, it is in html. move it CSV or other processible form. (see logger fixes)

2. Enable visualization of detailed logs.

3. Extend this to VBS and C# as required. Possibly JScript also.

Considerations
--------------
1. In Designer/server end, ruby has implemented #1. Replicate that here.
This is based in log4r for ruby.
For other languages:
* Java: log4j. See Specifications below
* VBScript: will have to be hand coded. Discuss with me.
* C#: Use log4net, or any other standard Microsoft specific logger.
* JScript: stritti.github.io/log4js and log4javascript.org exists for Javascript. Might not work for Jscript; if so, port from our VBScript logger.

2. In Trial/chart, Using D3js.org and NVD3.org, a timeseries chart can be easily constructed.
Use this and similar for the charts.
* The reusable libraries are available for download (d3.js and nvd3.js/nvd3.css). Copies kept in ivv.igate.com/Tools.

2.1 For this, we need the following:
* logging in a csv+json format, so that all details are captured and then can be visualized without using extensive parsing
* ResultTracker should also log into the execution logs (besides sending to reporters), with a specific marker to distinguish from other trace logs.

Specifications
--------------
1. For detailed logging, see specifications as in LoggingInCsv.java.

2. For visualization, enable the following:
* Chart will provide a  method to append to its internal data, include the eventLogs.js file, and draw the chart.
  - Essentially same as the Trail/chart/TimeSeries.html file, customized as follows:
    * Each event is modified so that...
      * Result events have a value of (5-4-3-2 respectively for TESTCASE-ITERATION-TESTSTEP-ACTION START/FINISH's)
        Trace logs have a value of 1.
        * FUTURE: how to distinguish between nested Steps
      * Color of events hava a color as follows:
        * Red,Red,Amber,Green,Black for Error,Failed,Warn,Passed,Done results
        * Red,Red,Amber,Grey,Black for Fatal,Error,Warn,Debug,Trace logs
          * FUTURE: Fatal logs and Error results should distinguish: check options here (Increase width of the bar; or use a black boundary etc.)
    * Tooltip should detail the event object,
      * Any decent will for first
      * tooltip can put html. If so, convert js object into a suitable format...
        - http://nvd3-community.github.io/nvd3/examples/documentation.html#tooltip has examples
        - Formatting should be:
          + fixed format for timestamp, context, log type, entity type/name etc.
          + generic JSON format for variable part
            - JSON.stringify(o) might work for starters
    * Other tweaks such as:
      * Dont show value on Y axis.
      * Date formats, timezone must be local
      * Click on tooltip to go to textual report bookmarks
* Put the chart at end of the html event file. Under a 'View Execution Trace' format
  - FUTURE: Consider other types of nvd3.org (Pie chart for Pass/Fail distribution & Coverage for e.g.)
*/
