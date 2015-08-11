package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.content.IWayObject;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.nominal.ColorObject;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.way.AdditionalMode;
import com.rokuan.calliopecore.sentence.structure.way.NominalWayObject;

public class WayConverter {
	public static final WordPattern MEANS_OF_TRANSPORT_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "à|en|par"),
			WordPattern.simple(WordType.MEAN_OF_TRANSPORT));
	
	public static final WordPattern LANGUAGE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.WAY_PREPOSITION, "en"),
			WordPattern.simple(WordType.LANGUAGE)
			);
	
	public static final WordPattern MODE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.WAY_PREPOSITION),
			WordPattern.simple(WordType.MODE));
	
	public static final WordPattern COLOR_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.WAY_PREPOSITION, "en"),
			WordPattern.simple(WordType.COLOR)); 

	public static boolean isAWayAdverbial(WordBuffer words){
		return words.syntaxStartsWith(MEANS_OF_TRANSPORT_PATTERN)
				|| words.syntaxStartsWith(LANGUAGE_PATTERN)
				|| words.syntaxStartsWith(MODE_PATTERN)
				|| words.syntaxStartsWith(COLOR_PATTERN);
	}

	public static IWayObject parseWayAdverbial(WordBuffer words){
		IWayObject result = null;
		
		if(words.syntaxStartsWith(MODE_PATTERN)){
			AdditionalMode custom = new AdditionalMode();
			
			words.consume();
			custom.mode = words.getCurrentElement().getCustomMode();
			words.consume();
			
			result = custom;
		} else if(words.syntaxStartsWith(MEANS_OF_TRANSPORT_PATTERN)){
			NominalWayObject nominal = new NominalWayObject();
			ComplementObject compl = new ComplementObject();
			String mean = null;

			words.consume();

			mean = words.getCurrentElement().getValue();
			words.consume();

			compl.object = mean;
			nominal.object = compl;
			
			result = nominal;
		} else if(words.syntaxStartsWith(LANGUAGE_PATTERN)){
			LanguageObject lang = new LanguageObject();
			
			words.consume();	// "en"
			lang.language = words.getCurrentElement().getLanguageInfo();
			words.consume();
			
			result = lang;
		} else if(words.syntaxStartsWith(COLOR_PATTERN)){
			ColorObject col = new ColorObject();
			
			words.consume(); // "en"
			col.color = words.getCurrentElement().getColorInfo();
			words.consume();
			
			result = col;
		}
		
		return result;
	}
}
