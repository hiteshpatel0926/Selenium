/*
Requirements
------------
1. Improve logging to put as much data into a csv format.
* enables large scale viewing using Excel, filtering etc.
* enables programmatic access for downstream processing and visualization
* can use multiple columns of data, including stacktraces

2. Extend this to VBS and C# as required. Possibly JScript also.

Specifications
--------------
0. In Designer/server end, ruby has implemented this. Replicate that here.
* Java: log4j. See Specifications below
* VBScript: will have to be hand coded. Discuss with me.
* C#: Use log4net, or any other standard Microsoft specific logger.
* JScript: stritti.github.io/log4js and log4javascript.org exists for Javascript. Might not work for Jscript; if so, port from our VBScript logger.

1. Current logger covers most of above, except below:
* Delimiter should be ','; csv encoding should be used for text
* Varargs should be split into separate columns delimited by ','.
* Arrays and Maps should be JSON-coded
* Exceptions should dump stack traces. Stack traces should be moved to the rightmost columns to declutter when seen in Excel. See sample file sampleLog.csv.
* log4j configurations should also be set to ',' delimiter. File size limits should prevent files from growing too big to view in Excel. <-- Designer/server end will work well here

*/

package cbf.utils;

class LogUtils {
  public static final String DELIM = ',';

	public void debug(Object... varargs) {
		logger.debug(buildPayload(varargs));  <-- context is added by toString
	}
  // similarly trace, detail, warning, error, log calls

  public void handleError(String msg, Object... varargs) {
    error(msg, varargs);  // log an error

    // form an error object
		Exception rootCause = null;
		if (varargs.length != 0) {
			if (varargs[0] instanceof Exception) {
				rootCause = (Exception) varargs[0];
			}
		}

    Exception e = new FrameworkException(msg + DELIM + toString(varargs), rootCause);
    error(e); // log it also; TODO: two log entries to be combined to one
    throw e;
  }

	public void handleError(String msg, FrameworkException exception) {
		error(msg, "Rethrowing exception", exception);
		throw exception;
	}

  final static int STACK_TRACE_POS = 12;
  /*
  * build payload for log string
  */
  private static String buildPayload(Object... varargs) {
    int count = varargs.length + 2;
    int payloadSize = ((count > STACK_TRACE_POS)? count: STACK_TRACE_POS)+2;  // upto 2 exception traces logged
    Object[] payload = new Object[payloadSize];
    payload[0] = name;
    payload[1] = owner;
    int ix = 2;
    Exception e = null;
    for (Object arg: varargs) {
      payload[ix++] = arg;
      if (e == null && arg instanceof Exception)
        e = (Exception)arg;
    }
    for (int i=ix; i<payLoadSize; ++i)
      payload[i] = "_";

    if (e != null) {
      payload[payload.length-2] = e.getStackTrace();
      Throwable t = e.getCause();
      if (t != null && !(t instanceof FrameworkException)) {
        payload[payload.length-1] = t.getStackTrace();
      }
    }

    return CSV.encode(payload);
	}
}
