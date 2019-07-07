/*
Background
----------
1. WebUIDriver suffer from multiple issues:
* Violates single-responsibility by doing several things
  - wraps over over selenium calls
  - implements a full set of UI keywords including sendKeys, killProcess
* Doesnt quite implement a full set of keyword
  - Some of these delegated to TableHandler
* Calls failed in case of issues (sometimes); doesnt call passed ever; doesnt handle error mostly.
  - <-- CafeNext generators take care of ensuring appropriate logging of events

2. It will be good to streamline it so that:
* Its usage is clean and standard whether from CBF or CAFENext <-- keyword layer
* Step definitions layer for BDD

Considerations
--------------
1. WebUIDriver is a pure library layer.
A separate keyword layer will implement keyword model.

2. Usage:
* The keyword parameters are not uniform (target, value).
  There are keywords at browser level, frame level.
  So, each keyword is mapped to a public method of Keyword.java
  It will have complete result logging etc.
* WebUIDriver is mapped to a generic wrapper library without TestResultLogging.

THe mappings are specified in keywords.xlsx
*/
