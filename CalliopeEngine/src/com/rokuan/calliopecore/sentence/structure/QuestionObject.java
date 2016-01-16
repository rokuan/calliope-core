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
		WHY,
		HOW_MANY,
		WHICH
	}

	public QuestionType questionType = QuestionType.YES_NO;
	
    public QuestionObject(){
        super(RequestType.QUESTION);
    }
}
