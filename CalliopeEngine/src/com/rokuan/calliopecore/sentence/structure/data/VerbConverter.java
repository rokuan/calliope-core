package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.PronounTarget;

public class VerbConverter {
	// existe-t-il / suis-je / m'envoie-t-il
	public static final WordPattern presentQuestionPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
			WordPattern.simple(WordType.VERB), 
			WordPattern.optional(WordPattern.simple(WordType.CONJUGATION_LINK)), 
			WordPattern.simple(WordType.PERSONAL_PRONOUN)); 
	
	// a-t-il mangé / suis-je venu / TODO: m'a-t-il donné
	public static final WordPattern pastQuestionPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)), 
			WordPattern.simple(WordType.AUXILIARY), 
			WordPattern.optional(WordPattern.simple(WordType.CONJUGATION_LINK)), 
			WordPattern.simple(WordType.PERSONAL_PRONOUN), 
			WordPattern.simple(WordType.VERB));
	
	// TODO: ajouter un moyen de verifier la forme du verbe
	public static final WordPattern infinitivePattern = WordPattern.simple(WordType.VERB);
	
	public static final WordPattern questionVerbPattern = WordPattern.or(
			pastQuestionPattern,
			presentQuestionPattern
			//infinitivePattern
			);
	
	public static boolean isAQuestionVerbalForm(WordBuffer words){
		return words.syntaxStartsWith(questionVerbPattern);
	}
	
	public static void parseQuestionVerbalGroup(WordBuffer words, InterpretationObject object){
		if(words.syntaxStartsWith(pastQuestionPattern)){
			if(words.getCurrentElement().isOfType(WordType.TARGET_PRONOUN)){
				object.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}
			
			words.consume();	// AUXILIARY
			
			if(words.getCurrentElement().isOfType(WordType.CONJUGATION_LINK)){
				words.consume();
			}
			
			object.subject = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();
			
			object.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();
		} else if(words.syntaxStartsWith(presentQuestionPattern)){
			if(words.getCurrentElement().isOfType(WordType.TARGET_PRONOUN)){
				object.target = new PronounTarget(Type.parseTargetPronoun(words.getCurrentElement().getValue()));
				words.consume();
			}		
			
			object.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
			words.consume();
			
			if(words.getCurrentElement().isOfType(WordType.CONJUGATION_LINK)){
				words.consume();
			}
			
			object.subject = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();
		}
	}
	
	private static Action.VerbAction getActionFromVerb(VerbConjugation conjug){
		if(conjug == null || conjug.getVerb() == null){
			return Action.VerbAction.UNDEFINED;
		}
		
		return (Action.VerbAction)conjug.getVerb().getAction();
	}
}
