package cbfx.factory.report;

public class XBTReporter implements ResultReporter, TestResultTracker.Observer  {
	/**
	 * Constructor to initialize reporter related parameters
	 * 
	 * @param params
	 *            map containing parameters
	 */
	public XBTReporter(Map params) {
		this.params = params;
		outputPath = (String) params.get("outputPath");
		if (!(FileUtils.makeFolder(outoutPath))) {
			logger.handleError("Cant create/access html reports folder; these will not be generated: " + outputPath);
		}

    setupFolder(outputPath);

    this.dataStream = outputPath + "/data.js";
  }

  public void finalize() {
    this.dataStream.close();
  }

  public Observer open(Map headers) {
    write("headers", headers);
    return this;
  }
    
  public void close(Observer o) {
  }

  public void start(**) {
    write("start", <build data>)
  }

  public void finish(**) {
    write("finish", <build data>)
  }

  public void log(**) {
    write("log", <build data>)
  }

	public String toString() {
		return StringUtils.mapString(this, params);

	}

  /*
  synchronized since all items are writing to it
  */
  private synchronized void write(String type, Map data) {
    dataStream.write("addLog(" + type + ", " + toString(data) + ");\n");
  }

  private void setupFolder(String folder) {
    tplFolder = ResourcePaths.getLabResource(params.get("templateFolder"), "");
    if (tplFolder doesnt exist)
      logger.handleError("Template folder configuration", parameters);
    FileUtils.copyFolder(tplFolder, folder);  // copy the entire folder structure!!
  }

	private String outputPath;
	private Map params;
	private LogUtils logger = new LogUtils(this);
}
