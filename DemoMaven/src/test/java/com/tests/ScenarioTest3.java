package com.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ScenarioTest3 extends TestBase {

	@Test
	public void ReadExcelStoreHashMap() {

		HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();
		LinkedHashMap<Integer, List> hashMap = new LinkedHashMap<Integer, List>();

		String sheetName = null;
		// Create an ArrayList to store the data read from excel sheet.
		List sheetData = new ArrayList();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(
					"C:\\Users\\Hiteshkumar Patel\\git\\Selenium\\DemoMaven\\src\\test\\java\\resources\\testdata.xlsx");
			// Create an excel workbook from the file system
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				XSSFSheet sheet = workBook.getSheetAt(i);
				// XSSFSheet sheet = workBook.getSheetAt(0);
				sheetName = workBook.getSheetName(i);

				Iterator rows = sheet.rowIterator();
				while (rows.hasNext()) {
					XSSFRow row = (XSSFRow) rows.next();
					Iterator cells = row.cellIterator();

					List data = new LinkedList();
					while (cells.hasNext()) {
						XSSFCell cell = (XSSFCell) cells.next();
						cell.setCellType(CellType.STRING);
						data.add(cell);
					}
					hashMap.put(row.getRowNum(), data);

					sheetData.add(data);
				}
				outerMap.put(sheetName, hashMap);
				hashMap = new LinkedHashMap<Integer, List>();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		System.out.println(Arrays.asList(outerMap));
	
	}
}