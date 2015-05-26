package com.rokuan.calliopecore.parser;

import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.SentencePattern;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.OrderObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject.QuestionType;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;
import com.rokuan.calliopecore.sentence.structure.data.NominalGroupConverter;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PhoneNumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.VerbConverter;
import com.rokuan.calliopecore.sentence.structure.data.WayConverter;
import com.rokuan.calliopecore.sentence.structure.nominal.AbstractTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public class Parser {
	//private WordDatabase db;

	public Parser(){

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
			// Ordre
			// TODO: verifier que le verbe est a l'imperatif present
			// db.findConjugatedVerb(words.get(0)).form == IMPERATIVE
			inter = new OrderObject();

			inter.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();

			if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.TARGET_PRONOUN) && !NominalGroupConverter.isADirectObject(words)){
				//inter.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				/*inter.target = new PronounTarget(Type.parsePossessivePronoun(words.getCurrentElement().getValue()));
				words.consume();*/
				words.next();

				if(NominalGroupConverter.isADirectObject(words)){
					words.previous();
					inter.target = new PronounTarget(Type.parsePossessivePronoun(words.getCurrentElement().getValue()));
					words.consume();
				} else {
					words.previous();
					inter.what = new PronounTarget(Type.parsePossessivePronoun(words.getCurrentElement().getValue()));
					words.consume();
				}
			} else if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE) && !NominalGroupConverter.isADirectObject(words)){
				inter.what = new AbstractTarget(Type.parseDirectPronoun(words.getCurrentElement().getValue()));
				words.consume();

				if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.TARGET_PRONOUN)){
					inter.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
					words.consume();
				}
			}

			parseObject(words, inter);
		} /*else if(words.syntaxStartsWith(SentencePattern.resultQuestionPattern)){
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
			words.consume();

			parseObject(words, qObject);

			inter = qObject;
		}*/
		else if(words.syntaxStartsWith(SentencePattern.interrogativePattern)){ 
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
		} else if(words.syntaxStartsWith(SentencePattern.resultQuestionPattern)){
			QuestionObject qObject = new QuestionObject();

			qObject.qType = QuestionObject.parseInterrogativePronoun(words.getCurrentElement().getValue());			
			words.consume();

			ComplementObject complement = new ComplementObject();
			StringBuffer whatString = new StringBuffer();

			// TODO: modifier pour parser les adjectifs
			while(!VerbConverter.isAQuestionVerbalForm(words)){
				whatString.append(words.getCurrentElement().getValue());
				whatString.append(' ');
				words.consume();
			}

			complement.object = whatString.toString().trim();
			qObject.what = complement;	

			VerbConverter.parseQuestionVerbalGroup(words, qObject);

			parseObject(words, qObject);

			inter = qObject;
		} else {
			// TODO: Le sujet est un groupe nominal ?
			// ComplementObject
		}

		return inter;
	}

	public void parseObject(WordBuffer words, InterpretationObject obj){
		/*if(words.getCurrentIndex() > words.size()){
			return;
		}*/

		while(words.isIntoBounds()){
			if(DateConverter.isADateData(words)){
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
					//obj.to = phoneNumber;
					ComplementObject to = new ComplementObject();					
					to.object = phoneNumber;					
					obj.target = to;
				}
			} else if(WayConverter.isAWayData(words)){ 
				obj.how = WayConverter.parseWayData(words);
			} else if(PlaceConverter.isAPlaceData(words)){
				obj.where = PlaceConverter.parsePlaceObject(words);
			} else if(NominalGroupConverter.isADirectObject(words)){
				obj.what = NominalGroupConverter.parseDirectObject(words);
			} else if(NominalGroupConverter.isAnIndirectObject(words)){ 
				obj.target = NominalGroupConverter.parseIndirectObject(words);
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
