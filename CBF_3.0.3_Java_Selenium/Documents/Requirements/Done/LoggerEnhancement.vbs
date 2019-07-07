'Requirement
'-----------
'1.Enhance current Logger functionality to track trace and error logs separately using dual log writer
'2.CSV files for trace and error logs with which logs can be analysed in a better way.


'Specifications
'--------------
'Usage of log4j framework for logging details at different levels like trace,debug,error,warn etc
'Existing Logger class in the framework should be redesigned in order to use log4j methods.

'Logger class
'Rename Logger to LogUtils to avoid conflicts between log4j Logger and framework Logger
'LogUtils will instantiate the log4j Logger class by passing the class name for which the logger needs to be initialised.
'trace, error, debug methods in LogUtils will inturn call log4j Logger's methods for trace, error, debug etc..




