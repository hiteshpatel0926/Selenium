/******************************************************************************
$Id : LogUtils.java 12/23/2016 4:08:42 PM
Copyright © 2016 Capgemini Group of companies. All rights reserved
(Subject to Limited Distribution and Restricted Disclosure Only.)
THIS SOURCE FILE MAY CONTAIN INFORMATION WHICH IS THE PROPRIETARY
INFORMATION OF CAPGEMINI GROUP OF COMPANIES AND IS INTENDED FOR USE
ONLY BY THE ENTITY WHO IS ENTITLED TO AND MAY CONTAIN
INFORMATION THAT IS PRIVILEGED, CONFIDENTIAL, OR EXEMPT FROM
DISCLOSURE UNDER APPLICABLE LAW.
YOUR ACCESS TO THIS SOURCE FILE IS GOVERNED BY THE TERMS AND
CONDITIONS OF AN AGREEMENT BETWEEN YOU AND CAPGEMINI GROUP OF COMPANIES.
The USE, DISCLOSURE REPRODUCTION OR TRANSFER OF THIS PROGRAM IS
RESTRICTED AS SET FORTH THEREIN.
******************************************************************************/

package cbf.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 
 * Utility provides functionalities related to logging and error handling Levels
 * of logging: debug|trace|details|warning|error
 * 
 * Logs utilities accept varargs of objects. StringUtils is used to stringify.
 * 
 * HandleError if first vararg (after text msg) is an Exception, it is treated
 * as root cause
 */
public class LogUtils {

	/**
	 * Logger for a given owner and specific logger
	 * @param owner
	 *            owner info is always logged with each log call as context
   * @param logger
   *            name
	 */
	public LogUtils(Object owner, String loggerName) {
		this.owner = owner;
    this.name = loggerName;
    logger = getLogger(loggerName);
	}

	/**
	 * Overloaded constructor to initialize exception owner
	 * 
	 * @param owner
	 *            value of owner
	 */
	public LogUtils(Object owner) {
    Class clazz = callerClass();
    LogUtils(owner, (clazz == null? null: toplevelClass(clazz).getName()));
	}

	/**
	 * Default constructor
	 */
	public LogUtils() {
		this(null);
	}

	/**
	 * Initializes the configuration file
	 * 
	 * @param configFile
	 *            configuration file
	 */
	public static void initialize(String configFile) {
		if (logInitialized == false) {
			try {
				DOMConfigurator.configure(configFile);
			} catch (Exception e) {
				handleError("Error in loading configuration", configFile, e);
			}
			logInitialized = true;
		}
	}

	/**
	 * Logs arguments as debug
	 * 
	 * @param varargs
	 *            object of arguments
	 */
	public void debug(Object... varargs) {
		logger.debug(StringUtils.toString(owner) + DELIM + toString(varargs));
	}

	/**
	 * Logs arguments as trace
	 * 
	 * @param varargs
	 *            object of arguments
	 */
	public void trace(Object... varargs) {
		logger.trace(StringUtils.toString(owner) + DELIM + toString(varargs));
	}

	/**
	 * Logs arguments as detail
	 * 
	 * @param varargs
	 *            object of arguments
	 */
	public void detail(Object... varargs) {
		logger.info(StringUtils.toString(owner) + DELIM + toString(varargs));
	}

	/**
	 * Logs arguments as warning
	 * 
	 * @param varargs
	 *            object of arguments
	 */
	public void warning(Object... varargs) {
		logger.warn(StringUtils.toString(owner) + DELIM + toString(varargs));
	}

	/**
	 * Logs arguments as error
	 * 
	 * @param varargs
	 *            object of arguments
	 */
	public void error(Object... varargs) {
		logger.error(StringUtils.toString(owner) + DELIM + toString(varargs));

	}

	/**
	 * Handles error and logs arguments
	 * 
	 * @param msg
	 *            text message for exception
	 * @param varargs
	 *            object of arguments
	 */
	public void handleError(String msg, Object... varargs) {
		Exception rootCause = null;
		if (varargs.length != 0) {
			if (varargs[0] instanceof Exception) {
				rootCause = (Exception) varargs[0];
			}
		}
		String text = "Error: " + msg + DELIM + toString(varargs);
		error(text);

		cbf.engine.TestResultLogger.error(varargs[0].toString(), "",
				varargs[2].toString());
		// Do **not** repeatedly log framework exception's
		if (rootCause != null && !(rootCause instanceof FrameworkException)) {
			error(msg, rootCause.getStackTrace());
		}

		throw new FrameworkException(text, rootCause);
	}

	/**
	 * TODO: public void handleError(String msg, Exception exception, Object...
	 * varargs) Useful in highlighting root cause
	 */

	/**
	 * Rethrow an exception after logging the error
	 * 
	 * @param msg
	 *            text message for exception
	 * @param exception
	 *            object of FrameworkException
	 */
	public void handleError(String msg, FrameworkException exception) {
		error("Error: " + msg + DELIM + "Re-throwing exception");
		throw exception;
	}

	private static final String DELIM = "|";

	private static String toString(Object... varargs) {
		String s = "", delim = "";
		for (Object obj : varargs) {
			s += delim + StringUtils.toString(obj);
			delim = DELIM;
		}
		return s;
	}

	/**
	 * Returns Log Utils format string
	 * 
	 */
	public String toString() {
		return StringUtils.mapString(this, loggerName);

	}

  private static final String myClassName = LogUtils.class.getName();
  private Class callerClass() {
    StackTraceElement caller;
    try {
      StackTraceElement[] stack = Throwable.stackTraceElements();
      for (StackTraceElement caller: stack) {
        if (caller.getClassName() == myClassName)
          continue;

        return Class.forName(caller.getClassName());
      }
    } catch(Exception e) {
      System.err.println("Warning: cant determine caller; logged attributes might be wrong" + e.getMessage());
    }
    return null;
  }

  /*
    If caller is a nested class, return the top level class.
    This means that a nested class will always use the logger of the top level.
    This is required to support anonymous/local classes etc.
    TODO: This is a limitation, but there is no clean way of removing it.
  */
  private Class toplevelClass(Class clazz) {
    while(true) {
      Class outer = clazz.enclosingClass();
      if (outer == null)
        return clazz;
      clazz = outer;
    }
  }

  private Logger getLogger(String name) {
    Logger l;
    try {
      if (name != null)
        l = Logger.getLogger(name);
    } catch(Exception e) {
      e.printStacktrace(System.err);
    }
    if (l != null)
      return l;

    return Logger.getRootLogger();
  }

	private Logger logger;
	private static boolean logInitialized = false;
	private Object owner;
  private String name;
}
