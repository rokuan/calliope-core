package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Action;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.VerbConjugation;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;

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

	public static final WordPattern isTherePattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "y"),
			WordPattern.simple(WordType.AUXILIARY, null, "avoir"),
			WordPattern.optional(WordPattern.simple(WordType.CONJUGATION_LINK, "t")),
			WordPattern.simple(WordType.PERSONAL_PRONOUN, "il")
			);

	public static final WordPattern CONJUGATED_VERB_PATTERN = WordPattern.sequence(
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.AUXILIARY), WordPattern.simple(WordType.VERB)),
					WordPattern.simple(WordType.VERB)
					)
			/*WordPattern.optional(WordPattern.simple(WordType.AUXILIARY)),
			WordPattern.simple(WordType.VERB)*/
			);

	// TODO: ajouter un moyen de verifier la forme du verbe
	public static final WordPattern infinitivePattern = WordPattern.simple(WordType.VERB);

	public static final WordPattern questionVerbPattern = WordPattern.or(
			pastQuestionPattern,
			presentQuestionPattern,
			isTherePattern
			//infinitivePattern
			);

	public static boolean isAQuestionVerbalForm(WordBuffer words){
		return words.syntaxStartsWith(questionVerbPattern);
	}

	public static boolean isAConjugatedVerb(WordBuffer words){
		return words.syntaxStartsWith(CONJUGATED_VERB_PATTERN);
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
		} else if(words.syntaxStartsWith(isTherePattern)){
			words.consume();	// y

			object.action = Action.VerbAction.THERE_IS;
			words.consume();

			if(words.getCurrentElement().isOfType(WordType.CONJUGATION_LINK)){
				words.consume();
			}

			// TODO: trouver le sujet correct
			//object.subject = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			object.subject = new PronounTarget(Type.Pronoun.UNDEFINED);
			words.consume();
		}
	}

	public static final void parseConjugatedVerb(WordBuffer words, InterpretationObject object){
		if(words.syntaxStartsWith(CONJUGATED_VERB_PATTERN)){
			if(words.getCurrentElement().isOfType(WordType.AUXILIARY)){
				if(words.hasNext()){
					words.next();

					if(words.getCurrentElement().isOfType(WordType.VERB)){
						words.previous();
						words.consume();
					} else {
						words.previous();
					}
				}
			}

			if(words.getCurrentElement().isOfType(WordType.VERB)){
				object.action = getActionFromVerb(words.getCurrentElement().getVerbInfo());
				words.consume();
			}
		}
	}

	private static Action.VerbAction getActionFromVerb(VerbConjugation conjug){
		if(conjug == null || conjug.getVerb() == null){
			return Action.VerbAction.UNDEFINED;
		}

		return (Action.VerbAction)conjug.getVerb().getAction();
	}
}
