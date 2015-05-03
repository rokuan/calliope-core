package com.rokuan.calliopecore.sentence;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.VerbConverter;

public class SentencePattern {
	public static final WordPattern yesNoQuestionPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN)),	// m', t'
			WordPattern.simple(WordType.AUXILIARY),
			WordPattern.optional(WordPattern.simple(WordType.CONJUGATION_LINK)),
			WordPattern.simple(WordType.PERSONAL_PRONOUN),
			WordPattern.simple(WordType.VERB)
			);

	public static final WordPattern isArePattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "est-ce"),
			WordPattern.simple(WordType.ANY, "que")
			);
	public static final WordPattern indirectOrderPattern = WordPattern.sequence(
			WordPattern.or(
					// Est-ce que tu peux/pourrais
					WordPattern.sequence(isArePattern, WordPattern.simple(WordType.PERSONAL_PRONOUN, "tu"), WordPattern.simple(WordType.VERB, null, "pouvoir")),
					// (Peux/pourrais)-tu
					WordPattern.sequence(WordPattern.simple(WordType.VERB, null, "pouvoir"), WordPattern.simple(WordType.PERSONAL_PRONOUN, "tu"))
					),
					WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN)),
					WordPattern.simple(WordType.VERB)
			);
	
	public static final String orderVerbRegex = "ordonner|demander|souhaiter|vouloir|exiger|aimer|sommer";
	// Je t'ordonne de ... || Je souhaite que tu (me/te/...)...
	// TODO: voir les autres cas
	public static final WordPattern affirmativeOrder = WordPattern.sequence(
			WordPattern.simple(WordType.PERSONAL_PRONOUN, "je"),
			WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN)),
			WordPattern.simple(WordType.VERB, null, orderVerbRegex),
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.ANY, "que"), WordPattern.simple(WordType.PERSONAL_PRONOUN, "tu"), WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN))),
					WordPattern.simple(WordType.ANY, "de"))
					);

	// Affiche-moi
	public static final WordPattern orderPattern = WordPattern.sequence(
			WordPattern.simple(WordType.VERB), 
			WordPattern.optional(WordPattern.sequence(
					WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
					WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN))
			)));
	// Quel(s/le(s)) est/sont
	/*public static final WordPattern resultQuestionPattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.INTERROGATIVE_PRONOUN), WordPattern.simple(WordType.INTERROGATIVE_ADJECTIVE)),
			WordPattern.simple(WordType.VERB, null, "�tre")
			);*/
	// Quelle temperature fait-il
	public static final WordPattern resultQuestionPattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.INTERROGATIVE_ADJECTIVE, "quel.*"), WordPattern.simple(WordType.INTERROGATIVE_ADJECTIVE, "quel.*")),
			// TODO: remplacer par un groupe nominal en prenant en compte les adjectifs (ex: quel petit chat)
			WordPattern.nonEmptyList(WordPattern.simple(WordType.COMMON_NAME)),
			VerbConverter.questionVerbPattern
			);
	
	public static final WordPattern interrogativePattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.INTERROGATIVE_PRONOUN), WordPattern.simple(WordType.INTERROGATIVE_ADJECTIVE)),
			WordPattern.optional(WordPattern.simple(WordType.AUXILIARY)),
			WordPattern.simple(WordType.VERB)
			);
	
	

	/*public static final WordPattern nominalGroupPattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE)
			WordPattern.
			);

	public static final WordPattern personPattern = WordPattern.

	public static final WordPattern subjectPattern = WordPattern.or(personPattern)*/
}
