package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.SentencePattern;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.OrderObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.Target;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.data.CriterionConverter;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PhoneNumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.WayConverter;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public class Interpreter {
	//private WordDatabase db;

	public Interpreter(){

	}

	/*public Interpreter(WordDatabase wd){
		db = wd;
	}*/

	public InterpretationObject parseInterpretationObject(WordBuffer words){
		InterpretationObject inter = null;

		//if(words.syntaxStartsWith(Word.WordType.AUXILIARY, Word.WordType.PERSONAL_PRONOUN, Word.WordType.VERB)){
		if(words.syntaxStartsWith(SentencePattern.yesNoQuestionPattern)){
			// TODO: les Yes/No question au present (ex: Aimes-tu ...)
			QuestionObject qObj = new QuestionObject();

			qObj.qType = QuestionType.YES_NO;

			if(words.getCurrentElement().isOfType(WordType.PERSONAL_PRONOUN)){
				qObj.target = new Target(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			words.consume();	// AUXILIARY
			qObj.subject = new Target(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();

			if(words.getCurrentElement().isOfType(WordType.CONJUGATION_LINK)){
				words.consume();
			}

			qObj.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();

			qObj.what = parseComplementObject(words);

			inter = qObj;			
			//return inter;			
		} else if(words.syntaxStartsWith(SentencePattern.indirectOrderPattern)){
			OrderObject oObject = new OrderObject();

			if(words.syntaxStartsWith(SentencePattern.isArePattern)){
				words.consume();	// est-ce
				words.consume();	// que
				words.consume();	// tu
				words.consume();	// peux/pourrais				
			} else {
				words.consume();	// peux/pourrais
				words.consume();	// tu
			}

			if(words.getCurrentElement().isOfType(WordType.PERSONAL_PRONOUN)){
				oObject.target = new Target(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			oObject.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();
			oObject.what = parseComplementObject(words);

			inter = oObject;
		} else if(words.syntaxStartsWith(SentencePattern.orderPattern)){
			//} else if(words.syntaxStartsWith(Word.WordType.VERB, Word.WordType.PERSONAL_PRONOUN)){
			// Ordre
			// db.findConjugatedVerb(words.get(0)).form == IMPERATIVE
			inter = new OrderObject();

			//inter.action = db.getVerb(words.getCurrentElement().getValue()).getAction();

			inter.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();

			if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.TARGET_PRONOUN)){
				inter.target = new Target(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			inter.what = parseComplementObject(words);
			//return inter;
		} else if(words.syntaxStartsWith(SentencePattern.resultQuestionPattern)){
			// Quel est/Quelle a ete
			QuestionObject qObject = new QuestionObject();

			qObject.qType = QuestionObject.parseInterrogativePronoun(words.getCurrentElement().getValue());			
			words.consume();

			// TODO: gerer les questions de la forme "Combien existe-t-il ..."
			if(words.syntaxStartsWith(WordType.AUXILIARY, WordType.VERB)){
				// TOCHECK: gerer le cas en allant chercher le verbe conjugue (AUXILIAIRE + VERB) ?
				words.consume();
			} 

			qObject.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			//qObject.action = db.getVerb(words.getCurrentElement().getValue()).getAction();	
			words.consume();

			qObject.what = parseComplementObject(words);

			inter = qObject;
		} else {
			// TODO: Le sujet est un groupe nominal ?
			// ComplementObject
		}

		return inter;
	}

	public ComplementObject parseComplementObject(WordBuffer words){
		if(words.getCurrentIndex() > words.size()){
			return null;
		}

		ComplementObject complement = new ComplementObject();

		while(words.isIntoBounds()){
			if(words.syntaxStartsWith(Word.WordType.PROPER_NAME)){
				// TODO: gerer les monuments
				complement.object = words.getCurrentElement().getValue();
				words.consume();
			} else if(DateConverter.isADateData(words)){
				complement.when = DateConverter.parseDateObject(words);
			} else if(NumberConverter.isACountData(words)){
				complement.count = NumberConverter.parseCountObject(words);
			} else if(PhoneNumberConverter.isAPhoneNumber(words)){	
				// TODO: creer une classe pour les numeros de telephone ?
				boolean objectField = words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE);
				
				String phoneNumber = PhoneNumberConverter.parsePhoneNumber(words);
				
				if(objectField){
					complement.object = phoneNumber;
				} else {
					complement.to = phoneNumber;
				}
				/*} else if(words.syntaxStartsWith(
					WordPattern.or(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.INDEFINITE_ARTICLE)), 
					WordPattern.simple(WordType.COMMON_NAME))){*/
			} else if(WayConverter.isAWayData(words)){ 
				complement.how = WayConverter.parseWayData(words);
			} else if(words.syntaxStartsWith(
					WordPattern.optional(WordPattern.or(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.INDEFINITE_ARTICLE))), 
					WordPattern.simple(WordType.COMMON_NAME))){
				words.consume();
				complement.object = words.getCurrentElement().getValue();
				words.consume();

				if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PREPOSITION_OF)){
					words.consume();

					if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
						words.consume();
					}

					if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.COMMON_NAME)){
						ComplementObject ofObj = new ComplementObject();

						ofObj.object = words.getCurrentElement().getValue();
						words.consume();

						complement.of = ofObj;
					}
					//} else if(words.hasNext() && words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				} else if(CriterionConverter.isACriterionData(words)){
					// TODO:
					complement.criteria = CriterionConverter.parseCriterionData(words); 
				}
			} else if(PlaceConverter.isAPlaceData(words)){
				complement.where = PlaceConverter.parsePlaceObject(words);
			} else {
				break;
			}
		}

		return complement;
	}

	private static Enum<?> getActionFromVerb(VerbConjugation conjug){
		return (conjug == null || conjug.getVerb() == null) ? Action.VerbAction.UNDEFINED : conjug.getVerb().getAction();
	}
}
