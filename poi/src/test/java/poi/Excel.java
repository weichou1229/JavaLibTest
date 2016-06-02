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
	private List<ExcelColumn> columns;
	private List<Map<String, Object>> rows;
	StrSubstitutor sub = new StrSubstitutor();
	
	public List<ExcelColumn> columns(){
		return this.columns;
	}
	public List<Map<String, Object>> rows(){
		return this.rows;
	}	
	
	public Excel read_Excel_to_listMap(String fileSource ,int sheetNo) throws Exception{
		try {
			//get excel file
			FileInputStream fi = new FileInputStream(fileSource);
			Workbook wb = WorkbookFactory.create(fi);
			Sheet sheet = wb.getSheetAt(sheetNo);
			//get all rows
			Iterator<Row> excelRows = sheet.rowIterator();
			//convert excel data to java object
			this.columns = this.fetchColumns_From_FirstRowOfCells(excelRows.next().cellIterator());
			this.rows = this.fetchRowDatas_from_rows_to_listMap(excelRows);
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} 
		return this;
	}
	
	private List<Map<String, Object>> fetchRowDatas_from_rows_to_listMap(Iterator<Row> excelRows) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

		while(excelRows.hasNext()){
			rows.add(this.fetchValueMap_from_row(excelRows.next()));
		}
		return rows;
	}
	private Map<String, Object> fetchValueMap_from_row(Row row){
		Map<String, Object> valueMap = new HashMap<>();
		for(ExcelColumn column : columns){
			valueMap.put(column.getName(),row.getCell(column.getNumber()));	//取出已經設定的欄位 放到Map
		}
		return valueMap;
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
