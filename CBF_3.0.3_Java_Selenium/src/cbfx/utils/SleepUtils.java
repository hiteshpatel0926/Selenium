/******************************************************************************
$Id : SleepUtils.java 12/23/2016 4:08:43 PM
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cbf.harness.Harness;
import cbf.utils.LogUtils;
import cbf.utils.StringUtils;

/**
 * 
 * Utility to handle sleep with different time slabs.
 * 
 */
public class SleepUtils {
	/**
	 * Sleep with level of TimeSlab and returns boolean value depending on level
	 * value
	 * 
	 * @param level
	 *            object of TimeSlab enum
	 * @return boolean result
	 */
	public static boolean sleep(TimeSlab level) {
		logger.trace("Sleep:" + level.toString());
		boolean sleep = false;
		int timer = interval(level.getLevel());
		if (timer > 0) {
			try {
				Thread.sleep(timer);
			} catch (Exception e) {
				logger.handleError("Error caught : " + e);
			}
			sleep = true;
		}
		logger.trace("Slept:" + level + "," + timer);
		logger.trace("Slept:" + level + "-" + timer);
		return sleep;
	}

	/**
	 * 
	 * Enum to define different sleep levels
	 * 
	 */
	public enum TimeSlab {
		YIELD("YIELD", 0), LOW("LOW", 1), MEDIUM("MEDIUM", 2), HIGH("HIGH", 3);

		private TimeSlab(String n, int lvl) {
			slabName = n;
			level = lvl;
		}

		/**
		 * Retrieves level of TimeSlab
		 * 
		 * @return TimeSlab level
		 */
		public int getLevel() {
			return level;
		}

		/**
		 * Overridden toString() method of Object class to return name for
		 * specified level
		 */
		@Override
		public String toString() {
			return slabName;
		}

		public final int level;
		public final String slabName;
	}

	/**
	 * Defines interval for sleep
	 * 
	 * @param lvl
	 *            level value for TimeSlab
	 * @return interval value
	 */
	public static int interval(int lvl) {
		int timer = 0;
		List<Integer> timerSlabs = getTimerSlabs();
		if (lvl >= 0) {
			if (lvl <= timerSlabs.size())
				timer = timerSlabs.get(lvl);
			else
				timer = timerSlabs.get(timerSlabs.size() - 1)
						* (lvl - timerSlabs.size() + 1);

		}
		if (timer < 0)
			timer = 0;
		return timer;
	}

  public static int interval(TimeSlab slab) {
    return interval(slab.getLevel());
  }

  private static final TimeSlab[] SLABS = {
		TimeSlab.YIELD,
    TimeSlab.LOW,
    TimeSlab.MEDIUM,
    TimeSlab.HIGH
  };

  public static TimeSlab slabByName(String name) {
		for (TimeSlab slab: SLABS) {
      if (slab.name().equalsIgnoreCase(name))
        return slab;
    }
    return null;
  }

  public static TimeSlab slabByLevel(int level) {
		for (TimeSlab slab: SLABS) {
      if (slab.getLevel() == level)
        return slab;
    }
    return null;
  }

	/**
	 * Retrieves TimeSlab levels and returns List
	 * 
	 * @return List of levels
	 */
	public static List<Integer> getTimerSlabs() {
		if (timerSlabs != null) {
			return timerSlabs;
		}
	  timerSlabs = new ArrayList<>();

		String slabs = (String) Harness.configuration().get("SleepTimerSlabs");
		//if ("" != slabs || slabs != null) 
		if ("" != slabs) {
			if (slabs != null){
			for (String temp : slabs.trim().split(",")) {
				timerSlabs.add(Integer.parseInt(temp));
			}
			}
		}
		
				
		if (timerSlabs.isEmpty()) {// Not specified; use the default
			timerSlabs = Arrays
					.asList(new Integer[] { 500, 3000, 10000, 20000 });
		}
		logger.trace("TimerSlabs Used=" + StringUtils.toString(timerSlabs));
		return timerSlabs;
	}

	/**
	 * Inserts wait
	 * 
	 * @param secs
	 *            Time to sleep(In Seconds)
	 */
	public static void sleep(int secs) {
		try {
			Thread.sleep(secs* 1000L);
		} catch (Exception e) {
			logger.handleError("Error while performing sleep operation", e);
		}
	}

	private SleepUtils() {
	}

	private static LogUtils logger = new LogUtils(SleepUtils.class);
	private static List<Integer> timerSlabs = null;
}
