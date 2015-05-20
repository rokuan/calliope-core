package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.structure.InterpretationObject;

/**
 * Created by LEBEAU Christophe on 23/02/2015.
 */
public interface SpeechParser {
    WordBuffer lexSpeech(String speech);
    InterpretationObject parseSpeech(WordBuffer words);
}
