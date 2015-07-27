package com.rokuan.calliopecore.sentence.structure;

import com.google.gson.annotations.Expose;

/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public class QuestionObject extends InterpretationObject {
	public enum QuestionType {
		UNDEFINED,
		YES_NO,
		WHAT,
		WHO,
		WHEN,
		WHERE,
		HOW,
		WHY,
		HOW_MANY,
		WHICH
	}

	@Expose
	public QuestionType questionType = QuestionType.YES_NO;
	
    public QuestionObject(){
        super(RequestType.QUESTION);
    }
    
    public static QuestionType parseInterrogativePronoun(String value){
    	// TODO: s'assurer qu'on ne tente pas de parser le mot "quelque(s)"
    	String lowerValue = value.toLowerCase();
    	
    	if(lowerValue.startsWith("quel") || lowerValue.equals("que") || lowerValue.equals("qu")){
    		return QuestionType.WHAT;
    	} else if(lowerValue.equals("quand")){
    		return QuestionType.WHEN;
    	} else if(lowerValue.equals("où")){
    		return QuestionType.WHERE;
    	} else if(lowerValue.equals("comment")){
    		return QuestionType.HOW;
    	} else if(lowerValue.equals("pourquoi")){
    		return QuestionType.WHY;
    	} else if(lowerValue.equals("qui")){
    		return QuestionType.WHO;
    	} else if(lowerValue.equals("combien")){
    		return QuestionType.HOW_MANY;
    	} else if(lowerValue.startsWith("lequel") || lowerValue.startsWith("lesquel")){
    		return QuestionType.WHICH;
    	}
    	
    	return QuestionType.UNDEFINED;
    }
}
