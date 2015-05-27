package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.Parser;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.Verb;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Verb.ConjugationTense;
import com.rokuan.calliopecore.sentence.Verb.Form;
import com.rokuan.calliopecore.sentence.Verb.Pronoun;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;

public class QuestionParseTest {
	@Test
	public void howManyTimeTest(){
		WordBuffer words = new WordBuffer();
		Word have = new Word("a", WordType.VERB, WordType.AUXILIARY);
		Verb<Action.VerbAction> toHave = new Verb<Action.VerbAction>("avoir", Action.VerbAction.HAVE, true);
		VerbConjugation toHaveConjug = new VerbConjugation(ConjugationTense.PRESENT, Form.INDICATIVE, Pronoun.IL_ELLE_ON, "avoir", toHave);
		toHaveConjug.setVerb(toHave);
		have.setVerbInfo(toHaveConjug);
		
		words.add(new Word("combien", WordType.INTERROGATIVE_ADJECTIVE));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("minutes"));
		words.add(new Word("y", WordType.PERSONAL_PRONOUN));
		words.add(have);
		words.add(new Word("t", WordType.CONJUGATION_LINK));
		words.add(new Word("il", WordType.PERSONAL_PRONOUN));
		words.add(new Word("dans", WordType.PREPOSITION_IN));
		words.add(new Word("une", WordType.DEFINITE_ARTICLE));
		words.add(new Word("heure", WordType.DATE_UNIT_HOUR, WordType.COMMON_NAME));
		
		InterpretationObject obj = new Parser().parseInterpretationObject(words);
		
		assertTrue(obj != null);
	}
}
