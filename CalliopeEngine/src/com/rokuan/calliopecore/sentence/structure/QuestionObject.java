package com.rokuan.calliopecore.sentence.structure;

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
		WHY
	}
	
	public QuestionType qType = QuestionType.YES_NO;
	
    public QuestionObject(){
        super(RequestType.QUESTION);
    }
    
    public static QuestionType parseInterrogativePronoun(String value){
    	// TODO: s'assurer qu'on ne tente pas de parser le mot "quelque(s)"
    	String lowerValue = value.toLowerCase();
    	
    	if(lowerValue.startsWith("quel")){
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
    	}
    	
    	return QuestionType.UNDEFINED;
    }
}
