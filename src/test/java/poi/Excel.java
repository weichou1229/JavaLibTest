package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	
	
	public Excel convert_Excel_to_listMap(String fileSource ,int sheetNo) throws Exception{
		
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(fileSource));
			Sheet sheet = wb.getSheetAt(sheetNo);
			Iterator<Row> rows = sheet.rowIterator();
			List<ExcelColumn> columns = fetchColumns_From_FirstRowOfCells(rows.next().cellIterator());
		} catch (Exception e) {
			throw new Exception("Excel readExcelFromFileSource failure~");
		} 
		return this;
	}
	
	/**
	 * 取第一行ROW的所有CELL為欄位 
	 * @param cells
	 * @return List ExcelColumn(欄位編號,欄位名稱)
	 */
	private List<ExcelColumn> fetchColumns_From_FirstRowOfCells(Iterator<Cell> cells){
		List<ExcelColumn> columns = new ArrayList<>();
		int number = 0;
		while(cells.hasNext()){
			String columnName = cells.next().getStringCellValue().trim();
			if(!columnName.equals(""))columns.add(new ExcelColumn(number,columnName));
			number++;
		}
		return columns;
	}
}
