package Enum;

import static org.junit.Assert.*;

import org.junit.Test;

public class PatternEnumTest {

	@Test
	public void test() {
		System.out.println();
		for(PatternEnum pattern : PatternEnum.values()){
			System.out.println(pattern);
		}
	}

}
