package Learnings;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//How to read excel files using Apache POI
public class ReadExcelSheet {
	public static void main(String[] args) throws IOException

	{

		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\hiteshpa\\Downloads\\Test.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			System.out.println(cell);
			System.out.println(sheet.getRow(0).getCell(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}