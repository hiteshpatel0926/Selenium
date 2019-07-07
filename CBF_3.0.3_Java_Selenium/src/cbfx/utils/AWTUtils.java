/******************************************************************************
$Id : AWTUtils.java 12/23/2016 4:09:05 PM
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import cbf.utils.LogUtils;

public class AWTUtils {
  private AWTUtils() {}

	public static void sendKey(String keyEvent) {
    logger.trace("sendKey", keyEvent);
		try {
			Robot key = new Robot();
			if ("enter".equalsIgnoreCase(keyEvent)) {
				key.keyPress(KeyEvent.VK_ENTER);
				key.keyRelease(KeyEvent.VK_ENTER);
				return;
			}
			if ("tab".equalsIgnoreCase(keyEvent)) {
				key.keyPress(KeyEvent.VK_TAB);
				key.keyRelease(KeyEvent.VK_TAB);
				return;
			}
			if ("F5".equalsIgnoreCase(keyEvent)) {
				key.keyPress(KeyEvent.VK_F5);
				key.keyRelease(KeyEvent.VK_F5);
				return;
			}

			if ("home".equalsIgnoreCase(keyEvent)) {
				key.keyPress(KeyEvent.VK_HOME);
				key.keyRelease(KeyEvent.VK_HOME);
				return;
			}

			if ("delete".equalsIgnoreCase(keyEvent)) {
				key.keyPress(KeyEvent.VK_DELETE);
				key.keyRelease(KeyEvent.VK_DELETE);
				return;
			}
		} catch (AWTException e) {
			logger.handleError("Error caused while clicking on '", keyEvent, e);
		}
	}

	public static void sendKeys(String keys) {
    logger.trace("sendKeys", keys);

		for (int i = 0; i < keys.length(); i++) {
			char c = keys.charAt(i);
			Robot robot;
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_NUMPAD0);
				robot.keyRelease(KeyEvent.VK_NUMPAD0);
				String altCode = Integer.toString(c);
				for (int j = 0; j < altCode.length(); j++) {
					c = (char) (altCode.charAt(j) + '0');
					robot.keyPress(c);
					robot.keyRelease(c);
				}
				robot.keyRelease(KeyEvent.VK_ALT);
			} catch (AWTException e) {
				logger.handleError("Unable to send keys ", keys, e);
			}
		}
	}

	private static LogUtils logger = new LogUtils(AWTUtils.class);
}
