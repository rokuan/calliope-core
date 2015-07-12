package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.Verb;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;

public interface WordDatabase {
	Word findWord(String word);
	//Verb<?> getVerb(String verbName);
	Verb getVerb(String verbName);
	VerbConjugation findConjugatedVerb(String conjugatedVerb);
}
