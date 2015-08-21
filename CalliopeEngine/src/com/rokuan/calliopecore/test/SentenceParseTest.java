package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.rokuan.calliopecore.parser.Parser;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CustomObject;
import com.rokuan.calliopecore.sentence.CustomPerson;
import com.rokuan.calliopecore.sentence.Verb;
import com.rokuan.calliopecore.sentence.Verb.Pronoun;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Verb.ConjugationTense;
import com.rokuan.calliopecore.sentence.Verb.Form;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject.RequestType;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.AdditionalPerson;
import com.rokuan.calliopecore.sentence.structure.data.nominal.CityObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.PronounTarget;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;

public class SentenceParseTest {
	@Test
	public void testGoTo(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB, WordType.COMMON_NAME);
		Verb toGo = new Verb("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);

		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("�", WordType.PREPOSITION_AT, WordType.PLACE_PREPOSITION));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Mairie", WordType.PLACE_TYPE, WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("Paris", WordType.PROPER_NAME, WordType.CITY));
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		assertEquals(obj.getRequestType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);

		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.questionType, QuestionType.HOW);

		IPlaceObject place = obj.where;
		NamedPlaceObject monument = (NamedPlaceObject)place;
		
		assertEquals(monument.name, "Mairie");
		assertEquals(((ComplementObject)obj.how).object, "voiture");
	}

	@Test
	public void testGoTo2(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB);
		Verb toGo = new Verb("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);

		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("�", WordType.PREPOSITION_AT, WordType.PLACE_PREPOSITION));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Tour", WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("Eiffel", WordType.PROPER_NAME));
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		assertEquals(obj.getRequestType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);

		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.questionType, QuestionType.HOW);

		IPlaceObject place = obj.where;
		
		assertEquals(place.getPlaceType(), PlaceType.NAMED_PLACE);

		assertEquals(((ComplementObject)obj.how).object, "voiture");
	}
	
	@Test
	public void testGoTo3(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB, WordType.COMMON_NAME);
		Verb toGo = new Verb("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);
		Word paris = new Word("Paris", WordType.CITY);
		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));

		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("�", WordType.PREPOSITION_AT, WordType.PLACE_PREPOSITION));
		words.add(paris);
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		assertEquals(obj.getRequestType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);

		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.questionType, QuestionType.HOW);

		IPlaceObject place = obj.where;
		
		CityObject city = (CityObject)place;
		
		assertEquals(city.city.getName(), "Paris");
		assertEquals(((ComplementObject)obj.how).object, "voiture");
	}
	
	@Test
	public void testGoTo4(){
		WordBuffer words = new WordBuffer();
		Word go = new Word("aller", Word.WordType.VERB, WordType.COMMON_NAME);
		Verb toGo = new Verb("aller", Action.VerbAction.GO, false);
		VerbConjugation toGoConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INFINITIVE, null, "aller", toGo);
		Word paris = new Word("Paris", WordType.CITY);
		
		toGoConjug.setVerb(toGo);
		go.setVerbInfo(toGoConjug);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));

		words.add(new Word("comment", WordType.INTERROGATIVE_PRONOUN));
		words.add(go);
		words.add(new Word("�", WordType.PREPOSITION_AT, WordType.PLACE_PREPOSITION));
		words.add(paris);
		words.add(new Word("�", WordType.PREPOSITION_AT));
		words.add(new Word("voiture", WordType.COMMON_NAME, WordType.MEAN_OF_TRANSPORT));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		assertEquals(obj.getRequestType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.GO);

		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.questionType, QuestionType.HOW);

		IPlaceObject place = obj.where;
		
		CityObject city = (CityObject)place;
		
		assertEquals(city.city.getName(), "Paris");
		assertEquals(((ComplementObject)obj.how).object, "voiture");
	}

	@Test
	public void testWhoIs(){
		WordBuffer words = new WordBuffer();

		Word be = new Word("est", Word.WordType.VERB);
		Verb toBe = new Verb("�tre", Action.VerbAction.BE, true);
		VerbConjugation toBeConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "�tre", toBe);		
		toBeConjug.setVerb(toBe);
		be.setVerbInfo(toBeConjug);
		Word person = new Word("Arnold Schwarzenegger", WordType.PERSON);
		CustomPerson schwarzy = new CustomPerson("Arnold Schwarzenegger", "SCHWARZY");
		person.setCustomPerson(schwarzy);
		
		
		words.add(new Word("qui", WordType.INTERROGATIVE_PRONOUN));
		words.add(be);
		words.add(person);
		
		/*words.add(new Word("Arnold", WordType.FIRSTNAME));
		words.add(new Word("Schwarzenegger", WordType.PROPER_NAME));*/

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		assertEquals(obj.getRequestType(), InterpretationObject.RequestType.QUESTION);
		assertEquals(obj.action, Action.VerbAction.BE);

		QuestionObject question = (QuestionObject)obj;
		assertEquals(question.questionType, QuestionType.WHO);
		//assertEquals(question.what.getGroupType(), GroupType.COMPLEMENT);
		assertEquals(question.what.getGroupType(), GroupType.PERSON);

		/*ComplementObject compl = (ComplementObject)question.what;
		assertEquals(compl.object, "Arnold Schwarzenegger");*/
		assertEquals(((AdditionalPerson)question.what).person.getName(), "Arnold Schwarzenegger");
	}

	@Test
	public void testDoubleDirectObject(){
		WordBuffer words = new WordBuffer();

		Word find = new Word("trouve", WordType.VERB);
		Verb toFind = new Verb("trouver", Action.VerbAction.FIND, false);
		VerbConjugation toFindConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.IMPERATIVE, Pronoun.TU, "trouver", toFind);
		toFindConjug.setVerb(toFind);
		find.setVerbInfo(toFindConjug);

		words.add(find);
		words.add(new Word("moi", WordType.TARGET_PRONOUN));
		words.add(new Word("des", WordType.INDEFINITE_ARTICLE, WordType.PREPOSITION_OF));
		words.add(new Word("vid�os", WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("chats", WordType.COMMON_NAME));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		ComplementObject compl = (ComplementObject)obj.what;
		assertEquals(compl.object, "vid�os");
		assert (compl.of != null);
		assertEquals(((ComplementObject)compl.of).object, "chats");
	}

	@Test
	public void testResultQuestion(){
		WordBuffer words = new WordBuffer();

		Word make = new Word("fait", WordType.VERB);
		Verb toMake = new Verb("faire", Action.VerbAction.DO__MAKE, false);
		VerbConjugation toMakeConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "faire", toMake);
		toMakeConjug.setVerb(toMake);
		make.setVerbInfo(toMakeConjug);

		words.add(new Word("quelle", WordType.INTERROGATIVE_ADJECTIVE));
		words.add(new Word("temp�rature", WordType.COMMON_NAME));
		words.add(make);
		words.add(new Word("il", WordType.PERSONAL_PRONOUN));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		ComplementObject compl = (ComplementObject)obj.what;
		assertEquals(obj.action, Action.VerbAction.DO__MAKE);
		assertEquals(((PronounTarget)obj.subject).pronoun, com.rokuan.calliopecore.sentence.Type.Pronoun.IL_ELLE_ON);
		assertEquals(compl.object, "temp�rature");
	}

	@Test
	public void testTrapQuestion(){
		WordBuffer words = new WordBuffer();

		Word make = new Word("fera", WordType.VERB);
		Verb toMake = new Verb("faire", Action.VerbAction.DO__MAKE, false);
		VerbConjugation toMakeConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "faire", toMake);
		toMakeConjug.setVerb(toMake);
		make.setVerbInfo(toMakeConjug);

		words.add(new Word("quel", WordType.INTERROGATIVE_ADJECTIVE));
		words.add(new Word("temps", WordType.COMMON_NAME));
		words.add(make);
		words.add(new Word("t", WordType.CONJUGATION_LINK));
		words.add(new Word("il", WordType.PERSONAL_PRONOUN));
		words.add(new Word("demain", WordType.DATE));

		InterpretationObject obj = new Parser().parseInterpretationObject(words);

		ComplementObject compl = (ComplementObject)obj.what;
		assertEquals(obj.action, Action.VerbAction.DO__MAKE);
		assertEquals(((PronounTarget)obj.subject).pronoun, com.rokuan.calliopecore.sentence.Type.Pronoun.IL_ELLE_ON);
		assertEquals(compl.object, "temps");
		assertEquals(obj.when.getTimeType(), TimeAdverbial.TimeType.SINGLE);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();
		Date sentenceDate = ((SingleTimeObject)obj.when).date;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		assertEquals(formatter.format(tomorrow), formatter.format(sentenceDate));
	}
	
	@Test
	public void testObjectSentence(){
		WordBuffer words = new WordBuffer();
		String objectName = "lumi�res de la cuisine";
		Word light = new Word(objectName, WordType.OBJECT);
		Word switchOff = new Word("�teinds", WordType.VERB);
		Verb toSwitchOff = new Verb("�teindre", Action.VerbAction.TURN_OFF, false);
		VerbConjugation switchConjugation = new VerbConjugation(ConjugationTense.PRESENT, Form.IMPERATIVE, Pronoun.TU, "�teinds", toSwitchOff);
		
		switchOff.setVerbInfo(switchConjugation);		
		light.setCustomObject(new CustomObject(objectName, "LIGHT_KITCHEN"));
		
		words.add(switchOff);
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("4", WordType.NUMBER));
		words.add(light);
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertEquals(obj.getRequestType(), RequestType.ORDER);
		assertEquals(obj.action, Action.VerbAction.TURN_OFF);
		
		assertEquals(obj.what.getGroupType(), GroupType.OBJECT);
		
		AdditionalObject customObject = (AdditionalObject)obj.what;
		
		assertEquals(customObject.object.getContent(), objectName);
	}
	
	@Test
	public void testAffirmativeSentence(){
		WordBuffer words = new WordBuffer();
		Word hate = new Word("d�testent", WordType.VERB);
		Verb toHate = new Verb("d�tester", Action.VerbAction.HATE, false);
		VerbConjugation hateConjugation = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.ILS_ELLES, "d�testent", toHate);
		
		hate.setVerbInfo(hateConjugation);
		
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("chiens", WordType.COMMON_NAME));
		words.add(hate);
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("chats", WordType.COMMON_NAME));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertEquals(obj.getRequestType(), RequestType.AFFIRMATION);
		assertEquals(obj.subject.getGroupType(), GroupType.COMPLEMENT);
	}
}
