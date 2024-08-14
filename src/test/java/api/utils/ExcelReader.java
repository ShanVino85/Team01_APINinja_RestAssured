package api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	
}
