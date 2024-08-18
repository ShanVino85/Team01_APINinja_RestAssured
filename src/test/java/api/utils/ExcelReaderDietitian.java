package api.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderDietitian {


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
