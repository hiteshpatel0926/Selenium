package cbf.testaccess;

/*
Sample change to ExcelTestSetFactory. Same for all others.
*/
public class ExcelTestSetFactory extends BaseTestSetFactory {

	/**
	 * Plugin constructor
	 * @param params
	 */
	public BaseExcelTestSetFactory(Map params) {
    super(params);
  }

	/**
	 * Main method which returns the testset for a given options
	 * @param tsSpecs: contains testSetFile/testSetSheet names
   * TODO: defaults
	 */
	public TestSet getTestSet(Map tsParams) {
    Map params = mergeWithParams(tsParams);
 
		final List<Map> instanceData = readExcelFile(params);

    return new TestSet() {
	    public int testInstanceCount() {
		    return instanceData.size();
	    }

	    public TestInstance testInstance(final int ix) {
        final Map data = instanceData.get(ix);
        return new BaseTestInstance(data) {
          public String instanceName() {
					  return data.get("TestCase Name");
          };
        }
      }
    }
  }

  private List<Map> readExcelFile(Map params) {
    String fileName, sheetName;
    try {
      fileName = labFolder + "/" + (String) params.get("testSetFile");
      sheetName = (String) params.get("testSetSheet");
    } catch(ClassCastException e) {
      logger.handleError("Invalid excel file/sheet configuration", params, e);
    }

    try {
		  DTAccess dtAccess = new DTAccess(fileName);
			return dtAccess.readSelectedRows(sheetName, FILTER_SELECT_YN);
		}catch(Exception e){
			logger.handleError("Reading testset file", fileName, sheetName, e);
		}

    return null;
  }

  private static final DTAccess.RowSelector FILTER_SELECTYN = 
    new DTAccess.RowSelector() {
    public boolean select(Map row, int rowIx) {
				return Utils.string2Bool(((String) row.get("SelectYN")), true); <-- add a default value argument to string2bool
    }
  };
}
