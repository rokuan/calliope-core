package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.rokuan.calliopecore.parser.Parser;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.Action.VerbAction;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.Verb;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Verb.ConjugationTense;
import com.rokuan.calliopecore.sentence.Verb.Form;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.OrderObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject.RequestType;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateDefinition;

public class JsonTest {
	@Test
	public void testInterpretationObjectDeserialization(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB);
		Verb toGo = new Verb("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);

		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("�", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Mairie", WordType.PLACE_TYPE, WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("Paris", WordType.PROPER_NAME, WordType.CITY));
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		String json = InterpretationObject.toJSON(obj);
		System.out.println(json);
		InterpretationObject object = InterpretationObject.fromJSON(json);
		
		assertEquals(object.getRequestType(), RequestType.QUESTION);
		assertEquals(((QuestionObject)object).questionType, QuestionType.HOW);		
	}
	
	@Test
	public void testInterpretationObjectDeserialization2() throws ParseException{
		// Allume toutes les lumieres a 17h40
		OrderObject obj = new OrderObject();
		AdditionalObject customObject = new AdditionalObject();
		customObject.count = new AllItemsObject();
		customObject.object = new CustomObject("lumi�re de la cuisine", "KITCHEN_LIGHT");
		SingleTimeObject time = new SingleTimeObject();
		time.dateDefinition = DateDefinition.TIME_ONLY;
		time.setTimePreposition(DateContext.WHEN);
		time.date = new SimpleDateFormat("HH:mm").parse("17:40");
		
		obj.action = VerbAction.TURN_ON;
		obj.what = customObject;
		
		String json = InterpretationObject.toJSON(obj);
		System.out.println(json);
		InterpretationObject object = InterpretationObject.fromJSON(json);
		
		assertEquals(object.getRequestType(), RequestType.ORDER);
	}
}
