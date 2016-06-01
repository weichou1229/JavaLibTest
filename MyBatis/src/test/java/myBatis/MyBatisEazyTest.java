package myBatis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyBatisEazyTest {
	private static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUp(){
		String resource = "mybatis-config.xml";
		String mySQLproperties = "mySQL-config.properties";
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = Resources.getResourceAsStream(resource);
			properties.load(Resources.getResourceAsStream(mySQLproperties));
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInsert() {
//		fail("Not yet implemented");
	}
	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
	}
	@Test
	public void testSelect() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Map> list = session.selectList("selectCusServiceMemoCode");
			for(Map m : list)System.out.println(m);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
		  session.close();
		}
	}
	@Test
	public void testDelete(){
//		fail("Not yet implemented");
	}
	
	@After
	public void tearDown(){
		
	}
}
