package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	XSSFSheet sheet = null;
	FileInputStream file = null;
	XSSFWorkbook workbook  = null;
	
	public static List<List<Object>> GetExcelTableInto2DArrayListString(String excelFile,String sheetName){

		List<List<Object>> OUT = new ArrayList<List<Object>>() ;
		
	        try {
				File myFile = new File(excelFile); 
				FileInputStream fis = null;
				try {
				    fis = new FileInputStream(myFile);
				} catch (FileNotFoundException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				} 

				// Finds the workbook instance for XLSX file 
				XSSFWorkbook myWorkBook = null;
				try {
				    myWorkBook = new XSSFWorkbook (fis);
				} catch (IOException e) {
				    e.printStackTrace();
				} 

				// Return first sheet from the XLSX workbook 
				XSSFSheet mySheet = myWorkBook.getSheet(sheetName);

				// Get iterator to all the rows in current sheet 
				Iterator<Row> rowIterator = mySheet.iterator(); 

				// Traversing over each row of XLSX file 
				int count=1;
				while (rowIterator.hasNext()) { 
				    Row row = rowIterator.next();
				    List<Object> InnerArray = new ArrayList();
				// For each row, iterate through each columns 
				    Iterator<Cell> cellIterator = row.cellIterator(); 

				    while (cellIterator.hasNext()) {

				        Cell cell = cellIterator.next(); 

				        switch (cell.getCellType()) { 
				        case Cell.CELL_TYPE_STRING: 
				            String c = cell.getStringCellValue();
				            InnerArray.add(c);
				            break; 
				        case Cell.CELL_TYPE_NUMERIC: 
				            int n = (int) cell.getNumericCellValue();
				            InnerArray.add(String.valueOf(n));
				            break; 
				        case Cell.CELL_TYPE_BOOLEAN:
				            boolean b = cell.getBooleanCellValue();
				            InnerArray.add(String.valueOf(b));
				        break; 
				        default : 
				            } 
				        }
				    OUT.add(InnerArray);
				    count++; 
				    }
			} catch (Exception e) {
				Util.Failed("Exception caught while reading data from excel "+e.getMessage());
			}

	    return OUT;
	}


}
