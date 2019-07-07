/**
 * Main class for Running Engine from Harness. Defines the runTestCase()
 * function.
 * 	
 */
public class Harness {

	/**
	 * Initializes logs, reporters in the results folder
	 * 
	 * @param runMap
	 * @param runName
	 */
	public static void initialize(String configFilePath, Map<String, String>runOptions) {
		config = loadConfiguration(configFilePath, runOptions);
 
		initializeResourcePaths(config);

		initializeLogs(ResourcePaths.runHome);

		initializeReporters();
  }

  public static Configuration config() {
    return config;
  }

  public static finalize() {
		reportingManager.close(); // CHECKME: this could be an issue
  }

	/**
	 * Instantiate a harness with a name
	 * This will not use any global variables other than reports, logs and Config
   * Hence thread-safe
	 * @param name
	 */
	public Harness(String name) {
    this.name = name;
	}

	/**
	 * Alternate constructor.
	 */
	public Harness() {
		this("Dummy");
	}
	
	public AppDriver loadAppDriver(Map runArgs) {
		try {
			return new AppLoader().loadApp(config, runArgs);  // new change
		} catch (Exception e) {
			logger.handleError("Failed to LoadAppDriver ", e);
		}
	}

	public Engine loadEngine(String runName) {
		try {
			TestResultTracker tracker = new TestResultTracker(reportingManager.getReporters());
			return new Engine(runName, tracker);
		} catch (Exception e) {
			logger.handleError("Failed to load engine: ", e);
		}
	}

	private static Configuration loadConfiguration(String configFileName, Map<String, String>runOptions) {
    Configuration config;
		try {
			config = new Configuration(configFileName);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Configuration file not exist "+ configFileName+e);
			logger.handleError("Configuration file not exist ", configFileName, e);
		} // CHECKME: catch(IOException e) // other exception

    if (runOptions != null
      substitute(config, runOptions);
    return config;
	}

	private void substitute(Configuration config, Map<String, String> runOptions) {
		config.set((LinkedHashMap) new Substitutor(runOptions).substitute(config
				.getAllProperties()));
		// TODO : Substitute Map is returning as a map while config is used
		// everywhere that is an object of Configuration class.
	}

	private static void initializeResourcePaths(Configuration config) {
		String workHome = null,runHome = null, autoHome = null;
		try{
			workHome = (String) config.get("WorkHome");
			autoHome = (String) config.get("AutoHome");
			runHome = (String) config.get("RunHome");
		}catch(Exception e){
			System.out.println("Please check the config files for workHome, autoHome and runHome");
		}
		
		logger.trace("AutoHome:", autoHome);
		if (autoHome == null || autoHome.equals("")) {
			logger.handleError("AUTO_HOME is invalid:", autoHome);
			return;
		} // TODO: handle invalid folder. Defaulting possibilities

		if (runHome == null || !(FileUtils.makeFolder(runHome))) {
			logger.handleError("Can't create/access run home folder: ", runHome);

		}

		if (workHome == null || !(FileUtils.makeFolder(workHome))) {
			logger.handleError("Can't access work home folder: ", workHome);
		}

		ResourcePaths.getInstance(autoHome, workHome, runHome);
	}

	private static void initializeLogs(String resultsFolder) {
		logger.trace("InitializeLogs(" + resultsFolder + ")");
		System.setProperty("rootPath", resultsFolder);
		logger.initialize(ResourcePaths.getInstance()
				.getFrameworkResource("Resources", "logConfig.xml") );
	}

	private static void initializeReporters() {
		// Add desired Reporters using ManageReporters
		Object reporterObj = config.get("ResultReporter");

		reportingManager = new ReportingManager(reporterObj, config);
	}

	private LogUtils logger = new LogUtils(this);

	private static Configuration config;
	private static ReportingManager reportingManager;
}
