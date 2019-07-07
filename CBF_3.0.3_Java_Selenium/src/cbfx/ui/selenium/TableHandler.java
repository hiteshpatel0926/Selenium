/******************************************************************************
$Id : TableHandler.java 12/23/2016 4:09:01 PM
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

package cbfx.ui.selenium;

import static cbf.engine.TestResultLogger.handleError;

import java.util.List;

import org.apache.commons.collections.collection.TransformedCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cbf.utils.StringUtils;

/**
 * Selenium wrapper to handle table related functionalities
 * 
 *
 */
public class TableHandler {

	/**
	 * For assigning driver instance to the class
	 */

	public TableHandler(WebUIDriver driver) {
		this.driver = driver;
	}

	private WebElement getWebElement(String elementName) {
		WebElement tableElement = null;
		try {
			tableElement = driver.findElement(elementName);
		} catch (NullPointerException e) {
			handleError("driver object is null", e);
		} catch (Exception e) {
			handleError("TableHandler: Get WebElement: " + elementName, e);
		}
		return tableElement;
	}

	/**
	 * For getting total number of columns in table
	 * 
	 * @param elementName
	 *            Element name for table locator
	 * @return number of columns
	 */

	public int getColumnCount(String elementName) {
		int colCount = 0;
		try {
			WebElement element = getWebElement(elementName);
			if (element != null)
				colCount = element.findElements(By.tagName("td")).size();
		} catch (NullPointerException e) {
			handleError("get elements returns null", e);
		} catch (Exception e) {
			handleError("TableHandler: Get Column Count for: " + elementName, e);
		}
		return colCount;
	}

	/**
	 * For getting total number of rows in table
	 * 
	 * @param elementName
	 *            Element name for table locator
	 * @return number of rows
	 */
	public int getRowCount(String elementName) {
		int rowCount = 0;
		try {
			WebElement element = getWebElement(elementName);
			if (element != null)
				rowCount = element.findElements(By.tagName("tr")).size();
		} catch (NullPointerException e) {
			handleError("get web elements returns null", e);
		} catch (Exception e) {
			handleError("TableHandler: Get row count for: " + elementName, e);
		}
		return rowCount;
	}

	/**
	 * For getting total number of columns for particular row in table
	 * 
	 * @param elementName
	 *            table element name
	 * @return number of columns in a row
	 */

	public int getColumnCountOfRow(String elementName) {
		WebElement tableElement = getWebElement(elementName);
		if (tableElement != null) {
			List<WebElement> tdCollection = tableElement.findElement(By.tagName("tr")).findElements(By.tagName("td"));
			try {
				if (tdCollection.isEmpty()) {
					tdCollection = tableElement.findElement(By.tagName("tr")).findElements(By.tagName("th"));
				}
			} catch (NullPointerException e) {
				handleError("NullPointerException handled", e);
			} catch (Exception e) {
				handleError("TableHandler: Get Column Count Of Row for: " + tableElement, e);
			}
			return tdCollection.size();
		}
		return 0;
	}

	/**
	 * For getting an object of the table by matching the name with given text
	 * 
	 * @param textTosearch
	 *            Name of the table you want to search
	 * @return Object of table
	 */
	public WebElement getTableObjectByText(String textTosearch) {
		List<WebElement> tablecollection = webDr.findElements(By.tagName("table"));
		int finalIndex = 0;
		try {
			for (int i = 0; i < tablecollection.size(); i++) {
				if (tablecollection.get(i).getText().contains(textTosearch)) {
					finalIndex = i;
					break;
				}
				if (i == tablecollection.size() - 1) {
					handleError("Table does not exist", tablecollection);
				}
			}
		} catch (Exception e) {
			handleError("TableHandler: Get Table Object By Text: " + textTosearch, e);
		}
		return tablecollection.get(finalIndex);
	}

	/**
	 * For getting data of particular cell
	 * 
	 * @param elementName
	 *            Element of the table
	 * @param row
	 *            Row number
	 * @param col
	 *            Column number
	 * @return data of that particular cell
	 */

	public String getCellData(String elementName, int row, int col) {
		WebElement tableElement = getWebElement(elementName);
		List<WebElement> trCollection = null;
		if (tableElement != null)
			trCollection = tableElement.findElements(By.tagName("tr"));
		int rowNum = 0;
		try {
			for (WebElement trElement : trCollection) {

				if (rowNum == row) {
					List<WebElement> tdCollection = trElement.findElements(By.tagName("td"));

					if (tdCollection.isEmpty()) {
						tdCollection = trElement.findElements(By.tagName("th"));
					}
					return tdCollection.get(col).getText();
				}
				rowNum++;
			}
		} catch (NullPointerException e) {
			handleError("in get call data method NullPointerException handled", e);
		} catch (Exception e) {
			handleError("TableHandler:Error in Get cell data for: " + tableElement, e);
		}
		return null;
	}

	/**
	 * For getting data of particular cell
	 * 
	 * @param elementName
	 *            Element of the table
	 * @param row
	 *            Row number
	 * @param colName
	 *            Column name
	 * @return data of that particular cell
	 */

	public String getCellData(String elementName, int row, String colName) {
		WebElement tableElement = getWebElement(elementName);
		if (tableElement != null) {
			List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));
			int rowNum = 0;
			int colNum = 0;
			try {
				for (WebElement trElement : trCollection) {
					if (rowNum == 0) {
						List<WebElement> tdCol = trElement.findElements(By.tagName("td"));
						if (tdCol.isEmpty()) {
							tdCol = trElement.findElements(By.tagName("th"));
						}
						for (int i = 0; i < tdCol.size(); i++) {
							if (tdCol.get(i).getText().contains(colName)) {
								colNum = i;
								break;
							}
						}
					}

					if (rowNum == row) {
						List<WebElement> tdCollection = trElement.findElements(By.tagName("td"));
						return tdCollection.get(colNum).getText();
					}
					rowNum++;
				}
			} catch (NullPointerException e) {
				handleError("cell data is not found", e);
			} catch (Exception e) {
				handleError("TableHandler:Exception in Get cell data for: " + tableElement, e);
			}
		}
		return null;
	}

	/**
	 * For getting data of particular cell
	 * 
	 * @param elementName
	 *            Element of the table
	 * @param rowName
	 *            Row name
	 * @param colName
	 *            Column name
	 * @return data of that particular cell
	 */
	public String getCellData(String elementName, String rowName, String colName) {

		WebElement tableElement = getWebElement(elementName);
		if (tableElement != null) {
			List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));
			int rowNum = 0;
			int colNum = 0;
			try {
				for (WebElement trElement : trCollection) {
					if (rowNum == 0) {
						List<WebElement> tdCol = trElement.findElements(By.tagName("td"));

						for (int i = 0; i < tdCol.size(); i++) {
							if (tdCol.get(i).getText().contains(colName)) {
								colNum = i;
								break;
							}
						}
					}
					List<WebElement> tdCol = trElement.findElements(By.tagName("td"));
					if (tdCol.get(0).getText().contains(rowName)) {
						return tdCol.get(colNum).getText();
					}
					rowNum++;
				}
			} catch (NullPointerException e) {
				handleError("get cell data returns null", e);
			} catch (Exception e) {
				handleError("TableHandler:Error Get cell data for: " + tableElement, e);
			}
		}
		return null;

	}

	/**
	 * To retrieve the data of a cell in dynamic table
	 * 
	 * @param elementName
	 *            table element
	 * @param searchName
	 *            unique text to identify the corresponding cell and the row number
	 * @param columnNumber
	 *            column number
	 * @return cell data
	 */
	public String getRelativeCellData(String elementName, String searchName, int columnNumber) {
		int rowCount = 0;

		WebElement tableElement = getWebElement(elementName);
		if (tableElement != null) {
			List<WebElement> allRows = tableElement.findElements(By.tagName("tr"));
			try {
				for (WebElement row : allRows) {

					List<WebElement> cells = row.findElements(By.tagName("td"));
					for (WebElement cell : cells) {
						if (cell.getText().equals(searchName)) {
							return getCellData(elementName, rowCount, columnNumber);
						}

					}
					rowCount++;
				}
			} catch (NullPointerException e) {
				handleError("Error in getRelativeCellData ", e);
			} catch (Exception e) {
				handleError("TableHandler: Get Relative Cell Data for: " + tableElement, e);
			}
		}
		return null;
	}

	/**
	 * To retrieve the data of a cell in dynamic table and click on it
	 * 
	 * @param elementName
	 *            Element name for table locator
	 * 
	 * @return Number of rows
	 */
	public String findRelativeCellAndClick(String elementName, String searchName, int columnNumber, String text) {
		int rowCount = 0;
		String cellData = null;

		WebElement tableElement = getWebElement(elementName);
		if (tableElement != null) {
			List<WebElement> allRows = tableElement.findElements(By.tagName("tr"));
			try {
				for (WebElement row : allRows) {
					List<WebElement> cells = row.findElements(By.tagName("td"));
					for (WebElement cell : cells) {
						if (cell.getText().equals(searchName)) {
							cellData = getCellData(elementName, rowCount, columnNumber);
							if (cellData.equals(text)) {
								clickCell(cell);
							}
							break;
						}
					}
					rowCount++;
					if (cellData != null) {
						break;
					}
				}
			} catch (NullPointerException e) {
				handleError("Error in findRelativeCellAndClick", e);
			} catch (Exception e) {
				handleError("TableHandler: Find Relative CellAndClick: " + elementName, e);
			}
		}
		return cellData;

	}

	private void clickCell(WebElement element) {
		element.click();
	}

	/**
	 * Overriding toString() method to return TableHandler format string
	 */
	@Override
	public String toString() {
		return StringUtils.mapString(this);
	}

	private WebDriver webDr;
	private WebUIDriver driver;
}
