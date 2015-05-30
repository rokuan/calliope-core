package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;

public class NumberParseTest {
	@Test
	public void testNth(){
		assertEquals(2, NumberConverter.parsePosition("deuxième"));
		assertEquals(5, NumberConverter.parsePosition("cinquième"));
		assertEquals(10, NumberConverter.parsePosition("dixième"));
		//assertEquals(32, NumberConverter.parsePosition("trente-deuxième"));
		//assertEquals(41, NumberConverter.parsePosition("quarante-et-unième"));
	}
	
	@Test
	public void testNumberString(){
		//assertEquals(42, NumberConverter.parsePosition("quarante-deuxième"));
		//assertEquals(42051, NumberConverter.parsePosition("quarante-deux-mille-cinquante-et-unième"));
	}
}
