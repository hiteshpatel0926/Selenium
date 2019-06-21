package Learnings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReadUsingIterator {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
	    {
			FileInputStream file = new FileInputStream("C:\\Users\\Hiteshkumar Patel\\git\\Selenium\\DemoMaven\\src\\test\\java\\resources\\testdata.xlsx");
		    
	        //Create Workbook instance holding reference to .xlsx file
	        XSSFWorkbook workbook = new XSSFWorkbook(file);

	        //Get first/desired sheet from the workbook
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        //Iterate through each rows one by one
	        Iterator<Row> rowIterator = sheet.iterator();
	        while (rowIterator.hasNext()) 
	        {
	            Row row = rowIterator.next();
	            //For each row, iterate through all the columns
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext()) 
	            {
	                Cell cell = cellIterator.next();
	                //Check the cell type and format accordingly
	                int i=0;
	                int j=0;
	                
	                switch (cell.getCellType()) 
	                {
	                    case NUMERIC:
	                        	System.out.print(cell.getNumericCellValue() + "\t");
	                            break;
	                    case STRING:
	                        System.out.print(cell.getStringCellValue() + "\t");	                                            
	                        break;
	                }
	            }


	            System.out.println("");
	        }

	        
	        file.close();
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        System.out.println("Fail");
	    }
		
		
	}

}
