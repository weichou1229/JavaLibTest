package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	StrSubstitutor sub = new StrSubstitutor();
	
	public Excel read_Excel(String fileSource ,int sheetNo) throws Exception{
		try {
			//input
			Workbook wb = WorkbookFactory.create(new FileInputStream(fileSource));
			Sheet sheet = wb.getSheetAt(sheetNo);
			Iterator<Row> rows = sheet.rowIterator();
			List<ExcelColumn> columns = this.fetchColumns_From_FirstRowOfCells(rows.next().cellIterator());
			//handle
			String templateString = "我${time}要去 ${place}玩 ~";
			while(rows.hasNext()){
				String resultString = this.fetchResultString(templateString,columns,rows.next());
				System.out.println(resultString);
			}
			//output
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} 
		return this;
	}
	
	private String fetchResultString(String templateString, List<ExcelColumn> columns, Row row) {
		Map valueMap = new HashMap<>();
		for(ExcelColumn column : columns){
			Cell cell = row.getCell(column.getNumber());
			Object value = column.getName().equals("time") ? cell.getDateCellValue() : cell ;
			valueMap.put(column.getName(), value);
		}
		return sub.replace(templateString, valueMap);
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
