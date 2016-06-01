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
import org.junit.Test;

public class PoiEazyTest {

	@Test
	public void testRead() {
		String file = "C:\\Users\\weich\\Downloads\\台中3日遊.xlsx";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(file));
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(sheet.getRow(0).getLastCellNum());

			
			Iterator<Row> rows = sheet.rowIterator();
			List<Map> list = new ArrayList<>();
			while(rows.hasNext()){list.add(fetchRowCellsToMap(rows.next()));}
			
			
			String templateString = "我要去 ${place}玩 ~";
			StrSubstitutor sub = new StrSubstitutor();
			for(Map m : list){
				System.out.println(sub.replace(templateString, m));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Map fetchRowCellsToMap(Row row){
		System.out.println("---------------");
		Map cells = new HashMap<>();
		cells.put("place", row.getCell(1));
		return cells;
		
	}

}
