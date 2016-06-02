package poi;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;

public class PoiEazyTest {

	@Test
	public void testRead() {
		String fileSource = "D:\\555.xlsx";
		int sheetNo = 0;
		try {
			System.out.println("read excel");
			Excel excel =new Excel().read_Excel_to_listMap(fileSource, sheetNo);
			for(ExcelColumn column : excel.columns())System.out.println(column);
			System.out.println(excel.rows().size());
			
			System.out.println("write file");
			String templateStirng = "INSERT INTO `eimi`.`MEMBER_CUS_MEMO`(`CUST_ID`,`PERSON_ID`,`MD_ID`,`MAIN_CD`,`SUB_CD`,`MEMO`,`CREATE_ID`,`CREATE_NAME`,`CREATE_DATE`,`UPDATE_ID`,`UPDATE_NAME`,`UPDATE_DATE`)SELECT CUST_ID,PERSON_ID,ID,'11','4','1.H&D東稻家居★網路人氣時尚家具★6/30前憑訊到各門市，加入會員贈得易Ponta150點及精美鋼筆http://goo.gl/Dy5NiA  2.本活動僅限收到活動簡訊或活動EDM會員，於活動期間內至全台H&D東稻家居門市申辦H&D生活聯名卡之會員，贈送之點數將於申辦H&D生活聯名卡後7個工作天內入點．','a0001','管理員',NOW(),'a0001','管理員',NOW() FROM eimi.MEMBER_DATA WHERE CUST_ID='${CUST_ID}'; \r\n" ;
    		File temp = File.createTempFile("INSERT_MEMBER_CUS_MEMO", ".sql"); //create a temp file
    		System.out.println("Temp file : " + temp.getAbsolutePath());
			StrSubstitutor sub = new StrSubstitutor();
			FileWriter fw = new FileWriter(temp);
			for(Map<String, Object> row : excel.rows()){
				fw.write(sub.replace(templateStirng, row));
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
