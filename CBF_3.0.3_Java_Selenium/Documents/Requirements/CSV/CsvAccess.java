/******************************************************************************
$Id : CsvAccess.java 12/23/2016 4:08:39 PM
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

/**
 * 
 * Handles all the function related to accessing CSV files
 * 
 */
public class CsvAccess {
	private CsvAccess() {
	}

	/**
	 * 
	 * Abstract row handler for the read interface
   * This will be called repeatedly for each row of the csv file being read
	 * 
	 */
	public interface RowHandler {
		/**
		 * Main method of the interface
		 * @param rowData
		 *            data of 
		 * @param rowIx
		 *            index of row to be accessed
		 * @return if true, indicates to stop reading further rows
		 */
		boolean handleRow(String[] rowData, int rowIx);
	}

	/**
	 * Read a file, and pass each row to rowHandler till it returns true
	 * 
	 * @param fileName
	 *            name of the file
	 * @param rowHandler
	 *            the rowHandler instance
	 * @return number of rows read
	 */
	public static int read(String fileName, RowHandler rowHandler) {
    int ix=0;
    
    try {
    File file=new File(fileName);
	csvReader=new CSVReader(new FileReader(file));
	} catch (FileNotFoundException e) {
		// TODO: Write Logger Statement
		logger.handleError("Invalid CSV file configuration",fileName,e);
	}
    String[] rowArrayToken;
    List<String> row = new ArrayList<String>();
    List<List<String>> rowList=new ArrayList<List<String>>();
    try {
		while ((rowArrayToken=csvReader.readNext())!=null) {
			row=Arrays.asList(rowArrayToken);
			rowList.add(row);
		}
	} catch (IOException e) {
		// TODO Write logger statement
		logger.handleError("Failed to Access the file",fileName,e);
	}
    // For Each Row
    for(List<String> list:rowList){
		String[] rowData = list.toArray(new String[0]);
		
		if(!(rowHandler.handleRow(rowData,ix++)))
			break;
    }
    return ix;
	}

  /**
  * RowHandler implementation for CSV files containing a table of fixed columns
  * First row is expected to be the list of keys.
  **/
  public static abstract class MapHandler implements RowHandler {
		public boolean handleRow(String[] rowData, int rowIx) {
      if (keys == null) {
        keys = rowData;
        return true;
      }

      DataRow row = new DataRow();
      int i=0;
      for (String key: keys) {
        row.put(key, rowData[i++]);
      }
      return handleRow(row, rowIx-1); // start with 0 for index
    }

		/**
		 * Main method of the interface
		 * @param row
		 *            rowData converted to a DataRow
		 * @param rowIx
		 *            index of row. Heading row is not counted.
		 * @return if true, indicates to stop reading further rows
		 */
		abstract boolean handleRow(DataRow row, int rowIx);

    private String[] keys = null;
  }

  public interface RowSelector {
    boolean select(DataRow row);
  }

  /**
  * Read all rows
  **/
  public static List<DataRow> read(String fileName, final RowSelector selector) {
    final List<DataRow> list = new ArrayList<DataRow>();

    MapHandler h = new MapHandler() {
      boolean handleRow(DataRow row, int rowIx) {
        if (selector == null || selector.select(row))
          list.add(row);
        return true;
      }
    };

    read(fileName, h);

    return list;
  }

 /* public static List<DataRow> read(String fileName) {
    return read(fileName, null);
  }
*/
 /* public static interface RowSource {
    
    return null at end
    
    public String[] nextRow(int ix);
  }

  public static int write(String filename, RowSource source) {
    open file

    for (int ix = 0; ; ++ix) {
      String[] row = source.nextRow(ix);
      if (row == null)
        break;
      // write row to file;
    }

    close file;
    return ix;
  }

*/
  /*
  Assumes each datarow has same keys
  */
 /* public static abstract class DataRowSource implements RowSource {
    abstract public DataRow nextDataRow(int ix);

    public String[] nextRow(int ix) {
      switch(ix) {
        case 0:
          firstRow = nextDataRow();
          if (firstRow == null)
            return null;
          return firstRow.keys();
        case 1:
          String[] v = firstRow.values();
          firstRow = null;  // no longer needed
          return v;
      }

      //  default:
      DataRow r = nextDataRow();
      if (r == null)
        return null
      return r.values();
    }

    private DataRow firstRow;
  }*/

  /*public static int write(String filename, final List<DataRow> rows) {
    DataRowSource s = new DataRowSource() {
      abstract public DataRow nextDataRow(int ix) {
        if (ix >= rows.size())
          return null;
        return rows.elementAt(ix);
      }
    }

    return write(filename, s);
  }
*/
	private static LogUtils logger = new LogUtils();
	private static CSVReader csvReader = null;
}
