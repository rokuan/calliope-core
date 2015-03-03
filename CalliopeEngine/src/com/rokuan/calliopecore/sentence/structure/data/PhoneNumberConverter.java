package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;

/**
 * Created by LEBEAU Christophe on 02/03/2015.
 */
public class PhoneNumberConverter {
    public static boolean isAPhoneNumber(WordBuffer words){
        return words.syntaxStartsWith(Word.WordType.NUMBER, Word.WordType.NUMBER);
    }

    public static String parsePhoneNumber(WordBuffer words){
        StringBuilder phoneNumber = new StringBuilder();

        while(words.getCurrentElement().isOfType(Word.WordType.NUMBER)){
            phoneNumber.append(words.getCurrentElement().getValue());
            words.next();
        }

        return phoneNumber.toString();
    }
}
