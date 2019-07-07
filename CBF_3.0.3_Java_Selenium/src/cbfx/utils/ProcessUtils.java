/******************************************************************************
$Id : ProcessUtils.java 12/23/2016 4:09:05 PM
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
package cbfx.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cbf.utils.LogUtils;

public class ProcessUtils {
  private ProcessUtils() { }

	public static boolean killProcess(String serviceName) {
		if (findProcess(serviceName) == null) // couldnt find
      return false;

		try {
			Runtime.getRuntime().exec("taskkill /f /IM " + serviceName);
		} catch (Exception e) {
			logger.handleError("Killing service", serviceName, e);
		}

    logger.trace("killProcess", serviceName);
    return true;
	}

  /**
   * Returns task line containing serviceName
   **/
  public static String findProcess(String serviceName) {
		String line = null;

		try {
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.contains(serviceName)) {
          break;
				}
			}
			input.close();
		} catch (Exception e) {
			logger.handleError("Reading tasklist", serviceName, e);
		}

    logger.trace("findProcess", serviceName, line);
    return line;
  }

	private static LogUtils logger = new LogUtils(ProcessUtils.class);
}
