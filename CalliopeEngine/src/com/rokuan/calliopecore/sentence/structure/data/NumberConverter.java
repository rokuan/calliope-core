package com.rokuan.calliopecore.sentence.structure.data;

import java.util.regex.Pattern;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.CountType;


/**
 * Created by LEBEAU Christophe on 28/02/2015.
 */
public class NumberConverter {
    private static final String[] numberMap = {
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

    private static final String[] tenStepMap = {
            "dix",
            "vingt",
            "trente",
            "quarante",
            "cinquante",
            "soixante"
    };
    
    private static final String[] tenPowerMap = {
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
    
    public static final WordPattern fixedItemPattern = WordPattern.sequence(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE), 
    		WordPattern.simple(Word.WordType.NUMERICAL_POSITION));
    
    public static final WordPattern fixedRangePattern = WordPattern.sequence(
    		WordPattern.simple(WordType.DEFINITE_ARTICLE), 
    		WordPattern.simple(Word.WordType.NUMBER), 
    		WordPattern.simple(Word.WordType.NUMERICAL_POSITION));
    
    public static final WordPattern quantityPattern = WordPattern.sequence(
    		WordPattern.simple(Word.WordType.QUANTITY), 
    		WordPattern.simple(Word.WordType.DEFINITE_ARTICLE));
    
    // TODO: les intervalles (du 3eme au 5eme)

    public static long parsePosition(String posStr){
        if(posStr.equals("premier") || posStr.equals("première") || posStr.equals("1er") || posStr.equals("1ère") || posStr.equals("1ere")){
            return 1;
        }

        if(posStr.endsWith("ième")){
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

        if(Pattern.compile("\\d+(è|e)me$").matcher(posStr).find()){
            return Long.parseLong(posStr.substring(0, posStr.length() - 3));
        } else if(Pattern.compile("\\d+e$").matcher(posStr).find()){
            return Long.parseLong(posStr.substring(0, posStr.length() - 1));
        }

        // TODO: trouver les autres cas
        return 0;
    }

    private static long parseCount(String countStr){        
        for(int i=0; i<tenPowerMap.length; i++){
        	if(Math.abs(tenPowerMap[i].length() - countStr.length()) <=1 && tenPowerMap[i].startsWith(countStr)){
                return (int)Math.pow(10, (i+1));
            }
        }
    	
        for(int i=0; i<tenStepMap.length; i++){
            if(Math.abs(tenStepMap[i].length() - countStr.length()) <=1 && tenStepMap[i].startsWith(countStr)){
                return (i+1) * 10;
            }
        }

        for(int i=0; i<numberMap.length; i++){
            if(Math.abs(numberMap[i].length() - countStr.length()) <=1 && numberMap[i].startsWith(countStr)){
                return (i+1);
            }
        }
        
        return 0;
    }
    
    /*private static long parseNumber(String[] parts){
    	long lastFactor = 1;
    	long result = 0;
    	long currentValue = 0;
    	
    	if(parts.length == 1){
    		return parseCount(parts[0]);
    	}
    	
    	ArrayList<Long> numberParts = new ArrayList<Long>();
    	//int i = 1;
    	//int currentValue = 0;
    	int i = 0;
    	
    	while(i < parts.length){
    		if(parts[i].equals("et")){
    			i++;
    		}
    		
    		if(i >= parts.length){
    			break;
    		}
    		
    		currentValue = parseCount(parts[i]);
    		long tmpValue = 0;
    		
    		i++;
    		
    		while(i < parts.length){    			
    			tmpValue = parseCount(parts[i]);
    			
    			if(tmpValue < currentValue){
    				currentValue += tmpValue;
    				i++;
    			} else {
    				currentValue *= tmpValue;
    				i++;
    				numberParts.add(currentValue);
    				break;
    			}
    		}
    	}
    	
    	numberParts.add(currentValue);
    	System.out.println(numberParts);
    	
    	return result;
    }*/

    public static boolean isACountData(WordBuffer words){
        // TODO:
        /*return words.syntaxStartsWith(WordType.DEFINITE_ARTICLE, Word.WordType.NUMERICAL_POSITION) // le premier
                || words.syntaxStartsWith(WordType.DEFINITE_ARTICLE, Word.WordType.NUMBER, Word.WordType.NUMERICAL_POSITION)  // les 5 premiers
                || words.syntaxStartsWith(Word.WordType.QUANTITY, Word.WordType.DEFINITE_ARTICLE);	// tou(te)s les*/
    	return words.syntaxStartsWith(fixedItemPattern)
    			|| words.syntaxStartsWith(fixedRangePattern)
    			|| words.syntaxStartsWith(quantityPattern);
    }

    public static CountObject parseCountObject(WordBuffer words){
    	if(words.getCurrentIndex() > words.size()){
    		// TODO: error
    		return null;
    	}
    	
        CountObject result = new CountObject();
        
        if(words.syntaxStartsWith(fixedItemPattern)){        	
        	words.consume();
        	result.count = 1;
            result.countType = CountObject.CountType.LIMIT;
            //result.position = Long.parseLong(words.getCurrentElement().getValue());
            result.position = parsePosition(words.getCurrentElement().getValue());
            result.range = CountObject.Range.FIXED;
            words.consume();
        } else if(words.syntaxStartsWith(fixedRangePattern)){
        	words.consume();
        	
        	try{
                result.count = Long.parseLong(words.getCurrentElement().getValue());
                result.countType = CountObject.CountType.LIMIT;
                words.consume();

                String posValue = words.getCurrentElement().getValue();

                if(posValue.startsWith("premi")){
                    result.position = 1;
                    result.range = CountObject.Range.FIRST;
                } else if(posValue.startsWith("derni")){
                    result.range = CountObject.Range.LAST;
                } else {
                    // TODO: error
                }
                
                words.consume();
            } catch(Exception e) {

            }
        } else if(words.syntaxStartsWith(quantityPattern)){
        	result.countType = CountType.ALL;
        	words.consume();
        	words.consume();
        }

        return result;
    }
}
