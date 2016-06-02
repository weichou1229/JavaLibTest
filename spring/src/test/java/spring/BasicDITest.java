package Spring;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'weich' at '2016/5/11 下午 9:05' with Gradle 2.12
 *
 * @author weich, @date 2016/5/11 下午 9:05
 */
public class BasicDITest {
    @Test 
    public void testSrpingCotainer() {
    	//Arrange
    	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});
        //Act
    	SomeLibrary classUnderTest = context.getBean("library", SomeLibrary.class);
        //Assert
    	assertTrue("the return object should not null", classUnderTest!=null);
    }
    @Test 
    public void testDifferentXmlPathRepresentation() {
    	//Arrange
    	String xmlPath = "classpath:/spring.xml";
    	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {xmlPath});
        //Act
    	SomeLibrary classUnderTest = context.getBean("library", SomeLibrary.class);
        //Assert
    	assertTrue("the return object should not null", classUnderTest!=null);
    }
}