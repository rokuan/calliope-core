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
}
