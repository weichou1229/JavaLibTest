package Properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void test() {
		Properties properties = new Properties();
		InputStream stream = this.getClass().getResourceAsStream("config.properties");
//		InputStream stream = this.getClass().getResourceAsStream("/Properties/config.properties");
		try {
		    properties.load(stream);
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		    return;
		} catch (Exception ex) {
		    ex.printStackTrace();
		    return;
		}
		System.out.println(properties.getProperty("host"));
		
		
	}

}
