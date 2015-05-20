package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rokuan.calliopecore.parser.Parser;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.Verb;
import com.rokuan.calliopecore.sentence.Verb.Pronoun;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Verb.ConjugationTense;
import com.rokuan.calliopecore.sentence.Verb.Form;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.data.place.MonumentObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup.GroupType;

public class SentenceParseTest {
	
	@Test
	public void testGoTo(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB);
		Verb<Action.VerbAction> toGo = new Verb<Action.VerbAction>("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);
		
		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Mairie", WordType.PLACE_TYPE, WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("Paris", WordType.PROPER_NAME, WordType.CITY));
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertEquals(obj.getType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);
		
		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.qType, QuestionType.HOW);
		
		PlaceObject place = obj.where;
		
		assertEquals(place.getType(), PlaceObject.PlaceType.MONUMENT);
		
		MonumentObject monument = (MonumentObject)place;
		assertEquals(monument.name, "Mairie");
		assertEquals(obj.how, "voiture");
	}
	
	@Test
	public void testGoTo2(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB);
		Verb<Action.VerbAction> toGo = new Verb<Action.VerbAction>("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);
		
		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Tour", WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("Eiffel", WordType.PROPER_NAME));
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertEquals(obj.getType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);
		
		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.qType, QuestionType.HOW);
		
		PlaceObject place = obj.where;
		
		assertEquals(place.getType(), PlaceObject.PlaceType.MONUMENT);
		
		assertEquals(obj.how, "voiture");
	}
	
	@Test
	public void testWhoIs(){
		WordBuffer words = new WordBuffer();
		
		Word be = new Word("est", Word.WordType.VERB);
		Verb<Action.VerbAction> toBe = new Verb<Action.VerbAction>("être", Action.VerbAction.BE, true);
		VerbConjugation toBeConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "être", toBe);		
		toBeConjug.setVerb(toBe);
		be.setVerbInfo(toBeConjug);
		
		words.add(new Word("qui", WordType.INTERROGATIVE_PRONOUN));
		words.add(be);
		words.add(new Word("Arnold", WordType.FIRSTNAME));
		words.add(new Word("Schwarzenegger", WordType.PROPER_NAME));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertEquals(obj.getType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.BE);
		
		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.qType, QuestionType.WHO);
		assertEquals(question.what.getType(), GroupType.COMPLEMENT);
		
		ComplementObject compl = (ComplementObject)question.what;
		assertEquals(compl.object, "Arnold Schwarzenegger");
	}

	@Test
	public void testDoubleDirectObject(){
		WordBuffer words = new WordBuffer();
		
		Word find = new Word("trouve", WordType.VERB);
		Verb<Action.VerbAction> toFind = new Verb<Action.VerbAction>("trouver", Action.VerbAction.FIND, false);
		VerbConjugation toFindConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.IMPERATIVE, Pronoun.TU, "trouver", toFind);
		toFindConjug.setVerb(toFind);
		find.setVerbInfo(toFindConjug);
		
		words.add(find);
		words.add(new Word("moi", WordType.TARGET_PRONOUN));
		words.add(new Word("des", WordType.INDEFINITE_ARTICLE, WordType.PREPOSITION_OF));
		words.add(new Word("vidéos", WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("chats", WordType.COMMON_NAME));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		ComplementObject compl = (ComplementObject)obj.what;
		assertEquals(compl.object, "vidéos");
		assert (compl.of != null);
		assertEquals(compl.of.object, "chats");
	}
	
	@Test
	public void testResultQuestion(){
		WordBuffer words = new WordBuffer();
		
		Word make = new Word("fait", WordType.VERB);
		Verb<Action.VerbAction> toMake = new Verb<Action.VerbAction>("faire", Action.VerbAction.DO__MAKE, false);
		VerbConjugation toMakeConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "faire", toMake);
		toMakeConjug.setVerb(toMake);
		make.setVerbInfo(toMakeConjug);
		
		words.add(new Word("quelle", WordType.INTERROGATIVE_ADJECTIVE));
		words.add(new Word("température", WordType.COMMON_NAME));
		words.add(make);
		words.add(new Word("il", WordType.PERSONAL_PRONOUN));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		ComplementObject compl = (ComplementObject)obj.what;
		assertEquals(obj.action, Action.VerbAction.DO__MAKE);
		assertEquals(((PronounTarget)obj.subject).pronoun, com.rokuan.calliopecore.sentence.Type.Pronoun.IL_ELLE_ON);
		assertEquals(compl.object, "température");
	}
}
