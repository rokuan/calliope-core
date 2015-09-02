package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.structure.InterpretationObject;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public interface AbstractParser {
	InterpretationObject parseText(String text);
}
