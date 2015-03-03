package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.structure.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.Target;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PhoneNumberConverter;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public class Interpreter {
    private WordDatabase db;

    public Interpreter(WordDatabase wd){
        db = wd;
    }

    public InterpretationObject parseInterpretationObject(WordBuffer words){
        InterpretationObject inter = null;

        if(words.syntaxStartsWith(Word.WordType.AUXILIARY, Word.WordType.PERSONAL_PRONOUN, Word.WordType.VERB)){
            inter = new InterpretationObject();
            inter.target = new Target(Type.parseSubjectPronoun(words.get(1).getValue()));
            inter.action = db.getVerb(words.get(2).getValue()).getAction();

            words.moveTo(3);

            inter.what = parseComplementObject(words);

            return inter;
        } else if(words.syntaxStartsWith(Word.WordType.VERB, Word.WordType.PERSONAL_PRONOUN)){
            // Ordre
            // db.findConjugatedVerb(words.get(0)).form == IMPERATIVE
            inter = new InterpretationObject();
            inter.target = new Target(Type.parseTargetPronoun(words.get(1).getValue()));
            inter.action = db.getVerb(words.get(0).getValue()).getAction();

            // Question


            return inter;
        }

        return inter;
    }

    public ComplementObject parseComplementObject(WordBuffer words){
        if(words.getCurrentIndex() > words.size()){
            return null;
        }

        ComplementObject complement = new ComplementObject();

        if(words.syntaxStartsWith(Word.WordType.PROPER_NAME)){
            complement.object = words.getCurrentElement().getValue();
            words.next();
        } else if(words.syntaxStartsWith(Word.WordType.DEFINITE_ARTICLE)){
            words.next();

            if(PhoneNumberConverter.isAPhoneNumber(words)){
                // TODO: numero de telephone
                complement.object = PhoneNumberConverter.parsePhoneNumber(words);
            } else {
                complement.count = NumberConverter.parseCountObject(words);
            }

            // TODO:

            return complement;
        }

        return null;
    }
}