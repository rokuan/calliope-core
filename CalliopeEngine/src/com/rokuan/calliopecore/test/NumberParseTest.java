package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;

public class NumberParseTest {
	@Test
	public void testNth(){
		assertEquals(2, NumberConverter.parsePosition("deuxi�me"));
		assertEquals(5, NumberConverter.parsePosition("cinqui�me"));
		assertEquals(10, NumberConverter.parsePosition("dixi�me"));
		//assertEquals(32, NumberConverter.parsePosition("trente-deuxi�me"));
		//assertEquals(41, NumberConverter.parsePosition("quarante-et-uni�me"));
	}
	
	@Test
	public void testNumberString(){
		//assertEquals(42, NumberConverter.parsePosition("quarante-deuxi�me"));
		//assertEquals(42051, NumberConverter.parsePosition("quarante-deux-mille-cinquante-et-uni�me"));
	}
}
