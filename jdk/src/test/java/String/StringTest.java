package String;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringTest {
	@Test
	public void test_add_witespace(){
		//Arrange
		int length = 6;
		String givenString = "a" ;
		String expect = "a     ";
		String result = null ;
		//Act
		result = String.format("%-"+length+"s", givenString);
		//Assert
		assertTrue(expect.equals(result));
	}
	@Test
	public void testFormat_add_leading_zeros(){
		//Arrange
		int length = 5 ;
		int giveNum = 2 ;
		String expect = "00002" ;
		String result = null;
		//Act
		result = String.format("%0"+length+"d", giveNum);
		//Assert
		assertTrue(expect.equals(result));
		
	}

}
