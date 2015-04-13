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
import com.rokuan.calliopecore.sentence.structure.PronounTarget;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.data.CriterionConverter;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;
import com.rokuan.calliopecore.sentence.structure.data.NominalGroupConverter;
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
				qObj.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			words.consume();	// AUXILIARY
			qObj.subject = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();

			if(words.getCurrentElement().isOfType(WordType.CONJUGATION_LINK)){
				words.consume();
			}

			qObj.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();

			//qObj.what = parseComplementObject(words);
			parseObject(words, qObj);

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
				oObject.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			oObject.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();
			//oObject.what = parseComplementObject(words);
			parseObject(words, oObject);

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
				//inter.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				inter.target = new PronounTarget(Type.parsePossessivePronoun(words.getCurrentElement().getValue()));
				words.consume();
			}

			//inter.what = parseComplementObject(words);
			parseObject(words, inter);
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

			//qObject.what = parseComplementObject(words);
			parseObject(words, qObject);

			inter = qObject;
		} else if(words.syntaxStartsWith(SentencePattern.interrogativePattern)){ 
			QuestionObject qObject = new QuestionObject();
			
			qObject.qType = QuestionObject.parseInterrogativePronoun(words.getCurrentElement().getValue());
			words.consume();
			
			Word verb;
			
			if(words.getCurrentElement().isOfType(WordType.AUXILIARY)){
				words.next();
				
				if(words.isIntoBounds()){
					if(words.getCurrentElement().isOfType(WordType.VERB)){
						// Auxiliaire + verbe
						words.previous();
						words.consume();
						verb = words.getCurrentElement();
						words.consume();
					} else {
						// Auxiliaire jouant le robe du verbe
						words.previous();
						verb = words.getCurrentElement();
						words.consume();
					}
				} else {
					words.previous();
					verb = words.getCurrentElement();
					words.consume();
				}
			} else {
				verb = words.getCurrentElement();
				words.consume();
			}
			
			qObject.action = getActionFromVerb(verb.getVerbInfo());
			
			parseObject(words, qObject);
			
			inter = qObject;
		} else {
			// TODO: Le sujet est un groupe nominal ?
			// ComplementObject
		}

		return inter;
	}

	/*public ComplementObject parseComplementObject(WordBuffer words){
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
	}*/

	public void parseObject(WordBuffer words, InterpretationObject obj){
		/*if(words.getCurrentIndex() > words.size()){
			return;
		}*/

		while(words.isIntoBounds()){
			if(words.syntaxStartsWith(Word.WordType.PROPER_NAME)){
				// TODO: gerer les monuments
				ComplementObject what = new ComplementObject();

				what.object = words.getCurrentElement().getValue();
				words.consume();

				obj.what = what;
			} else if(DateConverter.isADateData(words)){
				obj.when = DateConverter.parseDateObject(words);
			} else if(PhoneNumberConverter.isAPhoneNumber(words)){	
				// TODO: creer une classe pour les numeros de telephone ?
				boolean objectField = words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE);

				String phoneNumber = PhoneNumberConverter.parsePhoneNumber(words);

				if(objectField){
					ComplementObject phoneObj = new ComplementObject();

					phoneObj.object = phoneNumber;
					obj.what = phoneObj;
				} else {
					obj.to = phoneNumber;
				}
			} else if(WayConverter.isAWayData(words)){ 
				obj.how = WayConverter.parseWayData(words);
				/*} else if(words.syntaxStartsWith(
					WordPattern.optional(WordPattern.or(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.INDEFINITE_ARTICLE))), 
					WordPattern.simple(WordType.COMMON_NAME))){
				ComplementObject what = new ComplementObject();

				if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE) || words.getCurrentElement().isOfType(WordType.INDEFINITE_ARTICLE)){
					words.next();

					if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.COMMON_NAME)){
						words.previous();
						words.consume();
					} else {
						words.previous();
					}
				}

				what.object = words.getCurrentElement().getValue();
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

						what.of = ofObj;
					}
					//} else if(words.hasNext() && words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				} else if(CriterionConverter.isACriterionData(words)){
					// TODO:
					what.criteria = CriterionConverter.parseCriterionData(words); 
				}

				obj.what = what;
				 */
			} else if(NominalGroupConverter.isADirectObject(words)){
				obj.what = NominalGroupConverter.parseDirectObject(words);
			} else if(PlaceConverter.isAPlaceData(words)){
				obj.where = PlaceConverter.parsePlaceObject(words);
			} else if(NumberConverter.isACountData(words)){
				obj.count = NumberConverter.parseCountObject(words);
			} else {
				break;
			}
		}
	}

	private static Enum<?> getActionFromVerb(VerbConjugation conjug){
		return (conjug == null || conjug.getVerb() == null) ? Action.VerbAction.UNDEFINED : conjug.getVerb().getAction();
	}
}
