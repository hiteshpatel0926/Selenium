/**
 * Manages all the plugins related functions
 */
public class PluginManager {
	/**
	public static PluginManager getInstance(): remove this.
  Instead Harness.initialize() will create and provide a singleton.
  class Harness {
  static void initialize() {
		String	masterConfigFileName = ResourcePaths.getInstance()
					.getFrameworkResource("Resources", "MasterConfig.xml");
    pluginManager = new PluginManager(masterConfigFileName);
  }
  static PluginManager pluginManager() {
    return pluginManager;
  }

  All calls to PluginManager.xxx() will now be Harness.pluginManager().xxx()
  */

	/**
	 * Returns instance of specified Plugin
	 * 
	 * @param usageMap
	 *            parameters of plugin
	 * @return instance of Plugin
	 */

	public Object getPlugin(Map usageMap) { <-- remove static
		try {
			return getPlugin((String) usageMap.get("plugin"),
					(Map) usageMap.get("parameters"));
			} catch (ClassCastException e) {
			logger.handleError(
					"Parameters are not proper in user config file ", e);
		}
		return null;
	}

	/**
	 * Returns instance of specified Plugin
	 * 
	 * @param pluginName
	 *            of Plugin
	 * @param usageParams
	 *            parameters of plugin
	 * @return instance of Plugin
	 */
	public Object getPlugin(String pluginName,
			Map<String, Object> usageParams) { <-- remove static
		return parsePlugin(pluginName, usageParams);
	}

	public PluginManager(String masterConfigFileName) { <-- make it public
		try {
			masterConfig = new Configuration(masterConfigFileName);
		} catch (FileNotFoundException e) {
			logger.handleError("Plugin Configuration file not exist", masterConfigFileName,
					e);
		}
	}

	private Object parsePlugin(String pluginName,
			Map<String, Object> usageParams) {
		String className = null;
		Map<String, Object> masterParamsMap = new HashMap<String, Object>();

		try {
			Map<String, Object> masterPluginMap = (Map<String, Object>) masterConfig
					.get(pluginName);
			if (masterPluginMap == null) {
				logger.handleError(
						"Plugin details are not mentioned in config files",
						pluginName, usageParams);
			}
			className = (String) masterPluginMap.get("classname");

			masterParamsMap = (Map<String, Object>) masterPluginMap
					.get("parameters");

		} catch (ClassCastException e) {
			logger.handleError("Class Cast Exception ", e);
			return null;
		}

		Map<String, Object> finalMap = mergeMaps(masterParamsMap, usageParams);
		if (finalMap != null) {
			for (String key : finalMap.keySet()) {
				if (finalMap.get(key).equals("TBD")) {
					logger.handleError(key
							+ " value must be specified in user config for ",
							pluginName);
				}
			}
		}

		try {
			return initializePlugin(className, finalMap);
		} catch (FrameworkException fe) {
			logger.handleError("Error in instantiating plugin", pluginName, fe,
					className, finalMap, usageParams);
			return null;
		}
	}

	/**
	 * Initializes the class based on the plugin
	 * 
	 * @param className
	 *            plugin to be initialized
	 * @param finalMap
	 *            parameters related to  plugin
	 * @return class object
	 */
	public static Object initializePlugin(String className, Map finalMap) {
		try {
			return Class.forName(className).getDeclaredConstructor(Map.class)
					.newInstance(finalMap);
		} catch (ClassNotFoundException e) {
			logger.handleError("Class not found ", className, e);
		} catch (NoSuchMethodException e) {
			logger.handleError("No matching constructor found for ", className);
		} catch (Exception e) {
			logger.handleError("Class instantiation error ", className,
					finalMap, e);
		}
		return finalMap;
	}

	private Map<String, Object> mergeMaps(Map masterParamsMap, Map usageParams) {
		Map<String, Object> finalMap;
		if (masterParamsMap == null)
			finalMap = usageParams;

		else {
			finalMap = masterParamsMap;
			if (usageParams != null)
				finalMap.putAll(usageParams);
		}
		return finalMap;
	}

	/**
	 * Returns PluginManager format string
	 */

	public String toString() {
		return StringUtils.mapString(this, masterConfig);
	}

	public String masterConfigFileName;
	private LogUtils logger = new LogUtils(StringUtils.mapString(this, masterConfigFileName);
	private Configuration masterConfig;
}
