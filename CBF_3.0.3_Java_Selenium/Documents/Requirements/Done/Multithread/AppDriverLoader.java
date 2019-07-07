/******************************************************************************
$Id : AppLoader.java 12/23/2016 4:08:20 PM
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

package cbf.harness;

import java.util.HashMap;
import java.util.Map;

import cbf.model.AppDriver;
import cbf.plugin.PluginManager;
import cbf.utils.Configuration;
import cbf.utils.LogUtils;

/*

Replaces the earlier AppLoader

*/
public class AppDriverLoader {
	/**
	 * Loads application driver and returns it's instance
   * Logic: Get AppDriver plugin, insert runArgs under "args" key of plugin
   * and use PluginManager to load the plugin
   *
	 * @param config
	 * @param runArgs 
	 * 
	 * @return AppDriver instance
	 */
	public static AppDriver loadAppDriver(Configuration config, Map runArgs) {
    Map<String, Object> plugin = config.get("AppDriver");
    if (plugin == null) {
      logger.handleError("AppDriver plugin is not configured");
      // return null;
    }

    Map params = (Map)plugin.get("params");
    if (runArgs != null)
      params = cloneWithRunArgs(params, "args", runArgs);

    try {
      return (AppDriver)PluginManager.getPlugin(plugin.get("classname"), params);
    } catch(Exception e) {
      logger.handleError("Loading AppDriver", plugin.get("classname"), e, params);
    }
  }

  private Map cloneWithRunArgs(Map map, String key, Object val) {
    Map nmap = new HashMap();
    nmap.putAll(map);
    nmap.put(key, val);
    return nmap;
  }

  private static Logger logger = new Logger("AppDriverLoader");
}

/*
  Changes from old logic:
  1. Methods are static
  2. UIDriver is supposed to be inside the Configuration parameters
			params.put("UIDrivers", config.get("UIDrivers"));
  3. See WebAppDriver.java for new changes
*/
