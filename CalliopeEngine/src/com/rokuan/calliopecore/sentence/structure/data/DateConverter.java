package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class DateConverter {
	public static WordPattern datePattern = WordPattern.sequence(
			WordPattern.simple(Word.WordType.PREPOSITION_FROM),
			WordPattern.or(WordPattern.simple(Word.WordType.NUMERICAL_POSITION), WordPattern.simple(Word.WordType.NUMBER)),
			WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.DATE_MONTH), WordPattern.optional(WordPattern.simple(WordType.NUMBER)))),
			WordPattern.simple(Word.WordType.PREPOSITION_TO),
			WordPattern.or(WordPattern.simple(Word.WordType.NUMBER), WordPattern.simple(Word.WordType.NUMERICAL_POSITION)),
			WordPattern.simple(Word.WordType.DATE_MONTH),
			WordPattern.optional(WordPattern.simple(Word.WordType.NUMBER))
			);
	
    public static boolean isADateData(WordBuffer words){
        /*return words.syntaxStartsWith(Word.WordType.PREPOSITION_FROM, Word.WordType.DATE)  // d'aujourd'hui
                // Du 1 au 2 Janvier
                || words.syntaxStartsWith(Word.WordType.PREPOSITION_FROM, Word.WordType.NUMBER, Word.WordType.PREPOSITION_TO)
                // Du 1er au 2 Janvier
                || words.syntaxStartsWith(Word.WordType.PREPOSITION_FROM, Word.WordType.NUMERICAL_POSITION, Word.WordType.DATE_MONTH)
                || words.syntaxStartsWith(Word.WordType.PREPOSITION_FROM);*/
    	return WordPattern.syntaxStartsWith(words, datePattern); 
    }
}
