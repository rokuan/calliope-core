package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.OrderObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.Target;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PhoneNumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;


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
			inter = new QuestionObject();
			inter.target = new Target(Type.parseSubjectPronoun(words.get(1).getValue()));
			inter.action = db.getVerb(words.get(2).getValue()).getAction();

			words.moveTo(3);

			inter.what = parseComplementObject(words);

			return inter;
		} else if(words.syntaxStartsWith(Word.WordType.VERB, Word.WordType.PERSONAL_PRONOUN)){
			// Ordre
			// db.findConjugatedVerb(words.get(0)).form == IMPERATIVE
			inter = new OrderObject();
			inter.target = new Target(Type.parseTargetPronoun(words.get(1).getValue()));
			inter.action = db.getVerb(words.getCurrentElement().getValue()).getAction();

			words.consume();
			words.consume();

			inter.what = parseComplementObject(words);

			return inter;
		} else if(words.syntaxStartsWith(WordType.INTERROGATIVE_PRONOUN, WordType.VERB)){
			// Quel est/Quelle a ete
			QuestionObject qObject = new QuestionObject();
			
			qObject.qType = QuestionObject.parseInterrogativePronoun(words.getCurrentElement().getValue());
			
			words.next();
			
			if(words.syntaxStartsWith(WordType.AUXILIARY, WordType.VERB)){
				// TODO: gerer le cas en allant chercher le verbe conjugue (AUXILIAIRE + VERB) ?
				words.next();
			}
			
			qObject.action = db.getVerb(words.getCurrentElement().getValue()).getAction();	
			words.next();
			
			qObject.what = parseComplementObject(words);
			
			inter = qObject;
		}

		return inter;
	}

	public ComplementObject parseComplementObject(WordBuffer words){
		if(words.getCurrentIndex() > words.size()){
			return null;
		}

		ComplementObject complement = new ComplementObject();

		while(words.hasNext()){
			if(words.syntaxStartsWith(Word.WordType.PROPER_NAME)){
				// TODO: gerer les monuments
				complement.object = words.getCurrentElement().getValue();
				words.next();
			} else if(DateConverter.isADateData(words)){
				complement.when = DateConverter.parseDateObject(words);
			} else if(NumberConverter.isACountData(words)){
				complement.count = NumberConverter.parseCountObject(words);
			} else if(PhoneNumberConverter.isAPhoneNumber(words)){				
				// TODO: creer une classe pour les numeros de telephone ?
				complement.object = PhoneNumberConverter.parsePhoneNumber(words);
			} else if(words.syntaxStartsWith(WordType.DEFINITE_ARTICLE, WordType.COMMON_NAME)){
				words.consume();
				complement.object = words.getCurrentElement().getValue();
				words.consume();
			} else if(PlaceConverter.isAPlaceData(words)){
				complement.where = PlaceConverter.parsePlaceObject(words);
			} else {
				break;
			}
		}

		return complement;
	}
}
