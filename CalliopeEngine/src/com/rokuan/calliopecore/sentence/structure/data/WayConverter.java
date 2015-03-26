package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class WayConverter {
	public static final WordPattern meansOfTransportPattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "à|en|par"),
			WordPattern.simple(WordType.MEAN_OF_TRANSPORT));
	
	public static boolean isAWayData(WordBuffer words){
		return words.syntaxStartsWith(meansOfTransportPattern);
	}
	
	public static String parseWayData(WordBuffer words){
		String result = null;
		
		words.consume();
		
		result = words.getCurrentElement().getValue();
		words.consume();
		
		return result;
	}
}
