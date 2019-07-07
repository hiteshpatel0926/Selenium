/*
Requirements
------------
	Provide facility in Component based framework to create testset,testcase,testdata and uiMap files in CSV format by creating plugin of CSV library.
write respective class libraries for CSV Plugin.

Considerations
--------------
In component based framework designing, Testset,Testcase,Testdata and uiMap files has multiple sheets.
The following are considerations to make testset,testcase,testdata and uiMap files in CSV format.
	1. TestSet file has single sheet. It can be accessed and fetch data same as Excel workbook.
	2. uiMap file has multiple sheets as moduledrivers are more than one.
		Implementation of uiMap file in CSV Format has different approaches.
		Discussions needed regarding this---?.
		See Specifications(#1) below for approaches .
	3.Testcase file has multiple sheets like steps,Inline data sheet,variables,Iterations and References.
		we need to discuss on the impact of Iterations,References and variables to create test case file in CSV format---?.
		See Specifications(#2) below for approaches.
	4.Testcase data file has multiple sheets for different module drivers and for References.
		Implementation of test case data file in CSV format has different approaches.
		we need to discuss on the implementation appraoch and impact on references---?.
		See Specifications(#3) below for approaches.
	5.Create class library with following classes for CSV plugin.
		--CSVAccess,DTAccess,CSVDataFactory,CSVDeserializer,CSVTestCaseFactory,CSVTestSetFactory

Specifications
--------------
1.In uiMap file each Module driver has one sheet and in each sheet has object identification details i.e, Locators.
	In CSV format, we can create only one sheet.
Approach 1: create Single uiMap.csv file with one extra column i.e, ModuleCode. we can differentiate the elements with their module code.
Approach 2: create single csv file for each module driver and one file for global elements.

2.Test case file has different types of data like steps,Iterations,References,Inline data and variables.
Approach :crate seperate csv files for each data. file names could be like TestCaseName_Iterations,TestCaseName_References,..etc

3.Test Case Data file has different sheets for different module drivers.
Approach 1: create TestcaseData.csv file with one extra column i.e, ComponentCode. we can differentaite testcase data by their component code.
Regarding references we have to discuss.
Approach 2: create seperate csv file for each component and one file for references.

4. Create CsvDataFactory similary to ExcelDataFactory
RowSelector implmentation should be here.
*/
	/**
	 * 
	 * Implements RowSelector interface and selects row by ID
	 * 
	 */
	public static class RowSelectorByRowId implements CsvAccess.RowSelector {
		private String rowId;

		/**
		 * Constructor to initialize rowId
		 * 
		 * @param rowId
		 *            contains rowId value
		 */
		public RowSelectorByRowId(String rowId) {
			this.rowId = rowId;
		}

		/**
		 * Returns true/false depending on row value
		 * 
		 * @param row
		 *            Map of row values
		 * @param rowIx
		 *            index of row
		 * @return row exists or not
		 */
		public boolean select(Map row, int rowIx) {
			if (rowId.equals("")) {
				return true;
			} else {
				return rowId.equals((String) row.get("_rowId"));
			}
		}
	}
