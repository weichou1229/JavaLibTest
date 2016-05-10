package StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Assert;
import org.junit.Test;

public class StrSubstitutorTest {
	@Test
	public void testGetEscapeChar(){
		StrSubstitutor strSubstitutor = new StrSubstitutor();
		Assert.assertTrue(strSubstitutor.getEscapeChar()=='$');

	}
	
	@Test
	public void testReplaceSystemProperties(){
		//Arrange
		String expect = "You are running with java.version = 1.7.0_79 and os.name = Windows 8.1.";
		String result = null;
		//Act
		result = StrSubstitutor.replaceSystemProperties(
			      "You are running with java.version = ${java.version} and os.name = ${os.name}.");
		//Assert
		Assert.assertTrue(result.equals(expect));

	}
	@Test
	public void testMapValueReplace(){
		//Arrange
		String templateString = "The ${animal} jumped over the ${target}.";
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("animal", "quick brown fox");
		valuesMap.put("target", "lazy dog");
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String expect = "The quick brown fox jumped over the lazy dog.";
		String result = null;
		//Act
		result = sub.replace(templateString);
		//Assert
		Assert.assertTrue(result.equals(expect));
	}
	@Test
	public void testDefaultValueReplace(){
		//Arrange
		String templateString = "The ${animal} jumped over the ${target}. ${undefined.number:-1234567890}.";
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("animal", "quick brown fox");
		valuesMap.put("target", "lazy dog");
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String expect = "The quick brown fox jumped over the lazy dog. -1234567890";
		String result = null;	
		//Act
		result = sub.replace(templateString);
		//Assert
		System.out.println(result);
		Assert.assertTrue(result.equals(expect));
	}

	
}
