package com.rokuan.calliopecore.sentence.structure.data;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.Range;
import com.rokuan.calliopecore.sentence.structure.data.count.FixedItemObject;
import com.rokuan.calliopecore.sentence.structure.data.count.LimitedItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.MultipleItemsObject;


/**
 * Created by LEBEAU Christophe on 28/02/2015.
 */
public class NumberConverter {
    private static final String[] NUMBER_MAP = {
            "un",
            "deux",
            "trois",
            "quatre",
            "cinq",
            "six",
            "sept",
            "huit",
            "neuf",
            "dix",
            "onze",
            "douze",
            "treize",
            "quatorze",
            "quinze",
            "seize"
    };

    private static final String[] TEN_STEP_MAP = {
            "dix",
            "vingt",
            "trente",
            "quarante",
            "cinquante",
            "soixante"
    };
    
    private static final String[] TEN_POWER_MAP = {
    	"dix",
    	"cent",
    	"mille",
    	"_",
    	"_",
    	"million",
    	"_",
    	"_",
    	"milliard"
    };
    
    public static final WordPattern FIXED_ITEM_PATTERN = WordPattern.sequence(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE), 
    		WordPattern.simple(Word.WordType.NUMERICAL_POSITION));
    
    public static final WordPattern FIXED_RANGE_PATTERN = WordPattern.sequence(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE), 
    		WordPattern.simple(Word.WordType.NUMBER), 
    		WordPattern.simple(Word.WordType.NUMERICAL_POSITION));
    
    public static final WordPattern QUANTITY_PATTERN = WordPattern.sequence(
    		WordPattern.simple(Word.WordType.QUANTITY), 
    		WordPattern.simple(Word.WordType.DEFINITE_ARTICLE));
    
    public static final WordPattern SIMPLE_ARTICLE_PATTERN = WordPattern.or(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE),
    		WordPattern.simple(WordType.INDEFINITE_ARTICLE));
    
    private static final WordPattern SINGLE_ITEM_PATTERN = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND)),
			WordPattern.optional(WordPattern.simple(WordType.ANY, "num�ro(s?)")), 
			WordPattern.simple(WordType.NUMBER));
    
    private static final WordPattern SINGLE_POSITION_PATTERN = WordPattern.sequence(
    		WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND)),
    		WordPattern.simple(WordType.NUMERICAL_POSITION));
    
    public static final WordPattern MULTIPLE_ITEMS_PATTERN = WordPattern.sequence(
    		WordPattern.nonEmptyList(SINGLE_ITEM_PATTERN));
    
    public static final WordPattern MULTIPLE_POSITIONS_PATTERN = WordPattern.sequence(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE), 
    		WordPattern.simple(WordType.NUMERICAL_POSITION),
    		WordPattern.nonEmptyList(SINGLE_POSITION_PATTERN));
    
    public static final WordPattern COUNT_PATTERN = WordPattern.or(FIXED_ITEM_PATTERN, FIXED_RANGE_PATTERN, QUANTITY_PATTERN, SIMPLE_ARTICLE_PATTERN);
    
    
    // TODO: les intervalles (du 3eme au 5eme)

    public static long parsePosition(String posStr){
        if(posStr.equals("premier") || posStr.equals("premi�re") || posStr.equals("1er") || posStr.equals("1�re") || posStr.equals("1ere")){
            return 1;
        }

        if(posStr.endsWith("i�me")){
            String base = posStr.substring(0, posStr.length() - 4);

            if(base.length() == 0){
                // TODO: erreur
                return 0;
            }

            char lastChar = base.charAt(base.length() - 1);

            //if(lastChar == 'a' || lastChar == 'e' || lastChar == 'i' || lastChar == 'o' || lastChar == 'u'){
            // Voyelle supplementaire pour conserver la sonorite
            if(lastChar == 'u'){
                base = base.substring(0, base.length() - 1);
            }
            
            /*String[] parts = base.split("-");

            return parseNumber(parts);*/
            return parseCount(base);
        }

        if(Pattern.compile("\\d+(�|e)me$").matcher(posStr).find()){
            return Long.parseLong(posStr.substring(0, posStr.length() - 3));
        } else if(Pattern.compile("\\d+e$").matcher(posStr).find()){
            return Long.parseLong(posStr.substring(0, posStr.length() - 1));
        }

        // TODO: trouver les autres cas
        return 0;
    }

    private static long parseCount(String countStr){        
        for(int i=0; i<TEN_POWER_MAP.length; i++){
        	if(Math.abs(TEN_POWER_MAP[i].length() - countStr.length()) <=1 && TEN_POWER_MAP[i].startsWith(countStr)){
                return (int)Math.pow(10, (i+1));
            }
        }
    	
        for(int i=0; i<TEN_STEP_MAP.length; i++){
            if(Math.abs(TEN_STEP_MAP[i].length() - countStr.length()) <=1 && TEN_STEP_MAP[i].startsWith(countStr)){
                return (i+1) * 10;
            }
        }

        for(int i=0; i<NUMBER_MAP.length; i++){
            if(Math.abs(NUMBER_MAP[i].length() - countStr.length()) <=1 && NUMBER_MAP[i].startsWith(countStr)){
                return (i+1);
            }
        }
        
        return 0;
    }
    
    public static boolean isACountData(WordBuffer words){
        // TODO:
        /*return words.syntaxStartsWith(WordType.DEFINITE_ARTICLE, Word.WordType.NUMERICAL_POSITION) // le premier
                || words.syntaxStartsWith(WordType.DEFINITE_ARTICLE, Word.WordType.NUMBER, Word.WordType.NUMERICAL_POSITION)  // les 5 premiers
                || words.syntaxStartsWith(Word.WordType.QUANTITY, Word.WordType.DEFINITE_ARTICLE);	// tou(te)s les*/
    	return words.syntaxStartsWith(FIXED_ITEM_PATTERN)
    			|| words.syntaxStartsWith(FIXED_RANGE_PATTERN)
    			|| words.syntaxStartsWith(QUANTITY_PATTERN)
    			|| words.syntaxStartsWith(SIMPLE_ARTICLE_PATTERN)
    			|| words.syntaxStartsWith(MULTIPLE_POSITIONS_PATTERN);    	
    }
    
    public static boolean isASuffixCountData(WordBuffer words){
    	return words.syntaxStartsWith(MULTIPLE_ITEMS_PATTERN);
    }
    
    private static boolean isSingular(String article){
    	String[] parts = article.split("-");
    	
    	for(String word: parts){
    		char lastChar = word.charAt(word.length() - 1);
    		
    		if(lastChar == 's' || lastChar == 'x'){
    			return false;
    		}
    	}
    	
    	return true;
    }

    public static CountObject parseCountObject(WordBuffer words){
    	if(words.getCurrentIndex() > words.size()){
    		// TODO: error
    		return null;
    	}
    	
        CountObject result = null;
        
        if(words.syntaxStartsWith(MULTIPLE_POSITIONS_PATTERN)){
        	Set<Integer> numbers = new HashSet<Integer>();
        	
        	words.consume();	// DEFINITE_ARTICLE
        	
        	do {
        		if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
    				words.consume();
    			}
    			
    			numbers.add((int)parsePosition(words.getCurrentElement().getValue()));
    			words.consume();
        	} while(words.syntaxStartsWith(SINGLE_POSITION_PATTERN));
        	
        	result = new MultipleItemsObject(numbers);
        } else if (words.syntaxStartsWith(FIXED_ITEM_PATTERN)) {        	
        	words.consume();
        	result = new FixedItemObject(parsePosition(words.getCurrentElement().getValue()));
            words.consume();
        } else if(words.syntaxStartsWith(FIXED_RANGE_PATTERN)) {
        	words.consume();
        	
        	try{
        		Range r = Range.FIRST;
        		long count = Long.parseLong(words.getCurrentElement().getValue());        		
        		
        		words.consume();

                String posValue = words.getCurrentElement().getValue();

                if(posValue.startsWith("premi")){
                    r = CountObject.Range.FIRST;
                } else if(posValue.startsWith("derni")){
                    r = CountObject.Range.LAST;
                } else {
                    // TODO: error
                }
                
                words.consume();

        		result = new LimitedItemsObject(r, count);                
            } catch(Exception e) {

            }
        } else if(words.syntaxStartsWith(QUANTITY_PATTERN)) {
        	result = new AllItemsObject();
        	words.consume();
        	words.consume();
        } else if(words.syntaxStartsWith(SIMPLE_ARTICLE_PATTERN)) {        	
        	boolean singular = isSingular(words.getCurrentElement().getValue());
        	
        	words.consume();
        	
        	if(singular){
        		result = new FixedItemObject(1);
        	} else {
        		result = new AllItemsObject();
        	}
        }

        return result;
    }
    
    public static CountObject parseSuffixCountObject(WordBuffer words){
    	CountObject result = null;
    	
    	if(words.syntaxStartsWith(MULTIPLE_ITEMS_PATTERN)){
    		Set<Integer> numbers = new HashSet<Integer>();
    		
    		while(words.syntaxStartsWith(SINGLE_ITEM_PATTERN)){
    			if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
    				words.consume();
    			}
    			
    			if(words.getCurrentElement().getValue().startsWith("num�ro")){
    				words.consume();
    			}
    			
    			numbers.add(Integer.parseInt(words.getCurrentElement().getValue()));
    			words.consume();
    		}
    		
    		result = new MultipleItemsObject(numbers);
    	}
    	
    	return result;
    }
}
