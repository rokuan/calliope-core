package com.rokuan.calliopecore.sentence.structure.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimePeriodObject;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class DateConverter {
	private static final int DEFAULT_DATE_FIELD_VALUE = -1;

	// (NUMBER | NUMERICAL_POSITION) (DATE_MONTH NUMBER?)?
	private static final WordPattern fromDatePattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.optional(WordPattern.sequence(
					WordPattern.simple(WordType.DATE_MONTH), 
					WordPattern.optional(WordPattern.simple(WordType.NUMBER))))
			);
	// (NUMBER | NUMERICAL_POSITION) DATE_MONTH NUMBER?
	private static final WordPattern toDatePattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.sequence(
					WordPattern.simple(WordType.DATE_MONTH), 
					WordPattern.optional(WordPattern.simple(WordType.NUMBER)))
			);

	public static final WordPattern fromToDatePattern = WordPattern.sequence(
			WordPattern.simple(Word.WordType.PREPOSITION_FROM),
			WordPattern.or(fromDatePattern, WordPattern.simple(WordType.DATE)),
			WordPattern.simple(Word.WordType.PREPOSITION_TO),
			WordPattern.or(toDatePattern, WordPattern.simple(WordType.DATE))
			);

	public static final WordPattern betweenDatePattern = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_BETWEEN),
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), fromDatePattern),
					WordPattern.simple(WordType.DATE)
					),
					WordPattern.simple(WordType.PREPOSITION_AND),
					WordPattern.or(
							WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), toDatePattern),
							WordPattern.simple(WordType.DATE))
			);

	/*public static WordPattern timePattern = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_AT),
			WordPattern.simple(WordType.NUMBER),
			WordPattern.simple(WordType.DATE_UNIT_HOUR),
			WordPattern.optional(WordPattern.simple(WordType.NUMBER))
			);*/

	public static final WordPattern fixedDatePattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.or(WordPattern.simple(WordType.NUMBER), WordPattern.simple(WordType.NUMERICAL_POSITION)),
			WordPattern.simple(WordType.DATE_MONTH),
			WordPattern.optional(WordPattern.simple(WordType.NUMBER))
			//WordPattern.optional(timePattern)
			);
	public static final WordPattern minutesDefinitionPattern = WordPattern.or(
			WordPattern.sequence(
					WordPattern.or(WordPattern.simple(WordType.ANY, "moins"), WordPattern.simple(WordType.PREPOSITION_AND), WordPattern.simple(WordType.INDEFINITE_ARTICLE, "un(e?)")),
			WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
			WordPattern.simple(WordType.ANY, "quart|demi(e?)")
			));
	public static final WordPattern timePattern = WordPattern.or(
			// TODO:
			);
	
	public static final WordPattern timeDeclarationPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.ANY, "quand"), WordPattern.simple(WordType.PERSONAL_PRONOUN, "il"), WordPattern.simple(WordType.VERB, "sera"))
					),
					timePattern
					);
			

	public static boolean isADateData(WordBuffer words){
		return WordPattern.syntaxStartsWith(words, fromToDatePattern)
				|| WordPattern.syntaxStartsWith(words, betweenDatePattern)
				|| WordPattern.syntaxStartsWith(words, fixedDatePattern);
		//|| WordPattern.syntaxStartsWith(words, timePattern); 
	}

	public static TimeObject parseDateObject(WordBuffer words){
		TimeObject result = null;

		if(WordPattern.syntaxStartsWith(words, fromToDatePattern)){
			TimePeriodObject period = new TimePeriodObject();

			words.consume();	// PREPOSITION_FROM
			
			int[] fromDateFields = parseDate(words);
			
			words.consume();	// PREPOSITION_TO
			
			int[] toDateFields = parseDate(words);

			adjustInterval(fromDateFields, toDateFields);

			period.from = buildDateFromArray(fromDateFields);
			period.to = buildDateFromArray(toDateFields);

			result = period;
		} else if(WordPattern.syntaxStartsWith(words, betweenDatePattern)){
			TimePeriodObject period = new TimePeriodObject();
			
			words.consume();	// PREPOSITION_BETWEEN
			words.consume();	// DEFINITE_ARTICLE
			
			int[] fromDateFields = parseDate(words);
			
			words.consume();	// PREPOSITION_AND
			words.consume();	// DEFINITE_ARTICLE			
			
			int[] toDateFields = parseDate(words);

			adjustInterval(fromDateFields, toDateFields);

			period.from = buildDateFromArray(fromDateFields);
			period.to = buildDateFromArray(toDateFields);
			
			result = period;
		}

		return result;
	}

	private static int[] parseDate(WordBuffer words){
		// TODO: modifier si on prend en compte l'heure
		int[] date = new int[3];

		Arrays.fill(date, DEFAULT_DATE_FIELD_VALUE);

		if(words.getCurrentElement().isOfType(WordType.NUMBER)){
			date[0] = Integer.parseInt(words.getCurrentElement().getValue());
			words.consume();
		} else if(words.getCurrentElement().isOfType(WordType.NUMERICAL_POSITION)){
			date[0] = (int)NumberConverter.parsePosition(words.getCurrentElement().getValue());
			words.consume();
		}

		if(words.getCurrentElement().isOfType(WordType.DATE_MONTH)){
			// TODO: changer le local par celui de FRENCH ?
			try {
				Date monthDate = new SimpleDateFormat("MMMM", Locale.FRANCE).parse(words.getCurrentElement().getValue());
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(monthDate);
				date[1] = calendar.get(Calendar.MONTH);
			} catch (ParseException e) {
				// TODO: gerer ce cas				
			}

			words.consume();
		}

		if(words.getCurrentElement().isOfType(WordType.NUMBER)){
			date[2] = Integer.parseInt(words.getCurrentElement().getValue());
			words.consume();
		}

		return date;
	}

	private static Date buildDateFromArray(int[] dateArray){
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, dateArray[0]);
		calendar.set(Calendar.MONTH, dateArray[1]);
		calendar.set(Calendar.YEAR, dateArray[2]);

		return calendar.getTime();
	}

	private static void adjustInterval(int[] fromDateFields, int[] toDateFields){
		// Adjust month
		if(fromDateFields[1] == DEFAULT_DATE_FIELD_VALUE){
			fromDateFields[1] = toDateFields[1];
		}

		// Adjust year
		if(fromDateFields[2] == DEFAULT_DATE_FIELD_VALUE && toDateFields[2] != DEFAULT_DATE_FIELD_VALUE){
			if(fromDateFields[1] > toDateFields[1] 
					|| (fromDateFields[1] == toDateFields[1] && fromDateFields[0] > toDateFields[0])){
				// From should be a year ago if fromDate (month/date) is after toDate(month/date)
				fromDateFields[2] = toDateFields[2] - 1;
			} else {
				// From is the same year as To otherwise
				fromDateFields[2] = toDateFields[2];
			}
		} else if(fromDateFields[2] != DEFAULT_DATE_FIELD_VALUE && toDateFields[2] == DEFAULT_DATE_FIELD_VALUE){
			if(toDateFields[1] < fromDateFields[1]
					|| (toDateFields[1] == fromDateFields[1] && toDateFields[0] < fromDateFields[0])){
				toDateFields[2] = fromDateFields[2] + 1;
			} else {
				toDateFields = fromDateFields;
			}
		}
	}
}
