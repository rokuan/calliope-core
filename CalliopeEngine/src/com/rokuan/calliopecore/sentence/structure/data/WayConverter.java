package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public class WayConverter {
	public static final WordPattern meansOfTransportPattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "à|en|par"),
			WordPattern.simple(WordType.MEAN_OF_TRANSPORT));
	
	public static final WordPattern languagePattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "en"),
			WordPattern.simple(WordType.LANGUAGE)
			);

	public static boolean isAWayData(WordBuffer words){
		return words.syntaxStartsWith(meansOfTransportPattern)
				|| words.syntaxStartsWith(languagePattern);
	}

	public static NominalGroup parseWayData(WordBuffer words){
		if(words.syntaxStartsWith(meansOfTransportPattern)){
			ComplementObject compl = new ComplementObject();
			String result = null;

			words.consume();

			result = words.getCurrentElement().getValue();
			words.consume();

			compl.object = result;
			return compl;
		} else if(words.syntaxStartsWith(languagePattern)){
			LanguageObject lang = new LanguageObject();
			
			words.consume();	// "en"
			lang.language = words.getCurrentElement().getLanguageInfo();
			words.consume();
			
			return lang;
		}
		
		return null;
	}
}
