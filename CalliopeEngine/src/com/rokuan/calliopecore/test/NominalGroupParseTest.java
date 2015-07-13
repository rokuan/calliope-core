package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.NominalGroupConverter;
import com.rokuan.calliopecore.sentence.structure.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup.GroupType;

public class NominalGroupParseTest {
	@Test
	public void complementObjectParseTest(){
		WordBuffer words = new WordBuffer();
		
		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(new Word("chat", WordType.COMMON_NAME));
		
		NominalGroup nominal = NominalGroupConverter.parseDirectObject(words);
		
		assertEquals(nominal.getType(), GroupType.COMPLEMENT);
		
		ComplementObject compl = (ComplementObject)nominal;
		
		assertEquals(compl.object, "chat");
	}
	
	@Test
	public void customObjectParseTest(){
		WordBuffer words = new WordBuffer();
		String objectName = "QR code";
		Word qr = new Word(objectName, WordType.OBJECT);
		
		qr.setCustomObject(new CustomObject(objectName, "QR_CODE"));
		
		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(qr);
		
		NominalGroup nominal = NominalGroupConverter.parseDirectObject(words);
		
		assertEquals(nominal.getType(), GroupType.OBJECT);
		
		AdditionalObject obj = (AdditionalObject)nominal;
		
		assertEquals(obj.object.getContent(), objectName);
	}
}
