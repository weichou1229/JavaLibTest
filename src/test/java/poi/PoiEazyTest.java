package poi;

import org.junit.Test;

public class PoiEazyTest {

	@Test
	public void testRead() {
		String fileSource = "C:\\Users\\weich\\Downloads\\台中3日遊.xlsx";
		int sheetNo = 0;
		try {
			new Excel().read_Excel(fileSource, sheetNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
