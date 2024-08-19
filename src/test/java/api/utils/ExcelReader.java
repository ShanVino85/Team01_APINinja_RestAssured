package api.utils;

//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public FileInputStream fi;
	//public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	String data;
	String columnHeaderName;
	
	public static String excelFilePath = "./src/test/resources/Team01_APINinja_Data.xlsx";
	
	
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(excelFilePath);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try{
			data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	
public static Map<String, String> getTestData(String sheetname, int rownum) {
		
		Map<String, String> ioMap1 = new HashMap<>();
		ioMap1.clear();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ExcelReader.class.getResourceAsStream("/Team01_APINinja_Data.xlsx"));
			XSSFSheet sheet = workbook.getSheet(sheetname);
			XSSFRow headerRow = sheet.getRow(0);
			XSSFRow testRow = sheet.getRow(rownum);
			DataFormatter formatter = new DataFormatter();  
			//for (int j=1;j<=sheet.getLastRowNum();j++){
				//testRow = sheet.getRow(j);
			for(int i=0;i<headerRow.getLastCellNum();i++) {
			
				String colHeader = headerRow.getCell(i).getStringCellValue();
				
				String colValue = testRow.getCell(i) != null ? formatter.formatCellValue(testRow.getCell(i)):null;
			
				//String colValue = testRow.getCell(i) != null ? testRow.getCell(i).getStringCellValue():null;
			
				ioMap1.put(colHeader, colValue);
			
			  }
			//}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return ioMap1;
	}
	
	
	

	
}
